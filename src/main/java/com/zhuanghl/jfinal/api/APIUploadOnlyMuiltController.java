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
import com.zhuanghl.jfinal.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Before(PublishInterceptor.class)
public class APIUploadOnlyMuiltController extends BaseAPIController {

    /**
     * 处理单文件或多文件上传，上传成功后，返回url集合
     */
    public void upload() {
        if (!methodType("post")) {
            render404();
            return;
        }
        FileResponse response = new FileResponse();
        try {
            if (getAttr("success_images_url") != null
                    && !getAttr("success_images_url").toString().isEmpty()) {
                List<String> urls = new ArrayList<>();
                urls = getAttr("success_images_url");
                if (getAttr("failed_images_url") != null
                        && !getAttr("failed_images_url").toString().isEmpty()) {
                    response.setCode(Code.FAIL);//表示此次上传有未上传成功的文件
                    response.setFailed((List<String>) getAttr("failed_images_url"));
                }
                response.setData(urls);
                response.setCode(Code.SUCCESS);
                renderJson(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            renderJson(new BaseResponse(Code.ERROR));
        }
    }
}