package ai.goga74.ollama.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelInfo {
    private List<Model> models;
    
    public List<Model> getModels() { return models; }
    public void setModels(List<Model> models) { this.models = models; }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Model {
        private String name;
        private String size;
        private String modified_at;
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getSize() { return size; }
        public void setSize(String size) { this.size = size; }
        
        public String getModified_at() { return modified_at; }
        public void setModified_at(String modified_at) { this.modified_at = modified_at; }
    }
}
