package com.example.curdapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLDataException;

public class MainActivity extends AppCompatActivity {
    EditText firstname,lastname;
    TextView textView;
    DB_Controller controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname =(EditText)findViewById(R.id.firstName);
        lastname= (EditText)findViewById(R.id.lastName);
        textView = (TextView)findViewById(R.id.textView);

        controller = new DB_Controller(this,"",null,1);
    }

    public void btn_click(View view) {
        switch (view.getId()){
            case R.id.button_add:
                try{
                    controller.insert_student(firstname.getText().toString(),lastname.getText().toString());
                }
                catch (SQLiteException e){
                    Toast.makeText(MainActivity.this,"ALREADY EXISTS",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_delete:
                controller.delete_student(firstname.getText().toString());
                break;
            case R.id.button_update:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("ENTER NEW FirstName");

                final EditText new_firstname =new EditText(this);
                dialog.setView(new_firstname);

                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        controller.update_Student(firstname.getText().toString(),new_firstname.getText().toString());
                    }
                });
                dialog.show();
                break;
            case R.id.button_list:
                controller.list_All_students(textView);
                break;
        }
    }
}
