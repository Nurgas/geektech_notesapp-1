package com.geektech.notesapp.data.notes;

import com.geektech.notesapp.model.NoteEntity;

import java.util.Collection;

public interface INotesStorage {


    void addNote(NoteEntity noteEntity);

    NoteEntity getNote(int id);

    Collection<NoteEntity> getAllNotes();

    void deleteNote(int id);
}
