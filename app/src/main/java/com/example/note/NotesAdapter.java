package com.example.note;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    /*static*/ class NoteViewHolder extends RecyclerView.ViewHolder {
        LinearLayout containerView;
        TextView textView;

        NoteViewHolder(View view) {
            super(view);
            containerView = view.findViewById(R.id.note_row);
            textView = view.findViewById(R.id.note_row_text);

            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Note current = (Note) containerView.getTag();
                    Intent intent = new Intent(v.getContext(), EditActivity.class);
                    intent.putExtra("id", current.id);
                    intent.putExtra("content", current.noteText);
                    v.getContext().startActivity(intent);
                }
            });

            containerView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Note current = (Note) containerView.getTag();
                    MainActivity.database.noteDao().delete(current.id);
                    notifyItemRemoved(getAdapterPosition());
                    reload();
                    return true;
                }
            });

        }


    }

    private List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_row, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note current = notes.get(position);
        holder.textView.setText(current.noteText);
        holder.containerView.setTag(current);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    void reload() {
        notes = MainActivity.database.noteDao().selectAll();
        notifyDataSetChanged();
    }

}
