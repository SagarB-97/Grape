package sagarb.grape;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.io.InputStream;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);


	//Ask for Dynamic Permission for Android OS>=6.0 because of changes Android Permission Policies
    }
    public void proc(View v)
    {
        // Getting status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        // Showing status
        if(status!=ConnectionResult.SUCCESS)
        {
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
        }
        try {
            InputStream is = this.openFileInput("SavedData.txt");
            if(is!=null)
            {
                Intent opt = new Intent(this,options.class);
                startActivity(opt);
            }
            else
            {
                Intent form = new Intent(this,Form.class);
                startActivity(form);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Intent form = new Intent(this,Form.class);
            startActivity(form);
        }
    }
}
