package com.example.firebaseinsertdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    
    EditText t1,t2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        t1 = findViewById(R.id.editText1);
        t2 = findViewById(R.id.editText2);
        btn = findViewById(R.id.button);
        
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserHelper userHelper = new UserHelper();

                //get t1 and t2 and set it in  userHelper class name and phoneNo
                String name = t1.getText().toString();
                String phoneNo = t2.getText().toString();

                
                userHelper.setName(name);
                userHelper.setPhoneNo(phoneNo);

                //set or Insert data in firebase Database in users reference.
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("users");

                //make a child using name reference and push is used for push data in database
                reference.child(name).push().setValue(userHelper);
                Toast.makeText(MainActivity.this, "save successfully", Toast.LENGTH_SHORT).show();

                //This code is for clear text after lick button...
                t1.getText().clear();
                t2.getText().clear();

                
            }
        });
    }

}