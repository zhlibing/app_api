package com.zhuanghl.jfinal.api;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.zhuanghl.jfinal.common.bean.BaseResponse;
import com.zhuanghl.jfinal.common.bean.Code;
import com.zhuanghl.jfinal.model.PublishBozhu;
import com.zhuanghl.jfinal.model.PublishImage;
import com.zhuanghl.jfinal.model.PublishPoi;
import com.zhuanghl.jfinal.model.User;

public class APIHomeLoadController extends BaseAPIController {

    public void homeLoad() {
        if (!methodType("post")) {
            render404();
            return;
        }

        switch (getPara("key")) {
            case "0":
                Page<Record> publishPingyouPage =
                        Db.paginate(Integer.parseInt(getPara("pageNumber")),
                                Integer.parseInt(getPara("pageSize")),
                                "select *",
                                "from t_publish_pingyou order by creationDate desc");

                for (int i = 0; i < publishPingyouPage.getList().size(); i++) {
                    publishPingyouPage.getList().get(i).set("poiList", new PublishPoi().find(
                            "select * from t_publish_poi where pingyouId=? order by indexId",
                            publishPingyouPage.getList().get(i).get("pingyouId")));

                    publishPingyouPage.getList().get(i).set("user", new User().findFirst(
                            "select * from t_user where userId=?",
                            publishPingyouPage.getList().get(i).get("userId")));

                    publishPingyouPage.getList().get(i).set("type", "PINGYOU");
                }
                renderJson(new BaseResponse(Code.SUCCESS, "", publishPingyouPage));
                break;
            case "1":
                Page<Record> publishBozhuPage =
                        Db.paginate(Integer.parseInt(getPara("pageNumber")),
                                Integer.parseInt(getPara("pageSize")),
                                "select *",
                                "from t_user where isBozhu=1 order by creationDate desc");

                for (int i = 0; i < publishBozhuPage.getList().size(); i++) {
                    publishBozhuPage.getList().get(i).set("bozhuList", new PublishBozhu().find(
                            "select * from t_publish_bozhu where userId=?",
                            publishBozhuPage.getList().get(i).get("userId")));

                    publishBozhuPage.getList().get(i).set("type", "BOZHU");
                }
                renderJson(new BaseResponse(Code.SUCCESS, "", publishBozhuPage));
                break;
            case "2":
                Page<Record> publishDarenPage =
                        Db.paginate(Integer.parseInt(getPara("pageNumber")),
                                Integer.parseInt(getPara("pageSize")),
                                "select *",
                                "from t_publish_daren order by creationDate desc");

                for (int i = 0; i < publishDarenPage.getList().size(); i++) {
                    publishDarenPage.getList().get(i).set("images", new PublishImage().find(
                            "select * from t_publish_image where contentId=?",
                            publishDarenPage.getList().get(i).get("contentId")));
                }
                renderJson(new BaseResponse(Code.SUCCESS, "", publishDarenPage));
                break;
            case "3":
                Page<Record> publishDaoyouPage =
                        Db.paginate(Integer.parseInt(getPara("pageNumber")),
                                Integer.parseInt(getPara("pageSize")),
                                "select *",
                                "from t_user where isDaoyou=1 order by creationDate desc");

                for (int i = 0; i < publishDaoyouPage.getList().size(); i++) {
                    publishDaoyouPage.getList().get(i).set("type", "DAOYOU");
                }
                renderJson(new BaseResponse(Code.SUCCESS, "", publishDaoyouPage));
                break;
            case "4":
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
                break;
            case "5":
                Page<Record> publishShanghuPage =
                        Db.paginate(Integer.parseInt(getPara("pageNumber")),
                                Integer.parseInt(getPara("pageSize")),
                                "select *",
                                "from t_publish_shanghu order by creationDate desc");

                for (int i = 0; i < publishShanghuPage.getList().size(); i++) {
                    publishShanghuPage.getList().get(i).set("user", new User().findFirst(
                            "select * from t_user where userId=?",
                            publishShanghuPage.getList().get(i).get("userId")));

                    publishShanghuPage.getList().get(i).set("images", new PublishImage().find(
                            "select * from t_publish_image where contentId=?",
                            publishShanghuPage.getList().get(i).get("contentId")));
                }
                renderJson(new BaseResponse(Code.SUCCESS, "", publishShanghuPage));
                break;
            case "6":
                Page<Record> publishYouhuiquanPage =
                        Db.paginate(Integer.parseInt(getPara("pageNumber")),
                                Integer.parseInt(getPara("pageSize")),
                                "select *",
                                "from t_publish_youhuiquan order by creationDate desc");

                for (int i = 0; i < publishYouhuiquanPage.getList().size(); i++) {
                    publishYouhuiquanPage.getList().get(i).set("user", new User().findFirst(
                            "select * from t_user where userId=?",
                            publishYouhuiquanPage.getList().get(i).get("userId")));

                    publishYouhuiquanPage.getList().get(i).set("type", "YOUHUIQUAN");
                }
                renderJson(new BaseResponse(Code.SUCCESS, "", publishYouhuiquanPage));
                break;
            default:
                break;
        }
//        new PublishDaren().find("select * from t_publish_daren a left join t_publish_image b on a.contentId=b.contentId");
    }
}