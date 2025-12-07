# 🍳 Kitchen Flow

<div align="center">

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.3-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![H2](https://img.shields.io/badge/H2_Database-In_Memory-0000BB?style=for-the-badge&logo=databricks&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

**Sistema de Gestão de Validade e Estoque para Restaurantes**

[Funcionalidades](#-funcionalidades) •
[Arquitetura](#-arquitetura) •
[Instalação](#-instalação-e-execução) •
[API](#-documentação-da-api) •
[Contribuição](#-contribuição)

</div>

---

## 📋 Sobre o Projeto

O **Kitchen Flow** é uma API RESTful desenvolvida para gerenciar o controle de validade e estoque de ingredientes em ambientes de cozinha profissional. O sistema permite o cadastro de produtos organizados por categorias, controle de lotes com datas de entrada e validade, além de registro de consumo de estoque.

### Problema Resolvido

Restaurantes e cozinhas industriais enfrentam desafios significativos no controle de validade de ingredientes, resultando em desperdício de alimentos e riscos sanitários. O Kitchen Flow automatiza esse processo, fornecendo:

- 📦 Controle preciso de estoque por lotes
- ⏰ Rastreamento de datas de validade
- 📊 Registro de consumo para análise de demanda
- 🏷️ Categorização inteligente de produtos

---

## 🛠️ Stack Tecnológica

| Camada            | Tecnologia         | Versão   | Descrição                             |
| ----------------- | ------------------ | -------- | ------------------------------------- |
| **Backend**       | Spring Boot        | 3.5.3    | Framework principal                   |
| **Linguagem**     | Java               | 21 (LTS) | Runtime                               |
| **Persistência**  | Spring Data JPA    | -        | ORM e acesso a dados                  |
| **Banco (Dev)**   | H2 Database        | -        | Banco em memória para desenvolvimento |
| **Banco (Prod)**  | PostgreSQL         | 16+      | Banco de dados de produção            |
| **Migrations**    | Flyway             | -        | Versionamento de schema               |
| **Segurança**     | Spring Security    | -        | Autenticação e CSRF                   |
| **Documentação**  | SpringDoc OpenAPI  | 2.8.5    | Swagger UI automático                 |
| **Mapeamento**    | MapStruct          | 1.5.5    | Conversão DTO ↔ Entity                |
| **Validação**     | Jakarta Validation | -        | Bean Validation                       |
| **Produtividade** | Lombok             | 1.18.30  | Redução de boilerplate                |

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas (Layered Architecture) com separação clara de responsabilidades:

```
                           ┌─────────────────────────────────────────┐
                           │              Controllers                │
                           │  (CategoriaController, ProdutoController│
                           │      LotesController)                   │
                           └─────────────────┬───────────────────────┘
                                             │
                           ┌─────────────────▼───────────────────────┐
                           │              DTOs                       │
                           │  Request DTOs ←── MapStruct ──→ Response│
                           │      (Validação Jakarta)               │
                           └─────────────────┬───────────────────────┘
                                             │
                           ┌─────────────────▼───────────────────────┐
                           │              Services                   │
                           │  (Lógica de negócio, Transações)       │
                           └─────────────────┬───────────────────────┘
                                             │
                           ┌─────────────────▼───────────────────────┐
                           │              Repositories               │
                           │  (Spring Data JPA, CrudRepository)     │
                           └─────────────────┬───────────────────────┘
                                             │
                           ┌─────────────────▼───────────────────────┐
                           │              Database                   │
                           │     H2 (dev) │ PostgreSQL (prod)       │
                           │           Flyway Migrations             │
                           └─────────────────────────────────────────┘
```

### Estrutura de Diretórios

```
src/main/java/dev/andreyrsy/kitchen/flow/
├── config/                    # Configurações (Security, Swagger, MapStruct, JWT)
│   ├── SecurityConfig.java   # Regras de autorização por endpoint
│   ├── SecurityFilter.java   # Filtro de validação do token JWT
│   ├── TokenConfig.java      # Geração e validação de tokens JWT
│   ├── SwaggerConfig.java
│   └── MapStructConfig.java
├── controller/                # REST Controllers
│   ├── AuthController.java   # Endpoints de autenticação (login/register)
│   ├── CategoriaController.java
│   ├── ProdutoController.java
│   └── LotesController.java
├── dto/                       # Data Transfer Objects
│   ├── auth/                  # DTOs de autenticação
│   ├── request/               # DTOs de entrada
│   └── response/              # DTOs de saída
├── exception/                 # Exceções personalizadas
│   ├── GlobalExceptionHandler.java
│   └── business/              # Exceções de negócio
├── mapper/                    # MapStruct mappers
│   └── KitchenMapper.java
├── model/                     # Entidades JPA
│   ├── Categoria.java
│   ├── Produto.java
│   ├── Lotes.java
│   ├── StatusLote.java
│   └── user/                  # Entidades de usuário
│       ├── User.java
│       └── UserRole.java
├── repository/                # Spring Data Repositories
└── service/                   # Camada de serviços
    ├── CategoriaService.java
    ├── ProdutoService.java
    ├── LotesService.java
    └── AuthorizationService.java
```

---

## 📋 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

| Ferramenta                | Versão Mínima | Verificar Instalação |
| ------------------------- | ------------- | -------------------- |
| **JDK**                   | 21            | `java -version`      |
| **Maven**                 | 3.9.x         | `mvn -version`       |
| **Git**                   | 2.x           | `git --version`      |
| **PostgreSQL** (opcional) | 16+           | `psql --version`     |

> **📝 Nota**: O Maven Wrapper (`mvnw`) está incluído no projeto. Não é necessário instalar o Maven globalmente.

---

## 🚀 Instalação e Execução

### 1. Clonar o Repositório

```bash
git clone https://github.com/andreyrsy/kitchen-flow.git
cd kitchen-flow
```

### 2. Executar com Banco H2 (Desenvolvimento)

O perfil padrão utiliza banco H2 em memória, ideal para desenvolvimento e testes:

```bash
# Linux/macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

### 3. Executar com PostgreSQL (Produção)

Para ambiente de produção com PostgreSQL:

```bash
# 1. Criar banco de dados
psql -U postgres -c "CREATE DATABASE db_kitchen;"

# 2. Executar com perfil PostgreSQL
./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres
```

### 4. Acessar a Aplicação

| Recurso          | URL                                     |
| ---------------- | --------------------------------------- |
| **API Base**     | `http://localhost:8080/api/v1`          |
| **Swagger UI**   | `http://localhost:8080/swagger-ui.html` |
| **H2 Console**   | `http://localhost:8080/h2-console`      |
| **OpenAPI JSON** | `http://localhost:8080/v3/api-docs`     |

> **🔐 H2 Console**: Consulte as variáveis de ambiente para credenciais de acesso

---

## ⚙️ Configuração (Variáveis de Ambiente)

### Perfil Padrão (H2 - Desenvolvimento)

| Variável                     | Descrição             | Valor                    |
| ---------------------------- | --------------------- | ------------------------ |
| `spring.datasource.url`      | URL de conexão JDBC   | `jdbc:h2:mem:db_kitchen` |
| `spring.datasource.username` | Usuário do banco      | `DB_USERNAME`            |
| `spring.datasource.password` | Senha do banco        | `DB_PASSWORD`            |
| `spring.h2.console.enabled`  | Habilitar console H2  | `true`                   |
| `spring.jackson.date-format` | Formato de datas JSON | `dd-MM-yyyy`             |
| `spring.jackson.time-zone`   | Timezone              | `America/Sao_Paulo`      |

### Perfil PostgreSQL (Produção)

Ativar com: `-Dspring.profiles.active=postgres`

| Variável                     | Descrição                 | Valor                                         |
| ---------------------------- | ------------------------- | --------------------------------------------- |
| `spring.datasource.url`      | URL de conexão PostgreSQL | `jdbc:postgresql://localhost:5432/db_kitchen` |
| `spring.datasource.username` | Usuário PostgreSQL        | `DB_USERNAME`                                 |
| `spring.datasource.password` | Senha PostgreSQL          | `DB_PASSWORD`                                 |

> **⚠️ IMPORTANTE**: Configure as credenciais via variáveis de ambiente. **Nunca** commite senhas no código!

---

## 📖 Documentação da API

A documentação interativa está disponível via **Swagger UI** em:

```
http://localhost:8080/swagger-ui.html
```

### Endpoints Principais

#### 🏷️ Categorias (`/api/v1/categorias`)

| Método   | Endpoint                  | Descrição                  |
| -------- | ------------------------- | -------------------------- |
| `GET`    | `/api/v1/categorias`      | Listar todas as categorias |
| `GET`    | `/api/v1/categorias/{id}` | Buscar categoria por ID    |
| `POST`   | `/api/v1/categorias`      | Criar nova categoria       |
| `DELETE` | `/api/v1/categorias/{id}` | Remover categoria          |

**Exemplo - Criar Categoria:**

```bash
curl -X POST http://localhost:8080/api/v1/categorias \
  -H "Content-Type: application/json" \
  -d '{"nome": "Carnes"}'
```

#### 📦 Produtos (`/api/v1/produtos`)

| Método   | Endpoint                | Descrição                |
| -------- | ----------------------- | ------------------------ |
| `GET`    | `/api/v1/produtos`      | Listar todos os produtos |
| `GET`    | `/api/v1/produtos/{id}` | Buscar produto por ID    |
| `POST`   | `/api/v1/produtos`      | Criar novo produto       |
| `DELETE` | `/api/v1/produtos/{id}` | Remover produto          |

**Exemplo - Criar Produto:**

```bash
curl -X POST http://localhost:8080/api/v1/produtos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Filé Mignon",
    "unidadeMedida": "KG",
    "categoriaId": 1
  }'
```

#### 📋 Lotes (`/api/v1/lotes`)

| Método   | Endpoint                      | Descrição                   |
| -------- | ----------------------------- | --------------------------- |
| `GET`    | `/api/v1/lotes`               | Listar todos os lotes       |
| `GET`    | `/api/v1/lotes/{id}`          | Buscar lote por ID          |
| `POST`   | `/api/v1/lotes`               | Criar novo lote             |
| `POST`   | `/api/v1/lotes/consumir/{id}` | Consumir quantidade do lote |
| `DELETE` | `/api/v1/lotes/{id}`          | Remover lote                |

**Exemplo - Criar Lote:**

```bash
curl -X POST http://localhost:8080/api/v1/lotes \
  -H "Content-Type: application/json" \
  -d '{
    "quantidade": 10,
    "dataEntrada": "04-12-2024",
    "dataValidade": "04-01-2025",
    "produtoId": 1
  }'
```

**Exemplo - Consumir Estoque:**

```bash
curl -X POST http://localhost:8080/api/v1/lotes/consumir/1 \
  -H "Content-Type: application/json" \
  -d '{"quantidade": 2}'
```

---

## 🧪 Testes

### Executar Testes Unitários

```bash
./mvnw test
```

### Coleção Postman

Uma coleção Postman está incluída no projeto para facilitar os testes manuais:

📁 **Arquivo**: `kitchenflow.postman_collection.json`

**Importar no Postman:**

1. Abra o Postman
2. Clique em **Import**
3. Selecione o arquivo `kitchenflow.postman_collection.json`
4. Configure a variável `{{baseURL}}` para `http://localhost:8080/api/v1`

---

## 🗄️ Banco de Dados

### Migrations (Flyway)

O versionamento do schema é gerenciado pelo Flyway. Os scripts de migração estão em:

```
src/main/resources/db/migration/
├── V1__create_table_categoria.sql
├── V2__create_table_produto.sql
├── V3__create_table_lotes.sql
└── V4__create_table_users.sql
```

### Modelo de Dados

```
┌───────────────┐       ┌───────────────┐       ┌───────────────┐
│   CATEGORIA   │       │    PRODUTO    │       │     LOTES     │
├───────────────┤       ├───────────────┤       ├───────────────┤
│ id (PK)       │───┐   │ id (PK)       │───┐   │ id (PK)       │
│ nome          │   └──►│ nome          │   └──►│ quantidade    │
└───────────────┘       │ unidadeMedida │       │ dataEntrada   │
                        │ categoriaId(FK)│       │ dataValidade  │
                        └───────────────┘       │ produtoId (FK)│
                                                └───────────────┘

┌───────────────┐
│     USERS     │
├───────────────┤
│ id (PK, UUID) │
│ login         │
│ password      │
│ role (ENUM)   │
└───────────────┘
```

---

## 🔐 Segurança e Autenticação

O projeto implementa **Spring Security** com autenticação **JWT (JSON Web Token)** para proteger os endpoints da API. A arquitetura segue o padrão **stateless**, ideal para APIs RESTful.

### Por que implementamos autenticação e autorização?

Em um ambiente de cozinha profissional, diferentes colaboradores possuem diferentes níveis de responsabilidade:

| Cargo           | Role    | Responsabilidades                                                        |
| --------------- | ------- | ------------------------------------------------------------------------ |
| **Gerente**     | `ADMIN` | Controla a entrada de lotes no estoque, valida mercadorias recebidas     |
| **Funcionário** | `USER`  | Cadastra categorias e produtos, registra consumo do estoque no dia-a-dia |

> **💡 Contexto de Negócio**: Apenas o gerente pode criar/alterar lotes porque ele é responsável por conferir as mercadorias que chegam dos fornecedores, verificar quantidades e datas de validade. Os funcionários podem cadastrar novos produtos e categorias conforme necessário, mas não têm acesso à gestão de entrada de estoque.

---

### Arquitetura de Segurança

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                           FLUXO DE AUTENTICAÇÃO JWT                        │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  1. Login                           2. Requisição Autenticada               │
│  ┌──────────┐                       ┌──────────┐                           │
│  │  Client  │ ── POST /auth/login ──►│  Server  │                           │
│  │          │◄── { token: "..." } ── │          │                           │
│  └──────────┘                       └──────────┘                           │
│       │                                   ▲                                 │
│       │                                   │                                 │
│       │  3. Usa token nas requisições    │                                 │
│       │     Authorization: Bearer <token> │                                 │
│       └───────────────────────────────────┘                                 │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

### Componentes de Segurança

| Componente         | Arquivo               | Responsabilidade                              |
| ------------------ | --------------------- | --------------------------------------------- |
| **SecurityConfig** | `SecurityConfig.java` | Configuração das regras de autorização        |
| **SecurityFilter** | `SecurityFilter.java` | Intercepta requisições e valida o token JWT   |
| **TokenConfig**    | `TokenConfig.java`    | Geração e validação de tokens JWT (auth0-jwt) |
| **AuthController** | `AuthController.java` | Endpoints de login e registro de usuários     |

### Configurações de Segurança

- **CSRF**: Desabilitado (API stateless não usa cookies de sessão)
- **Sessão**: `SessionCreationPolicy.STATELESS` (sem estado no servidor)
- **Algoritmo JWT**: HMAC256
- **Expiração do Token**: 2 horas
- **Password Encoder**: BCrypt

---

### Matriz de Permissões por Endpoint

| Endpoint                      | Método   | `ADMIN` (Gerente) | `USER` (Funcionário) | Público |
| ----------------------------- | -------- | :---------------: | :------------------: | :-----: |
| `/auth/login`                 | `POST`   |                   |                      |   ✅    |
| `/auth/register`              | `POST`   |                   |                      |   ✅    |
| `/api/v1/categorias`          | `GET`    |        ✅         |          ✅          |         |
| `/api/v1/categorias`          | `POST`   |        ✅         |          ✅          |         |
| `/api/v1/categorias/{id}`     | `DELETE` |        ✅         |          ✅          |         |
| `/api/v1/produtos`            | `GET`    |        ✅         |          ✅          |         |
| `/api/v1/produtos`            | `POST`   |        ✅         |          ✅          |         |
| `/api/v1/produtos/{id}`       | `DELETE` |        ✅         |          ✅          |         |
| `/api/v1/lotes`               | `GET`    |        ✅         |          ✅          |         |
| `/api/v1/lotes`               | `POST`   |        ✅         |          ❌          |         |
| `/api/v1/lotes/{id}`          | `DELETE` |        ✅         |          ✅          |         |
| `/api/v1/lotes/consumir/{id}` | `POST`   |        ✅         |          ✅          |         |

> **🔒 Regra Principal**: Apenas usuários com role `ADMIN` podem criar novos lotes (`POST /api/v1/lotes`), pois representam a entrada de mercadorias no estoque — responsabilidade exclusiva do gerente.

---

### Como Utilizar a Autenticação

#### 1️⃣ Registrar um Novo Usuário

```bash
# Registrar um funcionário (USER)
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "login": "funcionario01",
    "password": "senha123",
    "role": "USER"
  }'

# Registrar um gerente (ADMIN)
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "login": "gerente01",
    "password": "senha123",
    "role": "ADMIN"
  }'
```

#### 2️⃣ Fazer Login e Obter Token

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "login": "gerente01",
    "password": "senha123"
  }'
```

**Resposta:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

#### 3️⃣ Usar o Token nas Requisições

```bash
# Criar um lote (requer ADMIN)
curl -X POST http://localhost:8080/api/v1/lotes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." \
  -d '{
    "quantidade": 50,
    "dataEntrada": "07-12-2024",
    "dataValidade": "07-03-2025",
    "produtoId": 1
  }'
```

#### 4️⃣ Exemplo de Erro de Autorização

Se um funcionário (`USER`) tentar criar um lote:

```bash
# Retorna HTTP 403 Forbidden
curl -X POST http://localhost:8080/api/v1/lotes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token_de_funcionario>" \
  -d '{"quantidade": 10, "produtoId": 1, ...}'
```

---

### Variáveis de Ambiente de Segurança

| Variável                    | Descrição              | Obrigatório |
| --------------------------- | ---------------------- | :---------: |
| `api.security.token.secret` | Chave secreta para JWT |     ✅      |

> **⚠️ IMPORTANTE**: A chave secreta (`secret`) deve ser configurada via variáveis de ambiente e **nunca** commitada no código-fonte. Use um valor forte com pelo menos 32 caracteres.

---

## 🤝 Contribuição

Contribuições são bem-vindas! Siga os passos abaixo:

### 1. Fork e Clone

```bash
git clone https://github.com/seu-usuario/kitchen-flow.git
cd kitchen-flow
```

### 2. Criar Branch

```bash
git checkout -b feature/minha-feature
```

### 3. Commit com Mensagens Descritivas

```bash
git commit -m "feat: adiciona endpoint de relatórios de validade"
```

### 4. Push e Pull Request

```bash
git push origin feature/minha-feature
```

### Padrões do Projeto

- **Código**: Seguir convenções Java/Spring
- **Commits**: Usar [Conventional Commits](https://www.conventionalcommits.org/)
- **Testes**: Adicionar testes para novas funcionalidades
- **Documentação**: Atualizar Swagger annotations em novos endpoints

---

## 🗺️ Roadmap

- [x] ~~Implementação completa de autenticação JWT~~ ✅
- [ ] Alertas de produtos próximos da validade
- [ ] Relatórios de consumo e desperdício
- [ ] Integração com sistemas de PDV
- [ ] Dashboard de métricas (Actuator)
- [ ] Containerização com Docker

---

## 📄 Licença

Este projeto está sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👨‍💻 Autor

**Andrey**

- Email: andreyrsy@gmail.com
- GitHub: [@andreyrsy](https://github.com/andreyrsy)

---

<div align="center">

**⭐ Se este projeto foi útil, considere dar uma estrela!**

</div>
