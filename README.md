# gtc-wurcs
Management of WURCS RDF for GlyTouCan data
* Subsumption


## Monosaccharide Composition
```
GRAPH <http://rdf.glytoucan.org/composition>{
   <http://rdf.glycoinfo.org/glycan/G14728XI> rocs:has_composition <http://rdf.glycoinfo.org/glycan/G24678II> .
   <http://rdf.glycoinfo.org/glycan/G24678II> a rocs:Monosaccharide_composition .
 }

```

## SPARQL
endpoint: `https://ts.glytoucan.org/sparql`

```
PREFIX glytoucan: <http://www.glytoucan.org/glyco/owl/glytoucan#>
PREFIX rocs: <http://www.glycoinfo.org/glyco/owl/relation#>
PREFIX glycan: <http://purl.jp/bio/12/glyco/glycan#>

SELECT DISTINCT ?id ?subsumedby_id
FROM <http://rdf.glytoucan.org/core>
FROM <http://rdf.glytoucan.org/topology>
FROM <http://rdf.glytoucan.org/composition>
FROM <http://rdf.glytoucan.org/basecomposition>
FROM <http://rdf.glytoucan.org/sequence/iupac_extended>
WHERE {
VALUES ?subsumedby_id {"G24678II"}
VALUES ?has_topology { rocs:has_topology }
VALUES ?has_composition { rocs:has_composition }
VALUES ?has_base_composition { rocs:has_base_composition }
OPTIONAL {
?s glytoucan:has_primary_id ?id .
?s ?has_topology ?ht .
?ht glytoucan:has_primary_id ?subsumedby_id .
?ht a rocs:Glycosidic_topology .
?s glycan:has_glycosequence ?seq .
?seq glycan:has_sequence ?iupac .
}
OPTIONAL {
?s glytoucan:has_primary_id ?id .
?s ?has_composition ?hc .
?hc glytoucan:has_primary_id ?subsumedby_id .
?hc a rocs:Monosaccharide_composition .
}
OPTIONAL {
?s glytoucan:has_primary_id ?id .
?s ?has_base_composition ?hbc .
?hbc glytoucan:has_primary_id ?subsumedby_id .
?hbc a rocs:Base_composition .
}
} ORDER BY ?iupac
```   
