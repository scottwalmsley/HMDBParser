package com.metabosoft.cm.hmdbparser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class HmdbFile extends DefaultHandler {
    private HmdbMetabolite metabolite;
    private List<HmdbMetabolite> metaboliteList = new ArrayList<>();
    private String temp;
    private boolean appendString;
    private boolean getSynonyms = false;
    private boolean getPathways = false;
    private boolean getSinglePathway = false;
    private boolean getBiofluids = false;
    private boolean getTissues = false;
    private HmdbPathway pathway;
    private String content;
    private int accessionCounter;

    public void characters(char[] buffer, int start, int length){
        temp = new String(buffer, start, length);

        //System.out.println("\n\n" + temp);

        if(appendString){
            content += temp;
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        temp = "";
        content = "";
        appendString = true;

        if(qName.equalsIgnoreCase("metabolite")){
            metabolite = new HmdbMetabolite();
            accessionCounter = 0;
        }
        if(qName.equalsIgnoreCase("smiles")){
            appendString = true;
        }
        if(qName.equalsIgnoreCase("synonyms")){
            getSynonyms = true;
            metabolite.initializeSynonyms();
        }
        if(qName.equalsIgnoreCase("pathways")){
            getPathways = true;
            metabolite.initializePathways();
        }
        if(qName.equalsIgnoreCase("pathway")){
            getSinglePathway = true;
            pathway = new HmdbPathway();
        }
        if( qName.equalsIgnoreCase("biofluid_locations")){
            getBiofluids = true;
            metabolite.initializeBiofluids();
        }
        if( qName.equalsIgnoreCase("tissue_locations")){
            getTissues = true;
            metabolite.initializeTissueLocations();
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException{
        String test = temp.trim();
        temp = content;
        if(qName.equalsIgnoreCase("metabolite")){
            this.metaboliteList.add(metabolite);
        } else if (qName.equalsIgnoreCase("creation_date")) {
            metabolite.setCreation_date(temp);
        }  else if (qName.equalsIgnoreCase("accession") && accessionCounter < 1) {
            metabolite.setAccession(temp);
            accessionCounter++;
        } else if (qName.equalsIgnoreCase("name")){
            metabolite.setName(temp);
            if(getSinglePathway ) {
                pathway.setPathway(temp);
            }
        } else if (qName.equalsIgnoreCase("kegg_id")) {
            metabolite.setKegg_id(temp);
        } else if (qName.equalsIgnoreCase("pubchem_compound_id")) {
            metabolite.setPubchem_compound_id(temp);
        } else if (qName.equalsIgnoreCase("chebi_id")) {
            metabolite.setChebi_id(temp);
        } else if (qName.equalsIgnoreCase("monisotopic_molecular_weight")){
            if(test.length()>0) {
                metabolite.setMass(Double.parseDouble(temp));
            }
        } else if (qName.equalsIgnoreCase("average_molecular_weight")){
            if(test.length()>0) {
                metabolite.setAverageMass(Double.parseDouble(temp));
            }
        } else if (qName.equalsIgnoreCase("chemical_formula")){
            metabolite.setFormula(temp);
        } else if (qName.equalsIgnoreCase("iupac_name")){
            metabolite.setIupac_name(temp);
        } else if (qName.equalsIgnoreCase("traditional_iupac")){
            metabolite.setTraditional_iupac(temp);
        } else if (qName.equalsIgnoreCase("cas_registry_number")){
            metabolite.setCas_number(temp);
        } else if (qName.equalsIgnoreCase("smiles")){
            metabolite.setSmiles(temp);
            //System.exit(1);
        } else if (qName.equalsIgnoreCase("inchi")){
            metabolite.setInchi(temp);
        } else if (qName.equalsIgnoreCase("inchikey")){
            metabolite.setInchikey(temp);
        } else if (qName.equalsIgnoreCase("direct_parent")){
            metabolite.setDirect_parent(temp);
        } else if (qName.equalsIgnoreCase("kingdom")) {
            metabolite.setKingdom(temp);
        } else if (qName.equalsIgnoreCase("super_class")) {
            metabolite.setSuper_class(temp);
        } else if (qName.equalsIgnoreCase("class")) {
            metabolite.setChemical_class(temp);
        } else if (qName.equalsIgnoreCase("sub_class")) {
            metabolite.setSub_class(temp);
        } else if (qName.equalsIgnoreCase("molecular_framework")) {
            metabolite.setMolecularFramework(temp);
        } else if (qName.equalsIgnoreCase("smpdb_id") & getSinglePathway){
            pathway.setSmpd_id(temp);

        } else if (qName.equalsIgnoreCase("kegg_map_id") & getSinglePathway){
            pathway.setKegg_mapp_id(temp);
        } else if (qName.equalsIgnoreCase("synonyms")){
            getSynonyms = false;
            if(metabolite.getSynonyms().size() < 1){
                metabolite.nullSynonyms();
            }
        } else if (qName.equalsIgnoreCase("synonym") & getSynonyms){
            metabolite.setSynonyms(temp);
        } else if (qName.equalsIgnoreCase("pathway")){
            getSinglePathway = false;
            metabolite.setPathways(pathway);
        } else if(qName.equalsIgnoreCase("pathways")){
            getPathways = false;
            if(metabolite.getPathways().size() ==0){
                metabolite.nullPathways();
            }
        } else if(qName.equalsIgnoreCase("biofluid_locations")){
            getBiofluids = false;
        } else if(qName.equalsIgnoreCase("biofluid") & getBiofluids){
            metabolite.setBiofluid_locations(temp);
        } else if(qName.equalsIgnoreCase("tissue_locations")){
            getBiofluids = false;
        } else if(qName.equalsIgnoreCase("tissue") & getTissues){
            metabolite.setTissue_locations(temp);
        }
        content = null;
        appendString = false;
    }

    public List<HmdbMetabolite> getMetaboliteList() {
        return metaboliteList;
    }

    public static final void serializeData(String fh, List<HmdbMetabolite> data) throws IOException {
        FileOutputStream fileOut =  new FileOutputStream(fh);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data);
        out.close();
        fileOut.close();
    }

    public static final List<HmdbMetabolite> deserializeData(String fh)throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(fh);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Object data = in.readObject();
        in.close();
        fileIn.close();
        return (List<HmdbMetabolite>) data;
    }

}
