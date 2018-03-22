package com.group23.program;

public class OvelsePaaApparat extends Ovelse {
	int apparat;
	String bruksinformasjon;
    public OvelsePaaApparat(int ovelseID, String navn, int apparat, String bruksinformasjon) {
		super(ovelseID, navn);
		this.apparat = apparat;
		this.bruksinformasjon = bruksinformasjon;
	}
}
