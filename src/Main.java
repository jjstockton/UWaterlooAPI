import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, HttpResponseException {

        UWaterlooClient client = new UWaterlooClient("");

        ArrayList<Unit> units = client.getUnits();

        for(Unit unit : units){
            System.out.println(unit.unitCode + " is short for " + unit.unitFullName);
        }


    }

}
