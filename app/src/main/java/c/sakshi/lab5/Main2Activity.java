package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    TextView textview;
    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences savedUser = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textview = (TextView) findViewById(R.id.welcomeMess);
        textview.setText("Welcome, " + savedUser.getString("username", ""));

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        DBHelper dbHelper;
        dbHelper = new DBHelper(sqLiteDatabase);
        notes = dbHelper.readNotes(savedUser.getString("username", ""));
        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note : notes)
        {
            displayNotes.add(String.format("Title:%s\nDate:%s", note.getTitle(), note.getDate()));
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                intent.putExtra("noteid", position);
                startActivity(intent);
            }
        });
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
                Intent startNewNote = new Intent(this, ThirdActivity.class);
                startActivity(startNewNote);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
