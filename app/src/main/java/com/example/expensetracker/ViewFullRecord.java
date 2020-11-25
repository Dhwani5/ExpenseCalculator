package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class ViewFullRecord extends AppCompatActivity {

    TextView title,amount,balance;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_full_record);
        title=findViewById(R.id.title);
        amount=findViewById(R.id.amount);
        balance=findViewById(R.id.balance);
        amount.setText(getIntent().getStringExtra("amount"));
        title.setText(getIntent().getStringExtra("title"));
        balance.setText(getIntent().getStringExtra("balance"));

        db = FirebaseFirestore.getInstance();
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord(getIntent().getStringExtra("ID"));
            }
        });
    }

    private void deleteRecord(String id) {
        db.collection("records").document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ViewFullRecord.this, "Deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ViewFullRecord.this, "Error deleting document", Toast.LENGTH_SHORT).show();


                    }
                });
    }
}
