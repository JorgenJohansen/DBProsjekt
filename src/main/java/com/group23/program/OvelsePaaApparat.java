package com.group23.program;

public class OvelsePaaApparat extends Ovelse {
	String beskrivelse;
	int ovelseID;
    public OvelsePaaApparat(int ovelseID, String beskrivelse) {
    	this.ovelseID = ovelseID;
		this.beskrivelse = beskrivelse;
		super(id,beskrivelse);
	}
}
