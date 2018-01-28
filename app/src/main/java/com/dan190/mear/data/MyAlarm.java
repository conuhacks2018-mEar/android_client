package com.dan190.mear.data;

import io.realm.RealmObject;

/**
 * Created by Dan on 27/01/2018.
 */

public class MyAlarm extends RealmObject {
    private String title;
    private String message;
    private long time;
    private int icon;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
