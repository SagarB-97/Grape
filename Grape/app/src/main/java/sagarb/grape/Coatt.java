package sagarb.grape;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Coatt extends AppCompatActivity {

    private String JSONUrl = GlobalVar.URL+"view";
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coatt);
        lv = (ListView)findViewById(R.id.listView);
        sendRequest();
    }
    public void sendRequest()
    {
        StringRequest stringRequest = new StringRequest(JSONUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        showJson(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Coatt.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void showJson(String Json)
    {
        ParseJson parseJson = new ParseJson(Json);
        parseJson.parseJSON();
        CustomList customList = new CustomList(this,ParseJson.names,ParseJson.emails,ParseJson.colleges,ParseJson.profs);
        lv.setAdapter(customList);
        customList.notifyDataSetChanged();
    }
}
