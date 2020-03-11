package au.gov.nla.rms.service.test.MaterialType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.gov.nla.rms.service.test.CopyrightStatusAssertions;

/**
 * Test for an unknown material type by having and empty MARC record
 * 
 * @author mharriso
 * 
 */
public class UnknownMaterialTypeTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private String recordFileFullPath(String leaf) {
        return "marcXML/au/gov/nla/rms/service/test/MaterialType/UnknownMaterialTypeTest/" + leaf;
    }

    @Test
    public void test() {
        CopyrightStatusAssertions.CoreTestData data = new CopyrightStatusAssertions.CoreTestData();
        data.expectedCopyrightStatusReason = "Unable to determine type of work";
        data.marcRecordFilename = recordFileFullPath("test.xml");

        CopyrightStatusAssertions csa = new CopyrightStatusAssertions(data);
        csa.assertCopyrightUndetermined();
    }
}
