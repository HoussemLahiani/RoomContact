package com.houssem.lahiani.roomcontact;


import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ContactDAO
{


    @Query("SELECT * FROM contact")
    Cursor getAllContacts();


    @Insert
    void insert(Contact contact);


    @Delete
    void delete(Contact contact);

    @Update
    void update(Contact contact);


    @Query("SELECT * FROM contact where _id=:id")
    Contact selectOne(int id);

}
