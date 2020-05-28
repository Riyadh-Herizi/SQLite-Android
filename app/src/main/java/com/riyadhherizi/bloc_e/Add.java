package com.riyadhherizi.bloc_e;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    DatabaseManager databaseManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText fname = findViewById(R.id.fullname);
        final EditText matricule = findViewById(R.id.matricule);
        databaseManager = new DatabaseManager(this);

        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if  (databaseManager.insertData(new Student(fname.getText().toString(),matricule.getText().toString()))>0)
              {
                  Toast.makeText(Add.this,"Student inserted",Toast.LENGTH_LONG).show();
                  startActivity(new Intent(Add.this,MainActivity.class));
                  finish();
              }
            }
        });

    }
}
