package com.zhuanghl.jfinal.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.Map;

/**
 * @author zhuanghl
 */
public class PublishLvxingzhe extends Model<PublishLvxingzhe> {

    public static String USER_ID = "userId";
    public static String CONTENT_ID = "contentId";
    public static String TIME = "time";
    public static String CONTACT_WAY = "contactWay";
    public static String CONTACT_NUMBER = "contactNumber";
    public static String TITLE = "title";
    public static String HAVE_OUTSIDE_LIMK = "haveOutsideLink";
    public static String OUTSIDE_LINK_URL = "outsideLinkUrl";
    public static String TYPE = "type";
    public static String CONTENT_DETAILS = "contentDetails";
    public static String CREATION_DATE = "creationDate";

    private static final long serialVersionUID = 1L;
    public static final PublishLvxingzhe publishLvxingzhe = new PublishLvxingzhe();

    @Override
    public Map<String, Object> getAttrs() {
        return super.getAttrs();
    }
}
