package com.softtech.nytime.model;

import com.google.gson.annotations.SerializedName;
import com.softtech.nytime.core.utils.NYTimesUtils;

import java.io.Serializable;
import java.util.List;
public class NewsModel
        implements Serializable {

    public static final String STANDARD_THUMBNAIL = "Standard Thumbnail";

    public long id;
    public String url;
    public String title;
    public String byline;
    public String published_date;
    public String source;
    @SerializedName("abstract")
    public String abs_abstract;

    public List<MediaModel> media;

    public String getImageUrl() {
        if (NYTimesUtils.isNullOrEmpty(media)) return "";
        if (NYTimesUtils.isNullOrEmpty(media.get(0).metaData)) return "";
        for (MetaDataModel item : media.get(0).metaData) {
            if (item.format.equalsIgnoreCase(STANDARD_THUMBNAIL)) {
                return item.url;
            }
        }
        return "";
    }
}