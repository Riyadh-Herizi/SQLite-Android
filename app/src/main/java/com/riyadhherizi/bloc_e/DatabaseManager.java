package com.riyadhherizi.bloc_e;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

private static final String Table_name="students";
private static final String Database_name="esi";
private static final String col1="id";
private static final String col2="fullName";
private static final String col3="matricule";
private Context context;

    DatabaseManager(Context context){
        super(context,Database_name,null,1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "Create table "+Table_name+  "( "+col1+" Integer Primary key Autoincrement ,"+col2 +" TEXT " +
                ", "+col3+" TEXT ) " ;
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+Table_name);
    }

    long insertData(Student student) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,student.getFullname());
        contentValues.put(col3,student.getMatricule());
        return database.insert(Table_name,null,contentValues);
    }

    ArrayList<Student> getAllData(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query(Table_name,null,null,null,null,null,null);
        ArrayList<Student> AllStudents = new ArrayList<>();
        while (cursor.moveToNext())
        {
           AllStudents.add(new Student(cursor.getString(1),cursor.getString(2),cursor.getInt(0)));
        }
        cursor.close();
        return AllStudents;
    }

    Student getStudent(Integer id) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query(Table_name,null,"id=?",new String[]{id.toString()} ,null,null,null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            return new Student(cursor.getString(1),cursor.getString(2),cursor.getInt(0));
        }
        cursor.close();
        return null;
    }


    long deleteStudent(Integer id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(Table_name,"id=?",new String[]{id.toString()});

    }

    long updateStudent(Integer id,Student new_student) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,new_student.getFullname());
        contentValues.put(col3,new_student.getMatricule());
        return database.update(Table_name,contentValues,"id=?",new String[]{id.toString()});

    }

    void deleteAll() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("delete from "+Table_name);
        Toast.makeText(context,"All students deleted",Toast.LENGTH_LONG).show();
    }

}
