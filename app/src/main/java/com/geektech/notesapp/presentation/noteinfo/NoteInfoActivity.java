package com.geektech.notesapp.presentation.noteinfo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.geektech.notesapp.R;
import com.geektech.notesapp.model.NoteEntity;

public class NoteInfoActivity extends AppCompatActivity {
    NoteEntity mNoteEntity;

    TextView textTitle, textDesc, textDate;


    //region Static

    public static void start(Context context, int id) {
        context.startActivity(intent(context, id));
    }

    public static Intent intent(Context context, int id) {
        Intent intent = new Intent(context, NoteInfoActivity.class);
        intent.putExtra("key", id);


        //TODO: Put id into intent extras

        return intent;
    }

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_info);

        init();
    }

    private void init() {

        textTitle = findViewById(R.id.textTitleNote);
        textDesc = findViewById(R.id.textDescNote);
        textDate = findViewById(R.id.currentDateNote);

        textTitle.setText(mNoteEntity.getTitle());
        textDesc.setText(mNoteEntity.getDescription());
//        textDate.setText(mNoteEntity.getCreatedAt());
        //TODO: Init all views
        //TODO: Get passed id from intent and load NoteEntity data into views
    }
}
