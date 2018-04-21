package com.zhuanghl.jfinal.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.Map;

/**
 * @author zhuanghl
 */
public class PublishPingyou extends Model<PublishPingyou> {

    public static String USER_ID = "userId";
    public static String PINGYOU_ID = "pingyouId";
    public static String NAME = "name";
    public static String TIME = "time";
    public static String PERSON_NUMBER = "personNumber";
    public static String CONTACT_WAY = "contactWay";
    public static String CONTACT_NUMBER = "contactNumber";
    public static String IS_FREE = "isFree";
    public static String PRICE = "price";
    public static String ATTACH_INTRODUCE = "attachIntroduce";
    public static String COVER_IMAGE = "coverImage";
    public static String CREATION_DATE = "creationDate";

    private static final long serialVersionUID = 1L;
    public static final PublishPingyou publishPingyou= new PublishPingyou();

    @Override
    public Map<String, Object> getAttrs() {
        return super.getAttrs();
    }
}
