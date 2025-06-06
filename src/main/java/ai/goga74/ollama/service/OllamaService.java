package ai.goga74.ollama.service;

import ai.goga74.ollama.dto.ChatRequest;
import ai.goga74.ollama.dto.ChatResponse;
import ai.goga74.ollama.dto.ModelInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OllamaService {
    
    private final WebClient webClient;
    
    public OllamaService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:11434")
                .build();
    }
    
    public Mono<ChatResponse> generateResponse(ChatRequest request) {
        return webClient.post()
                .uri("/api/generate")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatResponse.class);
    }
    
    public Mono<ModelInfo> getAvailableModels() {
        return webClient.get()
                .uri("/api/tags")
                .retrieve()
                .bodyToMono(ModelInfo.class);
    }
    
    public Mono<String> checkOllamaStatus() {
        return webClient.get()
                .uri("/api/version")
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("Ollama не доступен");
    }
}