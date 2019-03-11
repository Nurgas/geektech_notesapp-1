package com.geektech.notesapp.presentation.noteinfo;

import com.geektech.notesapp.data.notes.INotesStorage;
import com.geektech.notesapp.model.NoteEntity;

public class NoteInfoPresenter implements NoteInfoContract.Presenter {

    private NoteInfoContract.View mView;

    private INotesStorage mINotesStorage;

    private int mNoteId = -1;

    public NoteInfoPresenter(NoteInfoContract.View view, INotesStorage notesStorage) {

        mView = view;
        mINotesStorage = notesStorage;
    }

    private void loadNote(){
        if(mView !=null) {
            NoteEntity noteEntity = mINotesStorage.getNote(mNoteId);
            if (noteEntity !=null){
                mView.showNote(noteEntity);
            } else {
                mView.showLoadingError();
            }
        }
    }

    @Override
    public void loadNote(int id) {
        mNoteId = id;
        loadNote();
    }

    @Override
    public void onEditClick() {
        if(mView !=null) {
            mView.openEditNote(mNoteId);
        }
    }

    @Override
    public void onDeleteClick(int id) {
        if(mView !=null) {
            mINotesStorage.getNote(mNoteId);
            mView.finishView();
        }
    }

    @Override
    public void onBackClick() {
        mView.finishView();

    }

    @Override
    public void onResume() {
        loadNote();
    }

    @Override
    public void attachView(NoteInfoContract.View view) {
        mView =view;
        view.attachPresenter(this);

    }

    @Override
    public void detachView() {
        mView =null;



    }
}
