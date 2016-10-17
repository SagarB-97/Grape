package sagarb.grape;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;

public class Form extends AppCompatActivity {

    private EditText name,email,roll,prof,col;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        name=(EditText)findViewById(R.id.Name);
        email=(EditText)findViewById(R.id.Email);
        roll=(EditText)findViewById(R.id.Roll);
        prof=(EditText)findViewById(R.id.Prof);
        col=(EditText)findViewById(R.id.College);
    }
    public void Save(View v)
    {
        OutputStreamWriter outputStream;
        try {
            outputStream = new OutputStreamWriter(openFileOutput("SavedData.txt", this.MODE_PRIVATE));
            outputStream.write(name.getText().toString());
            outputStream.write("\n");
            outputStream.write(email.getText().toString());
            outputStream.write("\n");
            outputStream.write(roll.getText().toString());
            outputStream.write("\n");
            outputStream.write(prof.getText().toString());
            outputStream.write("\n");
            outputStream.write(col.getText().toString());
            outputStream.write("\n");
            outputStream.close();
            Toast.makeText(this, "Saved Data", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        Intent opt = new Intent(this,options.class);
        startActivity(opt);
    }
}
