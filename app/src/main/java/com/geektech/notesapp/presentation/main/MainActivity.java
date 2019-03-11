package com.geektech.notesapp.presentation.main;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.geektech.notesapp.App;
import com.geektech.notesapp.R;
import com.geektech.notesapp.model.NoteEntity;
import com.geektech.notesapp.presentation.addnote.AddNoteActivity;
import com.geektech.notesapp.presentation.main.recycler.NoteItemViewHolder;
import com.geektech.notesapp.presentation.main.recycler.NotesAdapter;
import com.geektech.notesapp.presentation.noteinfo.NoteInfoActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
    implements NoteItemViewHolder.NoteClickListener {

    private NotesAdapter mAdapter;
    private RecyclerView mRecycler;
    private FloatingActionButton mActionButton;

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    private void init() {

        mAdapter = new NotesAdapter(new ArrayList<NoteEntity>(), this);
        mRecycler = findViewById(R.id.recycler);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        mActionButton= findViewById(R.id.fab);
        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                openAddNoteScreen();

                overridePendingTransition(R.anim.left_in, R.anim.right_in);
            }
        });
    }

    @Override
    public void onClick(int position) {
        NoteEntity note = mAdapter.getNote(position);
        if(note !=null) {
            NoteInfoActivity.start(this, note.getId());
        }
    }

    private void openAddNoteScreen() {
        AddNoteActivity.start(this);
    }

    private void loadNotes() {
        mAdapter.setNotes(App.notesStorage.getAllNotes());

    }
}
