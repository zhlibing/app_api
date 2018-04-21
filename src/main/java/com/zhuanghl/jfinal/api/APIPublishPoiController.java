package com.zhuanghl.jfinal.api;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.zhuanghl.jfinal.common.bean.BaseResponse;
import com.zhuanghl.jfinal.common.bean.Code;
import com.zhuanghl.jfinal.common.utils.DateUtils;
import com.zhuanghl.jfinal.common.utils.RandomUtils;
import com.zhuanghl.jfinal.interceptor.UploadOnlyInterceptor;
import com.zhuanghl.jfinal.model.*;

import java.util.List;

public class APIPublishPoiController extends BaseAPIController {

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

        if (successUrl != null
                && successUrl.length() > 0) {
            String POI_ID = RandomUtils.randomCustomUUID();
            String sql = "SELECT * FROM t_publish_poi WHERE pingyouId=? order by indexId";
            List<PublishPoi> publishPois = PublishPoi.publishPoi.find(sql, getPara("pingyouId"));

            if (publishPois != null && publishPois.size() > 0) {
                if (getPara("index") != null
                        && getPara("index").equals("0")) {
                    Db.update("delete from t_publish_poi where pingyouId=?", getPara("pingyouId"));
                }
            }
            new PublishPoi()
                    .set(PublishPoi.POI_ID, POI_ID)
                    .set(PublishPoi.USER_ID, getPara("userId"))
                    .set(PublishPoi.INDEX_ID, getPara("index"))
                    .set(PublishPoi.PINGYOU_ID, getPara("pingyouId"))
                    .set(PublishPoi.LAT, getPara("lat"))
                    .set(PublishPoi.LON, getPara("lon"))
                    .set(PublishPoi.DISTANCE, getPara("distance"))
                    .set(PublishPoi.PROVENCE, getPara("provence"))
                    .set(PublishPoi.CITY, getPara("city"))
                    .set(PublishPoi.ZONE, getPara("zone"))
                    .set(PublishPoi.KEYWORD, getPara("keyword"))
                    .set(PublishPoi.ADDRESS, getPara("address"))
                    .set(PublishPoi.GD_IAMGE, getPara("gdImage"))
                    .set(PublishPoi.GD_CUT_IMAGE, successUrl)
                    .set(PublishPoi.VIEW_POINT_CONTENT_ID, getPara("viewPointContentId"))
                    .set(PublishPoi.HAVE_VIEW_POINT, getPara("haveViewPoint"))
                    .set(PublishPoi.IS_DRAFT, 1)
                    .set(PublishPoi.UPDATE_DATE, DateUtils.getNowTimeStamp())
                    .set(PublishImage.CREATION_DATE, DateUtils.getNowTimeStamp())
                    .save();

            String sql2 = "SELECT * FROM t_publish_poi WHERE pingyouId=? order by indexId";
            List<PublishPoi> publishPois2 = PublishPoi.publishPoi.find(sql2, getPara("pingyouId"));
            renderJson(new BaseResponse(Code.SUCCESS, successUrl, publishPois2));
        }
        if (failUrl != null
                && failUrl.length() > 0) {
            renderJson(new BaseResponse(Code.FAIL, failUrl));
        }
    }

    public void getUserPublishedPoiDraft() {
        if (!methodType("post")) {
            render404();
            return;
        }
        Page<Record> pingyouIds =
                Db.paginate(Integer.parseInt(getPara("pageNumber")),
                        Integer.parseInt(getPara("pageSize")),
                        "select pingyouId",
                        "from t_publish_poi where userId=? and isDraft = 1 group by pingyouId",
                        getPara("userId"));

        for (int i = 0; i < pingyouIds.getList().size(); i++) {
            String sql2 = "select * from t_publish_poi where pingyouId=? order by creationDate desc";
            List<Record> poiList = Db.find(sql2, pingyouIds.getList().get(i).get("pingyouId"));
            pingyouIds.getList().get(i).set("poiList", poiList);
        }
        renderJson(new BaseResponse(Code.SUCCESS, "", pingyouIds));
    }

    public void deleteUserPublishedPoiDraft() {
        if (!methodType("post")) {
            render404();
            return;
        }
        Db.update("delete from t_publish_poi where pingyouId=?", getPara("pingyouId"));
        renderJson(new BaseResponse(Code.SUCCESS, ""));
    }
}