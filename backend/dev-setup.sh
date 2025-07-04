#!/bin/bash
# BJJ Copilot Backend - Development Setup Script

set -e

echo "🥋 BJJ Copilot Backend - Docker Development Setup"
echo "=================================================="

# Function to check if Docker is running
check_docker() {
    if ! docker info > /dev/null 2>&1; then
        echo "❌ Docker is not running or not installed"
        echo "Please install Docker Desktop and make sure it's running"
        exit 1
    fi
    echo "✅ Docker is running"
}

# Function to check if ports are available
check_ports() {
    local ports=("5432" "6379" "8080")
    for port in "${ports[@]}"; do
        if lsof -Pi :$port -sTCP:LISTEN -t >/dev/null 2>&1 || netstat -an | grep :$port | grep LISTEN >/dev/null 2>&1; then
            echo "⚠️  Port $port is already in use"
            echo "Please stop the service using port $port or choose a different port"
            exit 1
        fi
    done
    echo "✅ Required ports (5432, 6379, 8080) are available"
}

# Function to start development database services only
start_dev_services() {
    echo ""
    echo "🚀 Starting development database services (PostgreSQL + Redis)..."
    docker compose -f docker-compose.dev.yml up -d
    
    echo ""
    echo "⏳ Waiting for services to be healthy..."
    sleep 10
    
    # Check if services are healthy
    if docker compose -f docker-compose.dev.yml ps | grep -q "healthy"; then
        echo "✅ Database services are ready!"
        echo ""
        echo "📊 Service URLs:"
        echo "   PostgreSQL: localhost:5432 (bjj_copilot/bjj_user/bjj_password)"
        echo "   Redis: localhost:6379"
        echo ""
        echo "🛠️  You can now run the backend locally with:"
        echo "   SPRING_PROFILES_ACTIVE=default mvn spring-boot:run"
        echo ""
        echo "🛑 To stop services: ./dev-setup.sh stop"
    else
        echo "❌ Some services failed to start. Check logs with:"
        echo "   docker compose -f docker-compose.dev.yml logs"
    fi
}

# Function to start full stack
start_full_stack() {
    echo ""
    echo "🚀 Starting full stack (Backend + PostgreSQL + Redis)..."
    docker compose up --build -d
    
    echo ""
    echo "⏳ Waiting for services to be healthy..."
    sleep 30
    
    # Check if services are healthy
    if docker compose ps | grep -q "healthy"; then
        echo "✅ Full stack is ready!"
        echo ""
        echo "📊 Service URLs:"
        echo "   Backend API: http://localhost:8080"
        echo "   Health Check: http://localhost:8080/actuator/health"
        echo "   PostgreSQL: localhost:5432"
        echo "   Redis: localhost:6379"
        echo ""
        echo "🛑 To stop services: ./dev-setup.sh stop"
    else
        echo "❌ Some services failed to start. Check logs with:"
        echo "   docker compose logs"
    fi
}

# Function to stop services
stop_services() {
    echo ""
    echo "🛑 Stopping all services..."
    docker compose down 2>/dev/null || true
    docker compose -f docker-compose.dev.yml down 2>/dev/null || true
    echo "✅ All services stopped"
}

# Function to show logs
show_logs() {
    echo ""
    echo "📋 Service logs:"
    echo "==============="
    if docker compose ps -q > /dev/null 2>&1 && [ -n "$(docker compose ps -q)" ]; then
        docker compose logs -f
    elif docker compose -f docker-compose.dev.yml ps -q > /dev/null 2>&1 && [ -n "$(docker compose -f docker-compose.dev.yml ps -q)" ]; then
        docker compose -f docker-compose.dev.yml logs -f
    else
        echo "No running services found"
    fi
}

# Function to clean up everything
clean_all() {
    echo ""
    echo "🧹 Cleaning up Docker resources..."
    stop_services
    docker system prune -f
    docker volume prune -f
    echo "✅ Cleanup completed"
}

# Function to show status
show_status() {
    echo ""
    echo "📊 Service Status:"
    echo "=================="
    
    if docker compose ps -q > /dev/null 2>&1 && [ -n "$(docker compose ps -q)" ]; then
        echo "Full Stack:"
        docker compose ps
    fi
    
    if docker compose -f docker-compose.dev.yml ps -q > /dev/null 2>&1 && [ -n "$(docker compose -f docker-compose.dev.yml ps -q)" ]; then
        echo ""
        echo "Development Services:"
        docker compose -f docker-compose.dev.yml ps
    fi
    
    if ! docker compose ps -q > /dev/null 2>&1 || [ -z "$(docker compose ps -q)" ]; then
        if ! docker compose -f docker-compose.dev.yml ps -q > /dev/null 2>&1 || [ -z "$(docker compose -f docker-compose.dev.yml ps -q)" ]; then
            echo "No services are currently running"
        fi
    fi
}

# Main script logic
case "${1:-help}" in
    "dev")
        check_docker
        check_ports
        stop_services
        start_dev_services
        ;;
    "full")
        check_docker
        check_ports
        stop_services
        start_full_stack
        ;;
    "stop")
        stop_services
        ;;
    "logs")
        show_logs
        ;;
    "status")
        show_status
        ;;
    "clean")
        clean_all
        ;;
    "help"|*)
        echo ""
        echo "Usage: $0 <command>"
        echo ""
        echo "Commands:"
        echo "  dev     - Start development database services only (PostgreSQL + Redis)"
        echo "  full    - Start full stack (Backend + PostgreSQL + Redis)"
        echo "  stop    - Stop all services"
        echo "  status  - Show service status"
        echo "  logs    - Show service logs (follow mode)"
        echo "  clean   - Stop services and clean up Docker resources"
        echo "  help    - Show this help message"
        echo ""
        echo "Examples:"
        echo "  $0 dev     # Start databases, run backend locally with Maven"
        echo "  $0 full    # Start everything in Docker"
        echo "  $0 stop    # Stop all services"
        echo ""
        ;;
esac