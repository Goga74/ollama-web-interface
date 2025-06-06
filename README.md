# Ollama Web Interface

A modern, user-friendly web interface for interacting with Ollama AI models through a Spring Boot application.

## Features

- 🎨 **Modern UI Design** - Clean and responsive web interface
- 🤖 **Model Selection** - Automatically detects and lists available Ollama models
- 💬 **Chat Interface** - Easy-to-use form for sending prompts to AI models
- 📱 **Responsive Design** - Works on desktop and mobile devices
- 🚀 **Real-time Responses** - Direct integration with Ollama REST API
- ⚡ **Keyboard Shortcuts** - Press Ctrl+Enter to submit quickly
- 🔧 **REST API** - Additional API endpoints for integration
- 🛠️ **Error Handling** - Comprehensive error handling and user feedback

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Ollama installed and running on localhost:11434
- At least one AI model downloaded in Ollama (e.g., `ollama pull gemma3:4b`)

## Installation

1. **Clone or download the project**
   ```bash
   git clone <repository-url>
   cd ollama-web-interface
   ```

2. **Ensure Ollama is running**
   ```bash
   ollama serve
   ```

3. **Build and run the application**
   ```bash
   mvn spring-boot:run
   ```

   Or build JAR and run:
   ```bash
   mvn clean package
   java -jar target/ollama-web-interface-1.0.0.jar
   ```

## Usage

1. **Open your web browser** and navigate to `http://localhost:8080`

2. **Select a model** from the dropdown menu (e.g., gemma3:4b)
   - The interface automatically loads all available models from your Ollama installation

3. **Enter your question** in the text area
   - Type any question or task you want the AI to help with
   - Examples: "Explain quantum physics", "Write a Python function", "Translate this text"

4. **Submit your request**
   - Click "🚀 Отправить запрос" button
   - Or use keyboard shortcut: `Ctrl+Enter`

5. **View the response**
   - The AI model's response will appear in the right panel
   - Response includes the model name and generated text

## API Endpoints

The application also provides REST API endpoints for programmatic access:

- `GET /api/models` - Get list of available models
- `POST /api/generate` - Generate AI response
- `GET /api/status` - Check Ollama connection status

### Example API Usage

**Get available models:**
```bash
curl http://localhost:8080/api/models
```

**Generate response:**
```bash
curl -X POST http://localhost:8080/api/generate \
  -H "Content-Type: application/json" \
  -d '{
    "model": "gemma3:4b",
    "prompt": "Hello, how are you?",
    "stream": false
  }'
```

## Configuration

The application can be configured via `src/main/resources/application.properties`:

```properties
# Application port
server.port=8080

# Ollama API settings
ollama.base-url=http://localhost:11434
ollama.timeout=30s
```

## Project Structure

```
ollama-web-interface/
├── pom.xml
├── README.md
├── src/main/java/com/example/ollama/
│   ├── OllamaWebApplication.java
│   ├── controller/
│   │   ├── WebController.java
│   │   └── ApiController.java
│   ├── service/
│   │   └── OllamaService.java
│   └── dto/
│       ├── ChatRequest.java
│       ├── ChatResponse.java
│       └── ModelInfo.java
├── src/main/resources/
│   ├── application.properties
│   └── templates/
│       └── index.html
```

## Troubleshooting

**"Ollama не доступен" error:**
- Ensure Ollama is running: `ollama serve`
- Check if Ollama is accessible: `curl http://localhost:11434/api/version`

**No models available:**
- Download a model: `ollama pull gemma3:4b`
- Verify models are installed: `ollama list`

**Port already in use:**
- Change the port in `application.properties`: `server.port=8081`

## Technologies Used

- **Spring Boot 3.2** - Main framework
- **Spring WebFlux** - Reactive web client for Ollama API
- **Thymeleaf** - Template engine
- **Maven** - Build tool
- **HTML/CSS/JavaScript** - Frontend

## License

This project is open source and available under the MIT License.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

If you encounter any issues or have questions, please create an issue in the project repository.