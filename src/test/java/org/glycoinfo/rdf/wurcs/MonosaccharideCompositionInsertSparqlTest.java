package org.glycoinfo.rdf.wurcs;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.glycoinfo.rdf.SparqlException;
import org.glycoinfo.rdf.dao.SparqlDAO;
import org.glycoinfo.rdf.dao.SparqlEntity;
import org.glycoinfo.rdf.dao.virt.VirtSesameTransactionConfig;
import org.glycoinfo.rdf.glycan.GlycoSequence;
import org.glycoinfo.rdf.wurcs.GRABSequenceSelectSparqlHasTopology;
import org.glycoinfo.rdf.wurcs.GlycosidicTopology;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Test the insert sparql Monosaccharide Composition With Linkage.
 * 
 * @author tokunaga, shinmachi
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MonosaccharideCompositionInsertSparqlTest.class, VirtSesameTransactionConfig.class })
@Configuration
@EnableAutoConfiguration
public class MonosaccharideCompositionInsertSparqlTest {

	public static Logger logger = (Logger) LoggerFactory
			.getLogger(MonosaccharideCompositionInsertSparqlTest.class);
	
	@Autowired
	SparqlDAO sparqlDAO;

	@Bean
	MonosaccharideCompositionInsertSparql getMonosaccharideCompositionInsertSparql() {
		MonosaccharideCompositionInsertSparql ins = new MonosaccharideCompositionInsertSparql();
		SparqlEntity sparqlentity = new SparqlEntity();
		sparqlentity.setValue(GlycosidicTopology.URI, "insertsacharideuri");
		sparqlentity.setValue(GlycoSequence.URI, "glycosequenceuri");
		sparqlentity.setValue(GlycosidicTopology.PrimaryId_1, "G13375XC");
		sparqlentity.setValue(GlycosidicTopology.PrimaryId_2, "G15843XU");
		ins.setSparqlEntity(sparqlentity);
		ins.setGraph("http://rdf.glytoucan.org/composition"); 
		return ins;
	}

	//先に空を出させて、インサートして、セレクトもう一回する	
	// Subsumes
	@Bean
	GRABSequenceSelectSparqlSubsumes getMonosaccharideCompositionSelectSparqlSubsumes() {
		GRABSequenceSelectSparqlSubsumes sis = new GRABSequenceSelectSparqlSubsumes();
		SparqlEntity sparqlentity = new SparqlEntity();
		sparqlentity.setValue(GlycosidicTopology.PrimaryId_1, "G15843XU");
		sis.setSparqlEntity(sparqlentity);
		return sis;
	}
	
	// Subsumed by
	@Bean
	GRABSequenceSelectSparqlSubsumedBy getMonosaccharideCompositionSelectSparqlSubsumedBy() {
		GRABSequenceSelectSparqlSubsumedBy sis = new GRABSequenceSelectSparqlSubsumedBy();
		SparqlEntity sparqlentity = new SparqlEntity();
		sparqlentity.setValue(GlycosidicTopology.PrimaryId_2, "G13375XC");
		sis.setSparqlEntity(sparqlentity);
		return sis;
	}

	
	@Test
	@Transactional
	public void insertSparql() throws SparqlException {
		// executive "monosaccharide composition with linkage insert" query
		sparqlDAO.insert(getMonosaccharideCompositionInsertSparql());

		// executive "subsumes" query
		List<SparqlEntity> list2 = sparqlDAO.query(getMonosaccharideCompositionSelectSparqlSubsumes());
		for (SparqlEntity sparqlEntity : list2) {
			String output2 = sparqlEntity.getValue("s");
		}

		// executive "subsumed by" query
		List<SparqlEntity> list = sparqlDAO.query(getMonosaccharideCompositionSelectSparqlSubsumedBy());
		for (SparqlEntity sparqlEntity : list) {
			String output = sparqlEntity.getValue("s");
		}

		
	}
	
	
/**	
	@Test
	public void testInsertSparql() throws SparqlException {
		logger.debug(getMonosaccharideCompositionInsertSparql().getSparql());
		
//		assertEquals(
//				"PREFIX rocs: <http://www.glycoinfo.org/glyco/owl/relation#>\n"
//				+ "INSERT DATA\n"
//				+ "{ GRAPH <http://rdf,glytoucan.org/topology>\n"
//				+ "{ <http://rdf.glycoinfo.org/glycan/G14728XI>\n"
//				+ " rocs:has_topology <http://rdf.glycoinfo.org/glycan/G24678II> .\n"
//				+ "<http://rdf.glycoinfo.org/glycan/L2primaryid>\n"
//				+ " a rocs:Glycosidic_topology .\n"
//				+ " }\n"
//				+ "}\n",
//				getGlycosidic_topologyInsertSparql().getSparql());
		
	}
	
	@Test
	@Transactional
	public void insertSparql() throws SparqlException {
		sparqlDAO.insert(getMonosaccharideCompositionInsertSparql());
		
		List<SparqlEntity> list = sparqlDAO.query(getMonosaccharideCompositionSelectSparql());
		for (SparqlEntity sparqlEntity : list) {
			String output = sparqlEntity.getValue("s");
			//Assert.assertEquals("http://purl.jp/bio/12/glyco/glycan#saccharide", output);
		}
	}

	@Test
	@Transactional
	public void insertSelectSparql() throws SparqlException {
		sparqlDAO.insert(getMonosaccharideCompositionInsertSparql());
		
		List<SparqlEntity> list = sparqlDAO.query(getMonosaccharideCompositionSelectSparql());
		if (list.iterator().hasNext()) {
			SparqlEntity se = list.iterator().next();
			if ("G24678II".equals(se.getValue("subsumedby_id")))
				Assert.assertTrue("pass",true);
		}
	}
	
	@Test
	@Transactional
	public void insertSelectSparqlSubsumedBy() throws SparqlException {
		sparqlDAO.insert(getMonosaccharideCompositionInsertSparql());
		
		List<SparqlEntity> list = sparqlDAO.query(getMonosaccharideCompositionSelectSparqlSubsumedBy());
		if (list.iterator().hasNext()) {
			SparqlEntity se = list.iterator().next();
			if ("G14728XI".equals(se.getValue("id")))
				Assert.assertTrue("pass",true);
		}
	}
	
	@Test
	 @Transactional
	public void selectSparql() throws SparqlException {
		GRABSequenceSelectSparqlSubsumes sss = getMonosaccharideCompositionSelectSparql();
		SparqlEntity se = sss.getSparqlEntity();
		se.setValue(GlycosidicTopology.PrimaryId_1, "G00031MO");
		
		sss.setSparqlEntity(se);
		
		List<SparqlEntity> list = sparqlDAO.query(sss);
		if (list.iterator().hasNext()) {
			se = list.iterator().next();
			logger.debug(se.getValue(GRABSequenceSelectSparqlHasTopology.SaccharideURI));
		}
	}
**/
	
}