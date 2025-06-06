package ai.goga74.ollama.controller;

import ai.goga74.ollama.dto.ChatRequest;
import ai.goga74.ollama.dto.ModelInfo;
import ai.goga74.ollama.service.OllamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class WebController {
    
    @Autowired
    private OllamaService ollamaService;
    
    @Value("${app.language:en}")
    private String defaultLanguage;
    
    @GetMapping("/")
    public String home(Model model, @RequestParam(value = "lang", required = false) String lang) {
        // Determine interface language
        String language = (lang != null) ? lang : defaultLanguage;
        
        model.addAttribute("chatRequest", new ChatRequest());
        model.addAttribute("language", language);
        
        // Get list of available models
        try {
            ModelInfo modelInfo = ollamaService.getAvailableModels().block();
            if (modelInfo != null && modelInfo.getModels() != null) {
                model.addAttribute("availableModels", modelInfo.getModels());
            } else {
                model.addAttribute("availableModels", new ArrayList<>());
            }
        } catch (Exception e) {
            model.addAttribute("availableModels", new ArrayList<>());
            String errorMessage = getErrorMessage(language, "models", e.getMessage());
            model.addAttribute("error", errorMessage);
        }
        
        // Return appropriate template
        return language.equals("ru") ? "index_ru" : "index";
    }
    
    @PostMapping("/chat")
    public String chat(@ModelAttribute ChatRequest chatRequest, Model model,
        @RequestParam(value = "lang", required = false) String lang) {
        // Determine interface language
        String language = (lang != null) ? lang : defaultLanguage;
        
        try {
            // Set default options
            if (chatRequest.getOptions() == null) {
                chatRequest.setOptions(new ChatRequest.ChatOptions());
            }
            
            // Send request to Ollama
            var response = ollamaService.generateResponse(chatRequest).block();
            
            if (response != null) {
                model.addAttribute("response", response.getResponse());
                model.addAttribute("model", response.getModel());
            } else {
                String errorMessage = getErrorMessage(language, "noresponse", "");
                model.addAttribute("error", errorMessage);
            }
            
        } catch (Exception e) {
            String errorMessage = getErrorMessage(language, "ollama", e.getMessage());
            model.addAttribute("error", errorMessage);
        }
        
        // Return back to main page with result
        model.addAttribute("chatRequest", chatRequest);
        model.addAttribute("language", language);
        
        // Get models list again for the form
        try {
            ModelInfo modelInfo = ollamaService.getAvailableModels().block();
            if (modelInfo != null && modelInfo.getModels() != null) {
                model.addAttribute("availableModels", modelInfo.getModels());
            } else {
                model.addAttribute("availableModels", new ArrayList<>());
            }
        } catch (Exception e) {
            model.addAttribute("availableModels", new ArrayList<>());
        }
        
        // Return appropriate template
        return language.equals("ru") ? "index_ru" : "index";
    }
    
    private String getErrorMessage(String language, String type, String details) {
        if (language.equals("ru")) {
            switch (type) {
            case "models":
                return "Не удалось получить список моделей: " + details;
            case "noresponse":
                return "Не получен ответ от модели";
            case "ollama":
                return "Ошибка при обращении к Ollama: " + details;
            default:
                return "Произошла ошибка: " + details;
            }
        } else {
            switch (type) {
            case "models":
                return "Failed to get models list: " + details;
            case "noresponse":
                return "No response received from model";
            case "ollama":
                return "Error connecting to Ollama: " + details;
            default:
                return "An error occurred: " + details;
            }
        }
    }
}
