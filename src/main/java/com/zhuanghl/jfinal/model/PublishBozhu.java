package com.zhuanghl.jfinal.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;
import java.util.Map;

/**
 * @author zhuanghl
 */
public class PublishBozhu extends Model<PublishBozhu> {

    public static String USER_ID = "userId";
    public static String CONTENT_ID = "contentId";
    public static String TITLE = "title";
    public static String IMAGES_SIZE = "imagesSize";
    public static String FIRST_IMAGE_URL = "firstImageUrl";
    public static String TYPE = "type";
    public static String CONTENT_DETAILS = "contentDetails";
    public static String CREATION_DATE = "creationDate";

    private static final long serialVersionUID = 1L;
    public static final PublishBozhu publishBozhu= new PublishBozhu();

    @Override
    public Map<String, Object> getAttrs() {
        return super.getAttrs();
    }
}
