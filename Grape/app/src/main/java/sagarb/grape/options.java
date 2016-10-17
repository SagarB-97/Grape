package sagarb.grape;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }
    public void editProf(View v)
    {
        deleteFile("SavedData.txt");
        Intent prev = new Intent(this,Form.class);
        startActivity(prev);
    }
    public void newsession(View v)
    {
        Intent scan = new Intent(this,MainActivity.class);
        startActivity(scan);
    }
    @Override
    public void onBackPressed()
    {
        Intent welcome = new Intent(this,WelcomeScreen.class);
        startActivity(welcome);
    }
}
