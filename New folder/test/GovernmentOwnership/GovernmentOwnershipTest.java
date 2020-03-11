package au.gov.nla.rms.service.test.GovernmentOwnership;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.gov.nla.rms.service.CopyrightStatusReturn;
import au.gov.nla.rms.service.test.CopyrightStatusKeys;
import au.gov.nla.rms.service.test.CopyrightStatusRunner;

/**
 * 
 * MARC Implementation:
 * http://ourweb.nla.gov.au/apps/wiki/display/spri/Determine+creator+type
 * 
 * @author mharriso
 * 
 */
public class GovernmentOwnershipTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private String recordFileFullPath(String leaf) {
        return "marcXML/au/gov/nla/rms/service/test/GovernmentOwnership/GovernmentOwnershipTest/" + leaf;
    }

    @Test
    public void emptyThirdCharacterPlaceCode() {
        String recordFile = recordFileFullPath("emptyThirdCharacterPlaceCode.xml");
        CopyrightStatusReturn csr = CopyrightStatusRunner.run(recordFile);
        assertNotNull("Got a CopyrightStatusReturn", csr);

        String copyrightStatus = csr.get(CopyrightStatusKeys.GOVERNMENT_OWNERSHIP_KEY);
        assertEquals("Government ownership is expected message", "Australia - Autonomous or semi-autonomous", copyrightStatus);
    }

    @Test
    public void threeCharacterPlaceCode() {
        String recordFile = recordFileFullPath("threeCharacterPlaceCode.xml");
        CopyrightStatusReturn csr = CopyrightStatusRunner.run(recordFile);
        assertNotNull("Got a CopyrightStatusReturn", csr);

        String copyrightStatus = csr.get(CopyrightStatusKeys.GOVERNMENT_OWNERSHIP_KEY);
        assertEquals("Government ownership is expected message", "Queensland - Autonomous or semi-autonomous", copyrightStatus);
    }

    /**
     * name in the 100 field and nothing on the government ownership fields
     * Other fields are present to give the work a date and a type. These are
     * needed to satisfy the java code.
     */
    @Test
    public void notGovernmentOwned() {
        String recordFile = recordFileFullPath("notGovernmentOwned.xml");
        CopyrightStatusReturn csr = CopyrightStatusRunner.run(recordFile);
        assertNotNull("Got a CopyrightStatusReturn", csr);

        String copyrightStatus = csr.get(CopyrightStatusKeys.GOVERNMENT_OWNERSHIP_KEY);
        assertEquals("Government ownership is expected message", "No Government Copyright Ownership", copyrightStatus);
    }
}
