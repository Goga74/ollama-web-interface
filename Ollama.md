# Ollama Local Setup Guide

This guide will help you install and run Ollama locally on your machine to use with the Ollama Web Interface.

## What is Ollama?

Ollama is a tool that allows you to run large language models locally on your computer. It provides a simple API to interact with various AI models without requiring cloud services or internet connectivity.

## Installation

### Windows

1. **Download Ollama for Windows**
   - Visit [https://ollama.com/download](https://ollama.com/download)
   - Download the Windows installer (`OllamaSetup.exe`)

2. **Install Ollama**
   - Run the installer as Administrator
   - Follow the installation wizard
   - Restart your computer after installation

3. **Verify Installation**
   ```cmd
   ollama --version
   ```

### macOS

1. **Download and Install**
   - Visit [https://ollama.com/download](https://ollama.com/download)
   - Download the macOS installer
   - Drag Ollama to your Applications folder

2. **Verify Installation**
   ```bash
   ollama --version
   ```

### Linux

1. **Install via curl**
   ```bash
   curl -fsSL https://ollama.com/install.sh | sh
   ```

2. **Or download manually**
   - Visit [https://ollama.com/download](https://ollama.com/download)
   - Download the Linux package for your distribution

3. **Verify Installation**
   ```bash
   ollama --version
   ```

## Running Ollama

### Start Ollama Service

**Windows:**
```cmd
ollama serve
```

**macOS/Linux:**
```bash
ollama serve
```

The service will start on `http://localhost:11434` by default.

### Download AI Models

Before you can use Ollama, you need to download at least one AI model:

**Recommended models for beginners:**

1. **Gemma 3 4B** (Good balance of speed and quality)
   ```bash
   ollama pull gemma3:4b
   ```

2. **Llama 3.1 8B** (More powerful, requires more RAM)
   ```bash
   ollama pull llama3.1:8b
   ```

3. **Phi 3 Mini** (Lightweight, fast responses)
   ```bash
   ollama pull phi3:mini
   ```

4. **Mistral 7B** (Good for coding tasks)
   ```bash
   ollama pull mistral:7b
   ```

### Test Your Installation

1. **Check if Ollama is running:**
   ```bash
   curl http://localhost:11434/api/version
   ```

2. **List downloaded models:**
   ```bash
   ollama list
   ```

3. **Test a simple chat:**
   ```bash
   ollama run gemma3:4b
   ```
   
   Type a message and press Enter. Type `/bye` to exit.

## System Requirements

### Minimum Requirements
- **RAM**: 8GB (for 4B parameter models)
- **Storage**: 4GB free space per model
- **CPU**: Modern multi-core processor

### Recommended Requirements
- **RAM**: 16GB+ (for larger models)
- **Storage**: 20GB+ free space
- **GPU**: NVIDIA GPU with CUDA support (optional, for faster inference)

### Model Size Guidelines
| Model Size | RAM Required | Use Case |
|------------|--------------|----------|
| 3B-4B      | 4-8GB       | Light tasks, quick responses |
| 7B-8B      | 8-16GB      | General purpose, good quality |
| 13B-15B    | 16-32GB     | High quality, complex tasks |
| 30B+       | 32GB+       | Professional use, best quality |

## Common Commands

### Model Management
```bash
# Download a model
ollama pull <model-name>

# List installed models
ollama list

# Remove a model
ollama rm <model-name>

# Show model information
ollama show <model-name>
```

### Running Models
```bash
# Start interactive chat
ollama run <model-name>

# Run with custom parameters
ollama run <model-name> --temperature 0.8

# One-time generation
echo "Hello, how are you?" | ollama run <model-name>
```

### Service Management
```bash
# Start Ollama service
ollama serve

# Stop Ollama (Ctrl+C in the terminal where it's running)

# Check service status
curl http://localhost:11434/api/version
```

## Advanced Configuration

### Custom Model Parameters

You can create custom model configurations:

1. **Create a Modelfile:**
   ```bash
   # Save as Modelfile
   FROM gemma3:4b
   PARAMETER temperature 0.8
   PARAMETER top_p 0.9
   SYSTEM "You are a helpful assistant."
   ```

2. **Create the custom model:**
   ```bash
   ollama create my-custom-model -f Modelfile
   ```

### Environment Variables

You can configure Ollama using environment variables:

```bash
# Change default port
export OLLAMA_HOST=0.0.0.0:11435

# Set custom models directory
export OLLAMA_MODELS=/path/to/models

# Enable debug logging
export OLLAMA_DEBUG=1
```

## GPU Acceleration (Optional)

### NVIDIA GPU (CUDA)
Ollama automatically detects and uses NVIDIA GPUs with CUDA support.

**Requirements:**
- NVIDIA GPU with compute capability 6.0+
- CUDA drivers installed

**Verify GPU usage:**
```bash
# Look for GPU information in the output
ollama run gemma3:4b --verbose
```

### AMD GPU (ROCm) - Linux only
Ollama supports AMD GPUs on Linux with ROCm.

**Install ROCm:**
```bash
# Follow AMD ROCm installation guide for your Linux distribution
```

## Troubleshooting

### Common Issues

**1. "ollama: command not found"**
- Restart your terminal/command prompt
- On Windows, restart your computer
- Verify PATH environment variable includes Ollama

**2. "Connection refused" when accessing localhost:11434**
- Make sure Ollama service is running: `ollama serve`
- Check if another service is using port 11434
- Try a different port: `OLLAMA_HOST=localhost:11435 ollama serve`

**3. "Out of memory" errors**
- Try smaller models (3B-4B parameters)
- Close other applications to free RAM
- Consider using quantized models (Q4, Q8 variants)

**4. Models downloading slowly**
- Check your internet connection
- Some models are large (several GB), be patient
- Resume interrupted downloads by running `ollama pull` again

**5. GPU not being used**
- Verify CUDA/ROCm installation
- Check GPU compatibility
- Monitor GPU usage with `nvidia-smi` (NVIDIA) or `rocm-smi` (AMD)

### Performance Tips

1. **Use appropriate model sizes** for your hardware
2. **Close unnecessary applications** while running large models
3. **Use SSD storage** for better model loading times
4. **Enable GPU acceleration** if available
5. **Adjust model parameters** (temperature, top_p) for your use case

## Popular Models to Try

| Model | Size | Description | Command |
|-------|------|-------------|---------|
| Gemma 3 | 4B | Google's efficient model | `ollama pull gemma3:4b` |
| Llama 3.1 | 8B | Meta's general-purpose model | `ollama pull llama3.1:8b` |
| Code Llama | 7B | Specialized for coding | `ollama pull codellama:7b` |
| Mistral | 7B | Good for reasoning tasks | `ollama pull mistral:7b` |
| Phi 3 | 3.8B | Microsoft's efficient model | `ollama pull phi3:mini` |
| Neural Chat | 7B | Fine-tuned for conversations | `ollama pull neural-chat:7b` |

## Next Steps

Once Ollama is running successfully:

1. **Test the API**: `curl http://localhost:11434/api/version`
2. **Download your preferred models**: Choose based on your hardware
3. **Run the Ollama Web Interface**: Follow the main README.md instructions
4. **Experiment with different models**: Try various models for different tasks

## Additional Resources

- **Official Documentation**: [https://ollama.com/docs](https://ollama.com/docs)
- **Model Library**: [https://ollama.com/library](https://ollama.com/library)
- **GitHub Repository**: [https://github.com/ollama/ollama](https://github.com/ollama/ollama)
- **Community Discord**: Join the Ollama community for support and tips

---

**Ready to use Ollama?** Head back to the main [README.md](README.md) to set up the web interface!