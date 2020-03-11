package au.gov.nla.rms.service.test.AnonymousCreator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.gov.nla.rms.service.businessrules.Work;
import au.gov.nla.rms.service.test.CopyrightStatusAssertions;

/*@formatter:off*/
/**
 * http://ourweb.nla.gov.au/apps/wiki/display/spri/Anonymous+creator+rule
 * 
 * Condition 1: Does the work have one or more individual contributors or
 * creators?
 * 
 * Condition 2: Does the work have a government creator or contributor?
 * 
 * Condition 3: Published (or taken if photo) before 1 January 1955?
 * 
 * Condition 4: Current date after date published + 70 years?
 * 
 * State 1: In Copyright
 * 
 * State 2: Copyright expired
 * 
 * State 3: Follow rules for the material type
 * 
 * 
 * The paths we test here are:
 * 
 * C1(n) -> C2(n) -> C3(n) -> C4(n) -> S1 (inCopyright)
 * 
 * C1(n) -> C2(n) -> C3(n) -> C4(y) -> S2 (copyrightExpiredPath1)
 * 
 * C1(n) -> C2(n) -> C3(y) -> S2 (copyrightExpiredPath2)
 * 
 * The paths ending at S3 need more investigation
 * 
 * MARCXML records have been created to meet each of the conditions above. The
 * records contain comments about how data have been chosen to meet the required
 * conditions. The records have been created manually and contain the bare
 * minimum for these tests.
 * 
 * C1(n) MARC Implementation: Based on
 * au.gov.nla.rms.service.businessrules.Work.isAnonymousPublication(), no 100
 * and no 700 fields means the publication is anonymous
 * 
 * C2(n) MARC Implementation:
 * http://ourweb.nla.gov.au/apps/wiki/display/spri/Determine+creator+type
 * 
 * Control field 008/28 is coded with '#' because it satisfies the
 * documentation, the code and the standard. The documentation mentiosn several
 * conditions which are all ANDed toge. As such, the MARC implementation is
 * simplest by just making one condition false.
 * 
 * C3(n) MARC Implementation: http://ourweb.nla.gov.au/apps/wiki/display/spri/
 * Determine+creation+or+publication+date Control field 008/6 contains "e"
 * Control field 008/7-10 contains "1956"
 * 
 * C3(y) MARC Implementation: http://ourweb.nla.gov.au/apps/wiki/display/spri/
 * Determine+creation+or+publication+date Control field 008/6 contains "e"
 * Control field 008/7-10 contains "1954"
 * 
 * C4(n) MARC Implementation: Nothing extra is needed in the MARC data for this
 * rule. C3(y) and C3(n) both provide sufficient data to evaluate this rule. See
 * the au.gov.nla.rms.service.DateContext Interface that was created to help
 * with these tests.
 * 
 * C4(y) - same as C4(n)
 * 
 * S1 is evaluated by the code being tested.
 * 
 * S2 is evaluated by the code being tested.
 * 
 * <h1>Extra MARC data needed to support the code</h1> The wiki documentation
 * suggests that the Anonymous rule is independent of material type. The java
 * code requires that a record be mappable to a
 * au.gov.nla.rms.service.businessrules.Work subclass. We need to add some MARC
 * data to satisfy the code, not the documentation. The test records are mapped
 * to an ArtisticWork by setting the MARC 300 field, sub field "a" to contain
 * "watercolour" and no other test words. The subfield does not really matter
 * for the java code, but seems the most sensible place from a MARC perspective.
 * See: http://ourweb.nla.gov.au/apps/wiki/display/spri/Determine+material+type
 * and the java class au.gov.nla.rms.service.businessrules.MaterialType
 */
/* @formatter:on */
public class AnonymousCreatorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private String recordFileFullPath(String leaf) {
        return "marcXML/au/gov/nla/rms/service/test/AnonymousCreator/AnonymousCreatorTest/" + leaf;
    }

    @Test
    public void inCopyrightPath1() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2026;
        data.expectedCopyrightStatusReason = "Until 2026 [Created/Published Date + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("inCopyrightPath1.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertInCopyright();
    }

    @Test
    public void copyrightExpiredPath1() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2027;
        data.expectedCopyrightStatusReason = "Since 2026 [Created/Published Date + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath1.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void copyrightExpiredPath2() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.expectedCopyrightStatusReason = Work.CREATED_PUBLICATION_DATE_BEFORE_1955;
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath2.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }
}
