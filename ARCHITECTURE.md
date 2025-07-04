# BJJ Copilot - Arquitetura do Sistema

## Visão Geral

O BJJ Copilot é uma aplicação web destinada a apoiar praticantes e instrutores de Brazilian Jiu-Jitsu (BJJ) no acompanhamento de progressão, gestão de graduações e fornecimento de orientação através de inteligência artificial. O sistema oferece funcionalidades específicas para diferentes personas (alunos e instrutores) e integra tecnologias modernas para uma experiência completa.

### Objetivos Principais

- **Acompanhamento de Graduações**: Controle detalhado da progressão de faixas e graus
- **Chat com IA**: Assistente inteligente para dúvidas técnicas e orientações
- **Gestão de Regras**: Sistema flexível para regras de graduação e progressão
- **Multi-personas**: Funcionalidades específicas para alunos e instrutores

## Stack Tecnológico

### Frontend
- **Angular** (Framework principal)
  - TypeScript para desenvolvimento type-safe
- **Angular Material** (UI Components)
- **RxJS** (Programação reativa)
- **NgRx** (Gerenciamento de estado - se necessário)

### Backend
- **Spring Boot** (Framework principal)
  - Java 17+
  - Spring Security (Autenticação e autorização)
  - Spring Data JPA (Persistência)
  - Spring Web (APIs REST)
- **PostgreSQL** (Banco de dados principal)
- **Redis** (Cache e sessões)

### Inteligência Artificial
- **GPT-4 Azure Foundry** (Processamento de linguagem natural)
- **Azure OpenAI Service** (Integração com APIs da OpenAI)

### Infraestrutura
- **Docker** (Containerização)
- **Azure Container Apps** (Hospedagem)
- **Azure Database for PostgreSQL** (Banco gerenciado)
- **Azure Redis Cache** (Cache gerenciado)

## Arquitetura de Alto Nível

```
┌─────────────────────────────────────────────────────────┐
│                    CLIENT LAYER                         │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  ┌─────────────────┐    ┌─────────────────┐            │
│  │   Angular SPA   │    │   Mobile PWA    │            │
│  │                 │    │   (Future)      │            │
│  └─────────────────┘    └─────────────────┘            │
│                                                         │
└─────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────┐
│                     API GATEWAY                         │
├─────────────────────────────────────────────────────────┤
│                                                         │
│              ┌─────────────────┐                        │
│              │  Load Balancer  │                        │
│              │   (Azure LB)    │                        │
│              └─────────────────┘                        │
│                                                         │
└─────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────┐
│                APPLICATION LAYER                        │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  ┌─────────────────────────────────────────────────────┐ │
│  │            Spring Boot Application                  │ │
│  │                                                     │ │
│  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  │ │
│  │  │   User      │  │ Graduation  │  │    AI       │  │ │
│  │  │ Management  │  │   System    │  │  Service    │  │ │
│  │  │             │  │             │  │             │  │ │
│  │  └─────────────┘  └─────────────┘  └─────────────┘  │ │
│  │                                                     │ │
│  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  │ │
│  │  │ Belt Rules  │  │ Notification│  │   Audit     │  │ │
│  │  │   Engine    │  │   Service   │  │   Service   │  │ │
│  │  │             │  │             │  │             │  │ │
│  │  └─────────────┘  └─────────────┘  └─────────────┘  │ │
│  └─────────────────────────────────────────────────────┘ │
│                                                         │
└─────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────┐
│                  EXTERNAL SERVICES                      │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  ┌─────────────────┐    ┌─────────────────┐            │
│  │  Azure OpenAI   │    │   Email Service │            │
│  │   (GPT-4)       │    │   (SendGrid)    │            │
│  └─────────────────┘    └─────────────────┘            │
│                                                         │
└─────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────┐
│                   DATA LAYER                            │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  ┌─────────────────┐    ┌─────────────────┐            │
│  │   PostgreSQL    │    │   Redis Cache   │            │
│  │   (Primary DB)  │    │   (Sessions)    │            │
│  └─────────────────┘    └─────────────────┘            │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

## Módulos Principais

### 1. User Management Module
**Responsabilidades:**
- Autenticação e autorização de usuários
- Gestão de perfis (aluno/instrutor)
- Controle de acesso baseado em roles

**Componentes:**
- `UserController` - Endpoints REST para usuários
- `UserService` - Lógica de negócio
- `UserRepository` - Acesso a dados
- `SecurityConfig` - Configurações de segurança

### 2. Graduation System Module
**Responsabilidades:**
- Controle de faixas e graus
- Histórico de progressão
- Validação de requisitos para graduação

**Componentes:**
- `GraduationController` - APIs de graduação
- `GraduationService` - Lógica de progressão
- `BeltRepository` - Gestão de faixas
- `GraduationHistoryRepository` - Histórico

### 3. AI Service Module
**Responsabilidades:**
- Integração com GPT-4 Azure
- Processamento de consultas
- Contexto específico de BJJ

**Componentes:**
- `AIController` - Endpoints de chat
- `AIService` - Integração OpenAI
- `ConversationService` - Gestão de conversas
- `ContextService` - Contexto BJJ

### 4. Belt Rules Engine
**Responsabilidades:**
- Regras configuráveis de graduação
- Validação de critérios
- Engine de decisão

**Componentes:**
- `RulesController` - Gestão de regras
- `RulesEngine` - Motor de regras
- `CriteriaValidator` - Validação
- `RulesRepository` - Persistência

### 5. Notification Service
**Responsabilidades:**
- Notificações de graduação
- Lembretes e alertas
- Comunicação multi-canal

**Componentes:**
- `NotificationService` - Serviço principal
- `EmailNotifier` - Notificações email
- `PushNotifier` - Notificações push
- `NotificationRepository` - Histórico

### 6. Audit Service
**Responsabilidades:**
- Log de atividades
- Rastreabilidade
- Compliance

**Componentes:**
- `AuditService` - Serviço de auditoria
- `AuditRepository` - Persistência
- `AuditInterceptor` - Interceptação automática

## Modelo de Dados

### Principais Entidades

```
User
├── id: UUID
├── email: String
├── name: String
├── role: UserRole (STUDENT, INSTRUCTOR, ADMIN)
├── createdAt: LocalDateTime
├── updatedAt: LocalDateTime
└── profile: UserProfile

UserProfile
├── id: UUID
├── userId: UUID
├── dateOfBirth: LocalDate
├── academy: String
├── currentBelt: Belt
├── startDate: LocalDate
└── preferences: JSON

Belt
├── id: UUID
├── name: String (WHITE, BLUE, PURPLE, BROWN, BLACK)
├── degree: Integer (0-10)
├── color: String
├── order: Integer
└── requirements: List<Requirement>

GraduationHistory
├── id: UUID
├── userId: UUID
├── fromBelt: Belt
├── toBelt: Belt
├── graduationDate: LocalDate
├── instructorId: UUID
├── notes: String
└── evidence: List<Evidence>

GraduationRule
├── id: UUID
├── fromBelt: Belt
├── toBelt: Belt
├── minimumTimeMonths: Integer
├── minimumClasses: Integer
├── minimumCompetitions: Integer
├── requirements: List<String>
└── isActive: Boolean

Conversation
├── id: UUID
├── userId: UUID
├── title: String
├── createdAt: LocalDateTime
├── updatedAt: LocalDateTime
└── messages: List<Message>

Message
├── id: UUID
├── conversationId: UUID
├── content: String
├── role: MessageRole (USER, ASSISTANT)
├── timestamp: LocalDateTime
└── metadata: JSON
```

## APIs e Integrações

### REST APIs Principais

#### Authentication API
```
POST /api/auth/login
POST /api/auth/logout
POST /api/auth/refresh
GET  /api/auth/me
```

#### Users API
```
GET    /api/users
GET    /api/users/{id}
POST   /api/users
PUT    /api/users/{id}
DELETE /api/users/{id}
GET    /api/users/{id}/profile
PUT    /api/users/{id}/profile
```

#### Graduations API
```
GET    /api/graduations
GET    /api/graduations/{userId}
POST   /api/graduations
PUT    /api/graduations/{id}
GET    /api/graduations/{userId}/history
GET    /api/graduations/rules
POST   /api/graduations/rules
```

#### AI Chat API
```
GET    /api/chat/conversations
POST   /api/chat/conversations
GET    /api/chat/conversations/{id}
POST   /api/chat/conversations/{id}/messages
DELETE /api/chat/conversations/{id}
```

### Integrações Externas

#### Azure OpenAI
- **Endpoint**: `https://{resource}.openai.azure.com/`
- **Model**: GPT-4
- **Usage**: Chat completions, embeddings
- **Authentication**: API Key + AAD

#### SendGrid (Email)
- **Endpoint**: `https://api.sendgrid.com/v3/`
- **Usage**: Transactional emails
- **Authentication**: API Key

## Arquitetura de Segurança

### Autenticação
- **JWT Tokens** com refresh mechanism
- **Azure Active Directory B2C** (opcional, para futuro)
- **Password hashing** com BCrypt
- **Session management** via Redis

### Autorização
- **Role-based access control (RBAC)**
- **Method-level security** com Spring Security
- **API rate limiting**
- **CORS configuration**

### Proteção de Dados
- **Encryption at rest** (Azure Database)
- **Encryption in transit** (HTTPS/TLS)
- **GDPR compliance** considerations
- **Data anonymization** for analytics

### Principais Roles

```
ADMIN
├── Gerenciar usuários
├── Configurar regras de graduação
├── Acessar auditoria completa
└── Administrar sistema

INSTRUCTOR
├── Graduar alunos
├── Visualizar progressão de alunos
├── Configurar regras específicas
└── Gerar relatórios

STUDENT
├── Visualizar própria progressão
├── Usar chat IA
├── Atualizar perfil
└── Visualizar histórico
```

## Arquitetura de Deployment

### Ambiente de Desenvolvimento
```
┌─────────────────┐    ┌─────────────────┐
│   Angular Dev   │    │  Spring Boot    │
│   (ng serve)    │    │  (local)        │
│   Port: 4200    │    │  Port: 8080     │
└─────────────────┘    └─────────────────┘
         │                       │
         └───────────────────────┘
                     │
         ┌─────────────────┐    ┌─────────────────┐
         │  PostgreSQL     │    │     Redis       │
         │  (Docker)       │    │   (Docker)      │
         │  Port: 5432     │    │   Port: 6379    │
         └─────────────────┘    └─────────────────┘
```

### Ambiente de Produção (Azure)
```
┌─────────────────────────────────────────┐
│           Azure Container Apps           │
├─────────────────────────────────────────┤
│                                         │
│  ┌─────────────────┐ ┌─────────────────┐ │
│  │   Frontend      │ │    Backend      │ │
│  │   Container     │ │   Container     │ │
│  │   (Nginx)       │ │ (Spring Boot)   │ │
│  └─────────────────┘ └─────────────────┘ │
│                                         │
└─────────────────────────────────────────┘
              │                 │
              ▼                 ▼
┌─────────────────┐    ┌─────────────────┐
│  Azure Database │    │  Azure Redis    │
│  for PostgreSQL │    │     Cache       │
└─────────────────┘    └─────────────────┘
```

## Fluxos Principais

### 1. Fluxo de Autenticação
```
User → Login Request → Backend Validation → JWT Generation → 
Frontend Storage → Authenticated Session
```

### 2. Fluxo de Graduação
```
Instructor → Select Student → Review Progress → Check Rules → 
Apply Graduation → Update Database → Send Notification
```

### 3. Fluxo de Chat IA
```
User → Chat Message → Context Preparation → Azure OpenAI → 
AI Response → Context Update → Display Response
```

### 4. Fluxo de Verificação de Regras
```
System → Check User Progress → Load Rules → Evaluate Criteria → 
Generate Recommendations → Notify Stakeholders
```

## Monitoramento e Observabilidade

### Logs
- **Structured logging** com Logback/SLF4J
- **Centralized logging** com Azure Log Analytics
- **Log levels** configuráveis por ambiente

### Métricas
- **Application metrics** com Micrometer
- **Infrastructure metrics** com Azure Monitor
- **Custom business metrics**

### Health Checks
- **Spring Boot Actuator** endpoints
- **Database connectivity** checks
- **External services** health
- **Redis connectivity** validation

### Alertas
- **Error rate** thresholds
- **Response time** monitoring
- **Database performance** alerts
- **Resource utilization** monitoring

## Considerações de Performance

### Caching Strategy
- **Redis** para session management
- **Application-level caching** para regras de graduação
- **Database query optimization**
- **CDN** para assets estáticos

### Database Optimization
- **Connection pooling** (HikariCP)
- **Query optimization** e indexação
- **Read replicas** para queries de leitura
- **Partitioning** para tabelas de auditoria

### Frontend Optimization
- **Lazy loading** de módulos Angular
- **Code splitting** e bundling
- **Image optimization**
- **Service Worker** para PWA

## Roadmap Técnico

### Fase 1 (MVP)
- [ ] Autenticação básica
- [ ] CRUD de usuários
- [ ] Sistema básico de graduações
- [ ] Chat IA simples

### Fase 2 (Funcionalidades Avançadas)
- [ ] Engine de regras avançado
- [ ] Notificações push
- [ ] Relatórios e analytics
- [ ] Mobile PWA

### Fase 3 (Enterprise)
- [ ] Multi-tenancy
- [ ] Integração Azure AD B2C
- [ ] API para terceiros
- [ ] Advanced analytics com ML

## Considerações de Escalabilidade

### Horizontal Scaling
- **Stateless application design**
- **Container orchestration** ready
- **Database sharding** preparação
- **Microservices** decomposition path

### Vertical Scaling
- **Resource monitoring**
- **Auto-scaling** configurations
- **Performance profiling**
- **Bottleneck identification**

Este documento serve como guia arquitetural para o desenvolvimento do BJJ Copilot, fornecendo uma visão abrangente do sistema desde a infraestrutura até os detalhes de implementação.