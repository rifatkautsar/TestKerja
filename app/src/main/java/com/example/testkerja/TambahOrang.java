package com.example.testkerja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahOrang extends AppCompatActivity {

    private String nama, alamat;
    private EditText etNama, etAlamat;
    private Button btTambah;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_orang);

        etNama = (EditText) findViewById(R.id.editTextNamaAnggota);
        etAlamat = (EditText) findViewById(R.id.editTextAlamatAnggota);
        btTambah = (Button) findViewById(R.id.buttonTambah);
        btTambah.setOnClickListener(new View.OnClickListener() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("").child("ARISAN").child("USER");


            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                alamat = etAlamat.getText().toString();

                if(TextUtils.isEmpty(nama)){
                    Toast.makeText(TambahOrang.this,"Kolom Nama Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(alamat)){
                    Toast.makeText(TambahOrang.this,"Kolom Alamat Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }
                else{
                    String key = myRef.push().getKey();
                    myRef.child(key).child("iduser").setValue(key);
                    myRef.child(key).child("nama").setValue(nama);
                    myRef.child(key).child("alamat").setValue(alamat);
                    myRef.child(key).child("bayar").setValue("false");
                    myRef.child(key).child("menang").setValue("false");
                    startActivity(new Intent(TambahActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

    }
}
