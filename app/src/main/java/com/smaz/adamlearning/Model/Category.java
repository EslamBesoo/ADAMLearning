package com.smaz.adamlearning.Model;

import java.io.Serializable;

/**
 * Created by mac on 3/24/17.
 */

public class Category implements Serializable {

    private String id;
    private String name;
    private String imageUrl;

    public Category(String Cid, String Cname, String CimageUrl) {
        this.id = Cid;
        this.name = Cname;
        this.imageUrl = CimageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



}
