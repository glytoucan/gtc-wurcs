package org.glycoinfo.rdf.wurcs;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glycoinfo.rdf.SelectSparql;
import org.glycoinfo.rdf.SelectSparqlBean;
import org.glycoinfo.rdf.SparqlException;
import org.glycoinfo.rdf.dao.SparqlDAO;
import org.glycoinfo.rdf.dao.SparqlEntity;
import org.glycoinfo.rdf.dao.virt.VirtSesameTransactionConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author shinmachi 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {CompositionWithLinkageConvertSelectSparqlTest.class, VirtSesameTransactionConfig.class })
@EnableAutoConfiguration
public class CompositionWithLinkageConvertSelectSparqlTest  {
  private static final Log logger = LogFactory.getLog(CompositionWithLinkageConvertSelectSparqlTest.class);
  
  @Bean
  public SelectSparql getSelectSparql() {
    return new CompositionWithLinkageConvertSelectSparql();
  }
  
  @Autowired
  SparqlDAO sparqlDAO;
  
  @Autowired
  SelectSparql selectBean;
  
  @Test
  @Transactional
  public void testSelectOutput() throws SparqlException {
    List<SparqlEntity> results = sparqlDAO.query(new SelectSparqlBean(selectBean.getSparql() + " LIMIT 10"));
    for (SparqlEntity sparqlEntity : results) {
      logger.debug(sparqlEntity.getData());
    }
  }
}
