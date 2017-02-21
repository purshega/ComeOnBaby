package com.ComeOnBaby.service;

import com.ComeOnBaby.model.AppUser;
import com.ComeOnBaby.model.Note;

import java.util.Date;
import java.util.List;


public interface NoteService {
    void addNewNote(Note note);
    void updateNote(Note note);
    void deleteNote(Note note);
    public Note findByUserDate(AppUser user, Date date);
    public List<Note> findUserNotes(AppUser user);
    public List<Note> findUserNotesInterval(AppUser user, Date startDate, Date endDate);
    List<Note> getAllNotes();
}
