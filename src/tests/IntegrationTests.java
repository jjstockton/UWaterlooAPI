package tests;

import UWaterloo.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class IntegrationTests {

    private static UWaterlooClient client = new UWaterlooClient("abf875e0dcd95bc93484f9437934fc6e");


    @Test
    public void getCourseTest(){

        Course c1 = client.getCourse("ECE", "103");
        Course c2 = client.getCourse("ECON", "101");
        Course c3 = client.getCourse("ECE", "600");

        assertEquals("ECE", c1.getSubject());
        assertEquals("103", c1.getCatalogNumber());
        assertEquals(0.5, c1.getUnits(), 0);
        assertNotNull(c1.getTitle());

        assertEquals(true, c2.getOfferings().isOnline());
        assertEquals(false, c2.getOfferings().isConradGrebelOnly());
        assertEquals("undergraduate", c2.getAcademicLevel());
        assertNotNull(c2.getDescription());

        assertEquals("graduate", c3.getAcademicLevel());
        assertNotNull(c3.getCalendarYear());

    }

    @Test
    public void getUnitsTest(){

        ArrayList<Unit> units = client.getUnits();

        ArrayList<String> names = new ArrayList<>();

        for(Unit u : units){
            names.add(u.getUnitCode());
        }

        assertNotNull(units.get(11).getUnitFullName());
        assertTrue(names.contains("ECE"));
        assertTrue(names.contains("CO"));
    }

    @Test
    public void getTermsTest() {
        ArrayList<Term> terms = client.getTerms();

        assertTrue(terms.size() > 0);
        assertNotNull(terms.get(0).getDescription());
    }

    @Test
    public void getScheduleTest() {
        ArrayList<Schedule> scheds = client.getSchedules("ECON", "101");
        for(Schedule s : scheds) {
            assertEquals("ECON", s.getSubject());
            assertEquals("101", s.getCatalogNumber());
            assertEquals("undergraduate", s.getAcademicLevel());
            assertTrue(s.getEnrollmentTotal() > 0);
            assertTrue(s.getClasses().length > 0);
            assertTrue(s.getReserves().length > 0);
            assertTrue(s.getReserves()[0].getEnrollmentCapacity() > 0);
        }
    }

}