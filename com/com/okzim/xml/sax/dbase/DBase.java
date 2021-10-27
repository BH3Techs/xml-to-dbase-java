package com.okzim.xml.sax.dbase;

import java.sql.*;
import java.util.*;

public class DBase{
   public static final String SQL_CREATE = "CREATE DATABASE sales_data";
   public static final String SQL_DBASE = "CREATE TABLE daily_sales(Opcode INT, TicketAmount INT, TicketTotal INT, VoidTicket INT, SaveTicket INT, RecallTicket INT, ItemsNo INT, Tail_TicketNumber INT, Tail_Time TIME, Tail_CashierNum INT, Tail_PosNo INT, Tail_TV INT, Tail_Date DATE, Tail_ReturnTicket INT, Tail_TrainingMode INT, Tail_PosOffLine INT, Tail_VoidTicket INT, Tail_BadRecord INT, Tail_SeqNo_byte0_1 INT, Tail_SeqNo_byte2 INT)";
   static Connection con;
   static Connection conn;
   
   static {
       try { 
         Class.forName("com.mysql.jdbc.Driver");
       }catch(ClassNotFoundException ex) {
         System.err.println("Driver not found: " + ex.getMessage());
       }
   };

   public static void dBaseExist(Connection con) throws SQLException {
    try {
        PreparedStatement psCreate = con.prepareStatement(SQL_CREATE);
        psCreate.execute();
    } catch (SQLException sqlException) {
        if (sqlException.getErrorCode() == 1007) {
           
        } else {
            // Some other problems, e.g. Server down, no permission, etc
            sqlException.printStackTrace();
        }
      }
   }
   
    public static void tableExist(Connection conn){
      try{
         PreparedStatement psTable = conn.prepareStatement(SQL_DBASE);
         psTable.execute();
         System.out.println("Table created");
      }catch(SQLException sqlException){ 
      
      }
   
   } 
}
   