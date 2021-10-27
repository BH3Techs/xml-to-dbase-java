package com.okzim.xml.sax.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;  
import java.sql.*;

public class PrintAllHandlerSax extends DefaultHandler {
   private static final String SQL_INSERT = "INSERT INTO daily_sales(Opcode, TicketAmount, TicketTotal, VoidTicket, SaveTicket, RecallTicket, ItemsNo, Tail_TicketNumber, Tail_Time, Tail_CashierNum, Tail_PosNo, Tail_TV, Tail_Date, Tail_ReturnTicket, Tail_TrainingMode, Tail_PosOffLine, Tail_VoidTicket, Tail_BadRecord,Tail_SeqNo_byte0_1,Tail_SeqNo_byte2) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
   private StringBuilder currentValue = new StringBuilder();
      
     @Override
     public void startDocument() { 
         System.out.println("Starting Document");
     }
   
     @Override
     public void endDocument() {
         System.out.println("End of Document");
     }
   
     @Override
     public void startElement(String uri,String localName,String qName,Attributes attributes) {   
         
            // reset the tag value
         currentValue.setLength(0);
         try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sales_data","root",""); 

         PreparedStatement psInsert = con.prepareStatement(SQL_INSERT)){
                
               con.setAutoCommit(false);
                
         if (qName.equalsIgnoreCase("TicketEnd")) {
             // get tag's attribute by name
             String Opcode = attributes.getValue("Opcode");
             psInsert.setString(1, Opcode);
             
             String TicketAmount= attributes.getValue("TicketAmount");
             psInsert.setString(2, Opcode);
             
             String TicketTotal= attributes.getValue("TicketTotal");
             psInsert.setString(3,TicketTotal);
             
             String VoidTicket= attributes.getValue("VoidTicket");
             psInsert.setString(4, VoidTicket);
             
             String SaveTicket= attributes.getValue("SaveTicket");
             psInsert.setString(5, SaveTicket);
             
             String RecallTicket= attributes.getValue("RecallTicket");
             psInsert.setString(6, RecallTicket);
             
             String ItemsNo= attributes.getValue("ItemsNo");
             psInsert.setString(7, ItemsNo);
             
             String Tail_TicketNumber= attributes.getValue("Tail_TicketNumber");
             psInsert.setString(8, Tail_TicketNumber);
             
             String Tail_Time= attributes.getValue("Tail_Time");
             psInsert.setString(9, Tail_Time);
             
             String Tail_CashierNum= attributes.getValue("Tail_CashierNum");
             psInsert.setString(10, Tail_CashierNum);
             
             String Tail_PosNo= attributes.getValue("Tail_PosNo");
             psInsert.setString(11, Tail_PosNo);
             
             String Tail_TV= attributes.getValue("Tail_TV");
             psInsert.setString(12, Tail_TV);
             
             String Tail_Date= attributes.getValue("Tail_Date");
             psInsert.setString(13, Tail_Date);
             
             String Tail_ReturnTicket= attributes.getValue("Tail_ReturnTicket");
             psInsert.setString(14, Tail_ReturnTicket);
             
             String Tail_TrainingMode= attributes.getValue("Tail_TrainingMode");
             psInsert.setString(15, Tail_TrainingMode);
             
             String Tail_PosOffLine= attributes.getValue("Tail_PosOffLine");
             psInsert.setString(16, Tail_PosOffLine);
             
             String Tail_VoidTicket= attributes.getValue("Tail_VoidTicket");
             psInsert.setString(17, Tail_VoidTicket);
             
             String Tail_BadRecord= attributes.getValue("Tail_BadRecord");
             psInsert.setString(18, Tail_BadRecord);
             
             String Tail_SeqNo_byte0_1= attributes.getValue("Tail_SeqNo_byte0_1");
             psInsert.setString(19, Tail_SeqNo_byte0_1);
             
             String Tail_SeqNo_byte2= attributes.getValue("Tail_SeqNo_byte2");
             psInsert.setString(20, Tail_SeqNo_byte2);
             
             psInsert.addBatch();
             
             int[] rows = psInsert.executeBatch();
             System.out.println(Arrays.toString(rows));
             con.commit();
                 
         }
        }catch (SQLException e) {
               System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
               e.printStackTrace();
         }
     }
   
     @Override
     public void characters(char ch[], int start, int length) {
          
         currentValue.append(ch, start, length);
   
     }

}