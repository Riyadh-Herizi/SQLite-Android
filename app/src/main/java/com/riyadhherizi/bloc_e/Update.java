package com.riyadhherizi.bloc_e;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    DatabaseManager databaseManager ;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        TextView  text = findViewById(R.id.user);
        final EditText fname = findViewById(R.id.fullname);
        final EditText matricule = findViewById(R.id.matricule);
        databaseManager = new DatabaseManager(this);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        final String id = bundle.getString("id");
        Student student = databaseManager.getStudent(Integer.parseInt(id));
        if(student != null ){
        fname.setText(student.getFullname());
        matricule.setText(student.getMatricule());
        text.setText("Student ID : " +student.getId());
        }
        else
        {
            Toast.makeText(Update.this,"Ops something went wrong ",Toast.LENGTH_LONG).show();

        }
        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Student student1 = new Student(fname.getText().toString(),matricule.getText().toString());
              if(databaseManager.updateStudent(Integer.parseInt(id),student1) >0 )  {
                  Toast.makeText(Update.this,"Student updated",Toast.LENGTH_LONG).show();
                  startActivity(new Intent(Update.this,MainActivity.class));
                  finish();
              }
            }
        });
    }
}
