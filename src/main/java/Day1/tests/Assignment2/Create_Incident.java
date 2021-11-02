package Day1.tests.Assignment2;

import org.testng.annotations.Test;
import Day1.tests.Assignment2.helpers.Operations;


public class Create_Incident extends BaseClass{
    @Test
    public void createIncident() {
        Operations.create_Incident(driver);
    }
}
