package com.houssem.lahiani.roomcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {

    EditText nom,phone;
    Button mod,sup;
    AppDatabase appDatabase;
    ContactDAO contactDAO;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        appDatabase=AppDatabase.getAppDatabase(DetailActivity.this);
        contactDAO=appDatabase.contactDAO();

        nom=findViewById(R.id.edtnom);
        phone=findViewById(R.id.edtphone);
        mod=findViewById(R.id.modifier);
        sup=findViewById(R.id.supprimer);

        id=getIntent().getStringExtra("id");
        Contact c= contactDAO.selectOne(Integer.parseInt(id));
        nom.setText(c.getName());
        phone.setText(c.getPhone());
mod.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v)
    {
        Contact ct=new Contact();
        ct.set_id(Integer.parseInt(id));
        ct.setName(nom.getText().toString());
        ct.setPhone(phone.getText().toString());

        contactDAO.update(ct);
        Intent i=new Intent(getApplicationContext(),ListContact.class);
        startActivity(i);
    }
});

sup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        contactDAO.delete(c);
        Intent i=new Intent(getApplicationContext(),ListContact.class);
        startActivity(i);
    }
});


    }
}