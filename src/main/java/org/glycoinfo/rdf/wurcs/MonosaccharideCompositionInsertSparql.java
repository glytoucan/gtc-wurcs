package org.glycoinfo.rdf.wurcs;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.glycoinfo.rdf.InsertSparql;
import org.glycoinfo.rdf.InsertSparqlBean;
import org.glycoinfo.rdf.UriProvider;
import org.glycoinfo.rdf.glycan.GlycoSequenceInsertSparql;
import org.glycoinfo.rdf.glycan.SaccharideUtil;

import scala.annotation.meta.setter;

/**
 * 
 * Insert Subsumption data of a glycosidic composition.  
 * 
 * @prefix rocs: <http://www.glycoinfo.org/glyco/owl/relation#> .
 * 
 * <http://rdf.glycoinfo.org/glycan/{Accession number 1}>
 *	rocs:has_composition <http://rdf.glycoinfo.org/glycan/{Accession number 2}> .
 *
 * <http://rdf.glycoinfo.org/glycan/{Accession number 2}>
 *	a rocs:Monosaccharide_composition ;
 *
 * @author tokunaga
 *
 */
public class MonosaccharideCompositionInsertSparql extends InsertSparqlBean implements GlycosidicTopology, UriProvider {

	String level_type = "a Monosaccharide_composition";
	String has_composition = "rocs:has_composition";
	private String m_PrimaryId = null;

	void init() {
		this.prefix="PREFIX rocs: <http://www.glycoinfo.org/glyco/owl/relation#>\n";
	}
	public MonosaccharideCompositionInsertSparql() {
		init();
	}
	
	public MonosaccharideCompositionInsertSparql(GlycoSequenceInsertSparql sequence ) {
		init();
		ArrayList<InsertSparql> list = new ArrayList<InsertSparql>();
		list.add(sequence);
		addRelated(list);
	}
	
	public String getInsert() {
		if (StringUtils.isNotBlank(getSparqlEntity().getValue(PrimaryId_1)) && StringUtils.isNotBlank(getSparqlEntity().getValue(PrimaryId_2))) {
			this.insert = "<http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(PrimaryId_1) +">"
					+ " rocs:has_composition <http://rdf.glycoinfo.org/glycan/"+ getSparqlEntity().getValue(PrimaryId_2) +"> .\n"
					+ "<http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(PrimaryId_2) +">"
					+ " a rocs:Monosaccharide_composition .\n";
		}
		return this.insert;
	}

	public String getSaccharideURI() {
		return "<" + getUri() + ">";
	}

	@Override
	public String getUri() {
		return SaccharideUtil.getURI(m_PrimaryId); 
	}
	
}