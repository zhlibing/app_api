package com.zhuanghl.jfinal.router;

import com.jfinal.config.Routes;
import com.zhuanghl.jfinal.action.IndexAction;

/**
 * @author zhuanghl
 */
public class ActionRouter extends Routes{

    @Override
    public void config() {
        add("/", IndexAction.class, "/");
    }

}
