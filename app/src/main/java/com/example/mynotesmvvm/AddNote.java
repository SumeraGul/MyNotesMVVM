package com.example.mynotesmvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mynotesmvvm.databinding.ActivityAddNoteBinding;

public class AddNote extends AppCompatActivity {
ActivityAddNoteBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String type = getIntent().getStringExtra("type");
        if (type.equals("update")) {
            setTitle("update");
            binding.title.setText(getIntent().getStringExtra("title"));
            binding.desc.setText(getIntent().getStringExtra("desc"));
            int id = getIntent().getIntExtra("id", 0);
            binding.btnaddNote.setText("Update Note");
            binding.btnaddNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent();
                    i.putExtra("title", binding.title.getText().toString());
                    i.putExtra("desc", binding.desc.getText().toString());
                    i.putExtra("id", id);
                    setResult(RESULT_OK, i);
                    finish();

                }
            });
        } else {
            setTitle("Add Mode");

        binding.btnaddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra("desc", binding.desc.getText().toString());
                i.putExtra("title", binding.title.getText().toString());

                setResult(RESULT_OK, i);
                finish();
            }
        });

    }}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddNote.this,MainActivity.class) );
    }
}