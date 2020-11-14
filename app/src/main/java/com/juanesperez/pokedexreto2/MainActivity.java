package com.juanesperez.pokedexreto2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText usernameET;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.btn_signin);
        usernameET = findViewById(R.id.et_username);
        loginBtn.setOnClickListener(this::login);
        db = FirebaseFirestore.getInstance();
    }

    private void login(View v) {
        String username = usernameET.getText().toString();
        HashMap<String,String> user = new HashMap<>();
        user.put("name",username);

        //Saber si el usuario ya esta registrado
        CollectionReference usersRef = db.collection("reto2");
        Query query = usersRef.whereEqualTo("name",username);
        query.get().addOnCompleteListener(
                task -> {
                    if(task.isSuccessful()){
                        if(task.getResult().size() > 0) {
                            for(QueryDocumentSnapshot document : task.getResult()){
                                onRegisterUser(document.getId());
                                Log.e(">>>>",document.getId());
                                break;
                            }
                        }else{
                            db.collection("reto2").document(username).set(user);
                            onRegisterUser(username);
                            Toast.makeText(this,"Hola "+username,Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        /*db.collection("reto2").document(username).set(user).addOnCompleteListener(
                task -> {
                    goToMain();
                    Toast.makeText(this,"Hola "+username,Toast.LENGTH_SHORT).show();
                }
        );*/
    }

    public void onRegisterUser(String user){
        Intent i = new Intent(this, Home.class);
        i.putExtra("myUser",user);
        startActivity(i);
    }
}