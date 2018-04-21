package com.zhuanghl.jfinal.api;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.zhuanghl.jfinal.common.bean.BaseResponse;
import com.zhuanghl.jfinal.common.bean.Code;
import com.zhuanghl.jfinal.common.utils.DateUtils;
import com.zhuanghl.jfinal.interceptor.UploadOnlyInterceptor;
import com.zhuanghl.jfinal.model.PublishPingyou;

@Before(UploadOnlyInterceptor.class)
public class APIPublishPingyouController extends BaseAPIController {

    /**
     * 处理单文件或多文件上传，上传成功后，返回url集合
     */
    public void save() {
        if (!methodType("post")) {
            render404();
            return;
        }
        String successUrl = getAttr("success_images_url");
        String failUrl = getAttr("failed_images_url");

        if (successUrl != null
                && successUrl.length() > 0) {
            new PublishPingyou()
                    .set(PublishPingyou.PINGYOU_ID, getPara("pingyouId"))
                    .set(PublishPingyou.USER_ID, getPara("userId"))
                    .set(PublishPingyou.NAME, getPara("name"))
                    .set(PublishPingyou.TIME, getPara("time"))
                    .set(PublishPingyou.PERSON_NUMBER, getPara("personNumber"))
                    .set(PublishPingyou.CONTACT_WAY, getPara("contactWay"))
                    .set(PublishPingyou.CONTACT_NUMBER, getPara("contactNumber"))
                    .set(PublishPingyou.IS_FREE, getPara("isFree"))
                    .set(PublishPingyou.PRICE, getPara("price"))
                    .set(PublishPingyou.ATTACH_INTRODUCE, getPara("attachIntroduce"))
                    .set(PublishPingyou.COVER_IMAGE, successUrl)
                    .set(PublishPingyou.CREATION_DATE, DateUtils.getNowTimeStamp())
                    .save();
            Db.update("update t_publish_poi set isDraft = '0' where pingyouId=?", getPara("pingyouId"));
            String sql2 = "SELECT * FROM t_publish_pingyou WHERE pingyouId=?";
            PublishPingyou publishPingyou = PublishPingyou.publishPingyou.findFirst(sql2, getPara("pingyouId"));
            renderJson(new BaseResponse(Code.SUCCESS, "", publishPingyou.getAttrs()));
        } else {
            new PublishPingyou()
                    .set(PublishPingyou.PINGYOU_ID, getPara("pingyouId"))
                    .set(PublishPingyou.USER_ID, getPara("userId"))
                    .set(PublishPingyou.NAME, getPara("name"))
                    .set(PublishPingyou.TIME, getPara("time"))
                    .set(PublishPingyou.PERSON_NUMBER, getPara("personNumber"))
                    .set(PublishPingyou.CONTACT_WAY, getPara("contactWay"))
                    .set(PublishPingyou.CONTACT_NUMBER, getPara("contactNumber"))
                    .set(PublishPingyou.IS_FREE, getPara("isFree"))
                    .set(PublishPingyou.PRICE, getPara("price"))
                    .set(PublishPingyou.ATTACH_INTRODUCE, getPara("attachIntroduce"))
                    .set(PublishPingyou.COVER_IMAGE, "")
                    .set(PublishPingyou.CREATION_DATE, DateUtils.getNowTimeStamp())
                    .save();
            Db.update("update t_publish_poi set isDraft = '0' where pingyouId=?", getPara("pingyouId"));
            String sql2 = "SELECT * FROM t_publish_pingyou WHERE pingyouId=?";
            PublishPingyou publishPingyou = PublishPingyou.publishPingyou.findFirst(sql2, getPara("pingyouId"));
            renderJson(new BaseResponse(Code.SUCCESS, "", publishPingyou.getAttrs()));
            renderJson(new BaseResponse(Code.SUCCESS, ""));
        }
        if (failUrl != null
                && failUrl.length() > 0) {
            renderJson(new BaseResponse(Code.FAIL, failUrl));
        }
    }
}