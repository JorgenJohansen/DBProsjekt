package com.group23.program;

import javafx.util.Pair;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.Date;


public class Client {
    public static  void main(String[] args) {
        String address = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_3?ssl=false&useSSL=false";
        String username = "didris_db";
        String pass = "1234";
        Scanner scan = new Scanner(System.in);
        Client client = new Client(address, username, pass);

        System.out.println("Type -h for help");

        while(true) {
            String s = scan.nextLine();
            String cmd;

            //Registrere apparater, øvelser og treningsøkter med tilhørende data.
            cmd = "reg-";
            if(s.startsWith(cmd)) {
                String[] params = s.substring(cmd.length()).split("-");

                switch (params[0]) {
                    case("dev"):
                        if(params.length == 3) {
                            client.RegDev(params[1], params[2]);
                        }
                        else {
                            System.out.println("Command not recognised");
                        }
                        break;

                    case("ex"):
                        try {
                            boolean dev = Boolean.parseBoolean(params[2]);

                            if(dev) {
                                client.RegEx(params[1], true, params[3], Integer.parseInt(params[4]));
                            }
                            else {
                                client.RegEx(params[1], false, params[3], 0);
                            }
                        }
                        catch (Exception e) {
                            System.out.println("Command not recognised");
                        }
                        break;

                    case("se"):
                        try {
//                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
//                            LocalDate date = LocalDate.parse(params[1], formatter);

                            String date = params[1];

                            int duration = 0; //format:  HH:mm
                            duration += Integer.parseInt(params[2].split(":")[0]) * 60;
                            duration += Integer.parseInt(params[2].split(":")[1]);

                            String info = params[3];
                            int PForm = Integer.parseInt(params[4]);
                            int feat = Integer.parseInt(params[5]);

                            client.RegSession(date, duration, info, PForm, feat);
                        }
                        catch (Exception e){
                            System.out.println("Command not recognised");
                        }
                        break;

                    default:
                        System.out.println("Command not recognised");
                        break;
                }
                continue;
            }

            //Få opp informasjon om et antall n sist gjennomførte treningsøkter med notater, der n spesifiseres av brukeren.
            cmd = "info-";
            if(s.startsWith(cmd)) {
                String[] params = s.substring(cmd.length()).split("-");
                try {
                    int n = Integer.parseInt(params[0]);
                    client.Info(n);
                }
                catch (Exception e) {
                    System.out.println("Command not recognised");
                }
                continue;
            }

            //For hver enkelt øvelse skal det være mulig å se en resultatlogg i et gitt tidsintervall spesifisert av brukeren.
            cmd = "log-";
            if(s.startsWith(cmd)) {
                String[] params = s.substring(cmd.length()).split("-");

                try {
//                    DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
//                    Date start = format.parse(params[1]);
//
//                    DateFormat format2 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
//                    Date end = format2.parse(params[2]);
                    String start = params[0];
                    String end   = params[1];
                    client.Log(start, end);

                }
                catch (Exception e) {
                    System.out.println(e);
                    System.out.println("Command not recognised");
                }

                continue;
            }

            //Lage øvelsegrupper.
            cmd = "create-";
            if(s.startsWith(cmd)) { //Lage øvelsegrupper.
                String[] params = s.substring(cmd.length()).split("-");
                client.Create(params[0]);
                continue;
            }

            //Finne øvelser som er i samme gruppe.
            cmd = "find-";
            if(s.startsWith(cmd)) {
                String[] params = s.substring(cmd.length()).split("-");
                try {
                    int id = Integer.parseInt(params[0]);
                    client.Find(id);
                }
                catch (Exception e) {
                    System.out.println("Command not recognised");
                }
                continue;
            }

            cmd = "compare-";
            if(s.startsWith(cmd)) {
                String[] params = s.substring(cmd.length()).split("-");
                try {
                    int EID1 = Integer.parseInt(params[0]);
                    int SID1 = Integer.parseInt(params[1]);
                    int EID2 = Integer.parseInt(params[2]);
                    int SID2 = Integer.parseInt(params[3]);

                    client.Compare(EID1, SID1, EID2, SID2);
                }
                catch (Exception e) {
                    System.out.println("Command not recognised");
                }
                continue;
            }

            if(s.equals("-h") || s.equals("help")) {
                String retval = "";
                retval += "Help page";
                retval += "\nRegistere ny apparat/øvelse/treningsøkt";
                retval += "\nApparat:     reg-dev-<name>-<description>";
                retval += "\nØvelse:      reg-ex-<name>-<onDevice(true/false)>-<info/description>-<deviceID (only if on device(>";
                retval += "\nTreningsøkt: reg-se-<time (format yyyy.mm.dd hh:mm:ss)>-<duration (format hh:mm)>-<info>-<form>-<feat>";
                retval += "\n";
                retval += "\nInfo om <n> siste treningsøkter";
                retval += "\ninfo-<n>";
                retval += "\n";
                retval += "\nSe resultater mellom <start tid(format yyyy.mm.dd hh:mm:ss)> og <slutt tid(format yyyy.mm.dd hh:mm:ss)>";
                retval += "\nlog-<start tid>-<slutt tid>";
                retval += "\n";
                retval += "\nLag ny øvelsesgtuppe";
                retval += "\ncreate-<navn>";
                retval += "\n";
                retval += "\nFinn øvelser i øvelsegruppe med id <id>";
                retval += "\nfind-<id>";
                retval += "\n";
                retval += "\nsammenligh to øvelser";
                retval += "\ncompare-<øvelse id 1>-<treningsøkt id 1>-<øvelse id 2>-<treningsøkt id 2>";
                retval += "\n";
                retval += "\nAvslutt";
                retval += "\nexit";
                retval += "\nquit";
                retval += "\nq";

                System.out.println(retval);
                continue;
            }

            if(s.equals("exit") || s.equals("quit") ||s.equals("q")) {
                System.exit(0);
            }

            System.out.println("Command not recognised");
        }
    }

    Queries queries;
    Database db;
    public Client(String address, String username, String pass) {
        queries = new Queries(address, username, pass);
        db = new Database(address, username, pass);
    }

    //Sette inn nytt apparat med navn og beskrivelse
    private void RegDev(String name, String desc) {
        Apparat dev = new Apparat(name, desc);
        try {
            db.create(dev);
        }
        catch (Exception e) {
            System.out.println("Error in creating device");
        }
    }

    //Legger til en øvelse
    private void RegEx(String name, boolean app, String info, int device) {
        try {
            if(app) {
                OvelsePaaApparat ov = new OvelsePaaApparat(device, name, info);
                queries.create(ov);
            }
            else {
                OvelseUtenApparat ov = new OvelseUtenApparat(name, info);
                queries.create(ov);
            }
        }
        catch (Exception e) {
            System.out.println("Error in creating device");
        }
    }

    //Legge til treningsøkter
    private void RegSession(String date, int duration, String info, int pForm, int feat) {
        if(pForm < 1 || pForm > 10 || feat < 1 || feat > 10) {
            System.out.println("Personlog form eller prestasjon var ikke mellom 1 og 10");
            return;
        }
        try {
            Treningsokt okt = new Treningsokt(date, duration, info, pForm, feat);
            db.create(okt);
        }
        catch (Exception e) {
            System.out.println("Something went wrong when trying to insert session");
        }
    }

    //Prints information about n last sessions
    private void Info(int n) {
        try {
            ArrayList<Pair<Treningsokt, Notat>> tre = queries.GetTreningsOktMedNotat();

            for(int i = 0; i < n; i++) {
                Pair<Treningsokt, Notat> pair = tre.get(i);
                Treningsokt okt = pair.getKey();
                Notat notat = pair.getValue();

                System.out.println("ID: " + okt.id);
                System.out.println("Dato: " + okt.dato);
                System.out.println("Varighet: " + okt.varighet);
                System.out.println("Info: " + okt.informasjon);
                System.out.println("Personlig form: " + okt.personligForm);
                System.out.println("Prestasjon: " + okt.prestasjon);

                if(notat==null) {
                    System.out.println("Notat ikke lagt til.");
                }
                else {
                    System.out.println("NotatID: " + notat.id);
                    System.out.println("Treningsformål:" + notat.treningsformaal);
                    System.out.println("Opplevelse:" + notat.opplevelse);
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            System.out.println("Error in getting last n sessions");
        }
    }

    //Finner alle resultater mellom to tidspunkter
    private void Log(String start, String stop) {
        try {
            ArrayList<Resultat> results = queries.resultatTidsInterval(start, stop);
            for(Resultat res : results) {
                System.out.println("Treningsokt: "  + res.treningsokt);
                System.out.println("Øvelse: " + res.ovelse);
                System.out.println("Kilo: " + res.kilo);
                System.out.println("Sett: " + res.sett);
                System.out.println("Reps: " + res.reps);
                System.out.println("Info: " + res.informasjon);
                System.out.println();
            }
            if(results.isEmpty()) {
                System.out.println("No results found");
            }
        }
        catch (Exception e) {
            System.out.println("Error in getting results");
        }
    }

    //Lage Øvelsesgruppe
    private void Create(String name) {
        OvelsesGruppe ovg = new OvelsesGruppe(name);
        try {
            db.create(ovg);
        }
        catch (Exception e) {
            System.out.println("Error in creating excercise group");
        }
    }
    //Finne ovelse via id på øvelsesgruppe
    private void Find(int id) {
        System.out.println("Find\n" + id);
        ArrayList<String> results = null;
        try {
            results = queries.getOvelseFromOvelsesGruppe(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(results);
    }

    //Sammenligner to resultater
    private void Compare(int exID1, int seID1, int exID2, int seID2) {
        Resultat res1 = null;
        Resultat res2 = null;
        try {
            res1 = queries.GetResultat(exID1, seID1);
            res2 = queries.GetResultat(exID2, seID2);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        if(res1 == null) {
            System.out.println("Første resultat ble ikke funnet");
            return;
        }
        if(res2 == null) {
            System.out.println("Andre resultat ble ikke funnet");
            return;
        }

        System.out.println("Treningsokt 1: "  + res1.treningsokt);
        System.out.println("Treningsokt 2: "  + res2.treningsokt);
        System.out.println();
        System.out.println("Øvelse 1: " + res1.ovelse);
        System.out.println("Øvelse 2: " + res2.ovelse);
        System.out.println();
        System.out.println("Kilo 1: " + res1.kilo);
        System.out.println("Kilo 2: " + res2.kilo);
        System.out.println();
        System.out.println("Sett 1: " + res1.sett);
        System.out.println("Sett 2: " + res2.sett);
        System.out.println();
        System.out.println("Reps 1: " + res1.reps);
        System.out.println("Reps 2: " + res2.reps);
        System.out.println();
        System.out.println("Info 1: " + res1.informasjon);
        System.out.println("Info 2: " + res2.informasjon);
        System.out.println();
    }
}
