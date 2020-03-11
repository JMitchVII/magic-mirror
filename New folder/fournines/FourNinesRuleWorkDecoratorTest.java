package au.gov.nla.rms.service.businessrules.work_decorator.fournines;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.gov.nla.rms.service.CopyrightStatusReturn;
import au.gov.nla.rms.service.businessrules.work_decorator.fournines.FourNinesAndC;
import au.gov.nla.rms.service.businessrules.work_decorator.fournines.FourNinesAndM;
import au.gov.nla.rms.service.businessrules.work_decorator.fournines.FourNinesAndQ;
import au.gov.nla.rms.service.test.CopyrightStatusAssertions;
import au.gov.nla.rms.service.test.CopyrightStatusKeys;

/**
 * Test various types of date coding.
 * 
 * @author mharriso
 */
public class FourNinesRuleWorkDecoratorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private String recordFileFullPath(String leaf) {
        return "marcXML/au/gov/nla/rms/service/test/DateCoding/DateCodingTest/" + leaf;
    }

    /**
     * Serials that are still going can have the end date coded as '9999'. This
     * magic number is documented here:
     * http://www.loc.gov/marc/bibliographic/bd008a.html (see the section
     * starting "Legal characters - Date 1 and Date 2")
     * 
     * The copyright status tool has been treating the '9999' value as an actual
     * date, and reporting that the serial will be in publication until some
     * time past the year 10000.
     * 
     * This test verifies that the correct behaviour of reporting copyright is
     * undetermined.
     * 
     * FIXME: The documentation for this requirement is missing from the wiki.
     * The closest is:
     * http://servicedesk.nla.gov.au/otrs/index.pl?Action=AgentTicketZoom
     * &TicketID=31049&ArticleID=&QueueID=11
     * 
     */
    @Test
    public void activeLiteraryDramaticMusicalSerial() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.expectedCopyrightStatusReason = FourNinesAndM.REASON;
        data.marcRecordFilename = recordFileFullPath("activeLiteraryDramaticMusicalMapSeries.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightUndetermined();

        CopyrightStatusReturn csr = csa.copyrightStatusReturn();
        String materialType = csr.get(CopyrightStatusKeys.MATERIAL_TYPE_KEY);
        assertEquals("Material type is as expected", "Literary Dramatic Musical", materialType);
    }

    @Test
    public void fourNinesAndC() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.expectedCopyrightStatusReason = FourNinesAndC.REASON;
        data.marcRecordFilename = recordFileFullPath("fourNinesAndC.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightUndetermined();
    }

    @Test
    public void fourNinesAndM() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.expectedCopyrightStatusReason = FourNinesAndM.REASON;
        data.marcRecordFilename = recordFileFullPath("fourNinesAndM.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightUndetermined();
    }

    @Test
    public void fourNinesAndQ() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.expectedCopyrightStatusReason = FourNinesAndQ.REASON;
        data.marcRecordFilename = recordFileFullPath("fourNinesAndQ.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightUndetermined();
    }
}
