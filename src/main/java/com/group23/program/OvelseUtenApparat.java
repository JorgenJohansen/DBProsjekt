package com.group23.program;

public class OvelseUtenApparat extends Ovelse {
    String beskrivelse;
    public int ovelseID;
    
    public OvelseUtenApparat(String navn, String beskrivelse) {
        this.navn = navn;
    	this.beskrivelse = beskrivelse;
    }
    
    public OvelseUtenApparat(int ovelseID, String navn, String beskrivelse) {
    	this.ovelseID = ovelseID;
    	this.navn = navn;
    	this.beskrivelse = beskrivelse;
    }
}
