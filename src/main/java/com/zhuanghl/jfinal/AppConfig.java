package com.zhuanghl.jfinal;

import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.zhuanghl.jfinal.config.Context;
import com.zhuanghl.jfinal.handler.ContextHandler;
import com.zhuanghl.jfinal.interceptor.ErrorInterceptor;
import com.zhuanghl.jfinal.model.*;
import com.zhuanghl.jfinal.plugin.HikariCPPlugin;
import com.zhuanghl.jfinal.router.APIRouter;
import com.zhuanghl.jfinal.handler.APINotFoundHandler;
import com.zhuanghl.jfinal.router.ActionRouter;

/**
 * JFinal总配置文件，挂接所有接口与插件
 *
 * @author zhuanghl
 */
public class AppConfig extends JFinalConfig {

    /**
     * 常量配置
     */
    @Override
    public void configConstant(Constants me) {
        me.setDevMode(true);//开启开发模式
        me.setEncoding("UTF-8");
        me.setViewType(ViewType.JSP);
    }

    /**
     * 所有接口配置
     */
    @Override
    public void configRoute(Routes me) {
        me.add(new APIRouter());//接口路由
        me.add(new ActionRouter()); //页面路由
    }

    /**
     * 插件配置
     */
    @Override
    public void configPlugin(Plugins me) {
        C3p0Plugin cp = new C3p0Plugin(loadPropertyFile("jdbc.properties"));
        me.add(cp);

//        初始化连接池插件
        loadPropertyFile("jdbc.properties");
        HikariCPPlugin hcp = new HikariCPPlugin(getProperty("jdbcUrl"),
                getProperty("user"),
                getProperty("password"),
                getProperty("driverClass"),
                getPropertyToInt("maxPoolSize"));

        me.add(hcp);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(hcp);
        me.add(arp);

        arp.addMapping("t_user", User.USER_ID, User.class);//用户表
        arp.addMapping("t_register_code", RegisterCode.MOBILE, RegisterCode.class); //注册验证码对象
        arp.addMapping("t_feedback", FeedBack.class); //意见反馈表
        arp.addMapping("t_publish_daren", PublishDaren.class); //达人发布表
        arp.addMapping("t_publish_bozhu", PublishBozhu.class); //达人发布表
        arp.addMapping("t_publish_image", PublishImage.class); //达人发布表
        arp.addMapping("t_publish_daoyou", PublishDaoyou.class); //达人发布表
        arp.addMapping("t_publish_pingyou", PublishPingyou.class); //达人发布表
        arp.addMapping("t_publish_poi", PublishPoi.class); //达人发布表
        arp.addMapping("t_publish_lvxingzhe", PublishLvxingzhe.class); //达人发布表
        arp.addMapping("t_publish_shanghu", PublishShanghu.class); //达人发布表
        arp.addMapping("t_publish_youhuiquan", PublishYouhuiquan.class); //达人发布表

    }

    /**
     * 拦截器配置
     */
    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new ErrorInterceptor());
    }

    /**
     * handle 配置*
     */
    @Override
    public void configHandler(Handlers me) {
        me.add(new ContextHandler());
        me.add(new APINotFoundHandler());
    }

    @Override
    public void afterJFinalStart() {
        Context.me().init();
    }

    @Override
    public void beforeJFinalStop() {
        Context.me().destroy();
    }

}