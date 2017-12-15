package org.glycoinfo.rdf.wurcs;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glycoinfo.batch.glyconvert.ConvertSelectSparql;
import org.glycoinfo.batch.glyconvert.GlyConvertSparql;
import org.glycoinfo.convert.GlyConvert;
import org.glycoinfo.rdf.SelectSparqlBean;
import org.glycoinfo.rdf.glycan.Saccharide;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

/**
 * 
 * A class used to retrieve the glycan sequences that do not have a Monosaccharide composition with linkage.
 * 
 * @author shinmachi
 *  
 */
public class CompositionWithLinkageConvertSelectSparql extends SelectSparqlBean implements InitializingBean {
  public static final String SaccharideURI = Saccharide.URI;
  public static final String Sequence = "Sequence";
  public static final String GlycanSequenceURI = "GlycanSequenceURI";
  public static final String AccessionNumber = Saccharide.PrimaryId;
  protected Log logger = LogFactory.getLog(getClass());

  String glycanUri;

  public CompositionWithLinkageConvertSelectSparql() {
    super();
    this.prefix = "PREFIX glycan: <http://purl.jp/bio/12/glyco/glycan#>\n"
        + "PREFIX glytoucan:  <http://www.glytoucan.org/glyco/owl/glytoucan#>\n"
        + "PREFIX rocs: <http://www.glycoinfo.org/glyco/owl/relation#>\n";
    this.select = "DISTINCT ?" + SaccharideURI + " ?" + AccessionNumber + " ?" + Sequence + "\n";
    this.from = "FROM <http://rdf.glytoucan.org/core>\n" 
    		+ "FROM <http://rdf.glytoucan.org/sequence/wurcs>\n"
    		+ "FROM <http://rdf.glytoucan.org/topology> \n" 
    		+ "FROM <http://rdf.glytoucan.org/compositionwithlinkage> \n";
  }

  /*
   * (non-Javadoc)
   * @see org.glycoinfo.rdf.SelectSparqlBean#getWhere()
   */
  public String getWhere() {
    String where = "?" + SaccharideURI + " a rocs:Glycosidic_topology .\n"
        + "?" + SaccharideURI + " glytoucan:has_primary_id ?" + AccessionNumber + " .\n"
    	+ "?" + SaccharideURI + " glycan:has_glycosequence ?" + GlycanSequenceURI + " .\n"
        + "?" + GlycanSequenceURI + " glycan:has_sequence ?Sequence .\n" 
    	+ "?" + GlycanSequenceURI + " glycan:in_carbohydrate_format glycan:carbohydrate_format_wurcs .\n";
    if (StringUtils.isNotBlank(getSparqlEntity().getValue(GlyConvertSparql.DoNotFilter)))
      return where;
    else
      return where + getFilter();
  }

  /**
   * 
   * The filter removes any non composition-related sequences.
   * 
   * @return
   */
  public String getFilter() {
    return "FILTER NOT EXISTS {\n?" + Saccharide.URI + " rocs:has_composition_with_linkage ?existingseq .\n }\n"
         + "FILTER NOT EXISTS {\n?" + Saccharide.URI + " glytoucan:has_primary_id \"G45005ZF\" .\n}"
         + "FILTER NOT EXISTS {\n?" + Saccharide.URI + " glytoucan:has_primary_id \"G74606YC\" .\n}"
         ;
  }
}
