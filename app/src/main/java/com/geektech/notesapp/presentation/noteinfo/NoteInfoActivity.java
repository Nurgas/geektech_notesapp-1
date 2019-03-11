package com.geektech.notesapp.presentation.noteinfo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.geektech.notesapp.App;
import com.geektech.notesapp.R;
import com.geektech.notesapp.model.NoteEntity;
import com.geektech.notesapp.presentation.noteedit.EditNoteActivity;
import com.geektech.util.DateUtil;


public class NoteInfoActivity extends AppCompatActivity implements View.OnClickListener, NoteInfoContract.View {

    private NoteInfoContract.Presenter mPresenter;

    private TextView textTitle, textDesc, textDate, editBtn, deleteBtn, backDtn;

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

        mPresenter = new NoteInfoPresenter(this, App.notesStorage);

        init();

        mPresenter.loadNote(getIntent().getIntExtra(EXTRA_ID, -1));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    private void init() {

        textTitle = findViewById(R.id.note_info_title);
        textDesc = findViewById(R.id.note_info_desc);
        textDate = findViewById(R.id.note_info_created_at);

        backDtn = findViewById(R.id.back_note_btn);
        backDtn.setOnClickListener(this);

        editBtn = findViewById(R.id.edit_note_btn);
        editBtn.setOnClickListener(this);

        deleteBtn = findViewById(R.id.delete_note_btn);
        deleteBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_note_btn:
                mPresenter.onDeleteClick(-1);
                break;
            case R.id.edit_note_btn:
                mPresenter.onEditClick();
                break;
            case R.id.back_note_btn:
                mPresenter.onBackClick();
        }
    }

    @Override
    public void showNote(NoteEntity note) {
        textTitle.setText(note.getTitle());
        textDesc.setText(note.getDescription());
        textDate.setText(DateUtil.formatDefoultDate(note.getCreatedAt()));

    }

    @Override
    public void openEditNote(int id) {
        EditNoteActivity.start(this, getIntent().getIntExtra(EXTRA_ID, -1));

    }

    @Override
    public void showLoadingError() {
        //TODO show error toast

    }

    @Override
    public void attachPresenter(NoteInfoContract.Presenter presenter) {
        mPresenter = presenter;

    }

    @Override
    public void finishView() {
        finish();

    }


}
