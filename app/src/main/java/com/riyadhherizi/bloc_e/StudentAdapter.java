package com.riyadhherizi.bloc_e;

import android.annotation.SuppressLint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Student> {

private ArrayList<Student> students ;
private Context context;

    StudentAdapter(Context context, ArrayList<Student> students ){
        super(context,R.layout.student,students);
        this.students = students;
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        @SuppressLint("ViewHolder")
        View view =inflater.inflate(R.layout.student, parent,false);
        TextView name = view.findViewById(R.id.name);
        TextView id = view.findViewById(R.id.id);
        TextView mat = view.findViewById(R.id.mat);

        name.setText("Full name : " +students.get(position).getFullname());
        id.setText("Matricule: " +students.get(position).getMatricule());
        mat.setText("Id : " +students.get(position).getId());


        return view;
    }
}
