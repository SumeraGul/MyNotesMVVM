package com.example.mynotesmvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NotesRepo notesRepo;
    private LiveData<List<Note>> notelist;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        notesRepo = new NotesRepo(application);
        notelist =  notesRepo.getAllData();
    }
    public void insert(Note note){
        notesRepo.insert(note);
    }
    public void update(Note note){
        notesRepo.update(note);
    }
    public void delete(Note note){
        notesRepo.delete(note);
    }

    public LiveData<List<Note>> getNotelist(){
        return notelist;
    }
}
