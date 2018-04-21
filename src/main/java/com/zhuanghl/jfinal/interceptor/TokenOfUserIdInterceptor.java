package com.zhuanghl.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
import com.zhuanghl.jfinal.common.bean.BaseResponse;
import com.zhuanghl.jfinal.common.bean.Code;
import com.zhuanghl.jfinal.common.utils.StringUtils;

/**
 * Token拦截器
 *
 * @author malongbo
 * @date 15-1-18
 * @package com.pet.project.interceptor
 */
public class TokenOfUserIdInterceptor implements Interceptor {
    @Override
    public void intercept(ActionInvocation ai) {
        Controller controller = ai.getController();
        try {
            //先调用，要不会取不到下面参数
            controller.getFiles();//已接收到的文件
        } catch (Exception e) {
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
