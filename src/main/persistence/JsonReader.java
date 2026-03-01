package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONObject;


// Represents a reader that reads workroom from JSON data stored in file
// Credit to CPSC 210 JsonSerilizationDemo repository;
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
    }

    // EFFECTS: reads workspace from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Workspace read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null;
    }

    // EFFECTS: parses workspace from JSON object and returns it
    private Workspace parseWorkspace(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: ws
    // EFFECTS: parses matrices from JSON array within the JSON object and adds them to workspace
    private void addMatrices(Workspace ws, JSONObject jsonObject) {
    }

    // MODIFIES: ws
    // EFFECTS: parses an individual matrix from its JSON object and adds it to workspace
    private void addMatrix(Workspace ws, JSONObject jsonObject) {
    }

    // MODIFIES: ws
    // EFFECTS: parses vectors from JSON array within the JSON object and adds them to workspace
    private void addVectors(Workspace ws, JSONObject jsonObject) {
    }

    // MODIFIES: ws
    // EFFECTS: parses an individual vector from its JSON object and adds it to workspace
    private void addVector(Workspace ws, JSONObject jsonObject) {
    }
}
