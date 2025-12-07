# 🍳 Kitchen Flow

<div align="center">

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.3-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Auth-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

**Sistema de Gestão de Validade e Estoque para Restaurantes**

</div>

---

## 💡 Por que este projeto existe?

Imagine uma cozinha de restaurante movimentada: dezenas de ingredientes chegando diariamente, cada um com sua data de validade, e uma equipe que precisa saber exatamente o que usar primeiro para evitar desperdício.

**Kitchen Flow** nasceu para resolver esse problema real.

Muitos restaurantes ainda controlam seus estoques em planilhas ou, pior, de memória. Isso gera:

- 🗑️ **Desperdício** — ingredientes vencem esquecidos no fundo da geladeira
- ⚠️ **Riscos sanitários** — produtos vencidos podem parar em pratos
- 💸 **Prejuízo financeiro** — dinheiro jogado fora literalmente

Com o Kitchen Flow, o gerente registra cada lote que chega (com quantidade e validade), e a equipe consulta o sistema para saber o que usar primeiro. Simples assim.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia                | Descrição                                   |
| ------------------------- | ------------------------------------------- |
| **Java 21**               | Linguagem principal                         |
| **Spring Boot 3.5**       | Framework backend                           |
| **Spring Security + JWT** | Autenticação e autorização                  |
| **Spring Data JPA**       | Persistência de dados                       |
| **PostgreSQL / H2**       | Banco de dados (produção / desenvolvimento) |
| **Flyway**                | Versionamento de banco                      |
| **MapStruct**             | Mapeamento DTO ↔ Entity                     |
| **SpringDoc OpenAPI**     | Documentação Swagger                        |

---

## 🚀 Como Executar

O projeto possui **dois perfis** de execução:

| Perfil     | Banco      | Uso recomendado |
| ---------- | ---------- | --------------- |
| `default`  | H2         | Desenvolvimento |
| `postgres` | PostgreSQL | **Produção**    |

### Opção 1: Desenvolvimento (H2)

```bash
git clone https://github.com/andreyrsy/kitchen-flow.git
cd kitchen-flow
./mvnw spring-boot:run
```

> Banco em memória — os dados são perdidos ao reiniciar.

### Opção 2: Produção (PostgreSQL) ✅ Recomendado

```bash
# 1. Crie o banco de dados
psql -U postgres -c "CREATE DATABASE db_kitchen;"

# 2. Execute com o perfil postgres
./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres
```

### Acessos

| Recurso     | URL                                     |
| ----------- | --------------------------------------- |
| **API**     | `http://localhost:8080/api/v1`          |
| **Swagger** | `http://localhost:8080/swagger-ui.html` |

> **Windows?** Use `mvnw.cmd` ao invés de `./mvnw`

---

## 🔐 Autenticação e Segurança

O sistema usa **JWT (JSON Web Token)** para proteger as rotas com arquitetura **stateless**.

### Fluxo de Autenticação

```
┌────────────┐    POST /auth/login   ┌────────────┐
│   Client   │ ────────────────────► │   Server   │
│            │ ◄──────────────────── │            │
└──────┴─────┘    { token: "..." }   └────────────┘
       │                                    ▲
       │    GET /api/v1/produtos            │
       └──── Authorization: Bearer <token> ─┘
```

### Componentes de Segurança

| Componente         | Arquivo               | Função                                |
| ------------------ | --------------------- | ------------------------------------- |
| **SecurityConfig** | `SecurityConfig.java` | Regras de autorização por endpoint    |
| **SecurityFilter** | `SecurityFilter.java` | Intercepta e valida o token JWT       |
| **TokenConfig**    | `TokenConfig.java`    | Geração e validação de tokens (auth0) |
| **AuthController** | `AuthController.java` | Endpoints `/auth/login` e `/register` |

### Configurações de Segurança

| Configuração         | Valor                    |
| -------------------- | ------------------------ |
| **CSRF**             | Desabilitado (stateless) |
| **Sessão**           | Stateless                |
| **Algoritmo JWT**    | HMAC256                  |
| **Expiração Token**  | 2 horas                  |
| **Password Encoder** | BCrypt                   |

### Roles de Usuário

| Cargo           | Role    | O que pode fazer                                 |
| --------------- | ------- | ------------------------------------------------ |
| **Gerente**     | `ADMIN` | Tudo (criar/deletar categorias, lotes, produtos) |
| **Funcionário** | `USER`  | Cadastrar produtos, consultar e consumir estoque |

> **💡 Contexto:** O gerente confere mercadorias dos fornecedores, por isso só ele cria lotes. Funcionários usam o sistema para baixar consumo.

### Como autenticar

**1. Registre um usuário:**

```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"login": "gerente", "password": "123456", "role": "ADMIN"}'
```

**2. Faça login e guarde o token:**

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"login": "gerente", "password": "123456"}'
```

**3. Use o token nas requisições:**

```bash
curl -X GET http://localhost:8080/api/v1/produtos \
  -H "Authorization: Bearer <seu_token>"
```

> **💡 Swagger:** Clique em **Authorize** e cole: `Bearer <seu_token>`

---

## 📡 Endpoints Principais

### Autenticação

| Método | Rota             | Descrição     |
| ------ | ---------------- | ------------- |
| `POST` | `/auth/register` | Criar usuário |
| `POST` | `/auth/login`    | Fazer login   |

### Categorias

| Método   | Rota                      | Acesso           | Descrição    |
| -------- | ------------------------- | ---------------- | ------------ |
| `GET`    | `/api/v1/categorias`      | Todos            | Listar todas |
| `POST`   | `/api/v1/categorias`      | **Apenas ADMIN** | Criar nova   |
| `DELETE` | `/api/v1/categorias/{id}` | **Apenas ADMIN** | Remover      |

### Produtos

| Método   | Rota                    | Descrição    |
| -------- | ----------------------- | ------------ |
| `GET`    | `/api/v1/produtos`      | Listar todos |
| `POST`   | `/api/v1/produtos`      | Criar novo   |
| `DELETE` | `/api/v1/produtos/{id}` | Remover      |

### Lotes (Estoque)

| Método   | Rota                          | Acesso           | Descrição        |
| -------- | ----------------------------- | ---------------- | ---------------- |
| `GET`    | `/api/v1/lotes`               | Todos            | Listar lotes     |
| `POST`   | `/api/v1/lotes`               | **Apenas ADMIN** | Criar lote       |
| `POST`   | `/api/v1/lotes/consumir/{id}` | Todos            | Consumir estoque |
| `DELETE` | `/api/v1/lotes/{id}`          | **Apenas ADMIN** | Remover lote     |

---

## 🗄️ Modelo de Dados

```
CATEGORIA ──1:N──► PRODUTO ──1:N──► LOTES
                                      │
                                      ├── quantidade
                                      ├── dataEntrada
                                      └── dataValidade
```

Cada **categoria** agrupa **produtos** (ex: "Carnes" → "Filé Mignon"). Cada **produto** pode ter múltiplos **lotes** com diferentes datas de validade.

---

## ⚙️ Variáveis de Ambiente

| Variável                     | Descrição            | Obrigatório |
| ---------------------------- | -------------------- | :---------: |
| `api.security.token.secret`  | Chave secreta do JWT |     ✅      |
| `spring.datasource.url`      | URL do banco         |     ✅      |
| `spring.datasource.username` | Usuário do banco     |     ✅      |
| `spring.datasource.password` | Senha do banco       |     ✅      |

---

## 📁 Estrutura do Projeto

```
src/main/java/dev/andreyrsy/kitchen/flow/
├── config/        # Segurança, Swagger, JWT
├── controller/    # Endpoints REST
├── dto/           # Objetos de transferência
├── exception/     # Tratamento de erros
├── mapper/        # Conversões com MapStruct
├── model/         # Entidades JPA
├── repository/    # Acesso ao banco
└── service/       # Lógica de negócio
```

---

## 🗺️ Próximos Passos

- [x] Autenticação JWT
- [ ] Alertas de produtos próximos da validade
- [ ] Relatórios de consumo
- [ ] Containerização com Docker

---

## 👨‍💻 Autor

**Andrey** — [@andreyrsy](https://github.com/andreyrsy)

---

<div align="center">

⭐ Se este projeto foi útil, considere dar uma estrela!

</div>
