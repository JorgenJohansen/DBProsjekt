package com.group23.program;

public class Apparat extends DatabaseTable{
    public String navn;
    public String beskrivelse;
    
    public Apparat(String navn, String beskrivelse) {
    	this.navn = navn;
    	this.beskrivelse = beskrivelse;
    }
    
    public Apparat(int id, String navn, String beskrivelse) {
    	this.id = id;
    	this.navn = navn;
    	this.beskrivelse = beskrivelse;
    }
}
