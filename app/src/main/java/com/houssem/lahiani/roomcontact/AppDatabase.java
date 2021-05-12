package com.houssem.lahiani.roomcontact;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Contact.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase INSTANCE;

    public abstract ContactDAO contactDAO();


public static AppDatabase getAppDatabase(Context context)
{
    if(INSTANCE==null)
    {
        synchronized (AppDatabase.class)
        {
            if(INSTANCE==null)
            {
                INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,
                        "contactsManager")/*.allowMainThreadQueries()*/.build();
            }
        }

    }
return INSTANCE;
}


}
