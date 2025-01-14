# Docker Setup for MySQL and Cineflux Application

This guide provides a step-by-step process to set up a MySQL database and a Cineflux application using Docker containers within a custom network.

## Steps

### 1. Create a Docker Network
```bash
docker network create my-network-app
```

### 2. Run the MySQL Database Container
Run the MySQL database container using the custom network and set the root password and database name:
```bash
docker run -d --name mysql-database \
  --network my-network-app \
  -e MYSQL_ROOT_PASSWORD=password \
  -e MYSQL_DATABASE=database \
  mysql
```
Optionally, expose the MySQL database port (3306):
```bash
docker run -d --name mysql-database \
  --network my-network-app \
  -e MYSQL_ROOT_PASSWORD=password \
  -e MYSQL_DATABASE=database \
  -p 3306:3306 \
  mysql
```

### 3. Build the Cineflux Application Image
Build the Docker image for the Cineflux application:
```bash
docker build -t cineflux_app .
```

### 4. Run the Cineflux Application Container
Run the Cineflux application container and configure it to connect to the MySQL database:
```bash
docker run -d --name app \
  --network my-network-app \
  -e DATABASE_HOST=172.18.0.2 \
  -e DATABASE_PORT=3306 \
  -e DATABASE_NAME=database \
  -e DATABASE_USERNAME=root \
  -e DATABASE_PASSWORD=password \
  -p 8070:7070 \
  cineflux_app
```
