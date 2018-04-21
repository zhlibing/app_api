package com.zhuanghl.jfinal.router;

import com.jfinal.config.Routes;
import com.zhuanghl.jfinal.api.*;

/**
 * @author zhuanghl
 */
public class APIRouter extends Routes {

    @Override
    public void config() {
        //公共api
        add("/api", CommonAPIController.class);
        //用户相关
//        add("/api/account", AccountAPIController.class);
        add("/api/account", APIAccountController.class);
        //文件相关
//        add("/api/fs", FileAPIController.class);
        add("/api/fs", APIFileController.class);
        add("/api/publishDaren", APIPublishDarenController.class);
        add("/api/publishBozhu", APIPublishBozhuController.class);
        add("/api/publishDaoyou", APIPublishDaoyouController.class);
        add("/api/publishLvxingzhe", APIPublishLvxingzheController.class);
        add("/api/publishShanghu", APIPublishShanghuController.class);
        add("/api/homeLoad", APIHomeLoadController.class);
        add("/api/publishPoi", APIPublishPoiController.class);
        add("/api/publishPingyou", APIPublishPingyouController.class);
        add("/api/publishYouhuiquan", APIPublishYouhuiquanController.class);
        add("/api/uploadOnlyMuilt", APIUploadOnlyMuiltController.class);
    }
}
