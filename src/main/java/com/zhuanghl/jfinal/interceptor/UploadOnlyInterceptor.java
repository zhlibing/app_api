package com.zhuanghl.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import com.zhuanghl.jfinal.common.bean.BaseResponse;
import com.zhuanghl.jfinal.common.bean.Code;
import com.zhuanghl.jfinal.common.utils.FileUtils;
import com.zhuanghl.jfinal.common.utils.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传总的控制器，所有文件上传类表单均拆分成文件上传和文本提交
 *
 * @author zhuanghl
 */

public class UploadOnlyInterceptor implements Interceptor {

    @Override
    public void intercept(ActionInvocation ai) {
        Controller controller = ai.getController();
        try {
            UploadFile uploadFile = controller.getFile();//已接收到的文件
            if (uploadFile != null) {
                String url = "";//用于保存上传成功的文件地址
                String failedFile = ""; //用于保存未成功上传的文件名

                File file = uploadFile.getFile();
                String urlPath = FileUtils.saveUploadFile(file);
                if (StringUtils.isEmpty(urlPath)) {
                    failedFile = uploadFile.getParameterName();//标记为上传失败
                } else {
                    //返回相对路径,用于响应
                    url = urlPath + file.getName();
                }
                controller.setAttr("success_images_url", url);
                if (failedFile.length() > 0) {
                    controller.setAttr("failed_images_url", failedFile);
                }
            } else {
                controller.setAttr("error", "文件不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ai.invoke();
    }
}