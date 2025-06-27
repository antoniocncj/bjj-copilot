# 🚀 Guia de Deploy no Vercel

Este documento explica como fazer o deploy da aplicação BJJ Copilot no Vercel usando GitHub Actions com GitOps e CI/CD.

## 📋 Pré-requisitos

- Conta do Vercel ativa
- Conta do GitHub (este repositório)
- Acesso aos secrets do repositório GitHub

## 🛠️ Setup Inicial do Vercel

### Opção 1: Setup via Interface Web (Recomendado)

1. **Acesse o Vercel**:
   - Vá para [vercel.com](https://vercel.com)
   - Faça login com sua conta GitHub

2. **Importe o Projeto**:
   - Clique em "New Project"
   - Selecione este repositório (`bjj-copilot`)
   - Configure as settings básicas:
     - Framework Preset: `Other`
     - Root Directory: `./` (raiz)
     - Build Command: (deixe vazio - é um site estático)
     - Output Directory: `./` (raiz)

3. **Deploy Inicial**:
   - Clique em "Deploy"
   - Aguarde a primeira build completar

### Opção 2: Setup via Vercel CLI

```bash
# 1. Instalar Vercel CLI
npm install -g vercel

# 2. Fazer login
vercel login

# 3. Configurar o projeto
vercel

# 4. Seguir as instruções do CLI
```

## 🔐 Configuração dos Secrets no GitHub

Após criar o projeto no Vercel, você precisa configurar os secrets no GitHub:

### Obtendo os Valores Necessários:

1. **VERCEL_TOKEN**:
   - Acesse: [vercel.com/account/tokens](https://vercel.com/account/tokens)
   - Clique em "Create Token"
   - Copie o token gerado

2. **VERCEL_ORG_ID**:
   - Acesse: [vercel.com/account](https://vercel.com/account)
   - Copie o "Team ID" ou "Personal Account ID"

3. **VERCEL_PROJECT_ID**:
   - Acesse seu projeto no Vercel
   - Vá em "Settings" > "General"
   - Copie o "Project ID"

### Configurando no GitHub:

Acesse: `Settings > Secrets and variables > Actions` no seu repositório e adicione:

#### Secrets Obrigatórios:
- `VERCEL_TOKEN`: Token de acesso do Vercel
- `VERCEL_ORG_ID`: ID da organização/conta do Vercel
- `VERCEL_PROJECT_ID`: ID do projeto no Vercel

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
   - Cria deploy de preview no Vercel
   - URL de preview disponível para testes

2. **Push para Main**:
   - Executa CI (validações)
   - Deploy de produção no Vercel
   - Site atualizado automaticamente

3. **Deploy Manual**:
   - Acesse "Actions" > "Deploy to Vercel"
   - Clique em "Run workflow"
   - Executa deploy de produção

## 🌐 Acessando o Site

Após o primeiro deploy bem-sucedido, o site estará disponível em duas URLs:

### URL de Produção:
```
https://[PROJECT-NAME].vercel.app
```

### URL Personalizada (se configurada):
```
https://seu-dominio.com
```

## 📁 Estrutura de Deploy

```
bjj-copilot/
├── index.html              # Página principal (entry point)
├── bjj-tracker.html         # Arquivo fonte da aplicação
├── error.html               # Página de erro 404
├── vercel.json              # Configuração do Vercel
└── .github/workflows/
    └── deploy-to-vercel.yml # Workflow de deploy
```

### Configuração do Vercel (`vercel.json`):

- **Builds**: Configura como processar arquivos estáticos
- **Routes**: Define roteamento da aplicação
- **Headers**: Adiciona headers de segurança
- **Rewrites**: Garante que todas as rotas apontem para index.html

## 🔍 Troubleshooting

### Deploy falhou?

1. **Verifique os secrets**: Certifique-se que `VERCEL_TOKEN`, `VERCEL_ORG_ID` e `VERCEL_PROJECT_ID` estão configurados
2. **Verifique logs**: Examine os logs do GitHub Actions para erros específicos
3. **Teste local**: Use `vercel dev` para testar localmente

### Site não carrega?

1. **Aguarde propagação**: Deploy pode levar alguns segundos para ficar disponível
2. **Verifique index.html**: Certifique-se que existe e está válido
3. **Verifique console**: Abra developer tools para ver erros JavaScript

### Preview deployment não funciona?

1. **Verifique PR**: Deploy de preview só acontece em pull requests
2. **Verifique status**: Veja o status do workflow nas Actions
3. **Aguarde**: Primeiro deploy pode levar mais tempo

### Cache antigo?

1. **Hard refresh**: Ctrl+Shift+R (ou Cmd+Shift+R no Mac)
2. **Aguarde**: Cache CDN pode levar alguns minutos para atualizar
3. **Vercel dashboard**: Veja deployments no painel do Vercel

## 🎯 Configurações Avançadas

### Domínio Personalizado:

1. Acesse seu projeto no Vercel
2. Vá em "Settings" > "Domains"
3. Adicione seu domínio
4. Configure DNS conforme instruções

### Variables de Ambiente:

```bash
# Via Vercel CLI
vercel env add [VARIABLE_NAME]

# Via Dashboard
# Settings > Environment Variables
```

### Webhooks:

Configure webhooks para integrar com outros serviços:
- Settings > Git > Deploy Hooks

## 💡 Dicas Adicionais

- **Performance**: Vercel oferece CDN global automaticamente
- **HTTPS**: Habilitado automaticamente para todos os deployments
- **Analytics**: Configure Vercel Analytics para métricas
- **Monitoring**: Use Vercel Speed Insights para performance

## 📊 Monitoramento

### Vercel Dashboard:
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
1. Verifique a [documentação oficial do Vercel](https://vercel.com/docs)
2. Examine os logs detalhados do GitHub Actions
3. Teste o setup local com `vercel dev`
4. Consulte o [Discord do Vercel](https://vercel.com/discord) para suporte da comunidade

## 🔄 Migrando do Azure

Se você estava usando Azure Blob Storage anteriormente:

1. **Mantenha Azure**: Workflow Azure continua funcionando
2. **Teste Vercel**: Use branches de teste para validar
3. **Substitua gradualmente**: Quando confiante, desabilite Azure workflow
4. **Atualize DNS**: Aponte domínio para Vercel quando pronto

---

**🎉 Pronto!** Sua aplicação BJJ Copilot agora está configurada para deploy automático no Vercel com GitOps e CI/CD completo.