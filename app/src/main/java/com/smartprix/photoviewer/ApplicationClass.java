package com.smartprix.photoviewer;

import android.app.Application;

import java.util.ArrayList;

/**
 * This is and will always be a test run.
 * Unless its being submitted to the Play Store, in which case, God bless your code.
 * Created by Manu Srivastava on 5/15/18.
 */
public class ApplicationClass extends Application {
    private ArrayList<ImageItem> list;

    public ArrayList<ImageItem> getList() {
        return list;
    }

    public void setList(ArrayList<ImageItem> list) {
        this.list = list;
    }

    public void initializeList() {
        list = new ArrayList<>();
    }
}
