package au.gov.nla.rms.service.test.MaterialType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.gov.nla.rms.service.businessrules.Work;
import au.gov.nla.rms.service.test.CopyrightStatusAssertions;

/*@formatter:off*/
/**
 * Condition 1: Is the Literary, dramatic or Musical work published?
 * 
 * Condition 2: Does the Government have copyright ownership of the work?
 * 
 * Condition 3: Is current year after year first published + 50 years?
 * (CC_AFTER_FIRST_PUBLISHED_PLUS_50_YEARS)
 * 
 * Condition 4: Is year first published before creator's year of death?
 * (CC_FIRST_PUBLISHED_BEFORE_CREATORS_DEATH)
 * 
 * Condition 5: Is year published before 1 January 1955?
 * (CC_PUBLISHED_BEFORE_1955)
 * 
 * Condition 6: Is creator's death date before 1 January 1955?
 * (CC_CREATORS_DEATH_BEFORE_1955)
 * 
 * Condition 7: Is current year after year first published + 70 years?
 * 
 * Condition 8: Is current year after year of creator's death + 70 years?
 * 
 * State 1: Copyright expired
 * 
 * State 2: In copyright
 * 
 * 
 * Variables V1: common::isPublished V2: common::isGovernmentOwned V3:
 * common::dateFirstPublished V4: common::craetorsYearOfDeath V5:
 * common::datePublished V6: Current year
 * 
 * MARC implementation
 * http://ourweb.nla.gov.au/apps/wiki/display/spri/Determine+material+type All
 * records are mapped to the LiteraryDramaticAndMusical type by setting 300 to
 * be 'map' Other MARC implementations are described in
 * au.gov.nla.rms.service.test.MaterialType package javadoc
 * 
 * C1(y) -> C2(y) -> C3(y) -> S1 (copyrightExpiredPath1) V1(y) V2(y) V3,
 * date=1900 V6, date=1951
 * 
 * C1(y) -> C2(y) -> C3(n) -> S2 (inCopyrightPath1) V1(y) V2(y) V3, date=1900
 * V6, date=1950
 * 
 * C1(y) -> C2(n) -> C4(y) -> C6(y) -> S1 (copyrightExpiredPath2) V1(y) V2(n)
 * V3, date=1900 V4, date=1950
 * 
 * C1(y) -> C2(n) -> C4(y) -> C6(n) -> C8(y) -> S1 (copyrightExpiredPath3) V1(y)
 * V2(n) V3, date=1900 V4, date=1956 V6 date=2027
 * 
 * C1(y) -> C2(n) -> C4(y) -> C6(n) -> C8(n) -> S2 (inCopyrightPath2) V1(y)
 * V2(n) V3, date=1900 V4, date=1956 V6 date=2026
 * 
 * C1(y) -> C2(n) -> C4(n) -> C5(y) -> S1 (copyrightExpiredPath4) V1(y) V2(n)
 * V3, date=1950 V4, date=1900
 * 
 * C1(y) -> C2(n) -> C4(n) -> C5(n) -> C7(y) -> S1 (copyrightExpiredPath5) V1(y)
 * V2(n) V3, date=1956 V4, date=1955 V6, 2027
 * 
 * C1(y) -> C2(n) -> C4(n) -> C5(n) -> C7(n) -> S2 (inCopyrightPath3) V1(y)
 * V2(n) V3, date=1956 V4, date=1955 V6, 2026
 * 
 * C1(n) -> S2 (inCopyrightPath4) V1(n)
 * 
 * @author mharriso
 * 
 */
/* @formatter:on */

public class LiteraryDramaticAndMusicalTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private String recordFileFullPath(String leaf) {
        return "marcXML/au/gov/nla/rms/service/test/MaterialType/LiteraryDramaticAndMusicalTest/" + leaf;
    }

    @Test
    public void copyrightExpiredPath1() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 1951;
        data.expectedCopyrightStatusReason = "Since 1950 [Created/Published Date + 50 Years]";
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath1.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void inCopyrightPath1() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 1950;
        data.expectedCopyrightStatusReason = "Until 1950 [Created/Published Date + 50 Years]";
        data.marcRecordFilename = recordFileFullPath("inCopyrightPath1.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertInCopyright();
    }

    @Test
    public void copyrightExpiredPath2() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.expectedCopyrightStatusReason = Work.CREATOR_DATE_OF_DEATH_BEFORE_1955;
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath2.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void copyrightExpiredPath3() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2027;
        data.expectedCopyrightStatusReason = "Since 2026 [Creator Date of Death + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath3.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void inCopyrightPath2() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2026;
        data.expectedCopyrightStatusReason = "Until 2026 [Creator Date of Death + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("inCopyrightPath2.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertInCopyright();
    }

    @Test
    public void copyrightExpiredPath4() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.expectedCopyrightStatusReason = Work.CREATED_PUBLICATION_DATE_BEFORE_1955;
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath4.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void copyrightExpiredPath5() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2027;
        data.expectedCopyrightStatusReason = "Since 2026 [Created/Published Date + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath5.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void inCopyrightPath3() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2026;
        data.expectedCopyrightStatusReason = "Until 2026 [Created/Published Date + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("inCopyrightPath3.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertInCopyright();
    }

    @Test
    public void inCopyrightPath4() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        // No reason stated for this case.
        data.expectedCopyrightStatusReason = "";
        data.marcRecordFilename = recordFileFullPath("inCopyrightPath4.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertInCopyright();
    }

}
