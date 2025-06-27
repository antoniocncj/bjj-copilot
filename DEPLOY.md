# 🚀 Guia de Deploy no Azure Blob Storage

Este documento explica como fazer o deploy da aplicação BJJ Copilot no Azure Blob Storage usando o GitHub Actions.

## 📋 Pré-requisitos

- Conta do Azure ativa
- Azure CLI instalado (para setup inicial)
- Repositório GitHub com as configurações de deploy

## 🛠️ Setup Inicial do Azure

### Opção 1: Script Automático

Execute o script de setup incluído no repositório:

```bash
./setup-azure.sh
```

Este script irá:
- Criar um Resource Group
- Criar uma Storage Account com nome único
- Habilitar static website hosting
- Fornecer as informações necessárias para configurar os secrets do GitHub

### Opção 2: Setup Manual

```bash
# 1. Fazer login no Azure
az login

# 2. Criar Resource Group
az group create --name bjj-copilot-rg --location "East US"

# 3. Criar Storage Account (substitua UNIQUE_NAME por um nome único)
az storage account create \
  --name bjjcopilotUNIQUE_NAME \
  --resource-group bjj-copilot-rg \
  --location "East US" \
  --sku Standard_LRS \
  --kind StorageV2

# 4. Habilitar static website hosting
az storage blob service-properties update \
  --account-name bjjcopilotUNIQUE_NAME \
  --static-website \
  --404-document error.html \
  --index-document index.html

# 5. Obter a chave da storage account
az storage account keys list \
  --account-name bjjcopilotUNIQUE_NAME \
  --resource-group bjj-copilot-rg \
  --query '[0].value' \
  --output tsv

# 6. Obter URL do site estático
az storage account show \
  --name bjjcopilotUNIQUE_NAME \
  --query "primaryEndpoints.web" \
  --output tsv
```

## 🔐 Configuração dos Secrets no GitHub

Acesse: `Settings > Secrets and variables > Actions` no seu repositório e adicione:

### Secrets Obrigatórios:
- `AZURE_STORAGE_ACCOUNT_NAME`: Nome da storage account criada
- `AZURE_STORAGE_ACCOUNT_KEY`: Chave de acesso da storage account

### Secrets Opcionais (para CDN):
- `AZURE_RESOURCE_GROUP`: Nome do resource group
- `AZURE_CDN_PROFILE_NAME`: Nome do perfil CDN (se usar)
- `AZURE_CDN_ENDPOINT_NAME`: Nome do endpoint CDN (se usar)

## 🚀 Deploy Automático

O deploy acontece automaticamente quando:
- Código é enviado para a branch `main`
- Manualmente via `Actions` tab no GitHub (workflow_dispatch)

### O que acontece no deploy:

1. **Preparação**: Copia arquivos necessários para diretório `dist/`
2. **Upload**: Envia arquivos para Azure Blob Storage ($web container)
3. **Cache**: Limpa cache do CDN (se configurado)
4. **Confirmação**: Fornece URL do site deployado

## 🌐 Acessando o Site

Após o primeiro deploy bem-sucedido, o site estará disponível na URL:
```
https://[STORAGE_ACCOUNT_NAME].z13.web.core.windows.net/
```

## 📁 Estrutura de Deploy

```
dist/
├── index.html          # Página principal (cópia de bjj-tracker.html)
├── error.html          # Página de erro 404
└── README.md           # Documentação
```

## 🔍 Troubleshooting

### Deploy falhou?

1. **Verifique os secrets**: Certifique-se que estão configurados corretamente
2. **Verifique permissões**: A storage account deve permitir acesso público aos blobs
3. **Verifique logs**: Examine os logs do GitHub Actions para erros específicos

### Site não carrega?

1. **Verifique URL**: Use a URL do static website, não do blob storage
2. **Aguarde propagação**: Pode levar alguns minutos para ficar disponível
3. **Verifique index.html**: Certifique-se que foi copiado corretamente

### Cache antigo?

1. **Purge manual**: Execute purge do CDN se estiver usando
2. **Ctrl+F5**: Force refresh no navegador
3. **Aguarde**: Cache pode levar até 1 hora para expirar

## 💡 Dicas Adicionais

- **Custom domain**: Configure um domínio personalizado nas configurações da storage account
- **HTTPS**: É habilitado automaticamente para static websites
- **CDN**: Adicione Azure CDN para melhor performance global
- **Monitoring**: Configure alertas para monitorar disponibilidade

## 📞 Suporte

Em caso de problemas:
1. Verifique a documentação oficial do Azure Static Websites
2. Examine os logs detalhados do GitHub Actions
3. Teste o setup local antes de fazer push para main