# Product Requirements Document (PRD)

## Produto
Landing Page para Alunos de Academia de Jiu-Jitsu acompanharem sua evolução nos golpes, faixas e graus.

---

## Visão Geral
Uma landing page simples que permite aos alunos de uma academia de Jiu-Jitsu visualizarem e acompanharem seu progresso nos principais golpes, faixas conquistadas e graus recebidos. A página será visualmente amigável, de fácil navegação e responsiva, utilizando somente HTML, CSS e JavaScript puro.

---

## Objetivo
- Apresentar uma interface limpa, rápida e intuitiva para acompanhamento de evolução dos alunos.
- Permitir que os alunos visualizem:
    - Nome e faixa atual
    - Lista de golpes aprendidos (com status de domínio)
    - Histórico de faixas e graus recebidos

---

## Público-alvo
- Alunos de academias de Jiu-Jitsu (iniciantes e avançados)
- Professores que desejam mostrar o progresso dos alunos

---

## Funcionalidades Principais
1. **Perfil do Aluno**
   - Nome do aluno
   - Faixa atual (ex: Branca, Azul, Roxa, Marrom, Preta)
   - Grau atual (ex: 1º, 2º, 3º grau, etc.)

2. **Evolução nos Golpes**
   - Lista dos principais golpes do Jiu-Jitsu
   - Marcação visual dos golpes já aprendidos/domínio (checkbox ou status)

3. **Histórico de Faixas e Graus**
   - Linha do tempo simples mostrando quando cada faixa/grau foi conquistado

4. **Design Simples e Responsivo**
   - Layout clean, adaptado para mobile e desktop
   - Cores relacionadas ao Jiu-Jitsu (ex: tons neutros, preto, branco, azul)

---

## Requisitos Não Funcionais
- Deve ser construído apenas com HTML, CSS e JavaScript puro (sem frameworks)
- Deve ser facilmente customizável para diferentes academias
- Carga rápida e fácil de hospedar (pode ser via GitHub Pages)

---

## Escopo Fora do Projeto (Out of Scope)
- Login/autenticação de usuário
- Banco de dados ou backend
- Upload de fotos ou arquivos
- Compartilhamento social

---

## Inspirações Visuais
- Sites de dashboard simples
- Aplicativos de acompanhamento de progresso (fitness, idiomas)

---

## Estrutura Básica Esperada

```
Header: Logo da academia e nome do aluno
|
|--- Perfil: Faixa e grau atuais
|
|--- Evolução nos Golpes: Lista de golpes + status
|
|--- Histórico: Linha do tempo de faixas e graus
|
Footer: Informações da academia
```

---

## MVP (Produto Mínimo Viável)
- Página única com informações estáticas de um aluno fictício
- Estrutura HTML clara para facilitar futura integração dinâmica
- Exemplo visual de cada seção

---

## Critérios de Aceitação
- Página carrega corretamente em desktop e mobile
- Todas as seções principais estão visíveis e organizadas
- Golpes podem ser marcados/desmarcados como aprendidos (interação via JS)
- Estrutura pronta para receber dados dinâmicos no futuro

---

## Observações
- O objetivo é ser simples, visualmente atrativo e rápido de implementar
- O código será comentado para facilitar manutenção e customização

```