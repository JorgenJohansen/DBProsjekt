package com.group23.program;

public class OvelsePaaApparat extends Ovelse {
	public int apparat;
	String bruksinformasjon;
    public OvelsePaaApparat(int apparat, String navn, String beskrivelse) {
		this.apparat = apparat;
	}
    
    public OvelsePaaApparat(int id, int apparat, String navn, String bruksinformasjon) {
    	this.id = id;
    	this.apparat = apparat;
    	this.navn = navn;
    	this.bruksinformasjon = bruksinformasjon;
    }
}
