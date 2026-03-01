package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
// Credit to CPSC 210 JsonSerilizationDemo repository;
public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass

        }
    }

    @Test
    void testWriterEmptyWorkspace() {
        try {
            Workspace ws = new Workspace("Empty Workspace");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkspace.json");
            writer.open();
            writer.write(ws);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkspace.json");
            ws = reader.read();
            assertEquals("Empty Workspace", ws.getName());
            assertTrue(ws.isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkspace() {
        try {
            Workspace ws = new Workspace("General Workspace");
            ws.addMatrix("Mat1", new Matrix(2, 2));
            Fraction[] fracs = {new Fraction(1, 1)};
            ws.addVector("Vec1", new Vector(fracs));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkspace.json");
            writer.open();
            writer.write(ws);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkspace.json");
            ws = reader.read();
            
            assertEquals("General Workspace", ws.getName());
            assertEquals(1, ws.getMatrices().size());
            assertEquals(1, ws.getVectors().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}


