package au.gov.nla.rms.service.test.MaterialType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.gov.nla.rms.service.businessrules.Work;
import au.gov.nla.rms.service.test.CopyrightStatusAssertions;

/*@formatter:off*/
/**
 * http://ourweb.nla.gov.au/apps/wiki/display/spri/Artistic+works+and+computer+
 * software
 * 
 * In order to set the type, the 300 field will contain "watercolour"
 * http://ourweb.nla.gov.au/apps/wiki/display/spri/Determine+material+type
 * 
 * Condition 1: CC1
 * 
 * Condition 2: Is the creators death before 1955?
 * 
 * Condition 3: Is the current year after the creator's death date + 70 years?
 * 
 * Condition 4: Is current year after the year the work was made + 50 years?
 * 
 * State 1: Copyright expired
 * 
 * State 2: In Copyright
 * 
 * C1(n) -> C2(y) -> S1 (copyrightExpiredPath1) C1(n) -> C2(n) -> C3(y) -> S1
 * (copyrightExpiredPath2) C1(n) -> C2(n) -> C3(n) -> S2 (inCopyrightPath1)
 * C1(y) -> C4(n) -> S2 (inCopyrightPath2) C1(y) -> C4(y) -> S1
 * (copyrightExpiredPath3)
 * 
 * 
 * Condition A MARC Implementation:
 * http://ourweb.nla.gov.au/apps/wiki/display/spri/Determine+creator+type
 * 
 * C1 see CC1
 * 
 * C2 MARC Implementation:
 * http://ourweb.nla.gov.au/apps/wiki/display/spri/Determine
 * +creator%27s+life+dates and
 * au.gov.nla.rms.service.businessrules.Work.isCreatorAlive()
 * 
 * C2(n) 100/d contains 1955-1956
 * 
 * C2(y) 100/d contains 1953-1954
 * 
 * C3 MARC Implementation: C3(n) No extra MARC data needed
 * 
 * C3(y) No extra MARC data needed
 * 
 * C4 MARC Implementation:
 * http://ourweb.nla.gov.au/apps/wiki/display/spri/Determine
 * +creation+or+publication+date
 * 
 * C4(n) Control field 008/6 contains "e" Control field 008/7-10 contains "1954"
 * 
 * C4(y) Control field 008/6 contains "e" Control field 008/7-10 contains "1954"
 * 
 * @author mharriso
 * 
 */
/* @formatter:on */
public class ArtisticWorksAndComputerSoftwareTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private String recordFileFullPath(String leaf) {
        return "marcXML/au/gov/nla/rms/service/test/MaterialType/ArtisticWorksAndComputerSoftwareTest/" + leaf;
    }

    @Test
    public void copyrightExpiredPath1() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.expectedCopyrightStatusReason = Work.CREATOR_DATE_OF_DEATH_BEFORE_1955;
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath1.xml");
        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void copyrightExpiredPath2() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2027;
        data.expectedCopyrightStatusReason = "Since 2026 [Creator Date of Death + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath2.xml");
        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void inCopyrightPath1() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 1957;
        data.expectedCopyrightStatusReason = "Until 2026 [Creator Date of Death + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("inCopyrightPath1.xml");
        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertInCopyright();
    }

    @Test
    public void inCopyrightPath2() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 1955;
        data.expectedCopyrightStatusReason = "Until 2004 [Created/Published Date + 50 Years]";
        data.marcRecordFilename = recordFileFullPath("inCopyrightPath2.xml");
        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertInCopyright();
    }

    @Test
    public void copyrightExpiredPath3() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2005;
        data.expectedCopyrightStatusReason = "Since 2004 [Created/Published Date + 50 Years]";
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath3.xml");
        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }
}
