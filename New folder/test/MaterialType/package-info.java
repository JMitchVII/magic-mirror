package au.gov.nla.rms.service.test.MaterialType;

/**
 * Common Variable: isGovernmentOwned
 * Example text in wiki documentation: Does the Government have copyright ownership of the work?
 * 
 * MARC Implementation:
 * 
 * http://ourweb.nla.gov.au/apps/wiki/display/spri/Determine+creator+type

 * isGovernmentOwned(n)
 * Control field 008/28 is coded with '#'
 * 
 * isGovernmentOwned(y)
 * Control field 008/15-17 is coded with 'qea'
 * Control field 008/28 is coded with 'a'
 * 
 * Tests in the GovernmentOwnershipTest class test other values such as
 * Control field 008/15-17 coded as 'at '
 * 
 * 
 * Common variable: isPublished
 * Example text in wiki documentation: Is the /foo/ published?
 * 
 * MARC Implementation:
 * http://ourweb.nla.gov.au/apps/wiki/display/spri/Determine+creation+or+publication+date
 * and
 * au.gov.nla.rms.service.businessrules.Work.isPublished
 * isPublished(y)
 * Control field 008/6 contains "e"
 * Control field 008/7-10 contains the year, and this will vary based on other condition requirements
 * 260$a contains the literal "PLACE_OF_PLUBLICATION"
 * 
 * isPublished(n)
 * Leave out the 260$a record
 * 
 * 
 * Common Variable: dateFirstPublished
 * Example text in wiki documentation: Is the current year after year first published + 70 years?
 * 
 * MARC Implementation:
 * All isPublished fields are set
 * Control field 008/7-10 contains the year
 * 
 * Common Variable: datePublished
 * Example text in wiki documentation: Is year published before 1 January 1955?
 * MARC Implementation:
 * Currently the same as dateFirstPublished
 * 
 * 
 * Common Variable: craetorsYearOfDeath
 * Example text in wiki documentation:
 * 
 * 100$d is set to a year range "yyyy-yyyy"
 */
