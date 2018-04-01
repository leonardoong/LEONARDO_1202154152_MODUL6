package com.example.android.leonardo_1202154152_modul6;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEmail, mPass;
    private Button mDaftar;

    private FirebaseAuth mAuth;
    String email,pass;
    DatabaseReference databaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail = (EditText)findViewById(R.id.reg_email);
        mPass = (EditText)findViewById(R.id.reg_pass);

        mDaftar = (Button)findViewById(R.id.button_daftar);

        mAuth = FirebaseAuth.getInstance();
        databaseUser = FirebaseDatabase.getInstance().getReference(HomeActivity.table3);

        mDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEmail.getText().toString();
                pass = mPass.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
                    mAuth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        String id = mAuth.getUid();
                                        String[] username = email.split("@");
                                        User user = new User(id, username[0], email);
                                        databaseUser.child(id).setValue(user);
                                        sendToMain();
                                    }else{
                                        String error = task.getException().getMessage();
                                        Toast.makeText(RegisterActivity.this, "Error : " + error, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else if(TextUtils.isEmpty(email)){
                    mEmail.setError("Masukkan Email");
                }else if(TextUtils.isEmpty(pass)){
                    mPass.setError("Masukkan Password");
                }else{
                    Toast.makeText(RegisterActivity.this, "Masukkan email dan password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void sendToMain(){
        Toast.makeText(RegisterActivity.this, "Selamat anda sudah berhasil login",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
