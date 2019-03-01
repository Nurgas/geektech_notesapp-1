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
import com.geektech.notesapp.presentation.intro.IntroActivity;
import com.geektech.notesapp.presentation.main.recycler.NoteItemViewHolder;
import com.geektech.notesapp.presentation.main.recycler.NotesAdapter;
import com.geektech.notesapp.presentation.noteinfo.NoteInfoActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
    implements NoteItemViewHolder.NoteClickListener {

    private NotesAdapter mAdapter;
    private RecyclerView mRecycler;
    private ArrayList<NoteEntity> mNoteEntitieList;
    private NoteItemViewHolder.NoteClickListener mListener;

    //region Static

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    //endregion

    //region Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    //endregion

    //region Init

    private void init() {
        mRecycler.findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NotesAdapter(mNoteEntitieList, mListener);
        mRecycler.setAdapter(mAdapter);
        //TODO: Initialize recycler, and Adapter
    }

    //endregion

    //region Click

    @Override
    public void onClick(int position) {
        mAdapter.getNote(position);
        NoteInfoActivity.start(this, position);
        //TODO: Get note from adapter via NotesAdapter.getItem(int position) and open NoteInfoActivity
    }

    //endregion

    private void loadNotes() {
        App.notesStorage.getAllNotes();
        //TODO: Load notes from NotesRepository, get from App instance
    }
}
