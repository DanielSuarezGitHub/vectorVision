package model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a workspace containing a collection of named matrices and vectors
public class Workspace implements Writable {
    private String name;
    private Map<String, Matrix> matrices; // Represents all matrices in the workspace
    private Map<String, Vector> vectors; // Represents all vectors in the workspace

    // REQUIRES: name != null
    // EFFECTS: constructs an empty workspace with the given name,
    // initializing empty collections for matrices and vectors
    public Workspace(String name) {
        this.name = name;
        this.matrices = new HashMap<>();
        this.vectors = new HashMap<>();
    }

    // EFFECTS: returns the name of this workspace
    public String getName() {
        return name;
    }

    // EFFECTS: returns the map of matrices currently stored in the workspace
    public Map<String, Matrix> getMatrices() {
        return matrices;
    }

    // EFFECTS: returns the map of vectors currently stored in the workspace
    public Map<String, Vector> getVectors() {
        return vectors;
    }

    // REQUIRES: name != null, m != null
    // EFFECTS: adds the matrix m to the workspace under the given name.
    // If a matrix with the same name already exists, it is replaced.
    public void addMatrix(String name, Matrix m) {
        matrices.put(name, m);
    }

    // REQUIRES: name != null, v != null
    // MODIFIES: this
    // EFFECTS: adds the vector v to the workspace under the given name.
    // If a vector with the same name already exists, it is replaced.
    public void addVector(String name, Vector v) {
        vectors.put(name, v);
    }

    // REQUIRES: name != null
    // MODIFIES: this
    // EFFECTS: removes the matrix with the given name from the workspace if it
    // exists,
    // returns the removed matrix, or null if it was not found
    public Matrix removeMatrix(String name) {
        return matrices.remove(name);
    }

    // REQUIRES: name != null
    // MODIFIES: this
    // EFFECTS: removes the vector with the given name from the workspace if it
    // exists,
    // returns the removed vector, or null if it was not found
    public Vector removeVector(String name) {
        return vectors.remove(name);
    }

    // EFFECTS: returns true if the workspace contains no matrices and no vectors
    public boolean isEmpty() {
        return matrices.isEmpty() && vectors.isEmpty();
    }

    // EFFECTS: returns this workspace as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);

        JSONArray matricesArray = new JSONArray();
        for (String key : matrices.keySet()) {
            JSONObject matrixEntry = new JSONObject();
            matrixEntry.put("name", key);
            matrixEntry.put("matrix", matrices.get(key).toJson());
            matricesArray.put(matrixEntry);
        }
        json.put("matrices", matricesArray);

        JSONArray vectorsArray = new JSONArray();
        for (String key : vectors.keySet()) {
            JSONObject vectorEntry = new JSONObject();
            vectorEntry.put("name", key); 
            vectorEntry.put("vector", vectors.get(key).toJson()); 
            vectorsArray.put(vectorEntry);
        }
        json.put("vectors", vectorsArray);

        return json;
    }

}
