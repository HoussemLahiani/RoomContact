package com.houssem.lahiani.roomcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    EditText nom,phone;
    Button add;
    ContactDAO contactDAO;
    AppDatabase appDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nom=findViewById(R.id.nom);
        phone=findViewById(R.id.phone);
        add=findViewById(R.id.add);




        add.setOnClickListener(view ->{
            Contact c=new Contact();
            c.setName(nom.getText().toString());
            c.setPhone(phone.getText().toString());
            new Thread(()->{
                appDatabase = AppDatabase.getAppDatabase(MainActivity.this);
                contactDAO=appDatabase.contactDAO();
                contactDAO.insert(c);

                Intent i= new Intent(getApplicationContext(),ListContact.class);
                startActivity(i);
                runOnUiThread(()->{

                });
            }).start();




        });
    }
}