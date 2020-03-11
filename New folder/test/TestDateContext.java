package au.gov.nla.rms.service.test;

import java.util.Calendar;

import au.gov.nla.rms.service.DateContext;

/**
 * DateContext to be used for tests. Allow the current year to be set to an
 * arbitrary value.
 * 
 * @author mharriso
 * 
 */
public class TestDateContext implements DateContext {

    private int currentYear = (Calendar.getInstance()).get(Calendar.YEAR);

    public TestDateContext() {
    }

    public TestDateContext(int currentYear) {
        this.currentYear = currentYear;
    }

    public int currentYear() {
        return currentYear;
    }
}
