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
 * Test the insert sparql Monosaccharide Composition With Linkage
 * 
 * @author shinmachi
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MonosaccharideCompositionWithLinkageInsertSparqlTest.class, VirtSesameTransactionConfig.class })
@Configuration
@EnableAutoConfiguration
public class MonosaccharideCompositionWithLinkageInsertSparqlTest {

	public static Logger logger = (Logger) LoggerFactory
			.getLogger(MonosaccharideCompositionWithLinkageInsertSparqlTest.class);
	
	@Autowired
	SparqlDAO sparqlDAO;

	@Bean
	MonosaccharideCompositionWithLinkageInsertSparql getMonosaccharideCompositionWithLinkageInsertSparql() {
		 MonosaccharideCompositionWithLinkageInsertSparql ins = new MonosaccharideCompositionWithLinkageInsertSparql();
		SparqlEntity sparqlentity = new SparqlEntity();
		sparqlentity.setValue(GlycosidicTopology.URI, "insertsacharideuri");
		sparqlentity.setValue(GlycoSequence.URI, "glycosequenceuri");
		sparqlentity.setValue(GlycosidicTopology.PrimaryId_1, "G14728XI");
		sparqlentity.setValue(GlycosidicTopology.PrimaryId_2, "G24678II");
		ins.setSparqlEntity(sparqlentity);
		ins.setGraph("http://rdf.glytoucan.org/compositionwithlinkage"); 
		return ins;
	}

	// Subsumes
	@Bean
	GRABSequenceSelectSparqlSubsumes getMonosaccharideCompositionWithLinkageSelectSparqlSubsumes() {
		GRABSequenceSelectSparqlSubsumes sis = new GRABSequenceSelectSparqlSubsumes();
		SparqlEntity sparqlentity = new SparqlEntity();
		sparqlentity.setValue(GlycosidicTopology.PrimaryId_1, "G14728XI");
		sis.setSparqlEntity(sparqlentity);
		return sis;
	}

	// Subsumed by
	@Bean
	GRABSequenceSelectSparqlSubsumedBy getMonosaccharideCompositionWithLinageSelectSparqlSubsumedBy() {
		GRABSequenceSelectSparqlSubsumedBy sis = new GRABSequenceSelectSparqlSubsumedBy();
		SparqlEntity sparqlentity = new SparqlEntity();
		sparqlentity.setValue(GlycosidicTopology.PrimaryId_2, "G24678II");
//		sparqlentity.setValue(GlycosidicTopology.PrimaryId_2, "G15843XU");
		sis.setSparqlEntity(sparqlentity);
		return sis;
	}

	@Test
	@Transactional
	public void insertSparql() throws SparqlException {
		// executive "monosaccharide composition with linkage insert" query
		sparqlDAO.insert(getMonosaccharideCompositionWithLinkageInsertSparql());

		// executive "subsumed by" query
		List<SparqlEntity> list = sparqlDAO.query(getMonosaccharideCompositionWithLinageSelectSparqlSubsumedBy());
		for (SparqlEntity sparqlEntity : list) {
			String output = sparqlEntity.getValue("s");
		}

		// executive "subsumes" query
		List<SparqlEntity> list2 = sparqlDAO.query(getMonosaccharideCompositionWithLinkageSelectSparqlSubsumes());
		for (SparqlEntity sparqlEntity : list2) {
			String output2 = sparqlEntity.getValue("s");
		}
		
	}

}
