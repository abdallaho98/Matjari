package com.sana.matjari;
/**
 * Created by asus on 14/03/2018.
 */

class MarketTag {

    /**
     * Main attributes of a post tag.*/

    private String Tag;

    /**
     * */


    /* eEmpty constructor*/
    public MarketTag() {

    }

    /* String Tag constructor */
    public MarketTag(String Tag) {
        this.Tag = Tag;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }
}
