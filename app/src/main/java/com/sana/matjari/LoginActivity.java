package com.sana.matjari;

import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity  ;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "FACELOG";
    private FirebaseAuth mAuth;
    public String email,password;
    public TextView Email,Password;
    private DatabaseReference mDatabase;

    private Button btnCnx;
    private Button insCnx;
    private EditText nomUtilisateurCnx;
    private EditText mdpCnx;
    private LinearLayout perLin;
    private Button validerBtn;
    private Button retourBtn;
    private EditText nomUIns;
    private EditText mdpIns;
    private EditText nomIns;
    private EditText prenomIns;
    private EditText numIns;
    private EditText adresseMIns;
    private EditText adresseIns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnCnx=findViewById(R.id.cnxBtn);
        insCnx=findViewById(R.id.insBtn);
        nomUtilisateurCnx=findViewById(R.id.nomUtilisateurCnx);
        mdpCnx=findViewById(R.id.mdpCnx);
        perLin=findViewById(R.id.perLin);
        validerBtn=findViewById(R.id.validerBtn);
        retourBtn=findViewById(R.id.retourBtn);
        nomUIns=findViewById(R.id.nomUIns);
        mdpIns=findViewById(R.id.mdpIns);
        nomIns=findViewById(R.id.nomIns);
        prenomIns=findViewById(R.id.prenomIns);
        numIns=findViewById(R.id.numIns);
        adresseMIns=findViewById(R.id.adresseMIns);
        adresseIns=findViewById(R.id.adresseIns);
        perLin.getChildAt(2).setVisibility(View.INVISIBLE);
        mAuth=FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();


        btnCnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG," "+nomUtilisateurCnx.getText().toString().trim()+" "+mdpCnx.getText().toString().trim());
                mAuth.signInWithEmailAndPassword(nomUtilisateurCnx.getText().toString().trim(), mdpCnx.getText().toString().trim())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });

        insCnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View l;
                try {
                    l= (LinearLayout) perLin.getChildAt(1);
                }
                catch (Exception e)
                {
                    l= (ScrollView) perLin.getChildAt(1);
                }
                perLin.removeViewAt(1);
                perLin.addView(l);
                insCnx.setVisibility(View.INVISIBLE);
                retourBtn.setVisibility(View.VISIBLE);
                perLin.getChildAt(2).setVisibility(View.INVISIBLE);
                perLin.getChildAt(1).setVisibility(View.VISIBLE);
            }
        });

        retourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View l;
                try {
                    l= (LinearLayout) perLin.getChildAt(1);
                }
                catch (Exception e)
                {
                    l= (ScrollView) perLin.getChildAt(1);
                }
                perLin.removeViewAt(1);
                perLin.addView(l);
                insCnx.setVisibility(View.VISIBLE);
                retourBtn.setVisibility(View.INVISIBLE);
                perLin.getChildAt(2).setVisibility(View.INVISIBLE);
                perLin.getChildAt(1).setVisibility(View.VISIBLE);
            }
        });

        validerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nomUIns==null || mdpIns==null || nomIns==null || prenomIns==null || numIns==null || adresseMIns==null || adresseIns==null)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Veillez entrer toutes les données SVP", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {


                    mAuth.createUserWithEmailAndPassword(adresseMIns.getText().toString().trim(), mdpIns.getText().toString().trim())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Client c=new Client(nomUIns.getText().toString().trim(),mdpIns.getText().toString().trim(),nomIns.getText().toString().trim(),prenomIns.getText().toString().trim(),Integer.parseInt(numIns.getText().toString()),adresseMIns.getText().toString().trim(),adresseIns.getText().toString().trim());
                                        mDatabase.child("Client").child("/").push().setValue(c);
                                        updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, "Authentication failed login.",
                                                Toast.LENGTH_SHORT).show();
                                        updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }

            }
        });



    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){updateUI(currentUser);}
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
