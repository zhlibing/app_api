package com.zhuanghl.jfinal.api;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.zhuanghl.jfinal.common.bean.BaseResponse;
import com.zhuanghl.jfinal.common.bean.Code;
import com.zhuanghl.jfinal.common.bean.DatumResponse;
import com.zhuanghl.jfinal.common.Require;
import com.zhuanghl.jfinal.common.utils.DateUtils;
import com.zhuanghl.jfinal.interceptor.TokenInterceptor;
import com.zhuanghl.jfinal.model.FeedBack;
import com.zhuanghl.jfinal.model.User;
import com.zhuanghl.jfinal.version.Version;
import com.zhuanghl.jfinal.version.VersionManager;

/**
 * 公共模块的api*
 * 
 * 意见反馈: POST /api/feedback
 * 版本更新检查: GET /api/version/check
 *
 * @author zhuanghl
 */
public class CommonAPIController extends BaseAPIController{

    /**
     * 处理用户意见反馈
     */
    @Before(TokenInterceptor.class)
    public void feedback(){
        if (!"post".equalsIgnoreCase(getRequest().getMethod())) {
            renderJson(new BaseResponse(Code.NOT_FOUND));
            return;
        }

        //内容
        String suggestion=getPara("suggestion");
        if(!notNull(Require.me()
                .put(suggestion, "suggestion can not be null"))){
            return;
        }

        FeedBack feedBack = new FeedBack().set(FeedBack.SUGGESTION, suggestion)
                .set(FeedBack.CREATION_DATE, DateUtils.getNowTimeStamp());

        User user = getUser();
        if (user != null) {
            feedBack.set(FeedBack.USER_ID, user.userId());
        }

        //保存反馈
        boolean flag = feedBack.save();

        renderJson(new BaseResponse(flag ? Code.SUCCESS : Code.FAIL, flag ? "意见反馈成功" : "意见反馈失败"));
    }

    /**
     * 版本更新检查*
     */
    @ActionKey("/api/version/check")
    public void checkVersion() {
        String version = getPara("version");//版本号
        String client = getPara("client"); //终端类型, 可选值有android, iphone
        if (!notNull(Require.me()
                .put(version, "version can not be null")
                .put(client, "client can not be null"))) {
            return;
        }
        
        //检查值是否有效
        if (!Version.checkType(client)) {
            renderArgumentError("client is invalid");
            return;
        }
        
        Version result = VersionManager.me().check(version, client);
        DatumResponse response = new DatumResponse(result);
        if (result == null) {
            response.setCode(Code.FAIL);//表示无更新
        }
        
        renderJson(response);
        
    }
}
