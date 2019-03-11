package com.geektech.notesapp.presentation.noteinfo;

import com.geektech.core.BaseMvpContract;
import com.geektech.notesapp.model.NoteEntity;

public interface NoteInfoContract {
    interface View extends BaseMvpContract.View<Presenter> {
        void showNote(NoteEntity note);

        void openEditNote(int id);

        void showLoadingError();


    }

    interface Presenter extends BaseMvpContract.Presenter<View>{

        void loadNote(int id);

        void onEditClick();

        void onDeleteClick(int id);

        void onBackClick();

        void onResume();

    }
}
