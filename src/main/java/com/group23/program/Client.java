package com.group23.program;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.Date;


public class Client {
    public static  void main(String[] args) {
        String address = "";
        String username = "";
        String pass = "";
        Scanner scan = new Scanner(System.in);
        Client client = new Client(address, username, pass);

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
                            client.RegEx(params[1], Boolean.parseBoolean(params[2]), params[3]);
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

            cmd = "help";
            if(s == cmd) {
                String retval = "";
                retval += "Help page";
                retval += "\nRegister new device/exercise/session";
                retval += "\nDevice:   reg-dev-<name>-<description>";
                retval += "\nExercise: reg-ex-<name>-<onDevice(true/false)>-<info/description>";
                retval += "\nSession:  reg-se-<time (format dd.mm.yyyy hh:mm:ss)>-<duration (format hh:mm)>-<info>-<form>-<feat>";
                retval += "\n";
                retval += "\nInfo about <n> last sessions";
                retval += "\ninfo-<n>";
                retval += "\n";
                retval += "\nSee results between <startTime(format dd.MM.yyyy HH:mm:ss)> and <endTime(format dd.MM.yyyy HH:mm:ss)>";
                retval += "\nlog-<startTime>-<endTime>";
                retval += "\n";
                retval += "\nCreate excercise groups";
                retval += "\ncreate-<name>";
                retval += "\n";
                retval += "\nFind excercises in group with id <id>";
                retval += "\nfind-<id>";
                retval += "\n";
                retval += "\nCompare two results";
                retval += "\ncompare-<excercise id 1>-<session id 1>-<exercise id 2>-<session id 2>";
                retval += "\n";
                retval += "\n";
                retval += "\n";

                System.out.println(retval);
            }

            cmd = "exit";
            if(s == cmd) {
                System.exit(0);
            }

            System.out.println("Command not recognised");
        }
    }

    Queries queries;
    public Client(String address, String username, String pass) {
        queries = new Queries(address, username, pass);
    }

    private void RegDev(String name, String desc) {
        System.out.println("RegDev\n" + name + "\n" + desc);
    }
    private void RegEx(String name, boolean app, String info) {
        System.out.println("RegEx\n" + name + "\n" + app + "\n" + info);
    }
    private void RegSession(String date, int duration, String info, int pForm, int feat) {
        System.out.println("RegSession\n" + date + "\n" + duration + "\n" + info + "\n" + pForm + "\n" + feat);
    }

    private void Info(int n) {
        System.out.println("Info\n" + n);
    }

    private void Log(String start, String stop) {
        System.out.println("Info\n" + start + "\n" + stop);
    }
    private void Create(String name) {
        System.out.println("Create\n" + name);
    }

    private void Find(int id) {
        System.out.println("Find\n" + id);
    }

    private void Compare(int exID1, int seID1, int exID2, int seID2) {
        System.out.println("Compare\n" + exID1 + "\n" + seID1 + "\n" + exID2 + "\n" + seID2);
    }
}
