package com.zhuanghl.jfinal.action;

import com.jfinal.core.Controller;
import com.zhuanghl.jfinal.common.utils.StringUtils;
import com.zhuanghl.jfinal.model.RegisterCode;

/**
 * @author zhuanghl
 */
public class IndexAction extends Controller {

    public void index () {
        render("index.jsp");
    }
    
    public void doc() {
        render("doc/index.html");
    }

    /**
     * 查询手机验证码 *
     * 测试使用*
     */
    public void findCode () {
        String mobile = getPara("mobile");
        if (StringUtils.isNotEmpty(mobile)) {
            String codeStr = "没查到该手机对应的验证码";
            RegisterCode code = RegisterCode.dao.findById(mobile);
            if (code != null && StringUtils.isNotEmpty(code.getStr(RegisterCode.CODE))) {
                codeStr = code.getStr(RegisterCode.CODE);
            }
            renderHtml(codeStr);
        }
    }

}
