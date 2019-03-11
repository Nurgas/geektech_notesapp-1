package com.geektech.notesapp.presentation.addnote;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.geektech.notesapp.App;
import com.geektech.notesapp.R;
import com.geektech.notesapp.model.NoteEntity;
import com.geektech.notesapp.presentation.main.MainActivity;

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEditTextTitle, mEditTextDesc;
    TextView saveBtn, backBtn;

    public static void start(Context context) {
        context.startActivity(new Intent(context, AddNoteActivity.class));

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
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveBtn:
                onClickSaveBtn();
                break;
            case R.id.backBtn:
                onClickBackBtn();
                break;
        }

    }

    private void onClickBackBtn() {
        MainActivity.start(this);
    }

    private void onClickSaveBtn() {
        addNote();

    }


    private void addNote() {

        NoteEntity note = new NoteEntity();
        note.setTitle(mEditTextTitle.getText().toString());
        note.setDescription(mEditTextDesc.getText().toString());
        App.notesStorage.addNote(note);
        finish();
    }


}
