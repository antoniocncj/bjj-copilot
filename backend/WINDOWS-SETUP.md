# BJJ Copilot Backend - Windows Development Guide

Este guia descreve como configurar e executar o backend do BJJ Copilot em um ambiente de desenvolvimento Windows usando Docker.

## Pré-requisitos

### Softwares Necessários
- **Docker Desktop for Windows** (versão mais recente)
- **Git for Windows**
- **Java 17** (OpenJDK ou Oracle JDK)
- **Maven 3.8+** (opcional, se preferir não usar Docker)

### Instalação do Docker Desktop
1. Baixe o Docker Desktop em: https://docs.docker.com/desktop/install/windows-install/
2. Execute o instalador
3. Reinicie o Windows se necessário
4. Abra o Docker Desktop e aguarde a inicialização

## Configuração Rápida

### Opção 1: Ambiente Completo com Docker (Recomendado)

Abra o Command Prompt ou PowerShell na pasta `backend` e execute:

```cmd
# Iniciar stack completo (Backend + Banco + Cache)
dev-setup.bat full
```

Isso irá:
- Baixar e construir todas as imagens necessárias
- Iniciar PostgreSQL na porta 5432
- Iniciar Redis na porta 6379  
- Construir e iniciar o backend na porta 8080

### Opção 2: Apenas Banco de Dados (para desenvolvimento local)

```cmd
# Iniciar apenas PostgreSQL e Redis
dev-setup.bat dev
```

Em seguida, em outro terminal, execute o backend localmente:

```cmd
set SPRING_PROFILES_ACTIVE=dev
mvn spring-boot:run
```

## Comandos Úteis

### Gerenciamento de Serviços
```cmd
dev-setup.bat status    # Ver status dos serviços
dev-setup.bat stop      # Parar todos os serviços
dev-setup.bat logs      # Ver logs dos serviços
dev-setup.bat clean     # Limpar todos os recursos Docker
```

### Verificação de Status
```cmd
# Verificar se o backend está funcionando
curl http://localhost:8080/actuator/health

# Verificar serviços Docker
docker ps

# Ver logs específicos
docker compose logs backend
```

## URLs de Desenvolvimento

| Serviço | URL | Credenciais |
|---------|-----|-------------|
| Backend API | http://localhost:8080 | - |
| Health Check | http://localhost:8080/actuator/health | - |
| PostgreSQL | localhost:5432 | usuário: `bjj_user`, senha: `bjj_password`, db: `bjj_copilot` |
| Redis | localhost:6379 | sem senha |

## Estrutura de Arquivos Docker

```
backend/
├── Dockerfile                  # Imagem Docker do backend
├── docker-compose.yml          # Stack completo
├── docker-compose.dev.yml      # Apenas banco + cache
├── dev-setup.bat              # Script helper para Windows
├── dev-setup.sh               # Script helper para Linux/macOS
├── .dockerignore              # Arquivos ignorados no build
└── init-db/
    └── 01-init.sql            # Script de inicialização do banco
```

## Solução de Problemas

### Docker Desktop não inicia
- Verifique se a virtualização está habilitada no BIOS
- Verifique se o WSL 2 está instalado e atualizado
- Reinicie o Windows

### Porta em uso
```cmd
# Verificar qual processo está usando a porta 8080
netstat -ano | findstr :8080

# Parar processo específico (substitua PID pelo número encontrado)
taskkill /PID [PID] /F
```

### Erro de permissão
- Execute o Command Prompt como Administrador
- Verifique se o Docker Desktop está executando

### Reset completo
```cmd
dev-setup.bat clean
docker system prune -a
docker volume prune -f
```

## Profiles de Configuração

| Profile | Banco de Dados | Uso |
|---------|----------------|-----|
| `dev` | H2 (em memória) | Desenvolvimento rápido, sem Docker |
| `default` | PostgreSQL | Desenvolvimento com banco real |
| `docker` | PostgreSQL | Execução em container |

## Comandos Maven Alternativos

Se preferir não usar Docker:

```cmd
# Compilar
mvn clean compile

# Executar testes
mvn test

# Executar com H2 (sem banco externo)
set SPRING_PROFILES_ACTIVE=dev
mvn spring-boot:run

# Gerar JAR
mvn clean package
```

## Arquitetura do Sistema

De acordo com o documento [ARCHITECTURE.md](../ARCHITECTURE.md), o ambiente de desenvolvimento possui:

- **Frontend Angular**: Porta 4200
- **Backend Spring Boot**: Porta 8080
- **PostgreSQL**: Porta 5432
- **Redis**: Porta 6379

## Suporte

Para problemas ou dúvidas:
1. Verifique os logs: `dev-setup.bat logs`
2. Consulte a documentação: [README.md](README.md)
3. Abra uma issue no repositório