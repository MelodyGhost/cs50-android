package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private EditText editText;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editText = findViewById(R.id.note_edit_text);
        String content = getIntent().getStringExtra("content");
        editText.setText(content);
        id = getIntent().getIntExtra("id", 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.database.noteDao().save(editText.getText().toString(), id);
    }
}
