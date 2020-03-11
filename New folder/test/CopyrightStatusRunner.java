package au.gov.nla.rms.service.test;

import au.gov.nla.rms.service.CopyrightStatus;
import au.gov.nla.rms.service.CopyrightStatusReturn;
import au.gov.nla.rms.service.DateContext;
import au.gov.nla.rms.service.util.MarcXML;

/**
 * Provides a method for running the copyright status tools and getting as
 * CopyrightStatusReturn object. The CopyrightStatusReturn object is what all
 * the unit tests use as data for comparing expected to actual results.
 * 
 * @author mharriso
 * 
 */
public class CopyrightStatusRunner {

    public static CopyrightStatusReturn run(String recordFile) {
        return run(recordFile, new CopyrightStatus());
    }

    public static CopyrightStatusReturn run(String recordFile, DateContext dateContext) {
        return run(recordFile, new CopyrightStatus(dateContext));
    }

    private static CopyrightStatusReturn run(String recordFile, CopyrightStatus cs) {
        CopyrightStatusReturn ret = null;
        try {
            String xml = MarcRecordLoader.loadMARCXMLFromResource(recordFile);
            MarcXML marc = new MarcXML(xml);
            ret = cs.calculateWithMarcXML(marc);
        } catch (Exception e) {
            System.out.println("Anonymous test failed: " + e);
        }
        return ret;
    }
}
