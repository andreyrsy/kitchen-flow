# Kitchen Flow API 🍳

![Status](https://img.shields.io/badge/status-ativo-success.svg)
![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)
![H2 Database](https://img.shields.io/badge/H2-Database-blue.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

## 🎯 O Problema

O desperdício de alimentos é um problema sério em restaurantes, resultando em:
- Perdas financeiras significativas
- Impacto ambiental negativo
- Gestão ineficiente de recursos

**Kitchen Flow** é uma solução para gerenciar o estoque de forma inteligente, ajudando restaurantes a:
- Reduzir o desperdício de alimentos
- Economizar recursos financeiros
- Melhorar a eficiência operacional

## ✨ Funcionalidades

### 🏷️ Categorias e Produtos

- CRUD de categorias de alimentos.
- CRUD de produtos vinculados a categorias.
- Associação de unidade de medida por produto.

### 📦 Estoque e Lotes

- Registro de lotes com data de validade e data de entrada.
- Controle de quantidade por lote.
- Consumo de produtos a partir de um lote específico.
- Rastreabilidade de lotes via ID.

### 📊 Base para Evolução

- Documentação interativa da API com Swagger.
- Estrutura preparada para futuros recursos como:
  - Paginação.
  - Segurança (Spring Security).
  - Relatórios e notificações.

---

## 🏗 Arquitetura

A aplicação segue uma arquitetura típica de API REST com camadas bem definidas:

- **Controller**: exposição dos endpoints HTTP.
- **Service**: regras de negócio relacionadas a estoque, produtos e lotes.
- **Repository**: acesso a dados com Spring Data JPA.
- **Domain/Entity**: modelos de domínio para categorias, produtos e lotes.
- **Migrations (Flyway)**: versionamento do schema de banco de dados.

A API é exposta sob o prefixo:
http://localhost:8080/api/v1/

---

## 🛠 Tecnologias

### Backend

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Web** (API REST)
- **Spring Data JPA**
- **H2 Database** (dev) / **PostgreSQL** (opcional)
- **Flyway** (migrações de banco)
- **Bean Validation**
- **Lombok**

### Ferramentas de Desenvolvimento

- **Maven**
- **Spring DevTools**
- **Postman / Insomnia**
- **SpringDoc OpenAPI (Swagger)** para documentação da API

---

## 🚀 Como Executar

O projeto pode rodar com **H2 (padrão)** ou **PostgreSQL**.

### ✅ Pré-requisitos

- **JDK 21** instalado e configurado.
- **Maven** instalado (`mvn -version`).
- **PostgreSQL 15+** (apenas se for usar o perfil `postgres`).

### 1. Clonar o repositório
git clone https://github.com/andreyrsy/kitchen-flow.git
cd kitchen-flow


### 2. Rodar com H2 (padrão – recomendado para testes)
mvn spring-boot:run


- API: `http://localhost:8080/api/v1/`
- Swagger: `http://localhost:8080/swagger-ui.html`
- H2 Console: `http://localhost:8080/h2-console`  
  - JDBC URL: `jdbc:h2:mem:db_kitchen`  
  - User: `sa`  
  - Password: *(vazio)*

### 3. Rodar com PostgreSQL (perfil `postgres`)

1. Criar o banco:
CREATE DATABASE db_kitchen;

2. Ajustar credenciais em:
src/main/resources/application-postgres.properties

3. Executar com o profile:
mvn spring-boot:run "-Dspring-boot.run.profiles=postgres"

---

## 📖 Documentação da API

### 🏷️ Categorias

| Método | Endpoint                 | Descrição              |
|--------|-------------------------|------------------------|
| `POST` | `/api/v1/categorias`   | Criar categoria        |
| `GET`  | `/api/v1/categorias`   | Listar categorias      |
| `GET`  | `/api/v1/categorias/{id}` | Buscar categoria por ID |
| `DELETE` | `/api/v1/categorias/{id}` | Deletar categoria   |

### 📦 Produtos

| Método | Endpoint               | Descrição              |
|--------|-----------------------|------------------------|
| `GET`  | `/api/v1/produtos`   | Listar produtos        |
| `GET`  | `/api/v1/produtos/{id}` | Buscar produto por ID |
| `POST` | `/api/v1/produtos`   | Criar produto          |
| `DELETE` | `/api/v1/produtos/{id}` | Deletar produto     |

### 📋 Lotes

| Método | Endpoint                        | Descrição                    |
|--------|----------------------------------|------------------------------|
| `GET`  | `/api/v1/lotes`                | Listar lotes                 |
| `GET`  | `/api/v1/lotes/{id}`          | Buscar lote por ID           |
| `POST` | `/api/v1/lotes`               | Criar lote                   |
| `POST` | `/api/v1/lotes/{id}/consumos` | Consumir produto de um lote  |
| `DELETE` | `/api/v1/lotes/{id}`        | Deletar lote                 |

---

## 💡 Exemplos de Uso

### Criar uma categoria
POST /api/v1/categorias
Content-Type: application/json

{
    "nome": "Laticínios"
}

### Criar um produto
POST /api/v1/produtos
Content-Type: application/json

{
    "nome": "Leite Integral",
    "unidadeMedida": "litros",
    "categoriaId": 1
}

### Criar um lote
POST /api/v1/lotes
Content-Type: application/json

{
    "quantidade": 50,
    "dataValidade": "25-12-2024",
    "dataEntrada": "20-12-2024",
    "produtoId": 1
}


### Consumir de um lote
POST /api/v1/lotes/1/consumos
Content-Type: application/json

{
    "quantidade": 10
}

---

## 🔮 Roadmap

- [x] Documentação interativa com Swagger/OpenAPI.
- [ ] Testes unitários e de integração.
- [ ] Autenticação e autorização com Spring Security.
- [ ] Paginação nas listagens.
- [ ] Notificações para produtos próximos ao vencimento.
- [ ] Relatórios e estatísticas de consumo.
- [ ] Containerização com Docker.
- [ ] Pipeline CI/CD com GitHub Actions.
- [ ] Frontend para consumir a API.

---

## 🤝 Contribuição

1. Faça um fork do projeto.
2. Crie uma branch para sua feature:
git switch -c feature/minha-feature


3. Commit suas alterações:
git commit -m "Adiciona minha-feature"

4. Envie para o repositório remoto:

5. Abra um Pull Request.

---

## 👨‍💻 Autor

**Andreyrsy**

- 💼 LinkedIn: [andreyrsy](https://linkedin.com/in/andreyrsy)
- 🐙 GitHub: [andreyrsy](https://github.com/andreyrsy)
- 📧 Email: andreyrsy@gmail.com

---

## 📄 Licença

Este projeto está sob a licença MIT. Sinta-se à vontade para usar, estudar e contribuir.
