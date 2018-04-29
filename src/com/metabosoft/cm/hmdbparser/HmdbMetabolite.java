package com.metabosoft.cm.hmdbparser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HmdbMetabolite implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accession;
    private String creation_date;
    private String kegg_id;
    private String pubchem_compound_id;
    private String chebi_id;
    private String name;
    private String formula;
    private double mass;
    private double averageMass;
    private String iupac_name;
    private String traditional_iupac;
    private String cas_number;
    private String smiles;
    private String inchi;
    private String inchikey;
    private String direct_parent;
    private String kingdom;
    private String super_class;
    private String chemical_class;
    private String sub_class;
    private String molecularFramework;
    private List<HmdbPathway> pathways;

    private List<String> synonyms;
    private List<String> biofluid_locations;
    private List<String> tissue_locations;

    public HmdbMetabolite() {

    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public String getKegg_id() {
        return kegg_id;
    }

    public void setKegg_id(String kegg_id) {
        this.kegg_id = kegg_id;
    }

    public String getPubchem_compound_id() {
        return pubchem_compound_id;
    }

    public void setPubchem_compound_id(String pubchem_compound_id) {
        this.pubchem_compound_id = pubchem_compound_id;
    }

    public String getChebi_id() {
        return chebi_id;
    }

    public void setChebi_id(String chebi_id) {
        this.chebi_id = chebi_id;
    }

    public String getAcession() {
        return this.accession;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getMass() {
        return this.mass;
    }

    public double getAverageMass() {
        return averageMass;
    }

    public void setAverageMass(double averageMass) {
        this.averageMass = averageMass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getIupac_name() {
        return iupac_name;
    }

    public void setIupac_name(String iupac_name) {
        this.iupac_name = iupac_name;
    }

    public String getTraditional_iupac() {
        return traditional_iupac;
    }

    public void setTraditional_iupac(String traditional_iupac) {
        this.traditional_iupac = traditional_iupac;
    }

    public String getCas_number() {
        return cas_number;
    }

    public void setCas_number(String cas_number) {
        this.cas_number = cas_number;
    }

    public String getSmiles() {
        return smiles;
    }

    public void setSmiles(String smiles) {
        this.smiles = smiles;
    }

    public String getInchi() {
        return inchi;
    }

    public void setInchi(String inchi) {
        this.inchi = inchi;
    }

    public String getInchikey() {
        return inchikey;
    }

    public void setInchikey(String inchikey) {
        this.inchikey = inchikey;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getFormula() {
        return formula;
    }

    public String getDirect_parent() {
        return direct_parent;
    }

    public void setDirect_parent(String direct_parent) {
        this.direct_parent = direct_parent;
    }

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }

    public String getSuper_class() {
        return super_class;
    }

    public void setSuper_class(String super_class) {
        this.super_class = super_class;
    }

    public String getChemical_class() {
        return chemical_class;
    }

    public void setChemical_class(String chemical_class) {
        this.chemical_class = chemical_class;
    }

    public String getSub_class() {
        return sub_class;
    }

    public void setSub_class(String sub_class) {
        this.sub_class = sub_class;
    }

    public String getMolecularFramework() {
        return molecularFramework;
    }

    public List<HmdbPathway> getPathways() {
        return pathways;
    }

    public void initializePathways() {
        this.pathways = new ArrayList<HmdbPathway>();

    }

    public void setPathways(HmdbPathway pathway) {
        this.pathways.add(pathway);

    }

    public void nullPathways() {
        this.pathways = null;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void initializeSynonyms() {
        this.synonyms = new ArrayList<String>();
    }

    public void setSynonyms(String synonym) {
        this.synonyms.add(synonym);
    }

    public void nullSynonyms() {
        this.synonyms = null;
    }

    public void setMolecularFramework(String molecularFramework) {
        this.molecularFramework = molecularFramework;
    }

    public List<String> getBiofluid_locations() {
        return biofluid_locations;
    }

    public void initializeBiofluids() {
        this.biofluid_locations = new ArrayList<>();
    }

    public void setBiofluid_locations(String biofluid_location) {
        this.biofluid_locations.add(biofluid_location);
    }

    public List<String> getTissue_locations() {
        return tissue_locations;
    }

    public void initializeTissueLocations() {
        this.tissue_locations = new ArrayList<>();
    }

    public void setTissue_locations(String tissue_location) {
        this.tissue_locations.add(tissue_location);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getDirect_parent() + "\t");
        sb.append(getKingdom() + "\t");
        sb.append(getSuper_class() + "\t");
        sb.append(getChemical_class() + "\t");
        sb.append(getSub_class() + "\t");
        sb.append(getMolecularFramework() + "\t");
        sb.append(getAcession() + "\t");
        sb.append(getName() + "\t");
        sb.append(getFormula() + "\t");
        sb.append(getMass() + "\t");
        sb.append(getAverageMass() + "\t");
        sb.append(getIupac_name() + "\t");
        sb.append(getInchikey() + "\t");
        sb.append(getCas_number() + "\t");
        sb.append(getSmiles());
        return sb.toString();
    }

}
