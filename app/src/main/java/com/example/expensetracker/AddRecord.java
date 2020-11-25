package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddRecord extends AppCompatActivity {

    private FirebaseFirestore db;

    EditText title,amount;
    Spinner type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        db = FirebaseFirestore.getInstance();
        title= findViewById(R.id.title);
        amount= findViewById(R.id.amount);
        type= findViewById(R.id.type);


        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecord();
            }
        });


    }

    private void addRecord() {
        Map<String, Object> product = new HashMap<>();
        product.put("title", title.getText().toString());
        product.put("amount", amount.getText().toString());
        product.put("type", type.getSelectedItem().toString());
        product.put("timestamp", FieldValue.serverTimestamp());

        db.collection("records")
                .add(product)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddRecord.this, "Added", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }
}
