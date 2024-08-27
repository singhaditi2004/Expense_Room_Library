package com.example.roomlibrarysample;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ShowAll extends AppCompatActivity {
    TextView expensesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_all);
        expensesList = findViewById(R.id.expenses_list);

        DBHelper dbhelper = DBHelper.getDB(getApplicationContext());
        List<Expense> list = dbhelper.expenseDao().getAllExpense();

        StringBuilder builder = new StringBuilder();
        for (Expense expense : list) {
            builder.append("Title: ").append(expense.getTitle())
                    .append(", Value: ").append(expense.getValue()).append("\n");
        }

        expensesList.setText(builder.toString());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}