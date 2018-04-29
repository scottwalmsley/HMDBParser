package com.metabosoft.cm.hmdbparser;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.sql.SQLException;

import java.util.List;

public class HMDBParser extends DefaultHandler{

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, SQLException {
        List<HmdbMetabolite> met;
        HmdbFile hmdbFile = new HmdbFile();


        boolean load = false;
        if(load) {
            SAXParserFactory spfac = SAXParserFactory.newInstance();

            SAXParser sp = spfac.newSAXParser();



            //sp.parse("test.xml", hmdbFile);
            sp.parse("hmdb_metabolites.xml", hmdbFile);

            met = hmdbFile.getMetaboliteList();
            hmdbFile.serializeData("hmdbMetabolites.ser", met);
            boolean debug = true;
        }else{
            try {
                met = hmdbFile.deserializeData("hmdbMetabolites.ser");
                boolean debug = true;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }




    }


   /* private void readMetabolites() throws IOException, SQLException {

        String hmdbTable = "";
        String line;

        System.out.println("No of metabolites: " + metaboliteList.size() + ".");

        Iterator<Metabolite> it = metaboliteList.iterator();
        while(it.hasNext()){
            Metabolite met = it.next();
        }

    }*/

}
