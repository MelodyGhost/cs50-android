package com.example.note;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    /*@Insert
   void create(Note... notes);*/
    @Query("INSERT INTO note (noteText) VALUES ('New note')")
    void create();

    @Query("SELECT * FROM note")
    public List<Note> selectAll();

    @Query("UPDATE note SET noteText = :contents WHERE id= :id")
    public void save(String contents, int id);

    @Query("DELETE FROM note WHERE id = :id")
    public void delete(int id);
}
