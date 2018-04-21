package com.zhuanghl.jfinal.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.Map;

/**
 * @author zhuanghl
 */
public class PublishYouhuiquan extends Model<PublishYouhuiquan> {

    public static String USER_ID = "userId";
    public static String YOUHUIQUAN_ID = "youhuiquanId";
    public static String CARD_NAME = "cardName";
    public static String USE_TIME = "useTime";
    public static String USE_LOCATION = "useLocation";
    public static String CARD_TYPE = "cardType";
    public static String CONTACT_WAY = "contactWay";
    public static String CONTACT_NUMBER = "contactNumber";
    public static String DISCOUNT = "discount";
    public static String IS_FREE = "isFree";
    public static String PRICE = "price";
    public static String ATTACH_INTRODUCE = "attachIntroduce";
    public static String COVER_IMAGE = "coverImage";
    public static String CREATION_DATE = "creationDate";

    private static final long serialVersionUID = 1L;
    public static final PublishYouhuiquan publishYouhuiquan = new PublishYouhuiquan();

    @Override
    public Map<String, Object> getAttrs() {
        return super.getAttrs();
    }
}
