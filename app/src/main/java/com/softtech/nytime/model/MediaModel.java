package com.softtech.nytime.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MediaModel
        implements Serializable {

    @SerializedName("media-metadata")
    public List<MetaDataModel> metaData;

}