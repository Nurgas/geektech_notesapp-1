package com.geektech.notesapp.presentation.addnote;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.geektech.notesapp.App;
import com.geektech.notesapp.R;
import com.geektech.notesapp.model.NoteEntity;

public class AddNoteActivity extends AppCompatActivity {
    NoteEntity mNoteEntity;
    EditText mEditTextTitle, mEditTextDesc;
    Button submitBtn;

    public static void start(Context contex) {
        contex.startActivity(new Intent(contex, AddNoteActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        init();
    }

    private void init() {

        mEditTextTitle = findViewById(R.id.title);
        mEditTextDesc = findViewById(R.id.description);
        submitBtn = findViewById(R.id.saveBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
    }

    private void addNote() {

       NoteEntity note = new NoteEntity();
       note.setTitle(mEditTextTitle.getText().toString());
       note.setDescription(mEditTextDesc.getText().toString());

        App.notesStorage.addNote(note);
        finish();
            }
}
