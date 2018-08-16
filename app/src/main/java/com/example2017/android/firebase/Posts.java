package com.example2017.android.firebase;

/**
 * Created by M7moud on 27-Nov-17.
 */
public class Posts {
    private String title;
    private String writer;
    private String img;
    private String catorgy_name;
    private String catorgy_image;
    private String shop_name;
    private String shop_img;

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_img() {
        return shop_img;
    }

    public void setShop_img(String shop_img) {
        this.shop_img = shop_img;
    }

    public Posts()
    {

    }

    public Posts(String title, String writer, String img) {
        this.title = title;
        this.writer = writer;
        this.img = img;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCatorgy_name() {
        return catorgy_name;
    }

    public void setCatorgy_name(String catorgy_name) {
        this.catorgy_name = catorgy_name;
    }

    public String getCatorgy_image() {
        return catorgy_image;
    }

    public void setCatorgy_image(String catorgy_image) {
        this.catorgy_image = catorgy_image;
    }
}
