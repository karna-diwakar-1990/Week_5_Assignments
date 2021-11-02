package Day1.tests.Assignment2;

import org.testng.annotations.Test;
import Day1.tests.Assignment2.helpers.Operations;

public class Assign_Incident_Test extends BaseClass{
    @Test
    public void assignIncidentTest(){
        Operations.assignIncident(driver, "INC0010029");
    }
}
