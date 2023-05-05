package com.example.mynotesmvvm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mynotesmvvm.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteViewModel = new ViewModelProvider((ViewModelStoreOwner) this,
                (ViewModelProvider.Factory) ViewModelProvider
                .AndroidViewModelFactory.getInstance(getApplication())).get(NoteViewModel.class);
        binding.floatingBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddNote.class);
                i.putExtra("type", "Add Mode");
                startActivityForResult(i, 1);
            }
        });
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setHasFixedSize(true);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        binding.recyclerview.setAdapter(adapter);
        noteViewModel.getNotelist().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.submitList(notes);

            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.RIGHT) {
                    noteViewModel.delete(adapter.getNote(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(MainActivity.this, AddNote.class);
                    i.putExtra("type", "update");
                    i.putExtra("title", adapter.getNote(viewHolder.getAdapterPosition()).getTitle());
                    i.putExtra("desc", adapter.getNote(viewHolder.getAdapterPosition()).getDescription());
                    i.putExtra("id", adapter.getNote(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(i, 2);



            }}
        }).attachToRecyclerView(binding.recyclerview);}


        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 1) {
                String title = data.getStringExtra("title");
                String desc = data.getStringExtra("desc");
                Note note = new Note(title, desc);
                noteViewModel.insert(note);
                Toast.makeText(this, "note added", Toast.LENGTH_SHORT).show();
            } else if (requestCode == 2) {
                String title = data.getStringExtra("title");
                String desc = data.getStringExtra("desc");
                Note note = new Note(title, desc);
                note.setId(data.getIntExtra("id", 0));
                noteViewModel.update(note);
                Toast.makeText(this, "note updated", Toast.LENGTH_SHORT).show();


            }

        }
    }

