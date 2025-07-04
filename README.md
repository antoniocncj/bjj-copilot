# BJJ Copilot

Um aplicativo inteligente para acompanhar a jornada no Brazilian Jiu-Jitsu (BJJ), oferecendo suporte personalizado para alunos e instrutores.

## 🥋 Visão Geral

O BJJ Copilot é uma plataforma que combina tecnologia moderna com a tradição do Brazilian Jiu-Jitsu, oferecendo:

- **Acompanhamento de Graduações**: Sistema completo para rastrear progresso de faixas e graus
- **IA Assistente**: Chat inteligente especializado em BJJ para dúvidas técnicas e orientações
- **Gestão de Academia**: Ferramentas para instrutores organizarem turmas e avaliações

## 🎯 Funcionalidades Principais

### Para Alunos
- Acompanhamento do progresso pessoal na graduação
- Chat com IA especializada em BJJ
- Histórico de treinos e evolução técnica
- Cronograma de exames de faixa

### Para Instrutores
- Gestão de alunos e suas graduações
- Sistema de avaliação e promoções
- Relatórios de progresso da turma
- Ferramentas de planejamento de aulas

## 📚 Documentação

- [Arquitetura do Sistema](./ARCHITECTURE.md)
- [Requisitos de Negócio](./REQUIREMENTS.md)
- [Personas e Papéis](./docs/personas.md)
- [Regras de Graduação](./docs/business-rules.md)
- [Especificações de Funcionalidades](./docs/features.md)
- [Requisitos Técnicos](./docs/technical-requirements.md)

## 📁 Estrutura do Projeto

```
bjj-copilot/
├── frontend/          # Aplicação Angular
│   ├── src/
│   ├── package.json
│   └── README.md
├── backend/           # API Spring Boot
│   ├── src/
│   ├── pom.xml
│   └── README.md
├── .github/           # Workflows CI/CD
│   └── workflows/
├── docs/              # Documentação adicional
├── ARCHITECTURE.md    # Arquitetura do sistema
├── REQUIREMENTS.md    # Requisitos de negócio
└── README.md         # Este arquivo
```

## 🚀 Status do Projeto

Este projeto está em fase de desenvolvimento inicial. A estrutura base foi configurada com:

### ✅ Concluído
- Estrutura de diretórios `/frontend` (Angular) e `/backend` (Spring Boot)
- Configuração de CI/CD com GitHub Actions
- Documentação técnica e arquitetural
- Configuração inicial dos projetos

### 🚧 Em Desenvolvimento
- APIs REST do backend
- Interface de usuário do frontend
- Integração com banco de dados
- Sistema de autenticação
- Chat com IA

## 🛠️ Configuração do Ambiente

### Pré-requisitos
- Node.js 20+
- Java 17+
- Maven 3.8+
- PostgreSQL 15+ (produção)
- Redis 7+ (produção)

### Frontend (Angular)
```bash
cd frontend
npm install
npm start
# Disponível em http://localhost:4200
```

### Backend (Spring Boot)
```bash
cd backend
mvn spring-boot:run
# Disponível em http://localhost:8080
```

Consulte os READMEs específicos em `/frontend` e `/backend` para mais detalhes.

## 📄 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.