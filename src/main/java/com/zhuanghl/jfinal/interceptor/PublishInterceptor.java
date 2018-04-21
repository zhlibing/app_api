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

public class PublishInterceptor implements Interceptor {

    @Override
    public void intercept(ActionInvocation ai) {
        Controller controller = ai.getController();
        try {
            List<UploadFile> fileList = controller.getFiles();//已接收到的文件
            if (fileList != null && !fileList.isEmpty()) {
                List<String> urls = new ArrayList<>();//用于保存上传成功的文件地址
                List<String> failedFiles = new ArrayList<String>(); //用于保存未成功上传的文件名

                for (UploadFile uploadFile : fileList) {
                    File file = uploadFile.getFile();
                    String urlPath = FileUtils.saveUploadFile(file);
                    if (StringUtils.isEmpty(urlPath)) {
                        failedFiles.add(uploadFile.getParameterName());//标记为上传失败
                    } else {
                        //返回相对路径,用于响应
                        urls.add(urlPath + file.getName());
                    }
                }
                controller.setAttr("success_images_url", urls);
                if (failedFiles.size() > 0) {
                    controller.setAttr("failed_images_url", failedFiles);
                }
            } else {
                controller.setAttr("success_images_url", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String userId = controller.getPara("userId");
        if (StringUtils.isEmpty(userId)) {
            controller.renderJson(new BaseResponse(Code.ARGUMENT_ERROR, "userId can not be null"));
            return;
        }

        controller.setAttr("userId", userId);
        ai.invoke();
    }
}