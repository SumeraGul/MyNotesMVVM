package com.example.mynotesmvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesRepo {
    private NoteDao noteDao;
    private LiveData<List<Note>> notelist;
    // constructor

    public NotesRepo(Application application){
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao= noteDatabase.noteDao();
        notelist= noteDao.getAllData();
    }
    public void insert(Note note){ new InsertTask(noteDao).execute(note);}
    public void update (Note note){new UpdateTask(noteDao).execute(note);}
    public void delete(Note note){new DeleteTask(noteDao).execute(note);}
    public LiveData<List<Note>>getAllData(){
        return notelist;
    }
    private static class InsertTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;
        public InsertTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);

            return null;
        }}
        private static class UpdateTask extends AsyncTask<Note,Void,Void> {
            private NoteDao noteDao;

            public UpdateTask(NoteDao noteDao) {
                this.noteDao = noteDao;
            }

            @Override
            protected Void doInBackground(Note... notes) {
                noteDao.update(notes[0]);

                return null;
            }
        }
            private static class DeleteTask extends AsyncTask<Note, Void, Void> {
                private NoteDao noteDao;

                public DeleteTask(NoteDao noteDao) {
                    this.noteDao = noteDao;
                }

                @Override
                protected Void doInBackground(Note... notes) {
                    noteDao.delete(notes[0]);

                    return null;
                }
            }
        }
