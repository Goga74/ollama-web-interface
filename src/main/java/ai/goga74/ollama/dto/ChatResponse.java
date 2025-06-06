package ai.goga74.ollama.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatResponse {
    private String model;
    private String response;
    private boolean done;
    private String created_at;
    
    // Getters and Setters
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    
    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }
    
    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }
    
    public String getCreated_at() { return created_at; }
    public void setCreated_at(String created_at) { this.created_at = created_at; }
}
