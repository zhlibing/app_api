package com.zhuanghl.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
import com.zhuanghl.jfinal.common.bean.BaseResponse;
import com.zhuanghl.jfinal.common.bean.Code;
import com.zhuanghl.jfinal.common.token.TokenManager;
import com.zhuanghl.jfinal.common.utils.StringUtils;
import com.zhuanghl.jfinal.model.User;

/**
 * Token拦截器
 *
 * @author malongbo
 * @date 15-1-18
 * @package com.pet.project.interceptor
 */
public class TokenInterceptor implements Interceptor {
    @Override
    public void intercept(ActionInvocation ai) {
        Controller controller = ai.getController();
        try {
            //先调用，要不会取不到下面参数
            controller.getFiles();//已接收到的文件
        } catch (Exception e) {
        }
        String token = controller.getPara("token");
        if (StringUtils.isEmpty(token)) {
            controller.renderJson(new BaseResponse(Code.ARGUMENT_ERROR, "token can not be null"));
            return;
        }

        User user = TokenManager.getMe().validate(token);
        if (user == null) {
            controller.renderJson(new BaseResponse(Code.TOKEN_INVALID, "token is invalid"));
            return;
        }

        controller.setAttr("user", user);
        ai.invoke();
    }
}
