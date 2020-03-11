package au.gov.nla.rms.service.test.MaterialType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.gov.nla.rms.service.businessrules.Work;
import au.gov.nla.rms.service.test.CopyrightStatusAssertions;

/*@formatter:off*/
/**
 * Condition 1: Is the engraving published?
 * 
 * Condition 2: Does the Government have copyright ownership of the work?
 * 
 * Condition 3: Was the work made before May 1 1969?
 * 
 * Condition 4: Is the current year after year first made + 50 years?
 * 
 * Condition 5: Is the current year after year first published + 50 years?
 * (CC_AFTER_FIRST_PUBLISHED_PLUS_50_YEARS)
 * 
 * Condition 6: Is year published before year of creators death?
 * (CC_FIRST_PUBLISHED_BEFORE_CREATORS_DEATH)
 * 
 * Condition 7: Is year published before 1 January 1955?
 * (CC_PUBLISHED_BEFORE_1955)
 * 
 * Condition 8: Is the current year after year first published + 70 years?
 * (CC_AFTER_FIRST_PUBLISHED_PLUS_70_YEARS)
 * 
 * Condition 9: Is creator's death date before 1 January 1955?
 * (CC_CREATORS_DEATH_BEFORE_1955)
 * 
 * Condition 10: Is current year after year of creator's death + 70 years?
 * 
 * State 1: In copyright
 * 
 * State 2: Copyright expired
 * 
 * 
 * Variables in rules: V1: Is published? V2: Date of first publication V3: Date
 * work was made V4: Year of creator's death V5: Current Year V6: Government
 * owned
 * 
 * MARC Implementation
 * 
 * All records are mapped to the "Engraving" type by setting 300 to 'engraving'
 * FIXME: Why does the java code do: String marc_245b =
 * marcXML.getMarcSubfield("245", "b"); To check for an engraving type?
 * 
 * V2 V1 == true and Control field 008/7-10 contains the year
 * 
 * V3 FIXME - there is nothing in the java code that checks V3 and there is
 * nothing in the documentation to see how it should be checked. This variable
 * get mixed up with V2 in the java code
 * 
 * V4 100$d is set to a year range "yyyy-yyyy"
 * 
 * V5 Set in java test code below.
 * 
 * ====================================
 * 
 * C1(y) -> C2(y) -> C3(y) -> C4(y) -> S2 (copyrightExpiredPath1) V1(y),
 * year=1968 V3=1968 V5=2019
 * 
 * C1(y) -> C2(y) -> C3(y) -> C4(n) -> S1 (inCopyrightPath1) V1(y), year=1968
 * V3=1968 V5=2018
 * 
 * C1(y) -> C2(y) -> C3(n) -> C5(y) -> S2 (copyrightExpiredPath2) V1(y),
 * year=1970 V3=1970 V5=2021
 * 
 * C1(y) -> C2(y) -> C3(n) -> C5(n) -> S1 (inCopyrightPath2) V1(y), year=1970
 * V3=1970 V5=2020
 * 
 * C1(y) -> C2(n) -> C6(y) -> C9(y) -> S2 (copyrightExpiredPath3) V1(y),
 * year=1900 V4=1899-1954
 * 
 * C1(y) -> C2(n) -> C6(y) -> C9(n) -> C10(y) -> S2 (copyrightExpiredPath4)
 * V1(y), year=1954 V4=1900-1955 V5=2026
 * 
 * 
 * C1(y) -> C2(n) -> C6(y) -> C9(n) -> C10(n) -> S1 (inCopyrightPath3) V1(y),
 * year=1954 V4=1900-1955 V5=2024
 * 
 * 
 * C1(y) -> C2(n) -> C6(n) -> C7(y) -> S2 (copyrightExpiredPath5) V1(y),
 * year=1954 V4=1900-1953
 * 
 * 
 * C1(y) -> C2(n) -> C6(n) -> C7(n) -> C8(y) -> S2 (copyrightExpiredPath6)
 * V1(y), year=1955 V4=1900-1954 V5=2026
 * 
 * C1(y) -> C2(n) -> C6(n) -> C7(n) -> C8(n) -> S1 (inCopyrightPath4) V1(y),
 * year=1955 V4=1900-1954 V5=2025
 * 
 * C1(n) -> S1 (inCopyrightPath5) V1(n)
 * 
 * @author mharriso
 * 
 */
/* @formatter:on */

public class EngravingTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private String recordFileFullPath(String leaf) {
        return "marcXML/au/gov/nla/rms/service/test/MaterialType/EngravingTest/" + leaf;
    }

    /**
     * Run common tests.
     * 
     * @param data
     * @return CopyrightStatusReturn that can be used for extra tests by the
     *         caller
     */
    // private CopyrightStatusReturn verifyCopyrightExpired(CoreTestData data) {
    // CopyrightStatusReturn csr = runTests(data);
    // CopyrightStatusAssertions csa = new CopyrightStatusAssertions(csr);
    // csa.assertCopyrightExpired();
    // csa.assertReason(data.expectedCopyrightStatusReson);
    // return csr;
    // }
    //
    // private CopyrightStatusReturn verifyInCopyright(CoreTestData data) {
    // CopyrightStatusReturn csr = runTests(data);
    // CopyrightStatusAssertions csa = new CopyrightStatusAssertions(csr);
    // csa.assertInCopyright();
    // csa.assertReason(data.expectedCopyrightStatusReson);
    // return csr;
    // }
    //
    // private CopyrightStatusReturn runTests(CoreTestData data) {
    // String recordFile = recordFileFullPath(data.marcRecordFilename);
    // CopyrightStatusReturn csr = null;
    // if (data.currentYear != null) {
    // DateContext dateContext = new TestDateContext(data.currentYear);
    // csr = CopyrightStatusRunner.run(recordFile, dateContext);
    // } else {
    // csr = CopyrightStatusRunner.run(recordFile);
    // }
    // assertNotNull("Got a CopyrightStatusReturn", csr);
    // return csr;
    // }

    @Test
    public void copyrightExpiredPath1() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2019;
        data.expectedCopyrightStatusReason = "Since 2018 [Created/Published Date + 50 Years]";
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath1.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void inCopyrightPath1() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2018;
        data.expectedCopyrightStatusReason = "Until 2018 [Created/Published Date + 50 Years]";
        data.marcRecordFilename = recordFileFullPath("inCopyrightPath1.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertInCopyright();
    }

    @Test
    public void copyrightExpiredPath2() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2021;
        data.expectedCopyrightStatusReason = "Since 2020 [Created/Published Date + 50 Years]";
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath2.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void inCopyrightPath2() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2020;
        data.expectedCopyrightStatusReason = "Until 2020 [Created/Published Date + 50 Years]";
        data.marcRecordFilename = recordFileFullPath("inCopyrightPath2.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertInCopyright();
    }

    @Test
    public void copyrightExpiredPath3() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.expectedCopyrightStatusReason = Work.CREATOR_DATE_OF_DEATH_BEFORE_1955;
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath3.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void copyrightExpiredPath4() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2026;
        data.expectedCopyrightStatusReason = "Since 2025 [Creator Date of Death + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath4.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void inCopyrightPath3() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2024;
        data.expectedCopyrightStatusReason = "Until 2025 [Creator Date of Death + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("inCopyrightPath3.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertInCopyright();
    }

    @Test
    public void copyrightExpiredPath5() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.expectedCopyrightStatusReason = Work.CREATED_PUBLICATION_DATE_BEFORE_1955;
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath5.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void copyrightExpiredPath6() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2026;
        data.expectedCopyrightStatusReason = "Since 2025 [Created/Published Date + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath6.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void inCopyrightPath4() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2025;
        data.expectedCopyrightStatusReason = "Until 2025 [Created/Published Date + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("inCopyrightPath4.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertInCopyright();
    }

    @Test
    public void inCopyrightPath5() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        // No reason is given in this case
        data.expectedCopyrightStatusReason = "";
        data.marcRecordFilename = recordFileFullPath("inCopyrightPath5.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertInCopyright();
    }
}
