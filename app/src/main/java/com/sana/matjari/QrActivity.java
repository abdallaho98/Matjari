package com.sana.matjari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrActivity extends AppCompatActivity {
    private DatabaseReference database,base;
    public String toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        base = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance().getReference().child("stores");
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setPrompt("Scan");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(false);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.initiateScan();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        // Check which request we're responding to
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (null != result)
        {
            if(result.getContents() == null)
            {
                Toast.makeText(this,"Canceled",Toast.LENGTH_LONG).show();
                startActivity(new Intent(QrActivity.this,MainActivity.class));
                finish();
            }
            else
            {

                toast = result.getContents().toString();

                database.addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        for (DataSnapshot datasnap: dataSnapshot.getChildren())
                        {

                            if(toast.equals(datasnap.getKey().toString().trim()))
                            {
                                Vendeur vendeur = new Vendeur();
                               // base.child("vendeur").child()
                               // vendeur.setName();
                                vendeur.setAddress(datasnap.child("adresse").getValue().toString());
                                vendeur.setMarketType(datasnap.child("category").getValue().toString());
                                vendeur.setMarketName(datasnap.child("nomMagasin").getValue().toString());
                                vendeur.setRegion(datasnap.child("city").getValue().toString());
                                vendeur.setWilaya(datasnap.child("country").getValue().toString());
                                vendeur.setTel(datasnap.child("tel").getValue().toString());
                                Intent intent = new Intent(QrActivity.this,SubActivity.class);
                                intent.putExtra("Vendeur",vendeur);
                                startActivity(intent);
                                finish();
                            }
                        }
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
        }
        super.onActivityResult(requestCode,resultCode,data);
    }


}
