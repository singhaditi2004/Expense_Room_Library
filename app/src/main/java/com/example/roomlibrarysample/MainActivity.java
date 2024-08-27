package com.example.roomlibrarysample;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText title, value;
    AppCompatButton btn, calc, show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.title);
        value = findViewById(R.id.value);
        btn = findViewById(R.id.save);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tit = title.getText().toString();
                String val = value.getText().toString();
                if (tit.isEmpty() || val.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Empty value can not be added", Toast.LENGTH_SHORT).show();
                }
                DBHelper dbhelper = DBHelper.getDB(getApplicationContext());
                dbhelper.expenseDao().addEx(
                        new Expense(tit, val)
                );
                List<Expense> list = dbhelper.expenseDao().getAllExpense();
                for (int i = 0; i < list.size(); i++) {
                    Log.d(TAG, "onClick: " + list.get(i).getTitle() + "value " + list.get(i).getValue());
                }
                title.setText("");
                value.setText("");
                Toast.makeText(MainActivity.this, "Value added successfully", Toast.LENGTH_SHORT).show();
            }
        });
        calc=findViewById(R.id.calculatate);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbhelper = DBHelper.getDB(getApplicationContext());
                List<Expense> list = dbhelper.expenseDao().getAllExpense();

                double total = 0.0;
                for (Expense expense : list) {
                    try {
                        total += Double.parseDouble(expense.getValue());
                    } catch (NumberFormatException e) {
                        Log.e(TAG, "Invalid expense value: " + expense.getValue(), e);
                    }
                }

                Toast.makeText(MainActivity.this, "Total Expenses: " + total, Toast.LENGTH_LONG).show();
            }
        });
        show=findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), ShowAll.class);
                startActivity(i);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}