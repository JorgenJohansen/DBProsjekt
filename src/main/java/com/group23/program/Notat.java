package com.group23.program;

public class Notat extends DatabaseTable {
    public int treningsokt;
    public String treningsformaal;
    public String opplevelse;
    
    public Notat(int trenings�kt, String treningsformaal, String opplevelse) {
    	this.treningsokt = trenings�kt;
    	this.treningsformaal = treningsformaal;
    	this.opplevelse = opplevelse;
    }
    
    public Notat(int id,  int trenings�kt, String treningsformaal, String opplevelse) {
    	this.id = id;
    	this.treningsokt = trenings�kt;
    	this.treningsformaal = treningsformaal;
    	this.opplevelse = opplevelse;
    }
}
