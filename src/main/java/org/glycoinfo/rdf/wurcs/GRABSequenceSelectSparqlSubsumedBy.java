package org.glycoinfo.rdf.wurcs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glycoinfo.rdf.SelectSparqlBean;
import org.glycoinfo.rdf.SparqlException;
import org.springframework.stereotype.Component;

/**
 * 
 *  GRAB graph selectSparql for subsumed by
 *  order by iupac literal
 * 
 * @author tokunaga, shinmachi
 *
 */

@Component
public class GRABSequenceSelectSparqlSubsumedBy extends SelectSparqlBean {
	
	public static final String SaccharideURI = GlycosidicTopology.URI;
	public static final String id = "id";

	public GRABSequenceSelectSparqlSubsumedBy(String sparql) {
		super(sparql);
	}

	public GRABSequenceSelectSparqlSubsumedBy() {
		super();
		this.prefix = "PREFIX glytoucan: <http://www.glytoucan.org/glyco/owl/glytoucan#>\n"
				+"PREFIX rocs: <http://www.glycoinfo.org/glyco/owl/relation#>\n"
				+ "PREFIX glycan: <http://purl.jp/bio/12/glyco/glycan#>\n";
		this.select = "DISTINCT ?id ?subsumedby_id\n";
		this.from = "FROM <http://rdf.glytoucan.org/core>\n"
				+ "FROM <http://rdf.glytoucan.org/topology>\n"
				+ "FROM <http://rdf.glytoucan.org/composition>\n"
				+ "FROM <http://rdf.glytoucan.org/compositionwithlinkage>\n"
				+ "FROM <http://rdf.glytoucan.org/basecompositionwithlinkage>\n"
				+ "FROM <http://rdf.glytoucan.org/basecomposition>\n"
				+ "FROM <http://rdf.glytoucan.org/sequence/iupac_extended>\n";
		this.orderby = "ORDER BY ?iupac \n";
	}

	public String getPrimaryId() {
		return "\"" + getSparqlEntity().getValue(GlycosidicTopology.PrimaryId_2) + "\"";
	}

	@Override
	public String getWhere() throws SparqlException {
		this.where = "VALUES ?subsumedby_id {" + getPrimaryId() + "}\n"
				+ "VALUES ?has_topology { rocs:has_topology }\n"
				+ "VALUES ?has_composition { rocs:has_composition }\n"
				+ "VALUES ?has_composition_with_linkage { rocs:has_composition_with_linkage }\n"
				+ "VALUES ?has_base_composition { rocs:has_base_composition }\n"
				+ "# Glycosidic_topology \n" 
				+ "OPTIONAL {\n" 
				+ "?s glytoucan:has_primary_id ?id .\n"
				+ "?s ?has_topology ?ht .\n"
				+ "?ht glytoucan:has_primary_id ?subsumedby_id .\n" 
				+ "?ht a rocs:Glycosidic_topology .\n" 
				+ "?s glycan:has_glycosequence ?seq .\n"
				+ "?seq glycan:has_sequence ?iupac .\n" + "}\n"
				+ "# Monosaccharide_composition_with_linkage \n"
				+ "OPTIONAL {\n" 
				+ "?s glytoucan:has_primary_id ?id .\n"
				+ "?s ?has_composition_with_linkage ?hcwl .\n"
				+ "?hcwl glytoucan:has_primary_id ?subsumedby_id .\n" 
				+ "?hcwl a rocs:Monosaccharide_composition_with_linkage .\n"
				+ "}\n"
				+ "# Monosaccharide_composition \n"
				+ "OPTIONAL {\n" 
				+ "?s glytoucan:has_primary_id ?id .\n"
				+ "?s ?has_composition ?hc .\n"
				+ "?hc glytoucan:has_primary_id ?subsumedby_id .\n" 
				+ "?hc a rocs:Monosaccharide_composition .\n"
//				+ "?s glycan:has_glycosequence ?seq .\n"
//				+ "?seq glycan:has_sequence ?iupac .\n" 
				+ "}\n"
				+ "# Base_composition_with_linkage \n"
				+ "OPTIONAL {\n" 
				+ "?s glytoucan:has_primary_id ?id .\n"
				+ "?s ?has_base_composition ?hbcwl .\n"
				+ "?hbcwl glytoucan:has_primary_id ?subsumedby_id .\n" 
				+ "?hbcwl a rocs:Base_composition_with_linkage .\n"
				+ "}\n"
				+ "# Base_composition \n"
				+ "OPTIONAL {\n" 
				+ "?s glytoucan:has_primary_id ?id .\n"
				+ "?s ?has_base_composition ?hbc .\n"
				+ "?hbc glytoucan:has_primary_id ?subsumedby_id .\n" 
				+ "?hbc a rocs:Base_composition .\n"
//				+ "?s glycan:has_glycosequence ?seq .\n"
//				+ "?seq glycan:has_sequence ?iupac .\n" 
				+ "}\n"
		;
		return where;
	}

	protected Log logger = LogFactory.getLog(getClass());

	String glycanUri;

	/**
	 * 
	 * the filter removes any sequences that already have a sequence in the
	 * GlyConvert.getTo() format.
	 * 
	 * @return
	 */

}
