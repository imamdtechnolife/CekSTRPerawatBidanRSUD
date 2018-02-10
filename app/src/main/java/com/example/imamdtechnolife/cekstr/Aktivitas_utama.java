package com.example.imamdtechnolife.cekstr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Aktivitas_utama extends AppCompatActivity {

    private Button btnSignUp;
    private TextView txtEmail;
    private  TextView txtPassword;
    private  TextView txtTampil;
    private ListView listNama;
    private ArrayList<String> listNamaNama = new ArrayList<>();
    private ArrayList<String> listKey = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivitas_utama);

        try
        {

            final DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference().child("Perawat");
            btnSignUp = (Button) findViewById(R.id.btnSignUp);
            txtEmail = (TextView) findViewById(R.id.txtEmail);
            txtPassword = (TextView) findViewById(R.id.txtPassword);
            final HashMap<String, String> dataMap = new HashMap<String, String>();
            txtTampil = (TextView) findViewById(R.id.txt_Tampil);
            final ArrayAdapter<String> list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listNamaNama);
            listNama = (ListView) findViewById(R.id.ListNama);
            listNama.setAdapter(list);


            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String nama = txtEmail.getText().toString().trim();
                    String ruang = txtPassword.getText().toString().trim();

                    dataMap.put("Nama", nama);
                    dataMap.put("Ruang", ruang);

                    mdatabase.setValue(dataMap);

                }
            });

            mdatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String ini = dataSnapshot.getValue().toString();
                    txtTampil.setText("Nama" + ini);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mdatabase.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    String isi = dataSnapshot.getValue(String.class);
                    listNamaNama.add(isi);
                    String key = dataSnapshot.getKey();
                    listKey.add(key);
                    list.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        catch(Exception e)
        {

        }


    }
}
