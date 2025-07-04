-- BJJ Copilot Database Initialization
-- This script will be executed when the PostgreSQL container starts for the first time

-- Create the database if it doesn't exist (usually handled by POSTGRES_DB environment variable)
-- CREATE DATABASE IF NOT EXISTS bjj_copilot;

-- Grant necessary permissions to the user
GRANT ALL PRIVILEGES ON DATABASE bjj_copilot TO bjj_user;

-- You can add additional initialization scripts here
-- For example, creating initial data, indexes, etc.

-- Log initialization
SELECT 'BJJ Copilot database initialized successfully' AS status;