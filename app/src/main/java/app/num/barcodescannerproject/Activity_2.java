package app.num.barcodescannerproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by italo on 07/07/16.
 */
public class Activity_2 extends AppCompatActivity {
    private String resultMensage;
    private ListView listView;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        Intent intent = getIntent();
        resultMensage = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        JSONObject json2 = new JSONObject();


        try {
            json2 = new JSONObject(resultMensage);
        }catch (Exception e){
            Log.e("Json Exception","bugou saporra!  =(");
        }


        //Negocio da lista

        listView = (ListView) findViewById(R.id.listView);



        ArrayList<String> values = new ArrayList<String>();
        Iterator<?> keys = json2.keys();
        while( keys.hasNext() ) {
            String key = (String)keys.next();
            Log.e("JsonKey",key);
            values.add(key);
        }
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,values);


        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });

    }
}
