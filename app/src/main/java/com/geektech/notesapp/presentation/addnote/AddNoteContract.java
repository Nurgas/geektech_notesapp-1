package com.geektech.notesapp.presentation.addnote;

import com.geektech.core.BaseMvpContract;
import com.geektech.notesapp.model.NoteEntity;

public interface AddNoteContract {
    interface View extends BaseMvpContract.View<Presenter> {
        void addNote(NoteEntity note);

    }

    interface Presenter extends BaseMvpContract.Presenter<View>{

        void onSaveClick(NoteEntity note);

        void onBackClick();


    }
}
