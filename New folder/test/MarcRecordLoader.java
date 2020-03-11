package au.gov.nla.rms.service.test;

import java.io.InputStream;

/**
 * Loads a MARC record from disc.
 * 
 * @author mharriso
 * 
 */
public class MarcRecordLoader {

    /**
     * Provides the caller with the text of a MARCXML record
     * 
     * @param resourcePath
     * @return
     */
    public static String loadMARCXMLFromResource(String resourcePath) {
        String resource = null;
        try {
            InputStream resourceInputStream = au.gov.nla.rms.service.test.MarcRecordLoader.class.getClassLoader().getResourceAsStream(resourcePath);
            if (resourceInputStream != null) {
                resource = org.apache.commons.io.IOUtils.toString(resourceInputStream, "UTF-8");
            }
        } catch (Exception e) {
            // if (logger != null) {
            // logger.logStackTrace(e);
            // }
            System.out.println("Failed to load MARCRecord: " + e.getMessage());
        }
        if (resource == null) {
            System.out.println("Could not find the resource: " + resourcePath);
        }
        return resource;
    }
}
