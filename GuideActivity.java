package com.healthycocktails.trujillo.healthycocktails;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by JennyG on 3/15/2016.
 */
public class GuideActivity extends ListActivity {

    ListView menuItems;
    //Initialize Array
    String[] gItems = {"Tools & Equipment", "Glassware", "Bar Essentials",
                        "Bar Terms", "General Advice", "Techniques", "Flavor & Garnishes"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        menuItems = (ListView)findViewById(R.id.guide_List);
        //Initialize adapter object
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(menuItems.getContext(), android.R.layout.simple_list_item_1, gItems);
        menuItems.setAdapter(adapter);

    }
}
