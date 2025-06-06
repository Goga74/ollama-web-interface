package ai.goga74.ollama.dto;

public class ChatRequest {
    private String model;
    private String prompt;
    private boolean stream = false;
    private ChatOptions options;
    
    public ChatRequest() {}
    
    public ChatRequest(String model, String prompt) {
        this.model = model;
        this.prompt = prompt;
    }
    
    // Getters and Setters
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    
    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
    
    public boolean isStream() { return stream; }
    public void setStream(boolean stream) { this.stream = stream; }
    
    public ChatOptions getOptions() { return options; }
    public void setOptions(ChatOptions options) { this.options = options; }
    
    public static class ChatOptions {
        private double temperature = 0.7;
        private int num_predict = 1000;
        
        // Getters and Setters
        public double getTemperature() { return temperature; }
        public void setTemperature(double temperature) { this.temperature = temperature; }
        
        public int getNum_predict() { return num_predict; }
        public void setNum_predict(int num_predict) { this.num_predict = num_predict; }
    }
}
