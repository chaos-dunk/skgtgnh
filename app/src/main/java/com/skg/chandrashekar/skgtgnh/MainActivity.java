package com.skg.chandrashekar.skgtgnh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView zero;
    TextView fortyfive;
    TextView ninety;
    DatabaseReference reference;
    TextView angle;
    TextView distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference("/app");
        TextView zero = (TextView) findViewById(R.id.zero);
        TextView fortyfive = (TextView) findViewById(R.id.fortyfive);
        TextView ninety = (TextView) findViewById(R.id.ninety);
        final TextView angle = (TextView) findViewById(R.id.angle);
        final TextView distance = (TextView) findViewById(R.id.distance);
        angle.setText(reference.child("/angle/value").toString());
        distance.setText(reference.child("/distance/value").toString());
        reference.child("/angle/value").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                angle.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        reference.child("/distance/value").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                distance.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child("/angle/value").setValue(0);
            }
        });
        fortyfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child("/angle/value").setValue(45);
            }
        });
        ninety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child("/angle/value").setValue(90);
            }
        });


    }
}
