package com.example.pumsper.testupdata;

import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private String ip;
    private EditText email;
    private EditText name;
    private Button sent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        email = (EditText) findViewById(R.id.edt_email);
        name = (EditText) findViewById(R.id.edt_name);
        sent = (Button) findViewById(R.id.btn_sent);
        ip="192.168.167.51";

        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void insert(){
        try{
            String em = email.getText().toString().trim();
            String na = name.getText().toString().trim();

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("email",em));
            nameValuePairs.add(new BasicNameValuePair("name",na));

            HttpClient httpclient = new DefaultHttpClient();
            String link = "http://"+ip+"/saoproject/b_up_counsel.php";
            Log.d("link",""+link);
            HttpPost httppost = new HttpPost(link);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
            httpclient.execute(httppost);
            Log.d("sent","ok");
            //onRestart();
        }catch (Exception e){
           // e.printStackTrace();
            Log.d("log_err", "Error http"+e.toString());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
