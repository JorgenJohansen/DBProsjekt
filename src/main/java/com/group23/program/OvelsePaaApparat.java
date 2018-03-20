package com.group23.program;

public class OvelsePaaApparat extends Ovelse {
	String beskrivelse;
    public OvelsePaaApparat(int ovelseID, String navn, String beskrivelse) {
		this.beskrivelse = beskrivelse;
		super(ovelseID, navn);
	}
}
