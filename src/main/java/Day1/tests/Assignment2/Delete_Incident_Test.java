package Day1.tests.Assignment2;

import Day1.tests.Assignment2.helpers.Operations;
import org.testng.annotations.Test;

public class Delete_Incident_Test extends BaseClass{
    @Test
    public void deleteIncident(){
        Operations.deleteIncident(driver, "INC0010022");
    }
}
