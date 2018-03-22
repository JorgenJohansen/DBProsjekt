package com.group23.program;

public class Treningsokt extends DatabaseTable{
    public String dato;
    public int varighet;
    public String informasjon;
<<<<<<< HEAD
    public int personligForm;
    public int prestasjon;
=======
    public String personligForm;
    public String prestasjon;
>>>>>>> 1b6db6e8034ac4944623ab94944ab06e01cc145e

    public Treningsokt(int id, String dato, int varighet, String informasjon, int personligForm, int prestasjon) {
        this.id = id;
        this.dato = dato;
        this.varighet = varighet;
        this.informasjon = informasjon;
        this.personligForm = personligForm;
<<<<<<< HEAD
        this.prestasjon = prestasjon;
=======
        this.prestasjon = presentasjon;
    }

    public Treningsokt(String dato, int varighet, String informasjon, String personligForm, String presentasjon) {
        this.dato = dato;
        this.varighet = varighet;
        this.informasjon = informasjon;
        this.personligForm = personligForm;
        this.prestasjon = presentasjon;
>>>>>>> 1b6db6e8034ac4944623ab94944ab06e01cc145e
    }
}
