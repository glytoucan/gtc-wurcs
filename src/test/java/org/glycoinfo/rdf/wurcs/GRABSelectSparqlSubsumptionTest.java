package org.glycoinfo.rdf.wurcs;

import static org.junit.Assert.*;

import java.util.Date;

import org.glycoinfo.rdf.SelectSparql;
import org.glycoinfo.rdf.SelectSparqlBean;
import org.glycoinfo.rdf.dao.SparqlEntity;
import org.glycoinfo.rdf.glycan.GlycoSequenceSelectSparql;
import org.glycoinfo.rdf.glycan.Saccharide;
import org.glycoinfo.rdf.wurcs.GRABSequenceSelectSparqlHasTopology;
import org.glycoinfo.rdf.wurcs.GRABSequenceSelectSparqlMotif;
import org.glycoinfo.rdf.wurcs.GRABSequenceSelectSparqlSubsumedBy;
import org.glycoinfo.rdf.wurcs.GRABSequenceSelectSparqlSubsumes;
import org.glycoinfo.rdf.wurcs.GRABSequenceSelectSparqlTopologyBy;
import org.glycoinfo.rdf.wurcs.GlycosidicTopology;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.research.ws.wadl.Application;

public class GRABSelectSparqlSubsumptionTest {
	public static Logger logger = (Logger) LoggerFactory
			.getLogger(GRABSelectSparqlSubsumptionTest.class);

	@Test
	public void jsonSerializationTest() throws Exception {
	    GRABSequenceSelectSparqlHasTopology dp = new GRABSequenceSelectSparqlHasTopology(); 
	    GRABSequenceSelectSparqlTopologyBy dpb = new GRABSequenceSelectSparqlTopologyBy();
	    GRABSequenceSelectSparqlSubsumedBy gdp = new GRABSequenceSelectSparqlSubsumedBy();
	    GRABSequenceSelectSparqlSubsumes gdpb = new GRABSequenceSelectSparqlSubsumes();
	    
	    SparqlEntity sparqlentity_1 = new SparqlEntity();
	    SparqlEntity sparqlentity_2 = new SparqlEntity();
	    SparqlEntity sparqlentity_3 = new SparqlEntity();
	    SparqlEntity sparqlentity_4 = new SparqlEntity();
	    
//	    sparqlentity.setValue(Saccharide.PrimaryId, "G000TEST");
	    sparqlentity_1.setValue(GlycosidicTopology.PrimaryId_1, "G63977XF");
	    dp.setSparqlEntity(sparqlentity_1);
	    
	    sparqlentity_2.setValue(GlycosidicTopology.PrimaryId_2, "G63977XF");
	    dpb.setSparqlEntity(sparqlentity_2);
	    
	    sparqlentity_3.setValue(GlycosidicTopology.PrimaryId_1, "G63977XF");
	    gdpb.setSparqlEntity(sparqlentity_1);
	    
	    sparqlentity_4.setValue(GlycosidicTopology.PrimaryId_2, "G63977XF");
	    gdp.setSparqlEntity(sparqlentity_2);

	     logger.debug(dp.getSparql());
	     logger.debug("\n");
	     logger.debug(dpb.getSparql());
	     logger.debug("\n");
	     logger.debug(gdp.getSparql());
	     logger.debug("\n");
	     logger.debug(gdpb.getSparql());
	     
//	     ObjectMapper mapper = new ObjectMapper();
//	     // {"glycanId":0,"accessionNumber":"G001234","dateEntered":"1970-01-01T00:00:00Z","structure":"structureString","structureLength":null,"mass":123.0,"motifs":null,"contributor":{"userId":1,"userName":"aoki","loginId":"aokinobu","email":"support@glytoucan.org","active":true,"validated":false,"affiliation":"Soka University","dateRegistered":"1970-01-01T00:00:00Z","lastLoggedIn":"1970-01-01T00:00:00Z","roles":null}}
//	     logger.debug(mapper.writeValueAsString(g));
//	     assertEquals("{\"glycanId\":0,\"accessionNumber\":\"G001234\",\"dateEntered\":\"1970-01-01T00:00:00Z\",\"structure\":\"structureString\",\"structureLength\":null,\"mass\":123.0,\"motifs\":null,\"contributor\":{\"userId\":1,\"userName\":\"aoki\",\"loginId\":\"aokinobu\",\"email\":\"support@glytoucan.org\",\"active\":true,\"validated\":false,\"affiliation\":\"Soka University\",\"dateRegistered\":\"1970-01-01T00:00:00Z\",\"lastLoggedIn\":\"1970-01-01T00:00:00Z\",\"roles\":null}}", 
//	    		 mapper.writeValueAsString(g));
	}     
}
