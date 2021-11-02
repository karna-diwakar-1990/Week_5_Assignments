package Day1.tests.Assignment2;

import org.testng.annotations.Test;
import Day1.tests.Assignment2.helpers.Operations;

public class Update_Incident_Test extends BaseClass {
    @Test
    public void updateIncidentTest(){
        Operations.updateIncident(driver, "INC0010017");
    }
}
