package tests;

import UWaterloo.*;
import UWaterloo.models.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IntegrationTests {

    private static UWaterlooClient client = new UWaterlooClient("");

    @Test
    public void getCourseTest() {

        Course c1 = client.getCourse("ECE", "103");
        Course c2 = client.getCourse("004874");
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
    public void getCoursesTest() {

        List<Course> courses1 = client.getCourses(1169);
        List<Course> courses2 = client.getCourses();

        assertTrue(courses1.size() > 100);
        assertTrue(courses2.size() > 100);

        for (Course c : courses1) {
            assertNotNull(c.getTitle());
        }

        for (Course c : courses2) {
            assertNotNull(c.getTitle());
        }
    }

    @Test
    public void getCoursesSubject() {
        List<Course> courses = client.getCourses("ECE");

        for (Course c : courses) {
            assertEquals("ECE", c.getSubject());
            assertNotNull(c.getDescription());
        }
    }

    @Test
    public void getUnitsTest() {

        List<Unit> units = client.getUnits();

        ArrayList<String> names = new ArrayList<>();

        for (Unit u : units) {
            names.add(u.getUnitCode());
        }

        assertNotNull(units.get(11).getUnitFullName());
        assertTrue(names.contains("ECE"));
        assertTrue(names.contains("CO"));
    }

    @Test
    public void getTermsTest() {
        List<Term> terms = client.getTerms();

        assertTrue(terms.size() > 0);
        assertNotNull(terms.get(0).getDescription());
    }

    @Test
    public void getScheduleTest() {
        List<Schedule> scheds = client.getSchedules("ECON", "101");
        for (Schedule s : scheds) {
            assertEquals("ECON", s.getSubject());
            assertEquals("101", s.getCatalogNumber());
            assertEquals("undergraduate", s.getAcademicLevel());
            assertTrue(s.getEnrollmentTotal() > 0);
            assertTrue(s.getClasses().size() > 0);
            assertTrue(s.getReserves().size() > 0);
            assertTrue(s.getReserves().get(0).getEnrollmentCapacity() > 0);
        }

        Schedule s = client.getSchedule(4867).get(0);

        assertNotNull(s.getClasses().get(0).getLocation().getBuilding());
        assertNotNull(s.getTitle());
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void noDataReturned() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("No data returned");

        client.getCourse("ECE", "6969");
    }

    @Test
    public void ExamSchedule() {
        try {
            ExamSchedule e = client.getExamSchedule("ECE", "105");
            assertNotNull(e.getSections().get(0).getDate());
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().equals("No data returned"));
        }

    }

}