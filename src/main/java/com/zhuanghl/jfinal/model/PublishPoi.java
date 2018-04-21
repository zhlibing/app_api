package com.zhuanghl.jfinal.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.Map;

/**
 * @author zhuanghl
 */
public class PublishPoi extends Model<PublishPoi> {

    public static String USER_ID = "userId";
    public static String PINGYOU_ID = "pingyouId";
    public static String INDEX_ID = "indexId";
    public static String POI_ID = "poiId";
    public static String LAT = "lat";
    public static String LON = "lon";
    public static String DISTANCE = "distance";
    public static String PROVENCE = "provence";
    public static String CITY = "city";
    public static String ZONE = "zone";
    public static String KEYWORD = "keyword";
    public static String ADDRESS = "address";
    public static String GD_IAMGE = "gdImage";
    public static String GD_CUT_IMAGE = "gdCutImage";
    public static String VIEW_POINT_CONTENT_ID = "viewPointContentId";
    public static String HAVE_VIEW_POINT = "haveViewPoint";
    public static String IS_DRAFT = "isDraft";
    public static String UPDATE_DATE = "updateDate";
    public static String CREATION_DATE = "creationDate";

    private static final long serialVersionUID = 1L;
    public static final PublishPoi publishPoi = new PublishPoi();

    @Override
    public Map<String, Object> getAttrs() {
        return super.getAttrs();
    }
}
