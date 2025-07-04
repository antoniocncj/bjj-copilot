@echo off
REM BJJ Copilot Backend - Development Setup Script for Windows

setlocal enabledelayedexpansion

echo 🥋 BJJ Copilot Backend - Docker Development Setup
echo ==================================================

REM Function to check if Docker is running
:check_docker
docker info >nul 2>&1
if errorlevel 1 (
    echo ❌ Docker is not running or not installed
    echo Please install Docker Desktop and make sure it's running
    exit /b 1
)
echo ✅ Docker is running
goto :eof

REM Function to start development database services only
:start_dev_services
echo.
echo 🚀 Starting development database services ^(PostgreSQL + Redis^)...
docker compose -f docker-compose.dev.yml up -d

echo.
echo ⏳ Waiting for services to be healthy...
timeout /t 10 /nobreak >nul

REM Check if services are running
docker compose -f docker-compose.dev.yml ps | findstr "Up" >nul
if errorlevel 1 (
    echo ❌ Some services failed to start. Check logs with:
    echo    docker compose -f docker-compose.dev.yml logs
) else (
    echo ✅ Database services are ready!
    echo.
    echo 📊 Service URLs:
    echo    PostgreSQL: localhost:5432 ^(bjj_copilot/bjj_user/bjj_password^)
    echo    Redis: localhost:6379
    echo.
    echo 🛠️  You can now run the backend locally with:
    echo    set SPRING_PROFILES_ACTIVE=default ^&^& mvn spring-boot:run
    echo.
    echo 🛑 To stop services: dev-setup.bat stop
)
goto :eof

REM Function to start full stack
:start_full_stack
echo.
echo 🚀 Starting full stack ^(Backend + PostgreSQL + Redis^)...
docker compose up --build -d

echo.
echo ⏳ Waiting for services to be healthy...
timeout /t 30 /nobreak >nul

REM Check if services are running
docker compose ps | findstr "Up" >nul
if errorlevel 1 (
    echo ❌ Some services failed to start. Check logs with:
    echo    docker compose logs
) else (
    echo ✅ Full stack is ready!
    echo.
    echo 📊 Service URLs:
    echo    Backend API: http://localhost:8080
    echo    Health Check: http://localhost:8080/actuator/health
    echo    PostgreSQL: localhost:5432
    echo    Redis: localhost:6379
    echo.
    echo 🛑 To stop services: dev-setup.bat stop
)
goto :eof

REM Function to stop services
:stop_services
echo.
echo 🛑 Stopping all services...
docker compose down 2>nul
docker compose -f docker-compose.dev.yml down 2>nul
echo ✅ All services stopped
goto :eof

REM Function to show logs
:show_logs
echo.
echo 📋 Service logs:
echo ===============
docker compose ps -q >nul 2>&1
if not errorlevel 1 (
    docker compose logs -f
) else (
    docker compose -f docker-compose.dev.yml ps -q >nul 2>&1
    if not errorlevel 1 (
        docker compose -f docker-compose.dev.yml logs -f
    ) else (
        echo No running services found
    )
)
goto :eof

REM Function to clean up everything
:clean_all
echo.
echo 🧹 Cleaning up Docker resources...
call :stop_services
docker system prune -f
docker volume prune -f
echo ✅ Cleanup completed
goto :eof

REM Function to show status
:show_status
echo.
echo 📊 Service Status:
echo ==================

docker compose ps -q >nul 2>&1
if not errorlevel 1 (
    echo Full Stack:
    docker compose ps
)

docker compose -f docker-compose.dev.yml ps -q >nul 2>&1
if not errorlevel 1 (
    echo.
    echo Development Services:
    docker compose -f docker-compose.dev.yml ps
)

REM Check if no services are running
docker compose ps -q >nul 2>&1
if errorlevel 1 (
    docker compose -f docker-compose.dev.yml ps -q >nul 2>&1
    if errorlevel 1 (
        echo No services are currently running
    )
)
goto :eof

REM Main script logic
if "%1"=="dev" (
    call :check_docker
    call :stop_services
    call :start_dev_services
) else if "%1"=="full" (
    call :check_docker
    call :stop_services
    call :start_full_stack
) else if "%1"=="stop" (
    call :stop_services
) else if "%1"=="logs" (
    call :show_logs
) else if "%1"=="status" (
    call :show_status
) else if "%1"=="clean" (
    call :clean_all
) else (
    echo.
    echo Usage: %0 ^<command^>
    echo.
    echo Commands:
    echo   dev     - Start development database services only ^(PostgreSQL + Redis^)
    echo   full    - Start full stack ^(Backend + PostgreSQL + Redis^)
    echo   stop    - Stop all services
    echo   status  - Show service status
    echo   logs    - Show service logs ^(follow mode^)
    echo   clean   - Stop services and clean up Docker resources
    echo   help    - Show this help message
    echo.
    echo Examples:
    echo   %0 dev     # Start databases, run backend locally with Maven
    echo   %0 full    # Start everything in Docker
    echo   %0 stop    # Stop all services
    echo.
)