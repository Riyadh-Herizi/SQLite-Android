package com.riyadhherizi.bloc_e;

import androidx.annotation.NonNull;

public class Student {

    private String fullname;
    private String matricule;
    private int id;


     Student(String fullname, String matricule, int id) {
        this.fullname = fullname;
        this.matricule = matricule;
        this.id = id;

    }
     Student(String fullname, String matricule) {
        this.fullname = fullname;
        this.matricule = matricule;
    }

     String getFullname() {
        return fullname;
    }



     String getMatricule() {
        return matricule;
    }


    public int getId() {
        return id;
    }



    @NonNull
    @Override
    public String toString() {
        return "Student{" +
                "fullname='" + fullname + '\'' +
                ", matricule='" + matricule + '\'' +
                ", id=" + id +
                '}';
    }
}
