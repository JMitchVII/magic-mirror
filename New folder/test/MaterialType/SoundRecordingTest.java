package au.gov.nla.rms.service.test.MaterialType;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.gov.nla.rms.service.CopyrightStatusReturn;
import au.gov.nla.rms.service.businessrules.Work;
import au.gov.nla.rms.service.test.CommonConditionVerifyer;
import au.gov.nla.rms.service.test.CopyrightStatusAssertions;
import au.gov.nla.rms.service.test.CopyrightStatusKeys;

/*@formatter:off*/
/**
 * http://ourweb.nla.gov.au/apps/wiki/display/spri/Sound+recordings
 * 
 * In order to set the type, leader (000 field) character position 6 contains
 * 'i' http://ourweb.nla.gov.au/apps/wiki/display/spri/Determine+material+type
 * 
 * Condition 1: is the work owned by the Government?
 * 
 * Condition 2: Was the work made before 1 January 1955?
 * 
 * Condition 3: Current year after year first published + 70 years?
 * 
 * Condition 4: Current year after year first published + 50 years?
 * 
 * State 1: Copyright expired
 * 
 * State 2: In Copyright
 * 
 * C1(n) -> C2(n) -> C3(n) -> S2 (inCopyrightPath1) C1(n) -> C2(n) -> C3(y) ->
 * S1 (copyrightExpiredPath1) C1(n) -> C2(y) -> S1 (copyrightExpiredPath2) C1(y)
 * -> C4(n) -> S2 (inCopyrightPath2) C1(y) -> C4(y) -> S1
 * (copyrightExpiredPath3)
 * 
 * 
 * MARC Implementation C1 see CC1
 * 
 * C2 http://ourweb.nla.gov.au/apps/wiki/display/spri/Determine+creation+or+
 * publication+date
 * 
 * C2(n) 008/6 contains 'e' 008/7-10 contains '1956'
 * 
 * C2(y) 008/6 contains 'e' 008/7-10 contains '1954'
 * 
 * C3(y|n) 260/$a must contain a value so that the item is considered to be
 * published.
 * 
 * C4(y|n) 260/$a must contain a value so that the item is considered to be
 * published.
 * 
 * @author mharriso
 * 
 */
/* @formatter:on */
public class SoundRecordingTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private String recordFileFullPath(String leaf) {
        return "marcXML/au/gov/nla/rms/service/test/MaterialType/SoundRecordingTest/" + leaf;
    }

    @Test
    public void inCopyrightPath1() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 1957;
        data.expectedCopyrightStatusReason = "Until 2026 [Created/Published Date + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("inCopyrightPath1.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertInCopyright();

        CopyrightStatusReturn csr = csa.copyrightStatusReturn();

        // Verify we followed the path expected
        CommonConditionVerifyer.verifyGovernmentOwnership(csr, false);
        verifyCondition2(csr, false);
    }

    @Test
    public void copyrightExpiredPath1() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2027;
        data.expectedCopyrightStatusReason = "Since 2026 [Created/Published Date + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath1.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();

        CopyrightStatusReturn csr = csa.copyrightStatusReturn();

        // Verify we followed the path expected
        CommonConditionVerifyer.verifyGovernmentOwnership(csr, false);
        verifyCondition2(csr, false);
    }

    @Test
    public void copyrightExpiredPath2() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2027;
        data.expectedCopyrightStatusReason = Work.CREATED_PUBLICATION_DATE_BEFORE_1955;
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath2.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();

        CopyrightStatusReturn csr = csa.copyrightStatusReturn();

        // Verify we followed the path expected
        CommonConditionVerifyer.verifyGovernmentOwnership(csr, false);
        verifyCondition2(csr, true);
    }

    @Test
    public void inCopyrightPath2() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 1957;
        data.expectedCopyrightStatusReason = "Until 2006 [Created/Published Date + 50 Years]";
        data.marcRecordFilename = recordFileFullPath("inCopyrightPath2.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertInCopyright();

        CopyrightStatusReturn csr = csa.copyrightStatusReturn();

        // Verify we followed the path expected
        CommonConditionVerifyer.verifyGovernmentOwnership(csr, true);
    }

    @Test
    public void copyrightExpiredPath3() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2007;
        data.expectedCopyrightStatusReason = "Since 2006 [Created/Published Date + 50 Years]";
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath3.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();

        CopyrightStatusReturn csr = csa.copyrightStatusReturn();

        // Verify we followed the path expected
        CommonConditionVerifyer.verifyGovernmentOwnership(csr, true);
    }

    private void verifyCondition2(CopyrightStatusReturn csr, Boolean expectedValue) {
        /*
         * FIXME: Creation and publication are mixed up in the code and the wiki
         * documentation. Only one of them will be reported in the
         * CopyrightStatusReturn. This is a problem when one rule talks about
         * creation date and another talks about publication date. We are unable
         * to determine which date is which from a CopyrightStatusReturn.
         */
        String creationDate = csr.get(CopyrightStatusKeys.PUBLICATION_DATE_KEY);
        Boolean haveCreationDate = false;
        int creationDateInt = 0;
        try {
            creationDateInt = Integer.parseInt(creationDate);
            haveCreationDate = true;
        } catch (NumberFormatException e) {

        }
        assertTrue("Able to find a creation date", haveCreationDate);
        if (expectedValue) {
            assertTrue("Creation date was before 1955", creationDateInt < 1955);
        } else {
            assertTrue("Creation date was not before 1955: (" + creationDate + ")", creationDateInt >= 1955);
        }
    }
}
