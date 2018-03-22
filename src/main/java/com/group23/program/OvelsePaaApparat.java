package com.group23.program;

public class OvelsePaaApparat extends Ovelse {
	String beskrivelse;
    public OvelsePaaApparat(int ovelseID, String navn, String beskrivelse) {
		super(ovelseID, navn);
		this.beskrivelse = beskrivelse;
	}
}
