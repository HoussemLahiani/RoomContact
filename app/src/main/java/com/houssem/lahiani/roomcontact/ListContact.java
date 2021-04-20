package com.houssem.lahiani.roomcontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListContact extends AppCompatActivity {

    ListView ls;

    AppDatabase appDatabase;
    ContactDAO contactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);

        appDatabase=AppDatabase.getAppDatabase(ListContact.this);
        contactDAO=appDatabase.contactDAO();

        ls=findViewById(R.id.lst);
        Cursor c= contactDAO.getAllContacts();

        SimpleCursorAdapter adapter= new SimpleCursorAdapter(ListContact.this,
                R.layout.item,c,
                new String[]{c.getColumnName(0),
                        c.getColumnName(1),
        c.getColumnName(2)},
                new int[]{R.id.id,R.id.nom,R.id.phone},1);

        ls.setAdapter(adapter);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tid=view.findViewById(R.id.id);
                Intent i=new Intent(getApplicationContext(),DetailActivity.class);
                i.putExtra("id",tid.getText().toString());
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"Ajouter contact");
        menu.add(0,2,0,"Quitter");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case 1:
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            case 2:
                Intent in=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
        }
        return super.onOptionsItemSelected(item);
    }
}