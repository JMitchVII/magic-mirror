package au.gov.nla.rms.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import au.gov.nla.rms.service.CopyrightStatusReturn;
import au.gov.nla.rms.service.DateContext;
import au.gov.nla.rms.service.businessrules.Work;

/**
 * Assert various things about the state we ended up in
 * 
 * @author mharriso
 * 
 */
public class CopyrightStatusAssertions {

    public static class CoreTestData {
        public Integer currentYear = null;
        public String expectedCopyrightStatusReason = null;;
        public String marcRecordFilename = null;;
    }

    private CoreTestData data;
    private CopyrightStatusReturn csr;

    public CopyrightStatusAssertions(CoreTestData data) {
        this.data = data;
    }

    public CopyrightStatusReturn copyrightStatusReturn() {
        return csr;
    }

    public void assertCopyrightExpired() {
        runTests();

        String copyrightStatus = csr.get(CopyrightStatusKeys.COPYRIGHT_STATUS_KEY);
        assertEquals("Copyright status is expired as expected", Work.COPYRIGHT_EXPIRED, copyrightStatus);

        assertReason();
    }

    public void assertInCopyright() {
        runTests();

        String copyrightStatus = csr.get(CopyrightStatusKeys.COPYRIGHT_STATUS_KEY);
        assertEquals("Copyright status is in copyright as expected", Work.IN_COPYRIGHT, copyrightStatus);

        assertReason();
    }

    public void assertCopyrightUndetermined() {
        runTests();

        String copyrightStatus = csr.get(CopyrightStatusKeys.COPYRIGHT_STATUS_KEY);
        assertEquals("Copyright status is undetermined as expected", Work.COPYRIGHT_UNDETERMINED, copyrightStatus);

        assertReason();
    }

    private void runTests() {
        if (data.currentYear != null) {
            DateContext dateContext = new TestDateContext(data.currentYear);
            csr = CopyrightStatusRunner.run(data.marcRecordFilename, dateContext);
        } else {
            csr = CopyrightStatusRunner.run(data.marcRecordFilename);
        }
        assertNotNull("Got a CopyrightStatusReturn", csr);
    }

    private void assertReason() {
        String actualReason = csr.get(CopyrightStatusKeys.COPYRIGHT_STATUS_REASON_KEY);
        assertEquals("Copyright status reason is expected message", data.expectedCopyrightStatusReason, actualReason);
    }

}
