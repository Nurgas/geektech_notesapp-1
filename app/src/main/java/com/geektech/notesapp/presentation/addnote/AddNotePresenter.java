package com.geektech.notesapp.presentation.addnote;

import com.geektech.notesapp.data.notes.INotesStorage;
import com.geektech.notesapp.model.NoteEntity;

public class AddNotePresenter implements AddNoteContract.Presenter{

    private AddNoteContract.View mView;

    private INotesStorage mINotesStorage;



    public AddNotePresenter (AddNoteContract.View view, INotesStorage notesStorage){

        mView = view;
        mINotesStorage = notesStorage;

    }


    @Override
    public void onSaveClick(NoteEntity note) {
        mINotesStorage.addNote(note);
    }

    @Override
    public void onBackClick() {
        mView.finishView();

    }

    @Override
    public void attachView(AddNoteContract.View view) {
        mView = view;
        view.attachPresenter(this);
    }

    @Override
    public void detachView() {
        mView = null;

    }
}
