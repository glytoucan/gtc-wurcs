package org.glycoinfo.rdf.wurcs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glycoinfo.rdf.SelectSparqlBean;
import org.glycoinfo.rdf.SparqlException;
import org.glycoinfo.rdf.glycan.Saccharide;
import org.springframework.stereotype.Component;

/**
 * 
 SelectSparql for Linkage Isomer
 * @author aoki
 *
 */

@Component
public class GRABSequenceSelectSparqlIsomer extends SelectSparqlBean {

	// public static final String SaccharideURI = Saccharide.URI;
	// public static final String id = "id";
	// public static final String GlycanSequenceURI = "GlycanSequenceURI";
	// public static final String AccessionNumber = Saccharide.PrimaryId;

	public static final String id = "id";

	// public static final String has_motif = Saccharide.URI;
	// public static final String has_linkage_isomer = "GlycanSequenceURI";
	// public static final String has_superstructure = Saccharide.PrimaryId;
	// public static final String has_substructure = Saccharide.PrimaryId;
	// public static final String hassumes = Saccharide.PrimaryId;
	// public static final String hassumes_by = Saccharide.PrimaryId;

	public GRABSequenceSelectSparqlIsomer(String sparql) {
		super(sparql);
	}

	public GRABSequenceSelectSparqlIsomer() {
		super();
		this.prefix = "PREFIX glycan: <http://purl.jp/bio/12/glyco/glycan#>\n"
				+ "PREFIX glytoucan:  <http://www.glytoucan.org/glyco/owl/glytoucan#>"
				+"PREFIX rogs: <http://http://www.glycoinfo.org/glyco/owl/relation#>";
		this.select = "DISTINCT ?id\n" + "?has_linkage_isomer ?isomer\n";
		this.from = "FROM <http://rdf.glytoucan.org/core>\nFROM <http://rdf.glytoucan.org/motif>\nFROM <http://rdf.glytoucan.org/isomer>";
//		this.from = "FROM <http://rdf.glycoinfo.org/glycan/browser/demo>";
	}

	public String getPrimaryId() {
		return "\"" + getSparqlEntity().getValue(Saccharide.PrimaryId) + "\"";
	}

	@Override
	public String getWhere() throws SparqlException {
		this.where = "VALUES ?id {" + getPrimaryId() + "}\n"
				+ "VALUES ?has_linkage_isomer { rogs:hasLinkageIsomer }\n"
				+ "?s glytoucan:has_primary_id ?id .\n"

				+ "OPTIONAL {\n" + "?s ?has_linkage_isomer ?hli .\n"
				+ "?hli glytoucan:has_primary_id ?isomer .\n" + "}\n"

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
	// public String getFilter() {
	// return "FILTER NOT EXISTS {\n" + "?" + SaccharideURI
	// + " glytoucan:has_derivatized_mass ?existingmass .\n}";
	// }

	// @Override
	// public void afterPropertiesSet() throws Exception {
	// Assert.state(getPrefix() != null, "A ident is required");
	// Assert.state(getSelect() != null, "A select is required");
	// }

}
