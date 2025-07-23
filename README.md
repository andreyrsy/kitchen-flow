# Kitchen Flow API 🍳

Kitchen Flow é uma API REST simples para gerenciar itens de uma cozinha ou despensa. Ela permite adicionar, listar, consumir e remover alimentos, além de calcular automaticamente o status de validade de cada item.

---

## Como Executar o Projeto 🚀

1.  **Pré-requisitos:**
    * Java 17 ou superior
    * Maven 3.x

2.  **Clone o repositório:**
    ```bash
    git clone https://github.com/andreyrsy/kitchen-flow.git
    cd <NOME_DO_SEU_PROJETO>
    ```

3.  **Configure o banco de dados:**
    * Abra o arquivo `src/main/resources/application.properties`.
    * Configure as propriedades do seu banco de dados (URL, username, password). Exemplo para PostgreSQL:
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/kitchen_db
        spring.datasource.username=postgres
        spring.datasource.password=your_password
        spring.jpa.hibernate.ddl-auto=update
        ```

4.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```
    A API estará disponível em `http://localhost:8080`.

---

## Endpoints da API 📖

A URL base para todos os endpoints é `/api`.

### 1. Adicionar Alimento

Adiciona um novo item ao estoque.

* **Método:** `POST`
* **Endpoint:** `/api`
* **Body (JSON):**
    ```json
    {
      "alimento": "Leite Integral",
      "quantidade": 2,
      "data_validade": "30-07-2025"
    }
    ```
* **Resposta de Sucesso (201 CREATED):**
    ```json
    {
        "id": 1,
        "alimento": "Leite Integral",
        "quantidade": 2,
        "data_validade": "30-07-2025"
    }
    ```

***

### 2. Listar todos os Alimentos

Lista todos os itens do estoque, incluindo um campo `status` calculado com base na data de validade.

* **Status de Validade:**
    * `VENCIDO`: A data de validade já passou.
    * `URGENTE`: Vence hoje ou amanhã.
    * `ATENCAO`: Vence em até 3 dias.
    * `NORMAL`: Validade superior a 3 dias.

* **Método:** `GET`
* **Endpoint:** `/api`
* **Resposta de Sucesso (200 OK):**
    ```json
    [
      {
        "id": 1,
        "alimento": "Leite Integral",
        "quantidade": 2,
        "dataValidade": "2025-07-30",
        "status": "ATENCAO"
      },
      {
        "id": 2,
        "alimento": "Ovos",
        "quantidade": 12,
        "dataValidade": "2025-08-15",
        "status": "NORMAL"
      }
    ]
    ```

***

### 3. Consumir Alimento

Atualiza a quantidade de um item no estoque após o consumo.

* **Método:** `PUT`
* **Endpoint:** `/api/produto/{id}/qtd/{qtd_consumida}`
* **Parâmetros de URL:**
    * `id`: O ID do alimento.
    * `qtd_consumida`: A quantidade a ser subtraída do estoque.
* **Exemplo de Uso:** `PUT /api/produto/1/qtd/1`
* **Resposta de Sucesso:** `200 OK` (corpo vazio).
* **Resposta de Erro:** Retorna um erro se o item não for encontrado ou se a quantidade a ser consumida for maior que o estoque.

***

### 4. Deletar Alimento

Remove um item do estoque pelo seu ID.

* **Método:** `DELETE`
* **Endpoint:** `/api/deletar/{id}`
* **Parâmetro de URL:**
    * `id`: O ID do alimento a ser deletado.
* **Exemplo de Uso:** `DELETE /api/deletar/1`
* **Resposta de Sucesso:** `200 OK` (corpo vazio).
