# BJJ Copilot - Evolution Tracker

Um aplicativo web simples para acompanhar a evolução de alunos de Jiu-Jitsu Brasileiro (BJJ), incluindo técnicas aprendidas, faixas e graus conquistados.

## 🥋 Sobre o Projeto

Esta é uma landing page responsiva desenvolvida em HTML, CSS e JavaScript puro que permite aos alunos de academias de Jiu-Jitsu visualizarem e acompanharem seu progresso de forma intuitiva e organizada.

### Funcionalidades

- **Perfil do Aluno**: Nome, faixa atual e grau
- **Evolução nas Técnicas**: Lista interativa de golpes com status de domínio
- **Histórico de Faixas**: Linha do tempo das conquistas
- **Design Responsivo**: Funciona perfeitamente em desktop e mobile
- **Persistência Local**: Dados salvos no localStorage do navegador

## 🚀 Deploy com GitOps

Este projeto está configurado para deploy automático usando GitHub Actions com GitOps e CI/CD.

### Opções de Deploy

#### Vercel (Recomendado) 🌟

Deploy moderno com CI/CD automático e GitOps:

```bash
# Configuração simples via Vercel CLI
npx vercel
```

**Secrets necessários:**
- `VERCEL_TOKEN`: Token de acesso do Vercel
- `VERCEL_ORG_ID`: ID da organização Vercel  
- `VERCEL_PROJECT_ID`: ID do projeto

**Recursos:**
- ✅ Deploy automático na branch `main`
- ✅ Deploy de preview em Pull Requests
- ✅ CI/CD com validação automática
- ✅ CDN global
- ✅ HTTPS automático
- ✅ Domínios personalizados

📖 **[Guia completo de setup Vercel](DEPLOY-VERCEL.md)**

#### Azure Blob Storage

Deploy tradicional para Azure:

**Secrets necessários:**
- `AZURE_STORAGE_ACCOUNT_NAME`: Nome da conta de armazenamento
- `AZURE_STORAGE_ACCOUNT_KEY`: Chave de acesso da conta

📖 **[Guia completo de setup Azure](DEPLOY.md)**

#### Netlify 🌟

Deploy moderno e fácil com CI/CD automático:

```bash
# Configuração simples via Netlify CLI
npm install -g netlify-cli
netlify init
```

**Secrets necessários:**
- `NETLIFY_AUTH_TOKEN`: Token de acesso do Netlify
- `NETLIFY_SITE_ID`: ID do site no Netlify

**Recursos:**
- ✅ Deploy automático na branch `main`
- ✅ Deploy de preview em Pull Requests
- ✅ CI/CD com validação automática
- ✅ CDN global
- ✅ HTTPS automático
- ✅ Domínios personalizados
- ✅ Formulários e funções serverless

📖 **[Guia completo de setup Netlify](DEPLOY-NETLIFY.md)**

### Fluxo GitOps

1. **Pull Request**: 
   - ✅ Validação automática (CI)
   - 🔍 Deploy de preview (Vercel/Netlify)
   - 📊 Testes de qualidade

2. **Merge na Main**:
   - ✅ Deploy automático de produção
   - 🌐 Site atualizado instantaneamente
   - 📈 Monitoramento automático

## 🛠️ Desenvolvimento Local

Para testar localmente, simplesmente abra o arquivo `bjj-tracker.html` em um navegador web moderno.

## 📁 Estrutura do Projeto

```
bjj-copilot/
├── bjj-tracker.html              # Aplicação principal (HTML + CSS + JS)
├── index.html                    # Entry point (cópia do bjj-tracker.html)
├── error.html                    # Página de erro 404
├── vercel.json                   # Configuração do Vercel
├── landingpage-prd.md            # Documento de requisitos do produto
├── DEPLOY-VERCEL.md              # Guia de deploy para Vercel
├── DEPLOY-NETLIFY.md             # Guia de deploy para Netlify
├── DEPLOY.md                     # Guia de deploy para Azure
├── .github/
│   └── workflows/
│       ├── deploy-to-vercel.yml  # Workflow de deploy para Vercel (GitOps)
│       ├── deploy-to-netlify.yml # Workflow de deploy para Netlify (GitOps)
│       └── deploy-to-azure.yml   # Workflow de deploy para Azure
└── README.md                     # Este arquivo
```

## 🎨 Personalização

O código foi desenvolvido de forma modular e comentada para facilitar customizações:

- **Cores e temas**: Modifique as variáveis CSS em `:root`
- **Dados do aluno**: Altere o objeto `studentData` no JavaScript
- **Técnicas**: Adicione ou remova técnicas no array `techniques`

## 📱 Compatibilidade

- ✅ Chrome (versões recentes)
- ✅ Firefox (versões recentes)
- ✅ Safari (versões recentes)
- ✅ Edge (versões recentes)
- ✅ Dispositivos móveis (iOS/Android)

## 📄 Licença

Este projeto é de código aberto e está disponível sob a licença MIT.