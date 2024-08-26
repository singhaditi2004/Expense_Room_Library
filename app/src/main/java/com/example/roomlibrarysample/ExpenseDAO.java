package com.example.roomlibrarysample;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpenseDAO {
    @Query("select * from expense")
    List<Expense> getExpense();

    @Insert
    void addEx(Expense exp);

    @Delete
    void deleteEx(Expense ex);
}
