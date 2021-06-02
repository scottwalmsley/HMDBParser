# HMDBParser
> *Copyright 2018,2019, 2020, 2021 [Scott Walmsley](https://scottwalmsley.github.io)*
<br>A parser for the Human Metabolome Database (xml).

## Background
The Human Metabolome Database (IHMDB) (http://www.hmdb.ca/) is a online,
downloadable database of metabolites that have been observered or are
considered observable in humans. This Java library was developed to facilitate
extraction of key bits of information from HMDB and to use that information
to build a local graph network for computational modelling. HMDBParser is
part of a larger personal project, Metaboloic Network Amalgamation, and
the HMDB metabolites are used as an underlay to chemical and metabolic
network analysis derived from experimental data.

### *Usage*
The HMDBParser is meant to be used as a .jar library.

### *Origin*
HMDBParser is provided free of charge for academic use.

### *License*
Apache 2.0

### *Requirements*
###### *Java version 8 update 161*
HMDBParser needs the 'hmdb_metabolites.xml (v4.0)' file. This file can be
downloaded from http://www.hmdb.ca/downloads.

## Components
### *1. HMDBParser*
This class contains contains Main for example usage of reading the HMDB.
The data is serialized for later deserialization in other functions.

### *2. HmdbFile*
This class is the primary storage - object class for information that hasbeen parsed from the hmdb_metabolites.xml file.

### *3. HmdbMetabolite*
The object class for the HMDB metabolites.

### *4. HmdbPathway*
The object class for extracting and storing pathway information from the
HMDB metabolite entries.

## Specific usage examples

#### *General Usage:*
```java

 // Instantiate a new hmdbFile class;
 HmdbFile hmdbFile = new HmdbFile();

 // Set up a xml parser factory
 SAXParserFactory spfac = SAXParserFactory.newInstance();
 SAXParser sp = spfac.newSAXParser();

 // Parse the hmdb_metabolites.xml file.
 sp.parse("hmdb_metabolites.xml", hmdbFile);

 // Extract the metabolites from the parser.
 List<HmdbMetabolite> met = hmdbFile.getMetaboliteList();
 ```


### *Serialization*
The HMDB List of HmdbMetabolite objects can be serialized through the
HmdbFile class function call. This creates a serialized bytecode object that
is ~16 times smaller than the originating xml file.  Within the HmdbPathway
and HmdbMetabolite classes is an important serialization UID:
```java
 private static final long serialVersionUID = 1L;
 ```
..it is important that this is appropriately used in the context of your
usage or a NoClassDef exception will occur, either on the same machine or
between machines.  It has been set up appropriately in this repository
and works as tested.   Examples of serialization and de-serialization are
as follows:

##### *Serialization*
```java
 // Serialize the HMDB metabolites list.
 hmdbFile.serializeData("hmdbMetabolites.ser", met);
```

##### *Deserialization*
```java
 // Instantiate a new HmdbFile class:
 HmdbFile hmdbFile = new HmdbFile();

 // Deserialization of a list of HMDB metabolites.
 List<HmdbMetabolite> listOfMetabolites = hmdbFile.deserializeData("hmdbMetabolites.ser");
```



