package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences savedUser = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textview = (TextView) findViewById(R.id.welcomeMess);
        textview.setText("Welcome, " + savedUser.getString("username", ""));
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        SharedPreferences savedUser = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        switch (item.getItemId())
        {
            case R.id.logout:
                Intent intent = new Intent(this, MainActivity.class);
                savedUser.edit().remove("username").apply();
                startActivity(intent);
                return true;
            case R.id.addnote:
                Toast.makeText(this, "Add Note selected", Toast.LENGTH_SHORT).show();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
