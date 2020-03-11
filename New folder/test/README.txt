These unit tests should map to the documentation in the wiki:
http://ourweb.nla.gov.au/apps/wiki/pages/viewpage.action?pageId=13601028

Problems with the code that limit the tests:
 
 - Only proximal causes of the copyright status determination are reported.
   More complete testing requires that the tool report the distal causes as well.
   The tool should report all intermediate decisions made in the 'flow chart'
   as well as exposing how the MARC data was mapped to the copyright law
   abstractions.
   
 - The java code does not distinguish between 'Date Published' and
   'Date First Published' and 'Date Created'. As a consequence, some conditions
   can't be evaluated. For example, in
   http://ourweb.nla.gov.au/apps/wiki/display/spri/Photographs
   the condition 'was the work created before 1 may 1969' can't be evaluated
   because 'date created' data does not really exist.
   
 Changes to the code that would make it more testable.
  - Split the business rules from the data acquisition
  - Split the data acquisition from MARC
  - Split MARC from XML
   