package tests;

import UWaterloo.internal.json.JsonObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class JsonTests {



    private static JsonObject testJson = new JsonObject(
            "{\"course_id\":\"007407\",\"subject\":\"PHYS\"," +
            "\"catalog_number\":\"234\",\"title\":\"Quantum Physics 1\",\"units\":0.5," +
            "\"description\":\"Background of quantum physics. Introduction to formalism of quantum physics. " +
                              "Introduction to operators. Quantization, waves and particles. The uncertainty " +
                              "principle. The Schroedinger equation for one-dimensional problems: bound states in " +
                              "square wells. Harmonic oscillator; transmission through barriers.\"," +
            "\"instructions\":[\"LEC\",\"TUT\"]," +
            "\"prerequisites\":\"PHYS 112 or 122; MATH 114 or 136; MATH 128 or 138 or 148;\"," +
            "\"antirequisites\":\"CHEM 256\\/356, NE 232, PHYS 233, ECE 405\"," +
            "\"corequisites\":\"One of MATH 228, AMATH 250, AMATH 251.\",\"crosslistings\":null," +
            "\"terms_offered\":[\"W\",\"S\"]," +
            "\"notes\":\"[Note: PHYS 236 or knowledge of computational methods recommended. Offered: W, S]\"," +
            "\"offerings\":{\"online\":false,\"online_only\":false,\"st_jerome\":false,\"st_jerome_only\":false," +
                           "\"renison\":false,\"renison_only\":false,\"conrad_grebel\":false," +
                           "\"conrad_grebel_only\":false, \"test_nest\":{\"cool\":true}}," +
            "\"needs_department_consent\":false,\"needs_instructor_consent\":false,\"extra\":[]," +
            "\"calendar_year\":\"1617\"," +
            "\"url\":\"http:\\/\\/www.ucalendar.uwaterloo.ca\\/1617\\/COURSE\\/course-PHYS.html#PHYS234\"," +
            "\"academic_level\":\"undergraduate\"}");

    @Test
    public void testGetString() {

        String jsonString = "{\"course\": \"ECE 105\"}";

        JsonObject json = new JsonObject(jsonString);

        assertEquals("ECE 105", json.getString("course"));
        assertEquals("234", testJson.getString("catalog_number"));
        assertEquals("1617", testJson.getString("calendar_year"));

    }

    @Test
    public void testGetBoolean() {
        assertTrue(new JsonObject("{\"test_boolean\": true}").getBoolean("test_boolean"));
        assertFalse(testJson.getJsonObject("offerings").getBoolean("online"));
        assertTrue(testJson.getJsonObject("offerings").getJsonObject("test_nest").getBoolean("cool"));
    }

    @Test
    public void testGetDouble() {
        assertEquals(0.5, testJson.getDouble("units"), 0.0001);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testNoSuchKey() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Couldn't find key: online");

        assertNull(testJson.getBoolean("online"));
    }

    @Test
    public void testNull() {
        assertNull(testJson.getString("crosslistings"));
    }


}
