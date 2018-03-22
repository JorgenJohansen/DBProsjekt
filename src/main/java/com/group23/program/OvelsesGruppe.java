package com.group23.program;

public class OvelsesGruppe extends DatabaseTable {
    String navn;
    
    public OvelsesGruppe(String navn) {
    	this.navn = navn;
    }
    public OvelsesGruppe(int id, String navn) {
    	this.id = id;
    	this.navn = navn;
    }
}
