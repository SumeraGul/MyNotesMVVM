package com.example.mynotesmvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.PluralsRes;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotesmvvm.databinding.EachRowBinding;

public class RecyclerViewAdapter extends ListAdapter<Note,RecyclerViewAdapter.ViewHolder> {
    public RecyclerViewAdapter(){
    super(CALLBACK);

    }

private static final DiffUtil.ItemCallback<Note> CALLBACK = new DiffUtil.ItemCallback<Note>(){

    @Override
    public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
        return oldItem.getId()==newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
        return oldItem.getTitle().equals(newItem.getTitle()) &&
                oldItem.getDescription().equals(oldItem.getDescription());
    }
};




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_row,parent,false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = getItem(position);
         String title = note.getTitle();
        String dec = note.getDescription();
        if (title != null) {
            holder.titl.setText(title);
            holder.desc.setText(dec);}
        

        // holder.binding.titletext.setText(note.getTitle());
        // holder.binding.desctext.setText(note.getDescription());



    }

    public Note getNote(int position) {

        return getItem(position);


    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        EachRowBinding binding;
          TextView titl, desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = EachRowBinding.bind(itemView);
            titl = binding.titletext;
            desc = binding.desctext;
            

        }
    }}
