package au.gov.nla.rms.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import au.gov.nla.rms.service.CopyrightStatusReturn;

/**
 * Verify that things actually happened to get us into the state we are in
 * 
 * Class contains method to verify that some conditions were actually evaluated
 * by the copyrightstatus tool. Check the package javadoc to see how the data
 * for these conditions are encoded in the test MARC records.
 * 
 * @author mharriso
 * 
 */
public class CommonConditionVerifyer {

    /**
     * CC_GOV_OWNERSHIP: Does the government have copyright ownership of the
     * work?
     * 
     * 
     * This method verifies the condition was actually checked in the code
     * 
     * @param expectedValue
     */
    public static void verifyGovernmentOwnership(CopyrightStatusReturn csr, Boolean expectedValue) {
        String actualMessage = csr.get(CopyrightStatusKeys.GOVERNMENT_OWNERSHIP_KEY);

        String expectedMessage = expectedValue ? "Queensland - Autonomous or semi-autonomous" : "No Government Copyright Ownership";

        assertEquals("Government ownership is expected message", expectedMessage, actualMessage);
    }

    /**
     * CC_AFTER_FIRST_PUBLISHED_PLUS_50_YEARS: Is the current year after year
     * first published + 50 years?
     */
    public static void verifyAfterFirstPublishedPlus50Years() {
        fail("not implemented");
    }

    /**
     * CC_FIRST_PUBLISHED_BEFORE_CREATORS_DEATH: Is year first published before
     * craetor's year of death?
     */
    public static void verifyYearFirstPublishedBeforeCreatorsDeath() {
        fail("not implemented");
    }

    /**
     * CC_PUBLISHED_BEFORE_1955: Is year published before 1 January 1955?
     **/
    public static void verifyPublishedBefore1955() {
        fail("not implemented");
    }

    /**
     * CC_CREATORS_DEATH_BEFORE_1955: Is creator's death date before 1 January
     * 1955?
     */
    public static void verifyCreatorDeathDateBefore1955() {
        fail("not implemented");
    }
}
