package base.mongo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import function.Function;
import function.Response;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import function.Mongo;
import helper.Helper;

@Document(collection="appel")
public class Appel {
	String destinateur;
	String destinataire;
	String minute;
	String seconde;
	String date;
	
	public Appel(String destinateur, String destinataire, String minute,
			String seconde, String date) {
		super();
		this.destinateur = destinateur;
		this.destinataire = destinataire;
		this.minute = minute;
		this.seconde = seconde;
		this.date = date;
	}
	
	
	public Appel(String destinataire, String minute, String seconde, String date) {
		super();
		this.destinataire = destinataire;
		this.minute = minute;
		this.seconde = seconde;
		this.date = date;
	}


	public Appel() {
		super();
	}
	public String getDestinateur() {
		return destinateur;
	}
	public void setDestinateur(String destinateur) {
		this.destinateur = destinateur;
	}
	public String getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getSeconde() {
		return seconde;
	}
	public void setSeconde(String seconde) {
		this.seconde = seconde;
	}
	public void setDate() throws Exception {
		Date date = Helper.HTMLDatetimeLocalToDate(this.date, "T");
		this.date = Helper.prettyDate(date, false);
	}
	
	public static ArrayList<Appel> historique(String numero) {
		ArrayList<Appel> array = new ArrayList<Appel>();
		Response res = null;
		Connection co = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			co = Function.getConnect();
			String sql = "select * from appel";
			sql += " where destinateur = '" + numero + "'";
			st = co.prepareStatement(sql);
			rs = st.executeQuery();
			Double solde = null;
			while(rs.next()) {
				Appel appel = new Appel(new Integer(rs.getInt("destinataire")).toString() , new Integer(rs.getInt("minutes")).toString() , new Integer(rs.getInt("seconde")).toString() , rs.getDate("date_appel").toString());
				array.add(appel);
			}
			return array;
		} catch(Exception ex)  {
			ex.printStackTrace();
			res = new Response("401", ex.toString());
		} finally {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(co != null) co.close();
			} catch(SQLException ex) {
				ex.printStackTrace();
				res = new Response("500", ex.toString());
			}
		}
		return array;
       

	}

	public static Response getSoldeCredit(String numero) {
		Response res = null;
		Connection co = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			co = Function.getConnect();
			String sql = "select * from v_somme_solde_credit";
			sql += " where numero = '" + numero + "'";
			st = co.prepareStatement(sql);
			rs = st.executeQuery();
			Double solde = null;
			if(rs.next()) {
				solde = rs.getDouble("valeur");
			}
			res = new Response("200", "Get solde credit OK", solde);
		} catch(Exception ex)  {
			ex.printStackTrace();
			res = new Response("401", ex.toString());
		} finally {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(co != null) co.close();
			} catch(SQLException ex) {
				ex.printStackTrace();
				res = new Response("500", ex.toString());
			}
		}
		return res;
	}
}
