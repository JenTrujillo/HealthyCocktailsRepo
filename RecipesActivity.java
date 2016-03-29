package com.healthycocktails.trujillo.healthycocktails;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JennyG on 3/14/2016.
 */
public class RecipesActivity extends ListActivity
{
    //URL to contact JSON
    private static String url = "http://localhost/json_get_data.php";

    //JSON Node Names
    private static final String recipesInfo = "Info";
    private static final String recipeID = "ID";
    private static final String recipeName = "Name";
    private static final String recipeCal = "Calories";
    private static final String recipeAlcLevel = "AlcLevel";
    private static final String recipeSugar = "Sugar";
    private static final String recipeIngredients = "Ingredients";
    private static final String recipePreparation = "Preparation";
    private static final String recipeGlass = "Glass";


    //String JSON_STRING;
    //private String jsonResult;
    //private String url = "http//localhost/json_get_data.php";
    //private ListView recipesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        //Calling async tast to get json
        new GetRecipes().execute();
    }
     //   recipesList = (ListView)findViewById(R.id.recipe_List);
       // accessWebService();
    //}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);//??
        return true;

    }

    //Async Task to get json by making HTTP call
    private class GetRecipes extends AsyncTask<Void, Void, Void> {

        //Hashmap for ListView
        ArrayList<HashMap<String, String>> recipesList;
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Shows progress dialog
            pDialog = new ProgressDialog(RecipesActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            //Creating service handler class instance
            WebRequest webreq = new WebRequest();

            //Making a request to url and getting response
            String jsonString = webreq.makeWebServiceCall(url, WebRequest.GET);
            Log.d("Response: ", "> " + jsonString);

            recipesList = ParseJSON(jsonString);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            //Updating parsed JSON data into ListView

            ListAdapter adapter = new SimpleAdapter(
                    RecipesActivity.this, recipesList,
                    R.layout.list_item, new String[]{recipeID},
                    new int[]{R.id.rName});
            setListAdapter(adapter);
        }
    }

    private ArrayList<HashMap<String, String>> ParseJSON(String json) {

        if(json != null){
            try{
                //Hashmap for listview
                ArrayList<HashMap<String, String>> RecipesList = new ArrayList<HashMap<String, String>>();

                JSONObject jsonObject = new JSONObject(json);

                //Getting JSON Array Node
                JSONArray recipes = jsonObject.getJSONArray(recipesInfo);

                //Looping through all recipes
                for (int i = 0; i < recipes.length();i++){
                    JSONObject c = recipes.getJSONObject(i);
                    String name = c.getString(recipeName);
                    //GET ALL DATA FROM RECIPES

                    //TMP hashmap for single recipe
                    HashMap<String, String> recipe = new HashMap<String, String>();
                    //Adding each child node to HasMap key => value
                    recipe.put(recipeName, name);

                    //Adding recipe to recipe list
                    RecipesList.add(recipe);

                }
                return RecipesList;
            }catch (JSONException e){
                e.printStackTrace();
                return null;
            }
        }else{
            Log.e("ServiceHandler", "Couldn't get any datat from the url");
            return null;
        }
    }


}




