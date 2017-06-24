package com.vanging.yoyo.persistence.models;

import java.sql.Date;

public class Class
{
    private String class_id;
    private String class_releaser;
    private String class_name;
    private Date class_date;

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_releaser() {
        return class_releaser;
    }

    public void setClass_releaser(String class_releaser) {
        this.class_releaser = class_releaser;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Date getClass_date() {
        return class_date;
    }

    public void setClass_date(Date class_date) {
        this.class_date = class_date;
    }
}
