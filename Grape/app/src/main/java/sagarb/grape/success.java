//Instead of a new Activity
//A Preloader can be used

package sagarb.grape;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class success extends AppCompatActivity {

    private String[] data = new String[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        try {
            InputStream is = this.openFileInput("SavedData.txt");
            if(is!=null) {
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(inputStreamReader);
                String temp;
                for(int i=0;i<5;i++) {
                    data[i] = br.readLine();
                }
                inputStreamReader.close();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        new sendTask().execute(data[0],data[1],data[2],data[3],data[4]);
    }

    class sendTask extends AsyncTask<String,Void,Void>
    {
        @Override
        protected Void doInBackground(String... params) {
            String namePas = params[0];
            String emailPas = params[1];
            String rollPas = params[2];
            String profPas = params[3];
            String colPas = params[4];
            String url="";
            List<NameValuePair> data = new ArrayList<NameValuePair>();
            data.add(new BasicNameValuePair("name",namePas));
            data.add(new BasicNameValuePair("email",emailPas));
            data.add(new BasicNameValuePair("roll_no",rollPas));
            data.add(new BasicNameValuePair("profession",profPas));
            data.add(new BasicNameValuePair("college",colPas));

            try{
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(data, "utf-8");
                url = GlobalVar.URL+"?"+paramString;
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                runOnUiThread(new Runnable() {
                    public void run() {

                        Toast.makeText(success.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        Intent successf = new Intent(success.this,successf.class);
                        startActivity(successf);
                    }
                });
            }
            catch (Exception e)
            {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    public void run() {

                        Toast.makeText(success.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                        Intent fail = new Intent(success.this,options.class);
                        startActivity(fail);
                    }
                });
            }
            return null;
        }
    }
}
