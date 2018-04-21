package com.zhuanghl.jfinal.api;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.zhuanghl.jfinal.common.Require;
import com.zhuanghl.jfinal.common.bean.BaseResponse;
import com.zhuanghl.jfinal.common.bean.Code;
import com.zhuanghl.jfinal.common.bean.FileResponse;
import com.zhuanghl.jfinal.common.utils.DateUtils;
import com.zhuanghl.jfinal.common.utils.RandomUtils;
import com.zhuanghl.jfinal.interceptor.PublishInterceptor;
import com.zhuanghl.jfinal.model.PublishDaren;
import com.zhuanghl.jfinal.model.PublishImage;
import com.zhuanghl.jfinal.model.PublishLvxingzhe;
import com.zhuanghl.jfinal.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class APIPublishLvxingzheController extends BaseAPIController {

    /**
     * 处理单文件或多文件上传，上传成功后，返回url集合
     */
    @Before(PublishInterceptor.class)
    public void upload() {
        if (!methodType("post")) {
            render404();
            return;
        }
        FileResponse response = new FileResponse();
        try {
            String contentId = RandomUtils.randomCustomUUID();
            if (getAttr("success_images_url") != null
                    && !getAttr("success_images_url").toString().isEmpty()) {
                List<String> urls = new ArrayList<>();
                urls = getAttr("success_images_url");
                for (int i = 0; i < urls.size(); i++) {
                    String imageId = RandomUtils.randomCustomUUID();
                    new PublishImage()
                            .set(PublishImage.CONTENT_ID, contentId)
                            .set(PublishImage.USER_ID, getAttr("userId"))
                            .set(PublishImage.IMAGE_ID, imageId)
                            .set(PublishImage.IMAGE_URL, urls.get(i))
                            .set(PublishImage.INDEXID, i)
                            .set(PublishImage.CREATION_DATE, DateUtils.getNowTimeStamp())
                            .save();
                }
                if (getAttr("failed_images_url") != null
                        && !getAttr("failed_images_url").toString().isEmpty()) {
                    response.setCode(Code.FAIL);//表示此次上传有未上传成功的文件
                    response.setFailed((List<String>) getAttr("failed_images_url"));
                }
            } else {
                //校验必填项参数
                if (!notNull(Require.me()
                        .put(getPara("contentDetails"), "contentDetails can not be null")
                )) {
                    return;
                }
            }
            new PublishLvxingzhe()
                    .set(PublishLvxingzhe.USER_ID, getAttr("userId"))
                    .set(PublishLvxingzhe.CONTENT_ID, contentId)
                    .set(PublishLvxingzhe.OUTSIDE_LINK_URL, getPara("outsideLinkUrl"))
                    .set(PublishLvxingzhe.HAVE_OUTSIDE_LIMK, getPara("haveOutsideLink"))
                    .set(PublishLvxingzhe.CONTACT_WAY, getPara("contactWay"))
                    .set(PublishLvxingzhe.CONTACT_NUMBER, getPara("contactNumber"))
                    .set(PublishLvxingzhe.TITLE, getPara("title"))
                    .set(PublishLvxingzhe.TIME, getPara("time"))
                    .set(PublishLvxingzhe.TYPE, "LVXINGZHE")
                    .set(PublishLvxingzhe.CONTENT_DETAILS, getPara("contentDetails"))
                    .set(PublishLvxingzhe.CREATION_DATE, DateUtils.getNowTimeStamp())
                    .save();

            String sql1 = "SELECT * FROM t_user WHERE userId=?";
            User user = User.user.findFirst(sql1, getAttr("userId"));

            String sql2 = "SELECT * FROM t_publish_lvxingzhe WHERE contentId=?";
            PublishLvxingzhe publishLvxingzhe = PublishLvxingzhe.publishLvxingzhe.findFirst(sql2, contentId);

            String sql3 = "SELECT * FROM t_publish_image WHERE contentId=? order by indexId";
            List<PublishImage> images = (List<PublishImage>) PublishImage.publishImage.find(sql3, contentId);

            Page<Record> publishLvxingzhePage =
                    Db.paginate(1,
                            2,
                            "select *",
                            "from t_publish_lvxingzhe");

            Map<String, Object> map = new HashMap<>();
            map.put("user", user.getAttrs());
            map.put("publishDaren ", publishLvxingzhe.getAttrs());
            map.put("publishImages ", images);
            map.put("record ", publishLvxingzhePage);

            response.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(Code.ERROR);
        }
        renderJson(response);
    }

    public void getAllPublishedLvxingzhe() {
        if (!methodType("post")) {
            render404();
            return;
        }

        Page<Record> publishLvxingzhePage =
                Db.paginate(Integer.parseInt(getPara("pageNumber")),
                        Integer.parseInt(getPara("pageSize")),
                        "select *",
                        "from t_publish_lvxingzhe order by creationDate desc");

        for (int i = 0; i < publishLvxingzhePage.getList().size(); i++) {
            publishLvxingzhePage.getList().get(i).set("user", new User().findFirst(
                    "select * from t_user where userId=?",
                    publishLvxingzhePage.getList().get(i).get("userId")));

            publishLvxingzhePage.getList().get(i).set("images", new PublishImage().find(
                    "select * from t_publish_image where contentId=?",
                    publishLvxingzhePage.getList().get(i).get("contentId")));
        }
        renderJson(new BaseResponse(Code.SUCCESS, "", publishLvxingzhePage));
    }
}