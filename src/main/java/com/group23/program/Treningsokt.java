package com.group23.program;

public class Treningsokt extends DatabaseTable{
    public String dato;
    public int varighet;
    public String informasjon;
    public int personligForm;
    public int prestasjon;

    public Treningsokt(int id, String dato, int varighet, String informasjon, int personligForm, int prestasjon) {
        this.id = id;
        this.dato = dato;
        this.varighet = varighet;
        this.informasjon = informasjon;
        this.personligForm = personligForm;
        this.prestasjon = prestasjon;
    }
}
