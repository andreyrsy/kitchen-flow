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

## ✨ Funcionalidades Principais

### 🏷️ Gestão de Categorias e Produtos
- Cadastro, listagem e exclusão de categorias de alimentos
- CRUD completo de produtos com associação a categorias

### 📦 Controle de Estoque (Lotes)
- Registro de entrada de novos lotes com data de validade
- Controle de quantidade por lote
- Consumo de produtos do estoque
- Rastreabilidade dos lotes

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 21**: Linguagem de programação moderna e robusta
- **Spring Boot 3.5.3**: Framework para desenvolvimento rápido e eficiente
- **Spring Data JPA**: Simplificação do acesso a dados
- **H2 Database / Postgresql**: Banco de dados em memória para fácil execução e testes
- **Flyway**: Controle de versão para banco de dados
- **Lombok**: Redução de código boilerplate
- **Bean Validation**: Validação declarativa de dados
- **SpringDoc OpenAPI (Swagger)**: Documentação interativa da API

### Ferramentas de Desenvolvimento
- **Maven**: Gerenciamento de dependências e build
- **Spring DevTools**: Produtividade no desenvolvimento
- **Postman**: Testes de API

## 🚀 Como Executar o Projeto

O projeto foi configurado para rodar facilmente com **H2 Database** (padrão) ou **PostgreSQL** (perfil opcional).

### Pré-requisitos

Para executar este projeto, seu ambiente deve estar configurado com as seguintes tecnologias:

1.  **Java Development Kit (JDK) 21**
    -   O projeto utiliza recursos modernos do Java 21.
    -   [Download JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)

2.  **Apache Maven**
    -   Gerenciador de dependências necessário para compilar e rodar o projeto.
    -   **Como instalar:**
        1.  Acesse a [página de download do Maven](https://maven.apache.org/download.cgi).
        2.  Baixe o arquivo binário zip (ex: `apache-maven-3.9.x-bin.zip`).
        3.  Extraia o arquivo em uma pasta de sua preferência.
        4.  Adicione o caminho da pasta `bin` (dentro da pasta extraída) às Variáveis de Ambiente (PATH) do seu sistema.
    -   **Verificação:** Abra o terminal e digite `mvn -version` para confirmar a instalação.

3.  **PostgreSQL 15+** (Opcional)
    -   Necessário apenas se você optar por rodar o projeto com o perfil de produção (`postgres`).
    -   Para testes rápidos, o projeto já vem configurado com **H2 Database** (banco em memória) que não requer instalação.

### Passo a Passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/andreyrsy/kitchen-flow.git
   cd kitchen-flow
   ```

2. **Execute a aplicação**

   **Opção A: Rodar com H2 (Padrão - Recomendado para Testes)**
   Basta executar o comando abaixo. O `application.properties` será carregado automaticamente.
   ```bash
   mvn spring-boot:run
   ```
   *O banco de dados H2 será iniciado automaticamente em memória.*

   **Opção B: Rodar com PostgreSQL**
   Caso queira usar um banco real, certifique-se de que o Postgres está rodando e execute ativando o perfil `postgres`.

   > **Importante:** Antes de rodar, você deve criar um banco de dados chamado `db_kitchen` no seu PostgreSQL.
   > Caso prefira usar outro nome ou alterar usuário/senha, edite o arquivo `src/main/resources/application-postgres.properties`.

   ```bash
   mvn spring-boot:run "-Dspring-boot.run.profiles=postgres"
   ```

3. **Acesse a API**
   - A API estará disponível em `http://localhost:8080/api/v1/`
   - **Documentação Interativa (Swagger):** Acesse `http://localhost:8080/swagger-ui.html` para testar os endpoints diretamente pelo navegador.
   - **Console H2:** Acesse `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:db_kitchen`, User: `sa`, Password: ` `)

## 📖 Documentação da API

### 🏷️ Categorias
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/v1/categorias` | Criar categoria |
| `GET` | `/api/v1/categorias` | Listar categorias |
| `GET` | `/api/v1/categorias/{id}` | Buscar categoria por ID |
| `DELETE` | `/api/v1/categorias/{id}` | Deletar categoria |

### 📦 Produtos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/v1/produtos` | Listar produtos |
| `GET` | `/api/v1/produtos/{id}` | Buscar produto por ID |
| `POST` | `/api/v1/produtos` | Criar produto |
| `DELETE` | `/api/v1/produtos/{id}` | Deletar produto |

### 📋 Lotes
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/v1/lotes` | Listar lotes |
| `GET` | `/api/v1/lotes/{id}` | Buscar lote por ID |
| `POST` | `/api/v1/lotes` | Criar lote |
| `POST` | `/api/v1/lotes/{id}/consumos` | Consumir produto do lote |
| `DELETE` | `/api/v1/lotes/{id}` | Deletar lote |

## 💡 Exemplos de Uso

### Criar uma Categoria
```bash
POST /api/v1/categorias
{
  "nome": "Laticínios"
}
```

### Criar um Produto
```bash
POST /api/v1/produtos
{
  "nome": "Leite Integral",
  "unidadeMedida": "litros",
  "categoriaId": 1
}
```

### Criar um Lote
```bash
POST /api/v1/lotes
{
  "quantidade": 50,
  "dataValidade": "25-12-2024",
  "dataEntrada": "20-12-2024",
  "produtoId": 1
}
```

### Consumir Produto
```bash
POST /api/v1/lotes/1/consumos
{
  "quantidade": 10
}
```

## 🔮 Roadmap do Projeto

### Próximas Implementações
- [x] Documentação interativa com Swagger/OpenAPI
- [ ] Testes unitários e de integração
- [ ] Autenticação e autorização com Spring Security
- [ ] Cache para consultas frequentes
- [ ] Paginação nas listagens
- [ ] Sistema de notificações para produtos próximos ao vencimento
- [ ] Relatórios e estatísticas de consumo
- [ ] Containerização com Docker
- [ ] CI/CD com GitHub Actions
- [ ] Frontend para consumir a API

## 👨‍💻 Autor

**Andreyrsy**

- 💼 **LinkedIn:** [andreyrsy](https://linkedin.com/in/andreyrsy)
- 🐙 **GitHub:** [andreyrsy](https://github.com/andreyrsy)
- 📧 **Email:** [andreyrsy@gmail.com]

## 📄 Licença

Este projeto está sob a licença MIT. Sinta-se livre para usar, modificar e contribuir!
