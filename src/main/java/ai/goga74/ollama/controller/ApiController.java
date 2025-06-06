package ai.goga74.ollama.controller;

import ai.goga74.ollama.dto.ChatRequest;
import ai.goga74.ollama.dto.ChatResponse;
import ai.goga74.ollama.dto.ModelInfo;
import ai.goga74.ollama.service.OllamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ApiController {
    
    @Autowired
    private OllamaService ollamaService;
    
    @PostMapping("/generate")
    public Mono<ChatResponse> generate(@RequestBody ChatRequest request) {
        return ollamaService.generateResponse(request);
    }
    
    @GetMapping("/models")
    public Mono<ModelInfo> getModels() {
        return ollamaService.getAvailableModels();
    }
    
    @GetMapping("/status")
    public Mono<String> getStatus() {
        return ollamaService.checkOllamaStatus();
    }
}
