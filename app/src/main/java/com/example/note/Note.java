package com.example.note;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "noteText")
    public String noteText;
}
