package com.healthycocktails.trujillo.healthycocktails;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by JennyG on 3/24/2016.
 */
public class AddRecipeActivity {
    EditText name_editText, ing_editText,sugar_editText, cal_editText, alc_editText, prep_editText;
    //String values
    String name, ing, sugar, cal, alc, prep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecipe);

        //to be passed to backgroundTask
        name_editText = (EditText)findViewById(R.id.addNameText);
        ing_editText = (EditText)findViewById(R.id.addIngText);
        sugar_editText = (EditText)findViewById(R.id.addSugText);
        cal_editText = (EditText)findViewById(R.id.addCalText);
        alc_editText = (EditText)findViewById(R.id.addAlcText);
        prep_editText = (EditText)findViewById(R.id.addPrepText);

    }



    //Method to add my recipe
    public void addMyRecipe(View view){

        name = name_editText;
        ing = ing_editText;
        sugar = sugar_editText;
        cal = cal_editText;
        alc = alc_editText;
        prep = prep_editText;
        String method = "register";
        //Object
        addRec_BackgroundTask backgroundTask = new addRec_BackgroundTask();
        backgroundTask.execute(method, name, ing, sugar, cal, alc, prep);
        finish();













    }//Almost there

    private void finish() {
    }
}
