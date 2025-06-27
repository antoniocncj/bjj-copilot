# 🚀 Guia de Deploy no Netlify

Este documento explica como fazer o deploy da aplicação BJJ Copilot no Netlify usando GitHub Actions com GitOps e CI/CD.

## 📋 Pré-requisitos

- Conta do Netlify ativa
- Conta do GitHub (este repositório)
- Acesso aos secrets do repositório GitHub

## 🛠️ Setup Inicial do Netlify

### Opção 1: Setup Automático via Netlify CLI

```bash
# Instalar Netlify CLI
npm install -g netlify-cli

# Fazer login no Netlify
netlify login

# Criar novo site (na raiz do projeto)
netlify init

# Seguir instruções interativas
```

### Opção 2: Setup Manual via Dashboard

1. **Acesse o Netlify Dashboard**:
   - Vá para [app.netlify.com](https://app.netlify.com)
   - Faça login em sua conta

2. **Criar Novo Site**:
   - Clique em "Add new site" > "Deploy manually"
   - Ou "Import from Git" se quiser conectar diretamente ao repositório

3. **Configurar o Site**:
   - **Site name**: `bjj-copilot-[seu-nome]` (ou nome de sua escolha)
   - **Build settings**: Não necessário (site estático simples)
   - **Publish directory**: `dist` (será criado pelo workflow)

## 🔐 Configuração dos Secrets no GitHub

Após criar o projeto no Netlify, você precisa configurar os secrets no GitHub:

### Obtendo os Valores Necessários:

1. **NETLIFY_AUTH_TOKEN**:
   - Acesse: [app.netlify.com/user/applications](https://app.netlify.com/user/applications)
   - Clique em "New access token"
   - Dê um nome descritivo (ex: "GitHub Actions BJJ Copilot")
   - Copie o token gerado

2. **NETLIFY_SITE_ID**:
   - Acesse seu site no Netlify Dashboard
   - Vá em "Site settings" > "General"
   - Copie o "Site ID" (API ID)

### Configurando no GitHub:

Acesse: `Settings > Secrets and variables > Actions` no seu repositório e adicione:

#### Secrets Obrigatórios:
- `NETLIFY_AUTH_TOKEN`: Token de acesso do Netlify
- `NETLIFY_SITE_ID`: ID do site no Netlify

## 🚀 Deploy Automático com GitOps

O deploy acontece automaticamente seguindo os princípios GitOps:

### Continuous Integration (CI):
- **Validação de código**: Verifica integridade dos arquivos HTML
- **Testes de qualidade**: Executa validações básicas
- **Verificação de formato**: Checa encoding e estrutura dos arquivos

### Continuous Deployment (CD):
- **Deploy automático na main**: Todo push na branch `main` gera deploy de produção
- **Deploy de preview**: Todo pull request gera deploy de preview
- **Deploy manual**: Pode ser executado manualmente via Actions

### Fluxos de Deploy:

1. **Pull Request**: 
   - Executa CI (validações)
   - Cria deploy de preview no Netlify
   - URL de preview disponível para testes

2. **Push para Main**:
   - Executa CI (validações)
   - Deploy de produção no Netlify
   - Site atualizado automaticamente

3. **Deploy Manual**:
   - Acesse "Actions" > "Deploy to Netlify"
   - Clique em "Run workflow"
   - Executa deploy de produção

## 🌐 Acessando o Site

Após o primeiro deploy bem-sucedido, o site estará disponível em duas URLs:

### URL de Produção:
```
https://[SITE-NAME].netlify.app
```

### URL Personalizada (se configurada):
```
https://seu-dominio.com
```

## 📁 Estrutura de Deploy

```
dist/                           # Diretório de publicação
├── index.html                  # Página principal (cópia do bjj-tracker.html)
└── error.html                  # Página de erro 404 (opcional)
```

## 🔍 Troubleshooting

### Deploy falhou?

1. **Verifique os secrets**: Certifique-se que `NETLIFY_AUTH_TOKEN` e `NETLIFY_SITE_ID` estão configurados
2. **Verifique logs**: Examine os logs do GitHub Actions para erros específicos
3. **Teste local**: Use `netlify dev` para testar localmente

### Site não carrega?

1. **Aguarde propagação**: Deploy pode levar alguns segundos para ficar disponível
2. **Verifique index.html**: Certifique-se que existe e está válido
3. **Verifique console**: Abra developer tools para ver erros JavaScript

### Preview deployment não funciona?

1. **Verifique PR**: Deploy de preview só acontece em pull requests
2. **Verifique status**: Veja o status do workflow nas Actions
3. **Aguarde**: Primeiro deploy pode levar mais tempo

### Erros de autenticação?

1. **Token expirado**: Gere um novo token no Netlify
2. **Site ID incorreto**: Verifique o Site ID no dashboard do Netlify
3. **Permissões**: Certifique-se que o token tem permissões adequadas

## 🎯 Configurações Avançadas

### Domínio Personalizado:

1. Acesse seu site no Netlify Dashboard
2. Vá em "Domain settings" > "Custom domains"
3. Clique em "Add custom domain"
4. Configure DNS conforme instruções

### Variables de Ambiente:

```bash
# Via Netlify CLI
netlify env:set VARIABLE_NAME "value"

# Via Dashboard
# Site settings > Environment variables
```

### Redirects e Headers:

Crie um arquivo `_redirects` ou `netlify.toml` na raiz:

```toml
# netlify.toml
[[redirects]]
  from = "/*"
  to = "/index.html"
  status = 200

[[headers]]
  for = "/*"
  [headers.values]
    X-Frame-Options = "DENY"
    X-Content-Type-Options = "nosniff"
```

### Formulários e Funções:

- **Netlify Forms**: Para formulários de contato
- **Netlify Functions**: Para funcionalidades serverless

## 💡 Dicas Adicionais

- **Performance**: Netlify oferece CDN global automaticamente
- **HTTPS**: Habilitado automaticamente para todos os deployments
- **Analytics**: Configure Netlify Analytics para métricas detalhadas
- **Split Testing**: Use A/B testing para diferentes versões

## 📊 Monitoramento

### Netlify Dashboard:
- Deployments recentes
- Métricas de performance
- Logs de build
- Analytics de tráfego

### GitHub Actions:
- Status dos workflows
- Logs detalhados de CI/CD
- Histórico de deployments

## 📞 Suporte

Em caso de problemas:
1. Verifique a [documentação oficial do Netlify](https://docs.netlify.com)
2. Examine os logs detalhados do GitHub Actions
3. Teste o setup local com `netlify dev`
4. Consulte o [fórum da comunidade Netlify](https://answers.netlify.com) para suporte

## 🔄 Migrando de Outras Plataformas

Se você estava usando outras plataformas (Azure, Vercel) anteriormente:

1. **Mantenha workflows existentes**: Outros workflows continuam funcionando
2. **Teste Netlify**: Use branches de teste para validar
3. **Substitua gradualmente**: Quando confiante, desabilite outros workflows
4. **Atualize DNS**: Aponte domínio para Netlify quando pronto

---

**🎉 Pronto!** Sua aplicação BJJ Copilot agora está configurada para deploy automático no Netlify com GitOps e CI/CD completo.