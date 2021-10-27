package com.okzim.xml.sax;

import com.okzim.xml.sax.dbase.DBase;
import com.okzim.xml.sax.handler.PrintAllHandlerSax;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.sql.*;
import java.util.*;


public class ReadXmlSaxParser {
static {
       try { 
         Class.forName("com.mysql.jdbc.Driver");
       }catch(ClassNotFoundException ex) {
         System.err.println("Driver not found: " + ex.getMessage());
         System.exit(0);
       }
   };


    private static final String FILENAME = "doc\\Xml\\sales.xml";
    public static void main(String[] args) {

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/","root","");
            SAXParser saxParser = factory.newSAXParser();
            DBase dbase = new DBase();
            dbase.dBaseExist(conn);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sales_data","root","");
            dbase.tableExist(con);
            PrintAllHandlerSax handler = new PrintAllHandlerSax();
            saxParser.parse(FILENAME, handler);  

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }catch(Exception e){
         System.out.println("Database Server is Offline");
        }

    }

}