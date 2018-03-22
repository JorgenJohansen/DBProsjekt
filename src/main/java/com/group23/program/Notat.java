package com.group23.program;

public class Notat extends DatabaseTable {
    public int treningsokt;
    public String treningsformaal;
    public String opplevelse;
    
    public Notat(int treningsokt, String treningsformaal, String opplevelse) {
    	this.treningsokt = treningsokt;
    	this.treningsformaal = treningsformaal;
    	this.opplevelse = opplevelse;
    }
    
    public Notat(int id,  int treningsokt, String treningsformaal, String opplevelse) {
    	this.id = id;
    	this.treningsokt = treningsokt;
    	this.treningsformaal = treningsformaal;
    	this.opplevelse = opplevelse;
    }
}
