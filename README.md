# API Agenda Spring Boot

Uma API RESTful para autenticação de usuários e gerenciamento de contatos construída com Spring Boot, Maven e banco de dados H2.

## 🚀 Funcionalidades

### Autenticação
- **Registro de Usuário**: `POST /api/auth/signup`
- **Login de Usuário**: `POST /api/auth/login`

### Gerenciamento de Contatos (CRUD)
- **Criar Contato**: `POST /api/agenda`
- **Listar Todos os Contatos**: `GET /api/agenda`
- **Buscar Contato por ID**: `GET /api/agenda/{id}`
- **Atualizar Contato**: `PUT /api/agenda/{id}`
- **Excluir Contato**: `DELETE /api/agenda/{id}`

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Boot Starter Web**
- **Spring Boot Starter Data JPA**
- **Spring Boot DevTools**
- **Spring Boot Actuator**
- **Banco H2** (em memória)
- **Maven** (ferramenta de build)

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/example/agenda/
│   │   ├── controller/          # Controladores REST
│   │   ├── dto/                 # Objetos de Transferência de Dados
│   │   ├── model/               # Entidades JPA
│   │   ├── repository/          # Repositórios JPA
│   │   ├── service/             # Lógica de Negócio
│   │   └── AgendaApplication.java
│   └── resources/
│       ├── application.properties
│       └── application-prod.properties
```

## 📡 Endpoints da API

### Autenticação

#### Registrar Usuário
```http
POST /api/auth/signup
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao@example.com",
  "senha": "senha123"
}
```

#### Login do Usuário
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "joao@example.com",
  "senha": "senha123"
}
```

### Gerenciamento de Contatos

#### Criar Contato
```http
POST /api/agenda
Content-Type: application/json

{
  "nome": "Maria Silva",
  "telefone": "+5511987654321"
}
```

#### Listar Todos os Contatos
```http
GET /api/agenda
```

#### Buscar Contato por ID
```http
GET /api/agenda/1
```

#### Atualizar Contato
```http
PUT /api/agenda/1
Content-Type: application/json

{
  "nome": "Maria Silva Atualizada",
  "telefone": "+5511123456789"
}
```

#### Excluir Contato
```http
DELETE /api/agenda/1
```

## 🏃‍♂️ Executando a Aplicação

### Desenvolvimento Local

1. **Pré-requisitos**: Java 17, Maven (ou use o Maven wrapper incluído)

2. **Executar a aplicação**:
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Acessar a aplicação**:
   - API: http://localhost:8080
   - Console H2: http://localhost:8080/h2-console
   - Health Check: http://localhost:8080/actuator/health

### Deploy com Docker

#### Build e Execução com Docker Compose

1. **Build e iniciar a aplicação**:
   ```bash
   docker-compose up --build
   ```

2. **Executar em modo detached**:
   ```bash
   docker-compose up -d --build
   ```

3. **Parar a aplicação**:
   ```bash
   docker-compose down
   ```

#### Comandos Docker Manuais

1. **Build da imagem Docker**:
   ```bash
   docker build -t agenda-app .
   ```

2. **Executar o container**:
   ```bash
   docker run -p 8080:8080 agenda-app
   ```

## 🚀 Deploy na VPS

### Pré-requisitos na VPS
- Ubuntu/Debian Linux
- Acesso SSH
- Usuário com privilégios sudo
- Git configurado com acesso ao repositório

### Comandos para Deploy

#### 1. Conectar na VPS:
```bash
ssh usuario@ip-da-vps
```

#### 2. Executar o deploy:
```bash
# Baixar o script de deploy
curl -O https://raw.githubusercontent.com/galotinho/agenda/main/deploy-vps.sh

# Dar permissão de execução
chmod +x deploy-vps.sh

# Executar o deploy
./deploy-vps.sh
```

#### 3. Verificar se está funcionando:
```bash
# Verificar containers
docker ps

# Verificar logs
docker-compose logs -f

# Testar a API
curl http://localhost:8080/actuator/health
```

### 🔄 Para atualizações futuras na VPS:

```bash
# Simplesmente execute o script novamente
./deploy-vps.sh
```

### 📍 URLs de acesso:

Após o deploy, a aplicação estará disponível em:
- **API**: `http://IP-DA-VPS:8080`
- **Health Check**: `http://IP-DA-VPS:8080/actuator/health`
- **Console H2**: `http://IP-DA-VPS:8080/h2-console` (apenas em desenvolvimento)

## 🧪 Testando a API

### Registrar usuário:
```bash
curl -X POST http://IP-DA-VPS:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@example.com",
    "senha": "senha123"
  }'
```

### Fazer login:
```bash
curl -X POST http://IP-DA-VPS:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@example.com",
    "senha": "senha123"
  }'
```

### Criar contato:
```bash
curl -X POST http://IP-DA-VPS:8080/api/agenda \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Maria Silva",
    "telefone": "+5511987654321"
  }'
```

### Listar contatos:
```bash
curl http://IP-DA-VPS:8080/api/agenda
```

## ⚙️ Configuração

### Desenvolvimento
- Usa `application.properties`
- Console H2 habilitado
- Log SQL habilitado

### Produção
- Usa `application-prod.properties`
- Console H2 desabilitado
- Log reduzido
- Endpoints de health check habilitados

## 🗄️ Banco de Dados

A aplicação usa banco H2 em memória por padrão. Para produção, considere migrar para:
- PostgreSQL
- MySQL
- MariaDB

## 🛠️ Comandos Úteis na VPS

```bash
# Ver status dos containers
docker ps

# Ver logs da aplicação
docker-compose logs -f

# Parar a aplicação
docker-compose down

# Reiniciar a aplicação
docker-compose restart

# Atualizar aplicação
./deploy-vps.sh

# Limpar containers antigos
docker system prune -a
```

## 🔒 Notas de Segurança

⚠️ **Importante**: Esta é uma implementação básica para fins de demonstração. Para uso em produção, considere:

1. **Hash de Senhas**: Implementar hash adequado de senhas (BCrypt)
2. **Autenticação JWT**: Adicionar tokens JWT para autenticação stateless
3. **Validação de Entrada**: Adicionar validação abrangente de entrada
4. **HTTPS**: Usar HTTPS em produção
5. **Banco de Dados**: Usar um banco persistente ao invés do H2
6. **Tratamento de Erros**: Implementar tratamento global de exceções
7. **Rate Limiting**: Adicionar limitação de taxa para endpoints da API

## 🧪 Testes

Executar testes com:
```bash
./mvnw test
```

## 📄 Licença

Este projeto é para fins educacionais.
