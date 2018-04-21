package com.zhuanghl.jfinal.api;

import com.jfinal.aop.Before;
import com.zhuanghl.jfinal.common.bean.BaseResponse;
import com.zhuanghl.jfinal.common.bean.Code;
import com.zhuanghl.jfinal.common.utils.DateUtils;
import com.zhuanghl.jfinal.common.utils.RandomUtils;
import com.zhuanghl.jfinal.interceptor.UploadOnlyInterceptor;
import com.zhuanghl.jfinal.model.PublishYouhuiquan;

public class APIPublishYouhuiquanController extends BaseAPIController {

    /**
     * 处理单文件或多文件上传，上传成功后，返回url集合
     */
    @Before(UploadOnlyInterceptor.class)
    public void save() {
        if (!methodType("post")) {
            render404();
            return;
        }
        String successUrl = getAttr("success_images_url");
        String failUrl = getAttr("failed_images_url");

        String youhuiquanId = RandomUtils.randomCustomUUID();
        if (successUrl != null
                && successUrl.length() > 0) {
            new PublishYouhuiquan()
                    .set(PublishYouhuiquan.YOUHUIQUAN_ID, youhuiquanId)
                    .set(PublishYouhuiquan.USER_ID, getPara("userId"))
                    .set(PublishYouhuiquan.CARD_NAME, getPara("cardName"))
                    .set(PublishYouhuiquan.USE_TIME, getPara("useTime"))
                    .set(PublishYouhuiquan.CARD_TYPE, getPara("cardType"))
                    .set(PublishYouhuiquan.USE_LOCATION, getPara("useLocation"))
                    .set(PublishYouhuiquan.DISCOUNT, getPara("discount"))
                    .set(PublishYouhuiquan.CONTACT_WAY, getPara("contactWay"))
                    .set(PublishYouhuiquan.CONTACT_NUMBER, getPara("contactNumber"))
                    .set(PublishYouhuiquan.IS_FREE, getPara("isFree"))
                    .set(PublishYouhuiquan.PRICE, getPara("price"))
                    .set(PublishYouhuiquan.ATTACH_INTRODUCE, getPara("attachIntroduce"))
                    .set(PublishYouhuiquan.COVER_IMAGE, successUrl)
                    .set(PublishYouhuiquan.CREATION_DATE, DateUtils.getNowTimeStamp())
                    .save();
            String sql2 = "SELECT * FROM t_publish_youhuiquan WHERE youhuiquanId=?";
            PublishYouhuiquan publishYouhuiquan = PublishYouhuiquan.publishYouhuiquan.findFirst(sql2, youhuiquanId);
            renderJson(new BaseResponse(Code.SUCCESS, "", publishYouhuiquan.getAttrs()));
        } else {
            new PublishYouhuiquan()
                    .set(PublishYouhuiquan.YOUHUIQUAN_ID, youhuiquanId)
                    .set(PublishYouhuiquan.USER_ID, getPara("userId"))
                    .set(PublishYouhuiquan.CARD_NAME, getPara("cardName"))
                    .set(PublishYouhuiquan.USE_TIME, getPara("useTime"))
                    .set(PublishYouhuiquan.CARD_TYPE, getPara("cardType"))
                    .set(PublishYouhuiquan.USE_LOCATION, getPara("useLocation"))
                    .set(PublishYouhuiquan.DISCOUNT, getPara("discount"))
                    .set(PublishYouhuiquan.CONTACT_WAY, getPara("contactWay"))
                    .set(PublishYouhuiquan.CONTACT_NUMBER, getPara("contactNumber"))
                    .set(PublishYouhuiquan.IS_FREE, getPara("isFree"))
                    .set(PublishYouhuiquan.PRICE, getPara("price"))
                    .set(PublishYouhuiquan.ATTACH_INTRODUCE, getPara("attachIntroduce"))
                    .set(PublishYouhuiquan.COVER_IMAGE, "")
                    .set(PublishYouhuiquan.CREATION_DATE, DateUtils.getNowTimeStamp())
                    .save();
            String sql2 = "SELECT * FROM t_publish_youhuiquan WHERE youhuiquanId=?";
            PublishYouhuiquan publishYouhuiquan = PublishYouhuiquan.publishYouhuiquan.findFirst(sql2, youhuiquanId);
            renderJson(new BaseResponse(Code.SUCCESS, "", publishYouhuiquan.getAttrs()));
        }
        if (failUrl != null
                && failUrl.length() > 0) {
            renderJson(new BaseResponse(Code.FAIL, failUrl));
        }
    }
}