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

public class BatchSelectSparqlConfirmTest {
	public static Logger logger = (Logger) LoggerFactory
			.getLogger(BatchSelectSparqlConfirmTest.class);

	@Test
	public void jsonSerializationTest() throws Exception {
	    CompositionConvertSelectSparql dp = new CompositionConvertSelectSparql(); 
	    BaseCompositionConvertSelectSparql dps = new BaseCompositionConvertSelectSparql(); 
	    
	     logger.debug(dp.getSparql());
	     logger.debug("\n");
	     
	     logger.debug(dps.getSparql());
	     logger.debug("\n");
	     
	}     
}
