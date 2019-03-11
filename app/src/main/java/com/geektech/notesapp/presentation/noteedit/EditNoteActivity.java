package com.geektech.notesapp.presentation.noteedit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.geektech.notesapp.App;
import com.geektech.notesapp.R;
import com.geektech.notesapp.model.NoteEntity;
import com.geektech.notesapp.presentation.noteinfo.NoteInfoActivity;

public class EditNoteActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView editTitle, editDesc, editSaveBtn, backBtn;

    NoteEntity note;

    private static final String EXTRA_ID = "id";

    public static void start(Context context, int id) {
        Intent intent = new Intent(context,EditNoteActivity.class);
        intent.putExtra(EXTRA_ID, id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        init();
    }



    private void init(){
        editTitle = findViewById(R.id.edit_note_title);
        editDesc = findViewById(R.id.edit_note_description);

        editSaveBtn = findViewById(R.id.edit_note_save_btn);
        editSaveBtn.setOnClickListener(this);

        backBtn = findViewById(R.id.edit_note_back_btn);
        backBtn.setOnClickListener(this);

        loadNote();
    }

    private void loadNote() {
        note = App.notesStorage.getNote(getIntent().getIntExtra(EXTRA_ID, -1));

        if (note != null) {
            editTitle.setText(note.getTitle());
            editDesc.setText(note.getDescription());
        }
    }

    private void addNote() {
        note.setTitle(editTitle.getText().toString());
        note.setDescription(editDesc.getText().toString());
        App.notesStorage.addNote(note);

        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_note_save_btn:
                onSaveClick();
                break;
            case R.id.edit_note_back_btn:
                onBackClick();
                break;
        }

    }

    private void onBackClick() {
        NoteInfoActivity.start(this, -1);
    }

    private void onSaveClick() {
        addNote();
    }



}
