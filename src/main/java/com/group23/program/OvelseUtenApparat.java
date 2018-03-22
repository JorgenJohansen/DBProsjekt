package com.group23.program;

public class OvelseUtenApparat extends Ovelse {
    String beskrivelse;
    
    public OvelseUtenApparat(int ovelseID, String navn, String beskrivelse) {
        super(ovelseID, navn);
    	this.beskrivelse = beskrivelse;
    }
}
