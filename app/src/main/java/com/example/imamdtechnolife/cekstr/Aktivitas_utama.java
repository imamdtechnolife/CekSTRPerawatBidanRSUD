package com.example.imamdtechnolife.cekstr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;

public class Aktivitas_utama extends AppCompatActivity {

    private Button btnSimpan;
    private TextView txtNama;
    private  TextView txtRuang;
    private  TextView txtTampil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivitas_utama);

        try
        {

            final DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference().child("Perawat");
            btnSimpan = (Button) findViewById(R.id.btnSimpan);
            txtNama = (TextView) findViewById(R.id.txtNama);
            txtRuang = (TextView) findViewById(R.id.txtRuangan);
            final HashMap<String, String> dataMap = new HashMap<String, String>();
            txtTampil = (TextView) findViewById(R.id.txt_Tampil);

            btnSimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String nama = txtNama.getText().toString().trim();
                    String ruang = txtRuang.getText().toString().trim();

                    dataMap.put("Nama", nama);
                    dataMap.put("Ruang", ruang);

                    mdatabase.child("Perawat").setValue(dataMap);

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


        }
        catch(Exception e)
        {

        }


    }
}
