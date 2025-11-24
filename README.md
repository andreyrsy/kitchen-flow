# 🍳 Kitchen Flow API

![Status](https://img.shields.io/badge/status-ativo-success.svg)
![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)
![H2 Database](https://img.shields.io/badge/H2-Database-blue.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

> **Gerencie seu estoque de forma inteligente e reduza o desperdício.**

---

## 🎯 Sobre o Projeto

O desperdício de alimentos é um desafio crítico para restaurantes, gerando prejuízos financeiros e impacto ambiental. O **Kitchen Flow** nasce como uma resposta a esse problema.

Nossa missão é oferecer uma ferramenta robusta para o **gerenciamento inteligente de estoque**, permitindo que estabelecimentos:
*   📉 **Reduzam o desperdício** controlando validades.
*   💰 **Economizem recursos** evitando compras desnecessárias.
*   ⚡ **Otimizem a operação** com dados precisos sobre o inventário.

---

## ✨ Funcionalidades

### 🏷️ Gestão de Produtos
*   **Categorização**: Organize alimentos em categorias personalizáveis.
*   **Cadastro Completo**: Gerencie produtos com detalhes e unidades de medida.

### 📦 Controle de Estoque (Lotes)
*   **Rastreabilidade**: Controle total sobre lotes de entrada.
*   **Validade**: Monitoramento de datas de vencimento para evitar perdas.
*   **Consumo Inteligente**: Baixa de estoque organizada por lotes.

---

## 🛠️ Tecnologias Utilizadas

O projeto foi construído com uma stack moderna e robusta:

| Categoria | Tecnologia | Descrição |
|-----------|------------|-----------|
| **Linguagem** | **Java 21** | Recursos modernos e alta performance. |
| **Framework** | **Spring Boot 3.5.3** | Desenvolvimento ágil e convenção sobre configuração. |
| **Dados** | **Spring Data JPA** | Abstração para persistência de dados. |
| **Banco (Dev)** | **H2 Database** | Banco em memória para testes rápidos e sem configuração. |
| **Banco (Prod)** | **PostgreSQL** | Banco relacional robusto para produção. |
| **Migração** | **Flyway** | Versionamento e evolução do esquema do banco. |
| **Docs** | **SpringDoc (Swagger)** | Documentação automática e interativa da API. |
| **Utils** | **Lombok** | Redução de código repetitivo (boilerplate). |

---

## 🚀 Começando

Siga os passos abaixo para rodar o projeto em sua máquina local.

### Pré-requisitos

Certifique-se de ter instalado:
1.  **[Java JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)**
2.  **[Maven](https://maven.apache.org/download.cgi)**
3.  **Git**

### Instalação

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/andreyrsy/kitchen-flow.git
    cd kitchen-flow
    ```

2.  **Compile o projeto:**
    ```bash
    mvn clean install
    ```

### Executando a Aplicação

Você pode rodar a aplicação de duas formas:

#### 🟢 Opção 1: Modo Desenvolvimento (H2) - *Recomendado*
Ideal para testes rápidos. Não requer instalação de banco de dados externo.

```bash
mvn spring-boot:run
```
*O banco H2 iniciará automaticamente em memória.*

#### 🔵 Opção 2: Modo Produção (PostgreSQL)
Para persistência real de dados.

1.  Crie um banco de dados chamado `db_kitchen` no seu PostgreSQL.
2.  (Opcional) Configure usuário/senha em `src/main/resources/application-postgres.properties`.
3.  Execute com o perfil `postgres`:

```bash
mvn spring-boot:run "-Dspring-boot.run.profiles=postgres"
```

---

## 📖 Documentação da API

A API possui documentação interativa via Swagger UI.
Após iniciar a aplicação, acesse:

👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

### Principais Endpoints

| Recurso | Método | Endpoint | Descrição |
|---------|--------|----------|-----------|
| **Categorias** | `POST` | `/api/v1/categorias` | Criar nova categoria |
| | `GET` | `/api/v1/categorias` | Listar todas |
| **Produtos** | `POST` | `/api/v1/produtos` | Cadastrar produto |
| | `GET` | `/api/v1/produtos` | Listar produtos |
| **Lotes** | `POST` | `/api/v1/lotes` | Registrar entrada de lote |
| | `POST` | `/api/v1/lotes/{id}/consumos` | Consumir item do estoque |

---

## 💡 Exemplos de Uso

Abaixo, alguns exemplos de payloads para testar a API (via Postman ou Swagger).

### 1. Criar Categoria
**POST** `/api/v1/categorias`
```json
{
  "nome": "Hortifruti"
}
```

### 2. Criar Produto
**POST** `/api/v1/produtos`
```json
{
  "nome": "Tomate Italiano",
  "unidadeMedida": "kg",
  "categoriaId": 1
}
```

### 3. Registrar Lote (Entrada)
**POST** `/api/v1/lotes`
```json
{
  "quantidade": 100,
  "dataValidade": "2024-12-31",
  "dataEntrada": "2024-11-24",
  "produtoId": 1
}
```

---

## 🔮 Roadmap

- [x] Documentação Swagger/OpenAPI
- [ ] Testes unitários e de integração
- [ ] Segurança (Spring Security + JWT)
- [ ] Cache (Redis)
- [ ] Paginação e Filtros avançados
- [ ] Notificações de vencimento
- [ ] Dockerização completa

---

## 🤝 Contribuição

Contribuições são bem-vindas!
1.  Faça um Fork do projeto
2.  Crie uma Branch para sua Feature (`git checkout -b feature/MinhaFeature`)
3.  Faça o Commit (`git commit -m 'Add some feature'`)
4.  Push para a Branch (`git push origin feature/MinhaFeature`)
5.  Abra um Pull Request

---

## 👨‍💻 Autor

<div align="center">

**Andreyrsy**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/andreyrsy)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/andreyrsy)
[![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:andreyrsy@gmail.com)

</div>

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
