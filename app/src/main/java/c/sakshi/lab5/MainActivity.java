package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{


    public void onButtonClick(View view)
    {
        SharedPreferences savedUser = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        EditText myTextField = (EditText) findViewById(R.id.userText);
        savedUser.edit().putString("username", myTextField.getText().toString()).apply();
        goToActivity2(myTextField.getText().toString());
    }

    public void goToActivity2(String s)
    {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("user", s);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        SharedPreferences savedUser = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        String usernameKey = "username";

        if (!savedUser.getString(usernameKey, "").equals(""))
        {
            goToActivity2(savedUser.getString(usernameKey, ""));
        }
        else
        {
            setContentView(R.layout.activity_main);
        }
    }
}
