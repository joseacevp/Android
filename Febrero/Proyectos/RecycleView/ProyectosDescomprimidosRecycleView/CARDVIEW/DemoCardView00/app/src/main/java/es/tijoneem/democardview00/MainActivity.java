package es.tijoneem.democardview00;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDataContent myDataContent = new MyDataContent();
    }

    @Override
    public void onListFragmentInteraction(DataModel item) {
        Log.i("DemoCardView00: ", item.toString());

    }
}
