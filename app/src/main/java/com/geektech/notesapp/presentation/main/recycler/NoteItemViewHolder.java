package com.geektech.notesapp.presentation.main.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.geektech.notesapp.R;
import com.geektech.notesapp.model.NoteEntity;

import java.text.SimpleDateFormat;

public class NoteItemViewHolder extends RecyclerView.ViewHolder {

    private NoteClickListener mListener;

    private TextView textTitle, textDesc, textDate;


    NoteItemViewHolder(
            @NonNull View itemView,
            NoteClickListener listener
    ) {
        super(itemView);
        mListener = listener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(getAdapterPosition());
            }
        });

        textTitle = itemView.findViewById(R.id.item_note_title);
        textDesc = itemView.findViewById(R.id.item_note_desc);
        textDate = itemView.findViewById(R.id.item_note_created_at);
    }

    void onBind(NoteEntity noteEntity) {

        textTitle.setText(noteEntity.getTitle());
        textDesc.setText(noteEntity.getDescription());
        textDate.setText(SimpleDateFormat.getDateInstance().format(noteEntity.getCreatedAt()));
    }

    public interface NoteClickListener {
        void onClick(int position);
    }
}
