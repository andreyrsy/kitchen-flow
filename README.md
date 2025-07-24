# Kitchen Flow API 🍳

![Status](https://img.shields.io/badge/status-ativo-success.svg)
![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

## 🎯 Sobre o Projeto

O **Kitchen Flow** é uma API RESTful desenvolvida para o gerenciamento de estoque de restaurantes. O principal objetivo é combater o desperdício de comida com um monitoramento proativo de datas de validade, permitindo que o estabelecimento saiba exatamente o que precisa ser consumido com urgência.

Este projeto demonstra a aplicação de desenvolvimento backend com o SpringBoot, criação de uma API REST, lógica de negócio, persistência de dados e boas práticas.

---

## ✨ Principais Funcionalidades

* **CRUD de Alimentos:** Adicionar, listar, atualizar e remover itens do inventário.
* **Controle de Quantidade:** Atualização inteligente do estoque ao consumir um item.
* **Cálculo de Status de Validade:** Classificação automática dos alimentos com base na proximidade da data de validade (`NORMAL`, `ATENÇÃO`, `URGENTE`, `VENCIDO`).
* **API RESTful:** Endpoints claros e bem definidos seguindo boas práticas.

---

## 🛠️ Tecnologias e Ferramentas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3
* **Módulos Spring:** Spring Web, Spring Data JPA
* **Persistência:** Hibernate
* **Banco de Dados:** PostgreSQL (Utilizei o Postgresql da Azure)
* **Gerenciador de Dependências:** Maven
* **Annotations:** Lombok para redução de código boilerplate.

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas para garantir a separação de responsabilidades e a manutenibilidade do código:

`Cliente (Postman/Frontend) → Controller (API Layer) → Service (Business Logic) → Repository (Data Access) → Banco de Dados`
* MVC ->
* **Controller (`KitchenController`):** Responsável por expor os endpoints da API, receber as requisições HTTP e retornar as respostas.
* **Service (`KitchenService`):** Onde reside a lógica de negócio principal, como o cálculo do status de validade e as regras de consumo.
* **Repository (`KitchenRepository`):** Interface que abstrai o acesso aos dados, utilizando o Spring Data JPA para interagir com o banco de dados.
* **Model/DTOs (`KitchenModel`, `KitchenResponseDTO`):** Representam as entidades de dados e os objetos de transferência de dados para a API.

---

## 🚀 Como Executar

### Pré-requisitos

* Java 17+
* Maven 3.x+
* PostgreSQL (ou outro banco de dados relacional)
* Uma IDE (IntelliJ, VS Code, Eclipse)

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/andreyrsy/kitchen-flow.git
    cd kitchen-flow
    ```

2.  **Configure o Banco de Dados:**
    * Crie um banco de dados no PostgreSQL (ex: `kitchen_db`).
    * No arquivo `src/main/resources/application.properties`, atualize as credenciais do seu banco de dados:
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/kitchen_db
        spring.datasource.username=seu_usuario
        spring.datasource.password=sua_senha
        spring.jpa.hibernate.ddl-auto=update
        ```

3.  **Instale as dependências e compile o projeto:**
    ```bash
    mvn clean install
    ```

4.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```

A API estará disponível em `http://localhost:8080/api`.

---

## API Endpoints 📖

| Funcionalidade | Método HTTP | Endpoint | Descrição |
| :--- | :--- | :--- | :--- |
| **Adicionar Alimento** | `POST` | `/api` | Adiciona um novo item ao estoque. |
| **Listar Alimentos** | `GET` | `/api` | Lista todos os itens e seu status de validade. |
| **Consumir Alimento** | `PUT` | `/api/produto/{id}/qtd/{qtd}` | Atualiza a quantidade de um item. |
| **Remover Alimento**| `DELETE` | `/api/deletar/{id}` | Remove um item do estoque pelo ID. |

### Exemplo de Uso:

#### 1. Adicionar Alimento
`POST /api`

**Request Body:**
```json
{
  "alimento": "Iogurte Natural",
  "quantidade": 4,
  "data_validade": "30-07-2025"
}
```
#### Success Response (201 CREATED):
```json
{
    "id": 1,
    "alimento": "Iogurte Natural",
    "quantidade": 4,
    "data_validade": "30-07-2025"
}
```
#### 2. Listar Alimentos
`GET /api`

Success Response (200 OK):
```json
{
  "id": 1,
  "alimento": "Iogurte Natural",
  "quantidade": 4,
  "dataValidade": "2025-07-30",
  "status": "ATENCAO" 
}
```
#### (O status ATENCAO foi calculado com base na data de 23/07/2025).
Lista todos os itens do estoque, incluindo um campo `status` calculado com base na data de validade.

* **Status de Validade:**
    * `VENCIDO`: A data de validade já passou.
    * `URGENTE`: Vence hoje ou amanhã.
    * `ATENCAO`: Vence em até 3 dias.
    * `NORMAL`: Validade superior a 3 dias.


## 🔮 Próximos Passos e Melhorias
### Este projeto tem potencial para evoluir. Algumas ideias para o futuro incluem:
```json
[ ] Segurança: Implementar Spring Security com autenticação JWT para permitir múltiplos usuários com inventários separados.

[ ] Containerização: Criar Dockerfile e docker-compose.yml para facilitar o deploy da aplicação e do banco de dados.

[ ] CI/CD: Configurar um pipeline de Integração e Entrega Contínua com GitHub Actions para automatizar testes e builds.

[ ] Tratamento de Erros: Implementar um Global Exception Handler (@ControllerAdvice) para padronizar as respostas de erro da API.

[ ] Notificações: Criar um serviço que envie notificações (por e-mail ou outro meio) quando um produto estiver próximo de vencer.

[ ] Testes: Aumentar a cobertura de testes unitários e de integração.
```

API Documentada -> http://localhost:8080/swagger-ui/index.html

<img width="1345" height="836" alt="image" src="https://github.com/user-attachments/assets/b610c772-6d82-4151-92d4-1c9f7ea01ee1" />


## 👨‍💻 Andreyrsy
- LinkedIn: https://linkedin.com/in/andreyrsy
- GitHub: https://github.com/andreyrsy
