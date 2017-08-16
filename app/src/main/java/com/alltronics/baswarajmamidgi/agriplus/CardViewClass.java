package com.alltronics.baswarajmamidgi.agriplus;

/**
 * Created by baswarajmamidgi on 15/8/17.
 */

public class CardViewClass {
    int imageurl;
    String content;

    CardViewClass(int url,String content) {
        this.imageurl = url;
        this.content = content;
    }

    public int getImageurl() {
        return imageurl;
    }

    public String getContent() {
        return content;
    }
}
