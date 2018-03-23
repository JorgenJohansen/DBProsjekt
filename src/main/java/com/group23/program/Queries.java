package com.group23.program;

import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;

import javax.management.Query;

public class Queries extends Database {


	public Queries(String address, String username, String password) {
		super(address, username, password);
	}


    /**
     * Genererer en komplett liste over alle treningsøkter i databasen
     * @return liste over treningsøkter og tilhørende notater
     * @throws SQLException hvis spørring har syntaxfeil eller ikke kan koble til databasne
     */
	public ArrayList<Pair<Treningsokt, Notat>> GetTreningsOktMedNotat() throws SQLException {
		ArrayList<Pair<Treningsokt, Notat>> list = new ArrayList<>();

		try (Connection connection = getConnection()) {

			String query = "SELECT *, Treningsokt.id AS TID, Notat.id AS NID " +
					"FROM Treningsokt " +
					"LEFT JOIN Notat ON Treningsokt.id = Notat.Treningsokt " +
					"ORDER BY Treningsokt.dato DESC";


			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet results = preparedStatement.executeQuery();

			while (results.next()) {
				int TID = results.getInt("TID");
				String dato = results.getString("dato");
				int varighet = results.getInt("varighet");
				String informasjon = results.getString("informasjon");
				int personligForm = results.getInt("personligForm");
				int presentasjon = results.getInt("prestasjon");
				Treningsokt okt = new Treningsokt(TID, dato, varighet, informasjon, personligForm, presentasjon);

				int NID = results.getInt("TID");
				if(NID != 0) {
					int treningsokt = results.getInt("treningsokt");
					String treningsformaal = results.getString("treningsformaal");
					String opplevelse = results.getString("opplevelse");

					list.add(new Pair(okt, new Notat(NID, treningsokt, treningsformaal, opplevelse)));
				}
				else {
					list.add(new Pair(okt, null));
				}
			}

		} catch (SQLException e) {
			throw e;
		}
		return list;
	}

//
	/**
	 * Generer en komplett liste av alle resultat i databasen
	 * @return liste over resultat
	 */
	public Resultat GetResultat(int EID, int SID) throws SQLException {
		try (Connection connection = getConnection()) {
			
			ArrayList<Resultat> resultatListe = new ArrayList<>();
			String query = "SELECT * FROM Resultat WHERE Resultat.ovelse = ? AND Resultat.treningsokt = ?";

			PreparedStatement getResultat = connection.prepareStatement(query);

			getResultat.setInt(1, EID);
			getResultat.setInt(2, SID);

			ResultSet results = getResultat.executeQuery();
			if(results.next()) {
				int treningsokt = results.getInt("treningsokt");
				int ovelse = results.getInt("ovelse");
				int kilo = results.getInt("kilo");
				int sett = results.getInt("sett");
				int reps = results.getInt("reps");
				String informasjon = results.getString("informasjon");
				return new Resultat(treningsokt, ovelse,kilo, sett, reps, informasjon);
			} else {
			    return null;
            }
		} catch (SQLException e) {
			throw e;
		}
	}

	/**
	 * Generer en liste ut ifra et intervall
	 * @param Start setter en start på intervallet
	 * @param Slutt setter en slutt på intervallet
	 * @return en liste med resultater i et satt intervall
	 * @throws SQLException
	 */
	public ArrayList<Resultat> resultatTidsInterval(String start, String slutt) throws SQLException {
		ArrayList<Resultat> list = new ArrayList<>(); 
	
		try (Connection connection = getConnection()) {
			String query = "SELECT Resultat.treningsokt, Resultat.ovelse, Resultat.kilo, Resultat.sett, Resultat.reps, Resultat.informasjon "+
					"FROM Resultat "+
					"JOIN Treningsokt ON Resultat.treningsokt = Treningsokt.id " +
					"WHERE dato BETWEEN ? AND ? ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, start);
			preparedStatement.setString(2, slutt);

			ResultSet results = preparedStatement.executeQuery();

			while (results.next()) {
				int treningsokt = results.getInt("treningsokt");
				int ovelse = results.getInt("ovelse"); 
				int kilo = results.getInt("kilo"); 
				int sett = results.getInt("sett");  
				int reps = results.getInt("reps"); 
				String informasjon = results.getString("informasjon");

				list.add(new Resultat(treningsokt, ovelse, kilo, sett, reps, informasjon));
			}

		} catch (SQLException e) {
			throw e;
		}
		return list;
	}

	/**
	 * Generer en liste med navn på ovelser i ovelsesGruppe
	 * @param ovelsesGruppeID er ID til ovelsesGruppe
	 * @return en liste med navn på ovelser
	 */
	public ArrayList<String> getOvelseFromOvelsesGruppe(int ovelsesGruppeID) throws SQLException  {
		try (Connection connection = getConnection()) {
			ArrayList<String> list = new ArrayList<>();
			String query = "SELECT * " + 
					"from OvelseIGruppe " + 
					"JOIN Ovelse ON Ovelse.id=OvelseIGruppe.ovelsesgruppe " + 
					"WHERE OvelseIGruppe.ovelse = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, ovelsesGruppeID);
			ResultSet results = preparedStatement.executeQuery();
			while(results.next()) {
				String navn = results.getString("navn");
				list.add(navn);
			}
			return list;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public ArrayList<OvelseUtenApparat> getOvelseUtenApparat() throws SQLException  {
		try (Connection connection = getConnection()) {
			ArrayList<OvelseUtenApparat> list = new ArrayList<>();
			String query = "SELECT c.ovelseID as ovelseID, c.beskrivelse as beskrivelse, p.navn as navn " +
                    "FROM OvelseUtenApparat c " +
                    "INNER JOIN Ovelse p ON c.ovelseID = p.id";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet results = preparedStatement.executeQuery();
			while(results.next()) {
                int ovelseID = results.getInt("ovelseID");
                String navn = results.getString("navn");
                String beskrivelse = results.getString("beskrivelse");
				list.add(new OvelseUtenApparat(ovelseID, navn, beskrivelse));
			}
			return list;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public ArrayList<OvelsePaaApparat> getOvelsePaaApparat() throws SQLException  {
		try (Connection connection = getConnection()) {
			ArrayList<OvelsePaaApparat> list = new ArrayList<>();
            String query = "SELECT c.ovelseID as ovelseID, c.apparat as apparat, " +
                    "c.bruksinformasjon as bruksinformasjon, p.navn as navn " +
                    "FROM OvelsePaaApparat c " +
                    "INNER JOIN Ovelse p ON c.ovelseID = p.id";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet results = preparedStatement.executeQuery();
			while(results.next()) {
				int ovelseID = results.getInt("ovelseID");
				String navn = results.getString("navn");
				int apparat = results.getInt("apparat");
				String bruksinformasjon = results.getString("bruksinformasjon");
				list.add(new OvelsePaaApparat(ovelseID, apparat, navn, bruksinformasjon));
			}
			return list;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	//Setters
	/**
	 * Denne metoden legger inn setter data i ovelsePaaApparat tabellen
	 * @param ovelseID er id p� ovelse
	 * @param apparat er en id p� apparat
	 * @param bruksinformasjon er en tekstslig beskrivelse
	 */
	public int create(OvelsePaaApparat row) throws SQLException  {
		try (Connection connection = getConnection()) {

            connection.setAutoCommit(false);

            String query_parent = "INSERT INTO Ovelse(navn) VALUES(?)";

            String query_child = "INSERT INTO OvelsePaaApparat(ovelseID, apparat, bruksinformasjon) VALUES(?,?,?)";

            PreparedStatement prpstmt_parent = connection.prepareStatement(query_parent, Statement.RETURN_GENERATED_KEYS);
            prpstmt_parent.setString(1, row.navn);
            prpstmt_parent.executeUpdate();

            int ovelseID;
            ResultSet generatedKeys = prpstmt_parent.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println("Insertion complete.");
                ovelseID = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Unable to insert into table");
            }

			PreparedStatement prpstmt_child = connection.prepareStatement(query_child);
            prpstmt_child.setInt(1, ovelseID);
            prpstmt_child.setInt(2, row.apparat);
            prpstmt_child.setString(3, row.bruksinformasjon);
            prpstmt_child.executeUpdate();

            connection.commit();

            connection.setAutoCommit(true);

            return ovelseID;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Denne metoden legger inn data i ovelseUtenApparat tabellen
	 * @param ovelseID er id p� ovelse
	 * @param beskrivelse er en tekstlig beskrivelse
	 */
	public int create(OvelseUtenApparat row) throws SQLException  {
        try (Connection connection = getConnection()) {

            connection.setAutoCommit(false);

            String query_parent = "INSERT INTO Ovelse(navn) VALUES(?)";

            String query_child = "INSERT INTO OvelseUtenApparat(ovelseID, beskrivelse) VALUES(?,?)";

            PreparedStatement prpstmt_parent = connection.prepareStatement(query_parent, Statement.RETURN_GENERATED_KEYS);
            prpstmt_parent.setString(1, row.navn);
            prpstmt_parent.executeUpdate();

            int ovelseID;
            ResultSet generatedKeys = prpstmt_parent.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println("Insertion complete.");
                ovelseID = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Unable to insert into table");
            }

            PreparedStatement prpstmt_child = connection.prepareStatement(query_child);
            prpstmt_child.setInt(1, ovelseID);
            prpstmt_child.setString(2, row.beskrivelse);
            prpstmt_child.executeUpdate();

            connection.commit();

            connection.setAutoCommit(true);

            return ovelseID;
        } catch (Exception e) {
            throw e;
        }

	}
}
