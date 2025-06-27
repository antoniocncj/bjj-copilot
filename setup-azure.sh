#!/bin/bash

# Azure Setup Script for BJJ Copilot Static Website
# This script helps set up the Azure infrastructure needed for hosting the BJJ Copilot application

set -e

echo "🥋 BJJ Copilot - Azure Setup Script"
echo "===================================="

# Check if Azure CLI is installed
if ! command -v az &> /dev/null; then
    echo "❌ Azure CLI is not installed. Please install it first:"
    echo "   https://docs.microsoft.com/en-us/cli/azure/install-azure-cli"
    exit 1
fi

echo "✅ Azure CLI is installed"

# Login check
if ! az account show &> /dev/null; then
    echo "🔐 Please login to Azure first:"
    az login
fi

echo "✅ Azure login verified"

# Configuration variables
RESOURCE_GROUP_NAME="bjj-copilot-rg"
STORAGE_ACCOUNT_NAME="bjjcopilot$(date +%s | tail -c 6)" # Unique name
LOCATION="East US"
SKU="Standard_LRS"

echo ""
echo "📋 Configuration:"
echo "   Resource Group: $RESOURCE_GROUP_NAME"
echo "   Storage Account: $STORAGE_ACCOUNT_NAME"
echo "   Location: $LOCATION"
echo "   SKU: $SKU"
echo ""

read -p "Do you want to proceed with this configuration? (y/N): " -n 1 -r
echo
if [[ ! $REPLY =~ ^[Yy]$ ]]; then
    echo "❌ Setup cancelled"
    exit 0
fi

echo "🚀 Starting Azure setup..."

# Create resource group
echo "📦 Creating resource group..."
az group create \
    --name $RESOURCE_GROUP_NAME \
    --location "$LOCATION"

echo "✅ Resource group created"

# Create storage account
echo "💾 Creating storage account..."
az storage account create \
    --name $STORAGE_ACCOUNT_NAME \
    --resource-group $RESOURCE_GROUP_NAME \
    --location "$LOCATION" \
    --sku $SKU \
    --kind StorageV2 \
    --allow-blob-public-access true

echo "✅ Storage account created"

# Enable static website hosting
echo "🌐 Enabling static website hosting..."
az storage blob service-properties update \
    --account-name $STORAGE_ACCOUNT_NAME \
    --static-website \
    --404-document error.html \
    --index-document index.html

echo "✅ Static website hosting enabled"

# Get storage account key
echo "🔑 Getting storage account key..."
STORAGE_KEY=$(az storage account keys list \
    --account-name $STORAGE_ACCOUNT_NAME \
    --resource-group $RESOURCE_GROUP_NAME \
    --query '[0].value' \
    --output tsv)

# Get static website URL
STATIC_WEBSITE_URL=$(az storage account show \
    --name $STORAGE_ACCOUNT_NAME \
    --query "primaryEndpoints.web" \
    --output tsv)

echo ""
echo "🎉 Setup completed successfully!"
echo ""
echo "📋 Configuration Summary:"
echo "   Resource Group: $RESOURCE_GROUP_NAME"
echo "   Storage Account: $STORAGE_ACCOUNT_NAME"
echo "   Static Website URL: $STATIC_WEBSITE_URL"
echo ""
echo "🔧 GitHub Secrets to configure:"
echo "   AZURE_STORAGE_ACCOUNT_NAME: $STORAGE_ACCOUNT_NAME"
echo "   AZURE_STORAGE_ACCOUNT_KEY: $STORAGE_KEY"
echo "   AZURE_RESOURCE_GROUP: $RESOURCE_GROUP_NAME"
echo ""
echo "📝 Next Steps:"
echo "   1. Add the secrets above to your GitHub repository"
echo "   2. Push code to main branch to trigger deployment"
echo "   3. Access your website at: $STATIC_WEBSITE_URL"
echo ""
echo "🛠️  To add secrets to GitHub:"
echo "   Go to: Settings > Secrets and variables > Actions"
echo "   Add each secret with the exact name and value shown above"