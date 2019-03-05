package com.geektech.notesapp.presentation.noteinfo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geektech.notesapp.App;
import com.geektech.notesapp.R;
import com.geektech.notesapp.model.NoteEntity;

public class NoteInfoActivity extends AppCompatActivity {

    private TextView textTitle, textDesc, textDate;
    private Button editBtn, deleteBtn;

    private static final String EXTRA_ID = "id";

    public static void start(Context context, int id) {
        context.startActivity(intent(context, id));
    }

    public static Intent intent(Context context, int id) {
        Intent intent = new Intent(context, NoteInfoActivity.class);
        intent.putExtra(EXTRA_ID, id);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_info);

        init();
    }


    private void init() {

        textTitle = findViewById(R.id.note_info_title);
        textDesc = findViewById(R.id.note_info_desc);
        textDate = findViewById(R.id.note_info_created_at);
        editBtn = findViewById(R.id.edit_note_btn);
        deleteBtn = findViewById(R.id.delete_note_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteEntity note = new NoteEntity();
                App.notesStorage.deleteNote(note.getId());
                finish();

            }
        });


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        loadNote();

    }

    private void loadNote() {
        NoteEntity note = App.notesStorage.getNote(getIntent().getIntExtra("id", -1));

        if(note !=null) {
            textTitle.setText(note.getTitle());
            textDesc.setText(note.getDescription());
            textDate.setText(note.getCreatedAt().toString()); //TODO Data format

        }


    }
}
