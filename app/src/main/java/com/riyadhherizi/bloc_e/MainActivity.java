package com.riyadhherizi.bloc_e;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseManager = new DatabaseManager(this);

        initListView();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseManager.deleteAll();
                initListView();
            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
        final Button add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startActivity(new Intent(MainActivity.this, Add.class));
            }
        });

        final Button get = findViewById(R.id.get_one);
        final EditText id_get = findViewById(R.id.id_get);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = databaseManager.getStudent(Integer.parseInt(id_get.getText().toString()));
                  if (student != null)
                   Toast.makeText(MainActivity.this,student.toString(),Toast.LENGTH_LONG).show();
                 else
                      Toast.makeText(MainActivity.this,"user does not exist",Toast.LENGTH_LONG).show();
                id_get.setText("");
            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Button delete_one = findViewById(R.id.one);
        final EditText id_delete = findViewById(R.id.id_delete);
        delete_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databaseManager.deleteStudent(Integer.parseInt(id_delete.getText().toString())) > 0 ) {
                    Toast.makeText(MainActivity.this,"student deleted ",Toast.LENGTH_LONG).show();
                    initListView();

                }
                else
                    Toast.makeText(MainActivity.this,"student does not exist ",Toast.LENGTH_LONG).show();
                id_delete.setText("");
            }
        });



//////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Button update = findViewById(R.id.update_one);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText id_update = findViewById(R.id.id_update);
                Intent intent = new Intent(MainActivity.this,Update.class);
                intent.putExtra("id",id_update.getText().toString());
                Student student = databaseManager.getStudent(Integer.parseInt(id_update.getText().toString()));
                if(student != null )
                startActivity(intent);
                else
                    Toast.makeText(MainActivity.this,"student does not exist ",Toast.LENGTH_LONG).show();

                id_update.setText("");
            }
        });

    }






    private void initListView() {
        StudentAdapter studentAdapter = new StudentAdapter(this,databaseManager.getAllData());
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(studentAdapter);
    }


}
