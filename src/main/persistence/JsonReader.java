package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents a reader that reads workroom from JSON data stored in file
// Credit to CPSC 210 JsonSerilizationDemo repository;
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workspace from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Workspace read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkspace(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses workspace from JSON object and returns it
    private Workspace parseWorkspace(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Workspace ws = new Workspace(name);
        addMatrices(ws, jsonObject);
        addVectors(ws, jsonObject);
        return ws;
    }

    // MODIFIES: ws
    // EFFECTS: parses matrices from JSON array within the JSON object and adds them
    // to workspace
    private void addMatrices(Workspace ws, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("matrices");
        for (Object json : jsonArray) {
            JSONObject nextMatrixEntry = (JSONObject) json;
            addMatrix(ws, nextMatrixEntry);
        }
    }

    // MODIFIES: ws
    // EFFECTS: parses an individual matrix from its JSON object and adds it to
    // workspace
    private void addMatrix(Workspace ws, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONObject matrixData = jsonObject.getJSONObject("matrix");

        int rows = matrixData.getInt("rows");
        int cols = matrixData.getInt("cols");
        
        Matrix m = new Matrix(rows, cols);
        
        JSONArray dataArray = matrixData.getJSONArray("data");

        for (int i = 0; i < rows; i++) {
            JSONObject rowVecJson = dataArray.getJSONObject(i);
            JSONArray components = rowVecJson.getJSONArray("components");
            for (int j = 0; j < cols; j++) {
                JSONObject fracJson = components.getJSONObject(j);
                long num = fracJson.getLong("numerator");
                long den = fracJson.getLong("denominator");
                m.setElement(i, j, new Fraction(num, den));
            }
        }
        ws.addMatrix(name, m);

    }

    // MODIFIES: ws
    // EFFECTS: parses vectors from JSON array within the JSON object and adds them
    // to workspace
    private void addVectors(Workspace ws, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("vectors");
        for (Object json : jsonArray) {
            JSONObject nextVectorEntry = (JSONObject) json;
            addVector(ws, nextVectorEntry);
        }
    }
    
    // MODIFIES: ws
    // EFFECTS: parses an individual vector from its JSON object and adds it to
    // workspace
    private void addVector(Workspace ws, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONObject vectorData = jsonObject.getJSONObject("vector");
        JSONArray components = vectorData.getJSONArray("components");
        
        Fraction[] fractions = new Fraction[components.length()];
        for (int i = 0; i < components.length(); i++) {
            JSONObject fracJson = components.getJSONObject(i);
            long num = fracJson.getLong("numerator");
            long den = fracJson.getLong("denominator");
            fractions[i] = new Fraction(num, den);
        }
        
        Vector v = new Vector(fractions);
        ws.addVector(name, v);
    }
}
