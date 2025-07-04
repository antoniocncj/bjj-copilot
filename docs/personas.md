# Personas e Papéis - BJJ Copilot

## 1. Visão Geral

Este documento define as personas principais e seus papéis no sistema BJJ Copilot, detalhando necessidades, objetivos e comportamentos de cada tipo de usuário.

## 2. Personas Principais

### 2.1 Aluno Iniciante - "Carlos Silva"

**Perfil Demográfico:**
- Idade: 28 anos
- Profissão: Analista de TI
- Graduação: Faixa Branca (2 listras)
- Tempo de prática: 8 meses

**Características:**
- Iniciante no BJJ, ainda aprendendo fundamentos
- Ansioso para evoluir e entender seu progresso
- Gosta de tecnologia e busca informações online
- Treina 3-4 vezes por semana

**Objetivos:**
- Acompanhar seu progresso de forma clara
- Entender quando será elegível para próxima graduação
- Tirar dúvidas sobre técnicas básicas
- Manter motivação para continuar treinando

**Necessidades no Sistema:**
- Interface simples e intuitiva
- Feedback claro sobre progresso
- Chat IA para dúvidas básicas
- Cronograma de evolução personalizado

**Frustrações:**
- Não saber se está progredindo adequadamente
- Dificuldade para lembrar técnicas aprendidas
- Incerteza sobre quando será promovido

### 2.2 Aluno Intermediário - "Marina Santos"

**Perfil Demográfico:**
- Idade: 24 anos
- Profissão: Estudante de Educação Física
- Graduação: Faixa Azul (3 listras)
- Tempo de prática: 3 anos

**Características:**
- Competidora ativa em torneios locais
- Focada em aperfeiçoamento técnico
- Treina 5-6 vezes por semana
- Interesse em ensinar no futuro

**Objetivos:**
- Preparar-se para competições
- Aprofundar conhecimento em áreas específicas
- Acompanhar estatísticas de treino
- Planejar próximos passos na graduação

**Necessidades no Sistema:**
- Registro detalhado de técnicas praticadas
- Análise de pontos fortes e fracos
- Chat IA para estratégias avançadas
- Comparação com outros alunos do mesmo nível

**Frustrações:**
- Falta de feedback específico sobre pontos a melhorar
- Dificuldade para organizar conhecimento técnico

### 2.3 Instrutor - "Professor João Mendes"

**Perfil Demográfico:**
- Idade: 35 anos
- Profissão: Professor de BJJ (tempo integral)
- Graduação: Faixa Preta (2º grau)
- Tempo de prática: 15 anos
- Responsável por 60+ alunos

**Características:**
- Dedicado ao ensino e desenvolvimento dos alunos
- Valoriza tradição, mas abraça tecnologia útil
- Preocupado com qualidade das graduações
- Gestor de academia

**Objetivos:**
- Acompanhar progresso de todos os alunos
- Tomar decisões informadas sobre promoções
- Otimizar tempo de gestão administrativa
- Manter qualidade do ensino

**Necessidades no Sistema:**
- Dashboard com visão geral da turma
- Ferramentas de avaliação de alunos
- Sistema de comunicação eficiente
- Relatórios de progresso detalhados
- Gestão de graduações e promoções

**Frustrações:**
- Tempo excessivo gasto em tarefas administrativas
- Dificuldade para acompanhar progresso individual
- Falta de dados objetivos para decisões de graduação

### 2.4 Administrador de Academia - "Roberto Costa"

**Perfil Demográfico:**
- Idade: 42 anos
- Profissão: Proprietário de Academia
- Graduação: Faixa Preta (3º grau)
- Experiência: 20+ anos

**Características:**
- Foco em gestão e crescimento do negócio
- Interesse em métricas e resultados
- Responsável por múltiplos instrutores
- Visão estratégica de longo prazo

**Objetivos:**
- Aumentar retenção de alunos
- Otimizar operações da academia
- Manter padrão de qualidade
- Expandir base de alunos

**Necessidades no Sistema:**
- Relatórios analíticos abrangentes
- Métricas de retenção e engajamento
- Ferramentas de gestão multi-instrutor
- Controle de qualidade das graduações

## 3. Papéis e Permissões

### 3.1 Papel: Aluno

**Permissões:**
- Visualizar próprio perfil e histórico
- Atualizar informações pessoais básicas
- Acessar chat IA
- Registrar treinos e observações pessoais
- Visualizar cronograma pessoal

**Restrições:**
- Não pode alterar graduação
- Acesso limitado a dados de outros alunos
- Não pode acessar ferramentas administrativas

### 3.2 Papel: Instrutor

**Permissões:**
- Todas as permissões de Aluno
- Gerenciar alunos de suas turmas
- Registrar graduações e promoções
- Criar e modificar planos de aula
- Acessar relatórios de turma
- Comunicar-se com grupos de alunos

**Restrições:**
- Não pode promover acima de sua própria graduação
- Acesso limitado a alunos de outras turmas
- Não pode alterar configurações do sistema

### 3.3 Papel: Administrador

**Permissões:**
- Todas as permissões de Instrutor
- Gerenciar todos os usuários do sistema
- Acessar todos os relatórios e analytics
- Configurar parâmetros do sistema
- Gerenciar instrutores e suas permissões
- Backup e restore de dados

**Restrições:**
- Deve respeitar regras de graduação estabelecidas
- Auditoria de todas as ações sensíveis

## 4. Jornadas do Usuário

### 4.1 Jornada do Aluno Iniciante

1. **Cadastro**: Registro inicial com informações básicas
2. **Primeiro Acesso**: Tutorial sobre funcionalidades
3. **Configuração**: Definição de objetivos pessoais
4. **Uso Diário**: Registro de treinos e consulta ao chat IA
5. **Acompanhamento**: Visualização regular do progresso
6. **Promoção**: Celebração de nova graduação

### 4.2 Jornada do Instrutor

1. **Configuração**: Setup inicial de turmas e alunos
2. **Gestão Diária**: Registro de presenças e observações
3. **Avaliação**: Análise periódica do progresso dos alunos
4. **Promoção**: Processo de graduação de alunos
5. **Relatórios**: Análise de métricas da turma
6. **Planejamento**: Definição de próximos objetivos

## 5. Cenários de Uso

### 5.1 Aluno consultando progresso antes de exame
- **Contexto**: Aluno próximo de avaliação quer verificar se está pronto
- **Ação**: Acessa dashboard pessoal e revisa métricas
- **Resultado**: Decisão informada sobre participação no exame

### 5.2 Instrutor avaliando aluno para promoção
- **Contexto**: Período de graduações se aproximando
- **Ação**: Revisa histórico completo e métricas do aluno
- **Resultado**: Decisão baseada em dados objetivos

### 5.3 Administrador analisando retenção
- **Contexto**: Revisão mensal de métricas da academia
- **Ação**: Consulta relatórios de engajamento e frequência
- **Resultado**: Identificação de oportunidades de melhoria