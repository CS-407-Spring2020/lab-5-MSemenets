package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    public void onButtonClick(View view)
    {
        EditText myTextField = (EditText) findViewById(R.id.userText);
        goToActivity2(myTextField.getText().toString());
    }

    public void goToActivity2(String s)
    {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("user", s);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
