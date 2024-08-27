package com.example.roomlibrarysample;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = Expense.class, exportSchema = false, version = 1)
public abstract class DBHelper extends RoomDatabase {
    private static final String DB_NAME = "expenseDb";
    private static DBHelper instance;

    public static synchronized DBHelper getDB(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DBHelper.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }

    public abstract ExpenseDAO expenseDao();


    @Override
    public void clearAllTables() {

    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(@NonNull DatabaseConfiguration databaseConfiguration) {
        return null;
    }
}
