package com.geektech.notesapp.data.notes;

import com.geektech.notesapp.model.NoteEntity;

import java.util.Collection;

public class NotesRepository implements INotesStorage {

    private INotesStorage mLocal, mRemote;

    public NotesRepository(INotesStorage local) {

        mLocal = local;
    }

    @Override
    public void addNote(NoteEntity noteEntity) {

        mLocal.addNote(noteEntity);
    }

    @Override
    public NoteEntity getNote(int id) {

        return mLocal.getNote(id);
    }

    @Override
    public Collection<NoteEntity> getAllNotes() {
        return mLocal.getAllNotes();
    }

    @Override
    public void deleteNote(int id) {
        mLocal.deleteNote(id);

    }
}
