package com.healthycocktails.trujillo.healthycocktails;

import android.app.ListActivity;
import android.os.Bundle;

/**
 * Created by JennyG on 3/15/2016.
 */
public class FavoriteActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
    }
}
