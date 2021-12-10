package base.vue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import function.Function;


public class Historique {
	int id;
	String numero;
	int idTypeTransaction;
	double montant;
	int type;
	String date;
	
	
	@Override
	public String toString() {
		return "Historique [id=" + id + ", numero=" + numero
				+ ", idTypeTransaction=" + idTypeTransaction + ", montant="
				+ montant + ", type=" + type + ", date=" + date + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public int getIdTypeTransaction() {
		return idTypeTransaction;
	}
	public void setIdTypeTransaction(int idTypeTransaction) {
		this.idTypeTransaction = idTypeTransaction;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Historique(int id, String numero, double montant, int idTypeTransaction, int type, String date) {	
		this.id = id;
		this.numero = numero;
		this.idTypeTransaction = idTypeTransaction;
		this.montant = montant;
		this.type = type;
		this.date = date;
	}
	public Historique(){}
	
	
	
	public Historique(int id) {
		super();
		this.id = id;
	}
	public static ArrayList<Historique> getHistorique() throws Exception{
		ArrayList<Historique> list=new ArrayList<Historique>();
		Connection conn=null;
		PreparedStatement st=null; 
		ResultSet result = null;
		System.out.println("eee");
		try {
			 conn = Function.getConnect();
	         String sql="select * from historique";
	         st=  conn.prepareStatement(sql);
	         result = st.executeQuery();
	         while(result.next()) list.add(new Historique(result.getInt("idTransaction"), result.getString("numero"), result.getDouble("montant"), result.getInt("typeTransaction"), result.getInt("type"), result.getString("dateTransaction")));        
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}finally{
			if(result != null) result.close();
			if(st!=null)st.close();
	        if(conn!=null)conn.close();
         } 
		return list; 
	}
	
	
    public  Historique[] getAll() throws Exception {
        Connection conn;
        conn = null;
        ResultSet result=null;
        String tab="historique";
           
        try{
            conn = Function.getConnect();
            String sql="select *from Historique";
            Statement st=conn.createStatement();
            result=st.executeQuery(sql);
             
            int taille=countRow(conn,tab);
           
            Historique[] val=new Historique[taille]; 
            int i=0;
            while(result.next()==true){
            	 int id=result.getInt("idTransaction");
                 String numero=result.getString("numero");
                 double montant=result.getDouble("montant");
                 int typeTransaction =result.getInt("typeTransaction");
                 int type=result.getInt("type");
                 String date =result.getString("dateTransaction");
               
            	Historique e=new Historique(id,numero,montant,typeTransaction,type,date);    
                 val[i]=e;
                i++;
                
            }
            return val;
            }catch(SQLException ex){

                throw ex;
             }finally{
             if(conn!=null)conn.close();
              if(result!=null)result.close();
             }
        }
        public static int countRow(Connection conn,String tab) throws Exception{
            int count=0;
            conn=null;
            Statement st=null;   
            
            ResultSet result=null;
            try{
                  conn = Function.getConnect();
                  String sql="select*from "+tab;
                  st=conn.createStatement();
                  result=st.executeQuery(sql);
                  while(result.next()){  
                      count++; 
                  }
                 // System.out.println(count);
               }catch(SQLException ex){
               throw ex;
               }
               finally{
            	   if(st!=null)st.close();
            	   if(conn!=null)conn.close();
            	   if(result!=null)result.close();
               }
               
             return count;
         }
     
    }

/*
create table historique (
idTransaction int,
numero varchar(10),
typeTransaction int,
type int,
date varchar(10),
montant real
);
insert into historique values (1, '0345656987', 2, 2, '2021-03-04', 50000.0), (2, '0344578961', 1, 3, '2021-04-04', 20000.0);

*/