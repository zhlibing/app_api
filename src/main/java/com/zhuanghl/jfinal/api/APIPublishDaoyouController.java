package com.zhuanghl.jfinal.api;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.zhuanghl.jfinal.common.bean.BaseResponse;
import com.zhuanghl.jfinal.common.bean.Code;
import com.zhuanghl.jfinal.common.utils.DateUtils;
import com.zhuanghl.jfinal.common.utils.RandomUtils;
import com.zhuanghl.jfinal.model.PublishDaoyou;

public class APIPublishDaoyouController extends BaseAPIController {

    public void upload() {
        if (!methodType("post")) {
            render404();
            return;
        }
        String contentId = RandomUtils.randomCustomUUID();
        new PublishDaoyou()
                .set(PublishDaoyou.USER_ID, getPara("userId"))
                .set(PublishDaoyou.CONTENT_ID, contentId)
                .set(PublishDaoyou.TAGS, getPara("tags"))
                .set(PublishDaoyou.TYPE, "BOZHU")
                .set(PublishDaoyou.CONTENT_DETAILS, getPara("contentDetails"))
                .set(PublishDaoyou.CREATION_DATE, DateUtils.getNowTimeStamp())
                .save();

        String sql = "SELECT * FROM t_publish_daoyou WHERE contentId=?";
        PublishDaoyou publishDaoyou = PublishDaoyou.publishDaoyou.findFirst(sql, contentId);

        renderJson(new BaseResponse(Code.SUCCESS, "", publishDaoyou.getAttrs()));
    }

    public void getAllDaoyou() {
        if (!methodType("post")) {
            render404();
            return;
        }
        Page<Record> publishDaoyouPage =
                Db.paginate(Integer.parseInt(getPara("pageNumber")),
                        Integer.parseInt(getPara("pageSize")),
                        "select *",
                        "from t_publish_daoyou order by creationDate desc");
        renderJson(new BaseResponse(Code.SUCCESS, "", publishDaoyouPage));
    }
}