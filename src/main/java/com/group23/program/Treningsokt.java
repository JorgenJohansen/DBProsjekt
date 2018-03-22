package com.group23.program;

public class Treningsokt extends DatabaseTable{
    public String dato;
    public int varighet;
    public String informasjon;
    public String personligForm;
    public String prestasjon;

    public Treningsokt(int id, String dato, int varighet, String informasjon, String personligForm, String presentasjon) {
        this.id = id;
        this.dato = dato;
        this.varighet = varighet;
        this.informasjon = informasjon;
        this.personligForm = personligForm;
        this.prestasjon = presentasjon;
    }

    public Treningsokt(String dato, int varighet, String informasjon, String personligForm, String presentasjon) {
        this.dato = dato;
        this.varighet = varighet;
        this.informasjon = informasjon;
        this.personligForm = personligForm;
        this.prestasjon = presentasjon;
    }
}
