# Kitchen Flow API 🍳

![Status](https://img.shields.io/badge/status-ativo-success.svg)
![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

## 🎯 Sobre o Projeto

O **Kitchen Flow** é uma API RESTful desenvolvida para o gerenciamento de estoque de restaurantes. O principal objetivo é evitar o desperdício de comida com um monitoramento proativo das datas de validade, permitindo que o estabelecimento saiba exatamente o que precisa ser consumido com urgência.

Este projeto demonstra a aplicação de desenvolvimento backend com Spring Boot, criação de uma API REST, lógica de negócio, persistência de dados e boas práticas de arquitetura em camadas.

---

## ✨ Principais Funcionalidades

### 🏷️ **Gestão de Categorias**
* **CRUD Completo:** Criar, listar e deletar categorias de produtos
* **Organização:** Estrutura hierárquica para melhor organização do estoque

### 📦 **Gestão de Produtos**
* **CRUD de Produtos:** Adicionar, listar e remover produtos do inventário
* **Categorização:** Cada produto pertence a uma categoria específica
* **Unidade de Medida:** Controle de unidades (kg, litros, unidades, etc.)

### 📋 **Gestão de Lotes**
* **Controle de Quantidade:** Gerenciamento preciso de estoque por lote
* **Datas de Validade:** Monitoramento de entrada e validade
* **Consumo Inteligente:** Atualização automática do estoque ao consumir produtos
* **Validação de Negócio:** Verificação de datas de validade vs entrada

### 🔍 **Cálculo de Status de Validade**
* **Classificação Automática:** Status baseado na proximidade da data de validade
* **Status Disponíveis:**
  - `NORMAL`: Validade superior a 3 dias
  - `ATENCAO`: Vence em até 3 dias
  - `URGENTE`: Vence hoje ou amanhã
  - `VENCIDO`: Data de validade já passou

---

## 🛠️ Tecnologias e Ferramentas

### **Backend**
* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.5.3
* **Módulos Spring:** 
  - Spring Web (REST API)
  - Spring Data JPA (Persistência)
  - Spring Boot Validation (Validação de dados)
  - Spring Boot DevTools (Desenvolvimento)

### **Persistência**
* **ORM:** Hibernate
* **Banco de Dados:** PostgreSQL
* **Migração:** Flyway Database Migration
* **Conexão:** JDBC com PostgreSQL Driver

### **Ferramentas**
* **Gerenciador de Dependências:** Maven
* **Redução de Boilerplate:** Lombok
* **Validação:** Bean Validation (Jakarta)

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas para garantir a separação de responsabilidades e a manutenibilidade do código:

```
Cliente (Postman/Frontend) → Controller → Service → Repository → Banco de Dados
```

### **Camadas da Aplicação:**

* **Controller Layer:** Expõe os endpoints da API REST
  - `CategoriaController` - `/api/v1/categoria`
  - `ProdutoController` - `/api/v1/produto`
  - `LotesController` - `/api/v1/lotes`

* **Service Layer:** Contém a lógica de negócio
  - `CategoriaService` - Regras de negócio para categorias
  - `ProdutoService` - Lógica de produtos e cálculo de status
  - `LotesService` - Gerenciamento de lotes e consumo

* **Repository Layer:** Abstração do acesso aos dados
  - `CategoriaRepository` - Operações CRUD de categorias
  - `ProdutoRepository` - Operações CRUD de produtos
  - `LotesRepository` - Operações CRUD de lotes

* **Model Layer:** Entidades JPA e DTOs
  - **Entidades:** `Categoria`, `Produto`, `Lotes`, `StatusValidade`
  - **DTOs:** `ProdutoRequestDto`, `ProdutoResponseDto`, `CategoriaResponseDto`, `LotesRequestDto`

---

## 🗄️ Modelo de Dados

### **Relacionamentos:**
```
Categoria (1) ←→ (N) Produto (1) ←→ (N) Lotes
```

* **Categoria → Produto:** One-to-Many (Uma categoria pode ter vários produtos)
* **Produto → Lotes:** One-to-Many (Um produto pode ter vários lotes)

### **Entidades Principais:**

**Categoria:**
- `id` (Long, PK)
- `nome` (String)

**Produto:**
- `id` (Long, PK)
- `nome` (String)
- `unidadeMedida` (String)
- `categoria` (Categoria, FK)

**Lotes:**
- `id` (Long, PK)
- `quantidade` (Integer)
- `data_validade` (LocalDate)
- `data_entrada` (LocalDate)
- `produto` (Produto, FK)

---

## 🚀 Como Executar

### **Pré-requisitos**

* Java 21+
* Maven 3.x+
* PostgreSQL 15+
* IDE (IntelliJ IDEA, VS Code, Eclipse)

### **Passos para Execução**

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/andreyrsy/kitchen-flow.git
   cd kitchen-flow
   ```

2. **Configure o Banco de Dados:**
   * Crie um banco de dados no PostgreSQL (ex: `db_kitchen`)
   * Atualize as credenciais no arquivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/db_kitchen
   spring.datasource.username=postgres
   spring.datasource.password=sua_senha
   ```

3. **Instale as dependências:**
   ```bash
   mvn clean install
   ```

4. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

A API estará disponível em `http://localhost:8080/api/v1/`

---

## 📖 API Endpoints

### **🏷️ Categorias** (`/api/v1/categoria`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/v1/categoria` | Criar nova categoria |
| `GET` | `/api/v1/categoria` | Listar todas as categorias |
| `DELETE` | `/api/v1/categoria/delete/{id}` | Deletar categoria por ID |

### **📦 Produtos** (`/api/v1/produto`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/v1/produto` | Listar todos os produtos |
| `POST` | `/api/v1/produto` | Adicionar novo produto |
| `DELETE` | `/api/v1/produto/deletar/{id}` | Remover produto por ID |

### **📋 Lotes** (`/api/v1/lotes`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/v1/lotes` | Listar todos os lotes |
| `POST` | `/api/v1/lotes` | Criar novo lote |
| `PUT` | `/api/v1/lotes/{id}/consumir/{qtdConsumida}` | Consumir quantidade do lote |
| `DELETE` | `/api/v1/lotes/deletar/{id}` | Deletar lote por ID |

---

## 💡 Exemplos de Uso

### **1. Criar Categoria**
```bash
POST /api/v1/categoria
Content-Type: application/json

{
  "nome": "Laticínios"
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "nome": "Laticínios",
  "produto": []
}
```

### **2. Adicionar Produto**
```bash
POST /api/v1/produto
Content-Type: application/json

{
  "nome": "Leite Integral",
  "unidadeMedida": "litros",
  "categoriaId": 1
}
```

**Response (201 CREATED):**
```json
{
  "id": 1,
  "nome": "Leite Integral",
  "unidadeMedida": "litros",
  "categoria": {
    "id": 1,
    "nome": "Laticínios"
  }
}
```

### **3. Criar Lote**
```bash
POST /api/v1/lotes
Content-Type: application/json

{
  "quantidade": 50,
  "dataValidade": "25-12-2024",
  "dataEntrada": "20-12-2024",
  "produtoId": 1
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "quantidade": 50,
  "data_validade": "2024-12-25",
  "data_entrada": "2024-12-20",
  "produto": {
    "id": 1,
    "nome": "Leite Integral"
  }
}
```

### **4. Consumir Produto**
```bash
PUT /api/v1/lotes/1/consumir/10
```

**Resultado:** A quantidade do lote será reduzida de 50 para 40 unidades.

### **5. Listar Produtos com Status**
```bash
GET /api/v1/produto
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nome": "Leite Integral",
    "unidade_medida": "litros",
    "categoriaDto": {
      "id": 1,
      "nome": "Laticínios"
    }
  }
]
```

---

## 🔧 Configurações

### **Application Properties**
```properties
# Aplicação
spring.application.name=kitchen-flow

# Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/db_kitchen
spring.datasource.username=postgres
spring.datasource.password=root
spring.datasource.driver-class-name=org.postgresql.Driver

# Jackson (Formatação de Data)
spring.jackson.date-format=dd-MM-yyyy
spring.jackson.time-zone=America/Sao_Paulo

# Flyway (Migração)
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## 🗃️ Migrações do Banco

O projeto utiliza Flyway para controle de versão do banco de dados:

* **V1__create_table_categoria.sql** - Criação da tabela categoria
* **V2__create_table_produto.sql** - Criação da tabela produto
* **V3__create_table_lotes.sql** - Criação da tabela lotes

---

## 🔮 Próximos Passos e Melhorias

### **Melhorias Planejadas:**

```json
[ ] **Segurança:** Implementar Spring Security com autenticação JWT
[ ] **Validação:** Adicionar Bean Validation nos DTOs
[ ] **Tratamento de Erros:** Global Exception Handler (@ControllerAdvice)
[ ] **Testes:** Cobertura de testes unitários e de integração
[ ] **Documentação:** Swagger/OpenAPI para documentação interativa
[ ] **Cache:** Implementar cache para consultas frequentes
[ ] **Paginação:** Consultas paginadas para listagens
[ ] **Auditoria:** Campos de auditoria (created_at, updated_at)
[ ] **Notificações:** Sistema de alertas para produtos próximos do vencimento
[ ] **Containerização:** Docker e Docker Compose
[ ] **CI/CD:** Pipeline com GitHub Actions
[ ] **Monitoramento:** Spring Boot Actuator e métricas
```

---

## 📊 Status do Projeto

- ✅ **Arquitetura em Camadas** - Implementada
- ✅ **CRUD Completo** - Categorias, Produtos e Lotes
- ✅ **Relacionamentos JPA** - One-to-Many configurados
- ✅ **Validação de Negócio** - Datas de validade
- ✅ **DTOs** - Separação de responsabilidades
- ✅ **Migração de Banco** - Flyway configurado
- ✅ **Lombok** - Redução de boilerplate
- 🔄 **Tratamento de Exceções** - Em desenvolvimento
- 🔄 **Testes** - Planejado
- 🔄 **Documentação API** - Planejado

---

## 👨‍💻 **Andreyrsy**


- 💼 LinkedIn: [andreyrsy](https://linkedin.com/in/andreyrsy)
- 🐙 GitHub: [andreyrsy](https://github.com/andreyrsy)

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---