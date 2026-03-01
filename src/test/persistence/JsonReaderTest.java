package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import model.Workspace;

// Credit to CPSC 210 JsonSerilizationDemo repository;
public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Workspace ws = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkspace() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkspace.json");
        try {
            Workspace ws = reader.read();
            assertEquals("Empty Test Workspace", ws.getName());
            assertTrue(ws.isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkspace() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkspace.json");
        try {
            Workspace ws = reader.read();
            assertEquals("General Test Workspace", ws.getName());
            
            assertEquals(1, ws.getMatrices().size());
            assertNotNull(ws.getMatrices().get("Mat1"));
            assertEquals(2, ws.getMatrices().get("Mat1").getNumRows());
            assertEquals(1, ws.getMatrices().get("Mat1").getElement(0,0).getNumerator());
            
            assertEquals(1, ws.getVectors().size());
            assertNotNull(ws.getVectors().get("Vec1"));
            assertEquals(3, ws.getVectors().get("Vec1").getComponent(0).getNumerator());
            assertEquals(4, ws.getVectors().get("Vec1").getComponent(1).getNumerator());
            
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
