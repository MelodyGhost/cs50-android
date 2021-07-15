package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static NoteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Room.databaseBuilder(getApplicationContext(), NoteDatabase.class, "notes")
                .allowMainThreadQueries().build();

        recyclerView = findViewById(R.id.note_list);
        layoutManager = new LinearLayoutManager(this);
        notesAdapter = new NotesAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(notesAdapter);

        FloatingActionButton button = findViewById(R.id.note_add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.noteDao().create();
                notesAdapter.reload();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        notesAdapter.reload();
    }
}
