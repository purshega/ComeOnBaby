package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.NoteDao;
import com.ComeOnBaby.model.AppUser;
import com.ComeOnBaby.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service("noteService")
@Transactional
public class NoteServiceImpl implements NoteService {

    private NoteDao noteDao;

    @Autowired(required = true)
    public void setCaseDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    @Transactional
    public void addNewNote(Note note) {
        noteDao.create(note);
    }

    @Override
    @Transactional
    public void updateNote(Note note) {
        noteDao.update(note);
    }

    @Override
    @Transactional
    public void deleteNote(Note note) {
        noteDao.delete(note);
    }

    @Override
    @Transactional
    public Note findByUserDate(AppUser user, Date date) {
        return noteDao.findByUserDate(user, date);
    }

    @Override
    @Transactional
    public List<Note> findUserNotes(AppUser user) {
        return noteDao.findUserNotes(user);
    }

    @Override
    @Transactional
    public List<Note> findUserNotesInterval(AppUser user, Date startDate, Date endDate) {
        return noteDao.findUserNotesInterval(user, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> getAllNotes() {
        return noteDao.findAll();
    }
}