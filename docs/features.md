# Especificações de Funcionalidades - BJJ Copilot

## 1. Visão Geral das Funcionalidades

Este documento detalha as especificações técnicas e funcionais de cada feature do BJJ Copilot.

## 2. Sistema de Acompanhamento de Graduações

### 2.1 Dashboard de Progresso Pessoal

#### Funcionalidade
Interface principal onde o aluno visualiza seu progresso atual e próximos objetivos.

#### Especificações Técnicas
- **Componentes visuais**: Barra de progresso, cronômetro, gráficos
- **Dados exibidos**: 
  - Graduação atual (faixa e graus)
  - Tempo na graduação atual
  - Tempo restante estimado para próxima graduação
  - Frequência de treinos (últimos 30/90 dias)
  - Próximas metas e objetivos

#### Regras de Negócio
- Cálculo automático baseado em tempo mínimo IBJJF
- Atualização em tempo real conforme atividade
- Personalização baseada no perfil do aluno

#### Critérios de Aceitação
- [ ] Exibir graduação atual corretamente
- [ ] Calcular tempo restante baseado em regras IBJJF
- [ ] Mostrar progresso visual intuitivo
- [ ] Ser responsivo em dispositivos móveis
- [ ] Carregar em menos de 2 segundos

### 2.2 Histórico de Graduações

#### Funcionalidade
Registro completo de todas as graduações do aluno, incluindo datas, instrutores e observações.

#### Especificações Técnicas
- **Formato**: Timeline cronológica
- **Dados armazenados**:
  - Data da graduação
  - Graduação anterior e nova
  - Instrutor responsável
  - Observações e comentários
  - Fotos da cerimônia (opcional)

#### Regras de Negócio
- Histórico imutável após criação
- Apenas instrutores autorizados podem adicionar graduações
- Backup automático de todos os registros

### 2.3 Calculadora de Elegibilidade

#### Funcionalidade
Sistema que calcula automaticamente quando um aluno se torna elegível para avaliação.

#### Especificações Técnicas
- **Algoritmo**: Baseado em tempo mínimo + critérios adicionais
- **Fatores considerados**:
  - Tempo na graduação atual
  - Frequência de treinos
  - Performance em avaliações
  - Comportamento e disciplina

#### Regras de Negócio
- Alertas automáticos para instrutores
- Notificações para alunos 30 dias antes da elegibilidade
- Consideração de feriados e pausas na academia

## 3. Chat com IA Especializada

### 3.1 Motor de IA BJJ

#### Funcionalidade
Assistente virtual especializado em Brazilian Jiu-Jitsu capaz de responder dúvidas técnicas e conceituais.

#### Especificações Técnicas
- **Base de conhecimento**:
  - Técnicas por categoria (guarda, passagem, finalizações, escapes)
  - Regras de competição (IBJJF, ADCC, etc.)
  - Conceitos e filosofia do BJJ
  - Histórico e evolução da arte
- **Tecnologia**: Large Language Model especializado
- **Integração**: API REST com resposta em JSON
- **Personalização**: Respostas adaptadas ao nível do usuário

#### Regras de Negócio
- Respostas devem ser seguras e não incentivar técnicas perigosas
- Não substitui orientação de instrutor qualificado
- Disclaimers sobre limitações e necessidade de prática supervisionada

#### Critérios de Aceitação
- [ ] Responder em menos de 3 segundos
- [ ] Reconhecer nível do usuário e adaptar linguagem
- [ ] Fornecer respostas precisas sobre técnicas básicas
- [ ] Incluir disclaimers de segurança quando apropriado
- [ ] Manter contexto da conversa por sessão

### 3.2 Sugestões Personalizadas

#### Funcionalidade
Sistema que oferece recomendações baseadas no perfil e progresso do aluno.

#### Especificações Técnicas
- **Machine Learning**: Algoritmo de recomendação
- **Dados de entrada**:
  - Graduação atual
  - Histórico de treinos
  - Áreas de dificuldade identificadas
  - Objetivos declarados pelo aluno
- **Tipos de sugestão**:
  - Técnicas para praticar
  - Vídeos educativos
  - Competições adequadas ao nível

#### Regras de Negócio
- Sugestões atualizadas semanalmente
- Baseadas em dados reais de progresso
- Aprovação de instrutores para recomendações avançadas

### 3.3 Base de Conhecimento Interativa

#### Funcionalidade
Biblioteca searchável de técnicas, conceitos e materiais educativos.

#### Especificações Técnicas
- **Categorização hierárquica**:
  - Por posição (guarda fechada, guarda aberta, etc.)
  - Por objetivo (finalização, passagem, sweep, escape)
  - Por nível de dificuldade
  - Por estilo/escola
- **Mídia suportada**:
  - Descrições textuais
  - Diagramas e ilustrações
  - Vídeos instrucionais
  - GIFs demonstrativos

#### Regras de Negócio
- Conteúdo validado por instrutores qualificados
- Versionamento para atualizações
- Acesso gradual baseado na graduação do aluno

## 4. Sistema de Gestão para Instrutores

### 4.1 Dashboard do Instrutor

#### Funcionalidade
Interface centralizada para gestão de turmas, alunos e atividades administrativas.

#### Especificações Técnicas
- **Widgets principais**:
  - Lista de alunos por turma
  - Alunos elegíveis para graduação
  - Estatísticas de frequência
  - Agenda de aulas e eventos
  - Notificações importantes

#### Regras de Negócio
- Acesso restrito aos alunos sob responsabilidade do instrutor
- Atualização automática de métricas
- Backup diário de dados gerenciados

### 4.2 Sistema de Avaliação

#### Funcionalidade
Ferramentas para avaliar progresso dos alunos e tomar decisões de graduação.

#### Especificações Técnicas
- **Formulários de avaliação**:
  - Aspectos técnicos (nota 1-10)
  - Aspectos comportamentais (satisfatório/melhorar)
  - Observações livres
  - Recomendações de foco
- **Histórico de avaliações**:
  - Timeline de todas as avaliações
  - Comparação de progresso ao longo do tempo
  - Métricas automáticas de melhoria

#### Regras de Negócio
- Avaliações devem ser regulares (mínimo mensais)
- Campos obrigatórios para decisões de graduação
- Sistema de aprovação para graduações críticas

### 4.3 Comunicação com Alunos

#### Funcionalidade
Sistema de mensagens e comunicados para turmas e alunos individuais.

#### Especificações Técnicas
- **Tipos de comunicação**:
  - Mensagens individuais
  - Comunicados para turma
  - Avisos gerais da academia
  - Notificações automáticas do sistema
- **Canais suportados**:
  - In-app notifications
  - Email
  - SMS (opcional)
  - Push notifications (app móvel)

#### Regras de Negócio
- Mensagens arquivadas permanentemente
- Opt-out disponível para comunicações não-críticas
- Escalação automática para mensagens urgentes

## 5. Relatórios e Analytics

### 5.1 Relatórios de Progresso

#### Funcionalidade
Relatórios detalhados sobre progresso individual e de turma.

#### Especificações Técnicas
- **Relatórios disponíveis**:
  - Progresso individual (mensal/semestral)
  - Performance da turma
  - Análise de retenção
  - Estatísticas de graduação
- **Formatos de exportação**:
  - PDF para impressão
  - Excel para análise
  - Visualizações interativas web

#### Regras de Negócio
- Geração automática mensal
- Acesso restrito conforme permissões
- Histórico de relatórios mantido por 5 anos

### 5.2 Métricas de Engajamento

#### Funcionalidade
KPIs e métricas para acompanhar saúde da academia e engajamento dos alunos.

#### Especificações Técnicas
- **Métricas principais**:
  - Taxa de retenção por graduação
  - Frequência média de treinos
  - Tempo médio para graduação
  - Satisfação dos alunos (NPS)
  - Utilização do chat IA

#### Regras de Negócio
- Atualização em tempo real
- Benchmarking com médias do sistema
- Alertas para métricas críticas

## 6. Funcionalidades Mobile

### 6.1 App Nativo

#### Funcionalidades Core
- Dashboard de progresso simplificado
- Chat IA otimizado para mobile
- Notificações push
- Check-in para aulas
- Visualização de cronograma

#### Especificações Técnicas
- **Plataformas**: iOS e Android
- **Framework**: React Native ou Flutter
- **Offline capability**: Dados básicos cached
- **Sincronização**: Automática quando online

### 6.2 Progressive Web App (PWA)

#### Funcionalidade
Versão web responsiva que funciona como app nativo.

#### Especificações Técnicas
- **Recursos PWA**:
  - Instalável na home screen
  - Funcionamento offline limitado
  - Push notifications
  - Cache inteligente de conteúdo

## 7. Integrações Externas

### 7.1 Sistemas de Pagamento

#### Funcionalidade
Integração para gestão de mensalidades e pagamentos.

#### Especificações Técnicas
- **Provedores suportados**: Stripe, PagSeguro, Mercado Pago
- **Funcionalidades**:
  - Cobrança recorrente de mensalidades
  - Pagamento de eventos e seminários
  - Relatórios financeiros básicos

### 7.2 Plataformas de Competição

#### Funcionalidade
Integração com sistemas de inscrição em torneios.

#### Especificações Técnicas
- **APIs suportadas**: Smoothcomp, Eventbrite
- **Funcionalidades**:
  - Verificação automática de graduação
  - Inscrições facilitadas
  - Importação de resultados

## 8. Segurança e Privacidade

### 8.1 Autenticação e Autorização

#### Especificações Técnicas
- **Métodos de autenticação**:
  - Email/senha com 2FA opcional
  - Login social (Google, Facebook)
  - Biometria (mobile)
- **Controle de acesso**: Role-based (RBAC)

### 8.2 Proteção de Dados

#### Especificações Técnicas
- **Criptografia**: AES-256 para dados sensíveis
- **HTTPS**: Obrigatório para todas as comunicações
- **LGPD Compliance**: 
  - Consentimento explícito
  - Direito ao esquecimento
  - Portabilidade de dados