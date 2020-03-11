package au.gov.nla.rms.service.test.MaterialType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.gov.nla.rms.service.businessrules.Work;
import au.gov.nla.rms.service.test.CopyrightStatusAssertions;

/*@formatter:off*/
/**
 * Condition 1: Does the government have copyright ownership of the work?
 * 
 * Condition 2: Was the work created before 1 May 1969?
 * 
 * Condition 3: Was the work created before 1 January 1955?
 * 
 * Condition 4: Is current year after year of creator's death + 70 years?
 * 
 * Condition 5: Is current year after year created + 50 years?
 * 
 * Condition 6: Is current year after year first published + 50 years?
 * 
 * State 1: Copyright Expired
 * 
 * State 2: In Copyright
 * 
 * Variables: V1: common::isGovernmentOwned V2: dateCreated V3:
 * common::craetorsYearOfDeath V4: common::dateFirstPublished V5: currentYear
 * 
 * MARC Implementation All records are mapped to the Photograph type by setting
 * 300$a to 'photograph'
 * 
 * C1(y) -> C2(y) -> C5(y) -> S1 (copyrightExpiredPath1) V1(y) V2, date=1968 V5,
 * date=2019
 * 
 * 
 * C1(y) -> C2(y) -> C5(n) -> S2 (inCopyrightPath1) V1(y) V2, date=1968 V5,
 * date=2018
 * 
 * 
 * C1(y) -> C2(n) -> C6(y) -> S1 (copyrightExpiredPath2) V1(y) V2, date=1970 V5,
 * date=2021
 * 
 * C1(y) -> C2(n) -> C6(n) -> S2 (inCopyrightPath2) V1(y) V2, date=1970 V5,
 * date=2020
 * 
 * C1(n) -> C3(y) -> S1 (copyrightExpiredPath3) V1(n) V2, date=1954
 * 
 * 
 * C1(n) -> C3(n) -> C4(y) -> S1 (copyrightExpiredPath4) V1(n) V2, date=1956 V3,
 * date=1956 V5, date=2027
 * 
 * C1(n) -> C3(n) -> C4(n) -> S2 (inCopyrightPath3) V1(n) V2, date=1956 V3,
 * date=1956 V5, date=2026
 * 
 * @author mharriso
 * 
 */
/* @formatter:on */

public class PhotographTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private String recordFileFullPath(String leaf) {
        return "marcXML/au/gov/nla/rms/service/test/MaterialType/PhotographTest/" + leaf;
    }

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
        data.expectedCopyrightStatusReason = Work.CREATED_PUBLICATION_DATE_BEFORE_1955;
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath3.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void copyrightExpiredPath4() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2027;
        data.expectedCopyrightStatusReason = "Since 2026 [Creator Date of Death + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("copyrightExpiredPath4.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightExpired();
    }

    @Test
    public void inCopyrightPath3() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.currentYear = 2026;
        data.expectedCopyrightStatusReason = "Until 2026 [Creator Date of Death + 70 Years]";
        data.marcRecordFilename = recordFileFullPath("inCopyrightPath3.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertInCopyright();
    }
}
