package com.example2017.android.firebase;

/**
 * Created by M7moud on 27-Nov-17.
 */
public class Posts {
    private String title;
    private String writer;
    private String img;



    public Posts() {
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
}
