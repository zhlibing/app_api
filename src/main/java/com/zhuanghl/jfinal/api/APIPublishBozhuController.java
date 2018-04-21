package com.zhuanghl.jfinal.api;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.zhuanghl.jfinal.common.bean.BaseResponse;
import com.zhuanghl.jfinal.common.bean.Code;
import com.zhuanghl.jfinal.common.utils.DateUtils;
import com.zhuanghl.jfinal.common.utils.RandomUtils;
import com.zhuanghl.jfinal.model.PublishBozhu;
import com.zhuanghl.jfinal.model.User;

public class APIPublishBozhuController extends BaseAPIController {

    public void upload() {
        if (!methodType("post")) {
            render404();
            return;
        }
        String contentId = RandomUtils.randomCustomUUID();
        new PublishBozhu()
                .set(PublishBozhu.USER_ID, getPara("userId"))
                .set(PublishBozhu.CONTENT_ID, contentId)
                .set(PublishBozhu.TITLE, getPara("title"))
                .set(PublishBozhu.IMAGES_SIZE, getPara("imagesSize"))
                .set(PublishBozhu.FIRST_IMAGE_URL, getPara("firstImageUrl"))
                .set(PublishBozhu.TYPE, "BOZHU")
                .set(PublishBozhu.CONTENT_DETAILS, getPara("contentDetails"))
                .set(PublishBozhu.CREATION_DATE, DateUtils.getNowTimeStamp())
                .save();

        String sql2 = "SELECT * FROM t_publish_bozhu WHERE contentId=?";
        PublishBozhu publishBozhu = PublishBozhu.publishBozhu.findFirst(sql2, contentId);

        renderJson(new BaseResponse(Code.SUCCESS, "", publishBozhu.getAttrs()));
    }

    public void getAllPublishedBozhu() {
        if (!methodType("post")) {
            render404();
            return;
        }

        Page<Record> publishBozhuPage =
                Db.paginate(Integer.parseInt(getPara("pageNumber")),
                        Integer.parseInt(getPara("pageSize")),
                        "select *",
                        "from t_publish_bozhu order by creationDate desc");

        for (int i = 0; i < publishBozhuPage.getList().size(); i++) {
            publishBozhuPage.getList().get(i).set("user", new User().findFirst(
                    "select * from t_user where userId=?",
                    publishBozhuPage.getList().get(i).get("userId")));
        }
        renderJson(new BaseResponse(Code.SUCCESS, "", publishBozhuPage));
    }
}