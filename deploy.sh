#!/bin/bash

# Deployment script for VPS
# Usage: ./deploy.sh [repository-url]
# Example: ./deploy.sh https://github.com/seu-usuario/agenda-spring-boot.git

REPO_URL=${1:-"https://github.com/seu-usuario/agenda-spring-boot.git"}
APP_DIR="/opt/agenda-app"

echo "🚀 Starting deployment process..."

# Check if Git is installed
if ! command -v git &> /dev/null; then
    echo "❌ Git is not installed. Please install Git first."
    exit 1
fi

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo "❌ Docker is not installed. Please install Docker first."
    exit 1
fi

# Check if Docker Compose is installed
if ! command -v docker-compose &> /dev/null; then
    echo "❌ Docker Compose is not installed. Please install Docker Compose first."
    exit 1
fi

# Create application directory if it doesn't exist
echo "📁 Setting up application directory..."
sudo mkdir -p $APP_DIR
sudo chown $USER:$USER $APP_DIR

# Clone or update repository
if [ -d "$APP_DIR/.git" ]; then
    echo "📥 Updating existing repository..."
    cd $APP_DIR
    git fetch origin
    git reset --hard origin/main
    git pull origin main
else
    echo "📥 Cloning repository..."
    git clone $REPO_URL $APP_DIR
    cd $APP_DIR
fi

# Stop existing containers
echo "🛑 Stopping existing containers..."
docker-compose down

# Remove old images (optional - uncomment if you want to force rebuild)
# echo "🗑️  Removing old images..."
# docker-compose down --rmi all

# Build and start the application
echo "🔨 Building and starting the application..."
docker-compose up --build -d

# Wait for the application to start
echo "⏳ Waiting for application to start..."
sleep 30

# Check if the application is running
if curl -f http://localhost:8080/actuator/health > /dev/null 2>&1; then
    echo "✅ Application is running successfully!"
    echo "🌐 Application URL: http://localhost:8080"
    echo "❤️  Health Check: http://localhost:8080/actuator/health"
    echo "📊 H2 Console: http://localhost:8080/h2-console (if enabled)"
else
    echo "❌ Application failed to start. Check logs:"
    docker-compose logs
    exit 1
fi

echo "🎉 Deployment completed successfully!"
