package com.example.democardview_alus;

import java.util.ArrayList;

public class MyDataContent {

    public static final ArrayList<DataModel> data = new ArrayList<DataModel>();

    public MyDataContent(){
        DataModel dataModel;
        for (int i = 0; i < es.tijoneem.democardview00.MyData.nameArray.length; i++) {
            dataModel = new DataModel();
            dataModel.setName(es.tijoneem.democardview00.MyData.nameArray[i]);
            dataModel.setVersion(es.tijoneem.democardview00.MyData.versionArray[i]);
            dataModel.setId(es.tijoneem.democardview00.MyData.id_[i]);
            dataModel.setImage(es.tijoneem.democardview00.MyData.drawableArray[i]);

            data.add(dataModel);
        }
    }
}
