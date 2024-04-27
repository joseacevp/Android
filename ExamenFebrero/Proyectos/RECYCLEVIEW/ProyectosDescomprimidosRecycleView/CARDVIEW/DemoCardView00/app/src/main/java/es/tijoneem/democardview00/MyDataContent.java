package es.tijoneem.democardview00;

import java.util.ArrayList;

public class MyDataContent {

    public static final ArrayList<DataModel> data = new ArrayList<DataModel>();

    public MyDataContent(){
        DataModel dataModel;
        for (int i = 0; i < MyData.nameArray.length; i++) {
            dataModel = new DataModel();
            dataModel.setName(MyData.nameArray[i]);
            dataModel.setVersion(MyData.versionArray[i]);
            dataModel.setId(MyData.id_[i]);
            dataModel.setImage(MyData.drawableArray[i]);

            data.add(dataModel);
        }
    }
}
