package com.group23.program;

import java.util.Scanner;


public class Client {
    public void Main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while(true) {
            String s = scan.next();

            if(s.startsWith("reg")) { //Registrere apparater, øvelser og treningsøkter med tilhørende data.

            }
            else if(s.startsWith("info")) { //Få opp informasjon om et antall n sist gjennomførte treningsøkter med notater, der n spesifiseres av brukeren.

            }
            else if(s.startsWith("log")) {

            }
            else if(s.startsWith("create")) {

            }
            else if(s.startsWith("find")) {

            }
            else if(s.startsWith("compare")) {

            }
            else if(s == "exit") {
                System.exit(0);
            }
        }
    }

    private void Reg() {

    }
    private void Info() {

    }
    private void Log() {

    }
    private void Create() {

    }
    private void Find() {

    }
    private void Compare() {
        
    }
}
