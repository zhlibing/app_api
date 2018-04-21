package com.zhuanghl.jfinal.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.Map;

/**
 * @author zhuanghl
 */
public class PublishImage extends Model<PublishImage> {

    public static String USER_ID = "userId";
    public static String CONTENT_ID = "contentId";
    public static String IMAGE_ID = "imageId";
    public static String IMAGE_URL = "imageUrl";
    public static String INDEXID = "indexId";
    public static String WIDTH = "width";
    public static String HEIGHT = "height";
    public static String CREATION_DATE = "creationDate";

    private static final long serialVersionUID = 1L;
    public static final PublishImage publishImage = new PublishImage();

    @Override
    public Map<String, Object> getAttrs() {
        return super.getAttrs();
    }
}
