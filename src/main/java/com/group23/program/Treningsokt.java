package com.group23.program;

public class Treningsokt extends DatabaseTable{
    public String dato;
    public int varighet;
    public String informasjon;
    public String personligForm;
    public String presentasjon;
    
    public Treningsokt(String dato, int varighet, String informasjon, String personligForm,String prestasjon) {
    	this.dato = dato;
    	this.varighet = varighet;
    	this.informasjon = informasjon;
    	this.personligForm = personligForm;
    	this.presentasjon = prestasjon;
    }
    
    public Treningsokt(int id,String dato, int varighet, String informasjon, String personligForm,String prestasjon) {
    	this.id = id;
    	this.dato = dato;
    	this.varighet = varighet;
    	this.informasjon = informasjon;
    	this.personligForm = personligForm;
    	this.presentasjon = prestasjon;
    }
}
