package com.geektech.notesapp.presentation.addnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.geektech.notesapp.R;
import com.geektech.notesapp.model.NoteEntity;

public class AddNoteActivity extends AppCompatActivity {
    NoteEntity mNoteEntity;
    EditText mEditTextTitle, mEditTextDesc;
    Button submitBtn;

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

        addNote();
        //TODO: Init views
        //TODO: 1 title input, 1 description input, 1 submit button
    }

    private void addNote() {

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mEditTextTitle.getText().toString().trim();
                String desc = mEditTextDesc.getText().toString().trim();
                mNoteEntity.setTitle(title);
                mNoteEntity.setDescription(desc);
                Intent intent = new Intent();
                intent.putExtra("key", true);
                setResult(RESULT_OK, intent);
                finish();


            }
        });
        //TODO: Fetch inputs text and save new Note via App.notesStorage
    }
}
