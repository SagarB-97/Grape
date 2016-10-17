package sagarb.grape;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class successf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successf);
    }
    @Override
    public void onBackPressed()
    {
        Intent back = new Intent(this,options.class);
        startActivity(back);
    }
    public void coat(View v)
    {
        Intent coatt = new Intent(this,Coatt.class);
        startActivity(coatt);
    }
}
