# Requisitos de Negócio - BJJ Copilot

## 1. Objetivo do Sistema

O BJJ Copilot tem como objetivo principal fornecer uma plataforma digital abrangente para o acompanhamento, gestão e ensino do Brazilian Jiu-Jitsu, atendendo tanto alunos quanto instrutores em suas jornadas na arte marcial.

## 2. Requisitos Funcionais

### 2.1 Acompanhamento de Graduações

#### RF01 - Registro de Graduação Atual
- O sistema deve permitir o registro da graduação atual do aluno (faixa e grau)
- Deve incluir data de promoção e instrutor responsável
- Deve manter histórico completo de todas as graduações anteriores

#### RF02 - Cronograma de Avaliações
- O sistema deve calcular e exibir datas estimadas para próximas avaliações
- Deve considerar tempo mínimo por graduação conforme regras da IBJJF
- Deve permitir agendamento de exames de faixa

#### RF03 - Acompanhamento de Progresso
- O sistema deve rastrear frequência de treinos
- Deve permitir registro de técnicas aprendidas
- Deve exibir estatísticas de evolução do aluno

### 2.2 Chat com IA Especializada

#### RF04 - Assistente Virtual BJJ
- O sistema deve fornecer um chat bot especializado em Brazilian Jiu-Jitsu
- Deve responder dúvidas sobre técnicas, regras e conceitos
- Deve oferecer sugestões personalizadas baseadas no nível do aluno

#### RF05 - Base de Conhecimento
- O sistema deve ter acesso a uma base de conhecimento abrangente sobre BJJ
- Deve incluir técnicas por categoria (guarda, passagem, finalizações, etc.)
- Deve estar atualizada com regras atuais de competição

### 2.3 Gestão de Academia

#### RF06 - Cadastro de Usuários
- O sistema deve permitir cadastro de alunos e instrutores
- Deve incluir informações básicas e específicas do BJJ
- Deve suportar diferentes níveis de acesso

#### RF07 - Gestão de Turmas
- Instrutores devem poder criar e gerenciar turmas
- Deve permitir associação de alunos às turmas
- Deve incluir ferramentas de comunicação com a turma

## 3. Requisitos Não Funcionais

### 3.1 Usabilidade
- Interface intuitiva e responsiva
- Suporte a dispositivos móveis e desktop
- Acessibilidade conforme padrões WCAG 2.1

### 3.2 Performance
- Tempo de resposta do chat IA inferior a 3 segundos
- Carregamento de páginas em até 2 segundos
- Suporte a pelo menos 1000 usuários simultâneos

### 3.3 Segurança
- Autenticação segura com criptografia
- Proteção de dados pessoais conforme LGPD
- Backup automático diário dos dados

### 3.4 Compatibilidade
- Suporte aos navegadores Chrome, Firefox, Safari e Edge
- Compatibilidade com iOS e Android
- API REST para integração com outros sistemas

## 4. Regras de Negócio

### 4.1 Graduação e Promoções
- Seguir regras oficiais da IBJJF para tempo mínimo entre graduações
- Apenas instrutores com faixa preta podem promover alunos para faixa preta
- Registro obrigatório de instrutor responsável pela promoção

### 4.2 Acesso e Permissões
- Alunos podem visualizar apenas seus próprios dados
- Instrutores podem gerenciar dados de seus alunos
- Administradores têm acesso completo ao sistema

### 4.3 Política de Dados
- Dados de menores de idade requerem autorização dos responsáveis
- Usuários podem solicitar exclusão de seus dados
- Histórico de graduações deve ser preservado mesmo após inativação

## 5. Critérios de Aceitação

### 5.1 MVP (Minimum Viable Product)
- Sistema de cadastro e autenticação
- Registro básico de graduações
- Chat IA funcional com conhecimento básico de BJJ
- Interface responsiva

### 5.2 Versão Completa
- Todas as funcionalidades especificadas
- Sistema de relatórios e analytics
- Integração com sistemas de pagamento
- Aplicativo móvel nativo

## 6. Limitações e Restrições

### 6.1 Técnicas
- Desenvolvimento utilizando tecnologias web modernas
- Banco de dados relacional para informações estruturadas
- API de IA externa para funcionalidades de chat

### 6.2 Regulamentares
- Conformidade com LGPD (Lei Geral de Proteção de Dados)
- Respeito às regras e tradições do Brazilian Jiu-Jitsu
- Não interferência em decisões oficiais de graduação

## 7. Stakeholders

- **Alunos de BJJ**: Usuários finais que acompanham sua evolução
- **Instrutores**: Professores que gerenciam alunos e graduações
- **Administradores de Academia**: Responsáveis pela gestão geral
- **Federações**: Entidades que regulamentam graduações oficiais