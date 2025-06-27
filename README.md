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

## 🚀 Deploy no Azure Blob Storage

Este projeto está configurado para deploy automático no Azure Blob Storage através do GitHub Actions.

### Configuração Necessária

Para configurar o deploy automático, adicione os seguintes secrets no seu repositório GitHub:

#### Secrets Obrigatórios:
- `AZURE_STORAGE_ACCOUNT_NAME`: Nome da conta de armazenamento do Azure
- `AZURE_STORAGE_ACCOUNT_KEY`: Chave de acesso da conta de armazenamento

#### Secrets Opcionais (para CDN):
- `AZURE_RESOURCE_GROUP`: Nome do grupo de recursos
- `AZURE_CDN_PROFILE_NAME`: Nome do perfil CDN
- `AZURE_CDN_ENDPOINT_NAME`: Nome do endpoint CDN

### Configuração do Azure Blob Storage

1. **Criar Storage Account**:
   ```bash
   az storage account create \
     --name <storage-account-name> \
     --resource-group <resource-group> \
     --location <location> \
     --sku Standard_LRS \
     --kind StorageV2
   ```

2. **Habilitar Static Website Hosting**:
   ```bash
   az storage blob service-properties update \
     --account-name <storage-account-name> \
     --static-website \
     --404-document error.html \
     --index-document index.html
   ```

3. **Obter a URL do site**:
   ```bash
   az storage account show \
     --name <storage-account-name> \
     --query "primaryEndpoints.web" \
     --output tsv
   ```

### Estrutura do Deploy

O workflow do GitHub Actions:
1. Copia `bjj-tracker.html` para `dist/index.html`
2. Faz upload dos arquivos para o container `$web` do Azure Storage
3. Opcionalmente, limpa o cache do CDN se configurado

## 🛠️ Desenvolvimento Local

Para testar localmente, simplesmente abra o arquivo `bjj-tracker.html` em um navegador web moderno.

## 📁 Estrutura do Projeto

```
bjj-copilot/
├── bjj-tracker.html          # Aplicação principal (HTML + CSS + JS)
├── landingpage-prd.md        # Documento de requisitos do produto
├── .github/
│   └── workflows/
│       └── deploy-to-azure.yml  # Workflow de deploy para Azure
└── README.md                 # Este arquivo
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