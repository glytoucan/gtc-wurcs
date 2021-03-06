package org.glycoinfo.rdf.wurcs;

import static org.junit.Assert.*;

import java.util.Date;

import org.glycoinfo.rdf.SelectSparql;
import org.glycoinfo.rdf.SelectSparqlBean;
import org.glycoinfo.rdf.dao.SparqlEntity;
import org.glycoinfo.rdf.glycan.GlycoSequenceSelectSparql;
import org.glycoinfo.rdf.glycan.Saccharide;
import org.glycoinfo.rdf.wurcs.GRABSequenceSelectSparqlMotif;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.research.ws.wadl.Application;

public class GRABSelectSparqlMotifTest {
	public static Logger logger = (Logger) LoggerFactory
			.getLogger(GRABSelectSparqlMotifTest.class);

	@Test
	public void jsonSerializationTest() throws Exception {
	    GRABSequenceSelectSparqlMotif dp = new GRABSequenceSelectSparqlMotif(); 

	    
	    SparqlEntity sparqlentity = new SparqlEntity();
	    sparqlentity.setValue(Saccharide.PrimaryId, "G000TEST");
//	    sparqlentity.setValue(Glycosidic_topology.PrimaryId_1, "GxxxxxxD");
	    dp.setSparqlEntity(sparqlentity);

	     logger.debug(dp.getSparql());
	     
//	     ObjectMapper mapper = new ObjectMapper();
//	     // {"glycanId":0,"accessionNumber":"G001234","dateEntered":"1970-01-01T00:00:00Z","structure":"structureString","structureLength":null,"mass":123.0,"motifs":null,"contributor":{"userId":1,"userName":"aoki","loginId":"aokinobu","email":"support@glytoucan.org","active":true,"validated":false,"affiliation":"Soka University","dateRegistered":"1970-01-01T00:00:00Z","lastLoggedIn":"1970-01-01T00:00:00Z","roles":null}}
//	     logger.debug(mapper.writeValueAsString(g));
//	     assertEquals("{\"glycanId\":0,\"accessionNumber\":\"G001234\",\"dateEntered\":\"1970-01-01T00:00:00Z\",\"structure\":\"structureString\",\"structureLength\":null,\"mass\":123.0,\"motifs\":null,\"contributor\":{\"userId\":1,\"userName\":\"aoki\",\"loginId\":\"aokinobu\",\"email\":\"support@glytoucan.org\",\"active\":true,\"validated\":false,\"affiliation\":\"Soka University\",\"dateRegistered\":\"1970-01-01T00:00:00Z\",\"lastLoggedIn\":\"1970-01-01T00:00:00Z\",\"roles\":null}}", 
//	    		 mapper.writeValueAsString(g));
	}     
}
