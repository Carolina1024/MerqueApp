package com.example.kitkat.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kitkat.R;
import com.example.kitkat.models.User;
import com.example.kitkat.providers.AuthProviders;
import com.example.kitkat.providers.UsersProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import dmax.dialog.SpotsDialog;

public class CompleteProfileActivity extends AppCompatActivity {
    TextInputEditText mTextInputUsername;
    Button mButtonRegister;
    //FirebaseAuth mAuth;
    //FirebaseFirestore mFirestore;
    AuthProviders mAuthProviders;
    UsersProvider mUsersproviders;
    AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        mTextInputUsername=findViewById(R.id.textInputEditTextUsernameC);
        mButtonRegister=findViewById(R.id.btnConfirmar);

        mAuthProviders=new AuthProviders();
        mUsersproviders=new UsersProvider();

        mDialog=new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Espere un momento....")
                .setCancelable(false).build();

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();

            }
        });


    }

    private void register() {
        String username=mTextInputUsername.getText().toString();

        if(!username.isEmpty()){
            updateUser(username);
        }else {
            Toast.makeText(this, "para continuar inserta el nombre del usuario", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUser(final String username) {
        String id=mAuthProviders.getUid();
        User user=new User();
        user.setUsername(username);
        user.setId(id);
        mDialog.show();
        mUsersproviders.update(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                mDialog.dismiss();
                if(task.isSuccessful()){
                    Intent intent=new Intent(CompleteProfileActivity.this,HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else {
                    Toast.makeText(CompleteProfileActivity.this, "No se almaceno el usuario en la base de datos", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}