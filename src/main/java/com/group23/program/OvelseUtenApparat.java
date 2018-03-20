package com.group23.program;

public class OvelseUtenApparat extends DatabaseTable{
    String beskrivelse;
    int ovelseID;
    
    public OvelseUtenApparat(String beskrivelse) {
    	this.beskrivelse = beskrivelse;
    }
    
    public OvelseUtenApparat(int ovelseID, String beskrivelse) {
    	this.ovelseID = ovelseID;
    	this.beskrivelse = beskrivelse;
    }
}
