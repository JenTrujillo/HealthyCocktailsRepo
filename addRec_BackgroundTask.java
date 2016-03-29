package com.healthycocktails.trujillo.healthycocktails;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by JennyG on 3/28/2016.
 */
public class addRec_BackgroundTask extends AsyncTask<String, Void, String> {

    Context ctx;
    //Constructor
    addRec_BackgroundTask(Context ctx)
    {
        this.ctx = ctx;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //Pass info to db
    @Override
    protected String doInBackground(String... params) {
        //url for register: add info into db
        String reg_url = "http://10.0.2.2/register.php";
        //login???
        String method = params[0];
        if (method.equals("register")) ;
        {
            String name = params[1];
            String ing = params[2];
            String sugar = params[3];
            String cal = params[4];
            String alc = params[5];
            String prep = params[6];

            try {
                //URL Object
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("Recipe", "UTP-8") + "= " +
                        URLEncoder.encode(name, "UTP-8") + "&" +
                        URLEncoder.encode("Recipe", "UTP-8") + "= " +
                        URLEncoder.encode(, "UTP-8") + "&" +
                        URLEncoder.encode("Recipe", "UTP-8") + "= " +
                        URLEncoder.encode(sugar, "UTP-8") + "&" +
                        URLEncoder.encode("Recipe", "UTP-8") + "= " +
                        URLEncoder.encode(cal, "UTP-8") + "&" +
                        URLEncoder.encode("Recipe", "UTP-8") + "= " +
                        URLEncoder.encode(alc, "UTP-8") + "&" +
                        URLEncoder.encode("Recipe", "UTP-8") + "= " +
                        URLEncoder.encode(prep, "UTP-8");
                //write data in bufferwriter
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                return "Adding data was a success!";


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx, result,Toast.LENGTH_LONG).show();
    }
}///Almost there

//https://www.youtube.com/watch?v=cOsZHuu8Qog&feature=iv&src_vid=k3O3CY75ITY&annotation_id=annotation_313008063