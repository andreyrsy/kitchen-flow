# Kitchen Flow API 🍳

![Status](https://img.shields.io/badge/status-ativo-success.svg)
![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

## 🎯 **Projeto Kitchenflow**

Olá! Sou o **Andrey** e este é meu primeiro projeto Spring Boot "de verdade". Quando comecei, eu entendia o que era um DTO, como fazer relacionamentos entre entidades, mas agora nesse projeto utilizando eles na prática ficou muito mais claro o funcionamento.

Este projeto nasceu de uma necessidade real e pessoal, que ocorreu quando trabalhei em um restaurante: **combater o desperdício de comida em restaurantes e comidas estragadas**.

O **Kitchen Flow** é minha tentativa de resolver esse problema com tecnologia, criando uma API que monitora com proatividade as datas de validade e ajuda a saber o que precisa ser consumido com urgência.

---

## 🚀 **O Que Este Projeto Me Ensinou**

### **📚 Conceitos que Dominei:**

**1. Arquitetura em Camadas**
- Aprendi que separar Controller → Service → Repository não é só "boa prática", é **essencial** para manter código limpo
- Descobri que cada camada tem sua responsabilidade específica e não deve "invadir" a outra

**2. Relacionamentos JPA**
- Confesso: no começo, `@OneToMany` e `@ManyToOne` me confundiam muito
- Mas quando entendi que `Categoria (1) → Produto (N) → Lotes (N)`, tudo fez sentido!
- Aprendi que relacionamentos são sobre **como os dados se conectam no mundo real**

**3. DTOs**
- No início, retornava entidades direto do banco (erro que me fez aprender os Dto's)
- Enfrentei o problema da recursão infinita na serialização de entidades, que gerava respostas
  com milhares de linhas devido aos relacionamentos bidirecionais. A ficha caiu quando entendi que
  precisava usar DTOs para criar um "contrato" e parar de expor o banco inteiro. E foi aqui onde os
  Dto's me ajudou a resolver para finalmente ditar o que a API devolve e quebrar o ciclo.
- Descobri que DTOs são como "traduções" entre o que está no banco e o que o cliente precisa
- A conversão manual de entidade para DTO me ensinou muito sobre estrutura de dados

**4. Validação de Dados**
- `@NotBlank`, `@NotNull` - essas anotações pequenas fazem uma diferença ENORME
- Aprendi que validar na entrada é melhor que descobrir o erro depois

**5. Tratamento de Exceções**
- O `GlobalExceptionHandler` foi essencial.
- Antes os erros apareciam como stack traces gigantes
- Agora retorno mensagens estruturadas

**6. Logs Estruturados**
- Descobri que logs não são só `System.out.println()`
- SLF4J me ensinou a diferença entre `log.info()`, `log.error()` e `log.debug()`
- Agora consigo debugar os problemas mais rápido

---

## 🛠️ **Tecnologias que Dominei**

### **Backend Stack:**
- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway**

### **Ferramentas**
- **Lombok**
- **Bean Validation**
- **Maven**

---

## 🏗️ **Arquitetura do Projeto**

```
Cliente → Controller → Service → Repository → Banco de Dados
```

### **Minha Estrutura de Pacotes:**
```
dev.andreyrsy.kitchen.flow/
├── controller/     # Endpoints da API
├── service/       # Lógica de negócio
├── repository/    # Acesso aos dados
├── model/        # Entidades JPA
├── dto/          # Objetos de transferência
└── exception/    # Tratamento global de erros
```

### **Relacionamentos que Implementei:**
```
Categoria (1) ←→ (N) Produto (1) ←→ (N) Lotes
```

**Por que essa estrutura?**
- Uma categoria pode ter vários produtos (ex: "Laticínios" tem leite, queijo, iogurte)
- Um produto pode ter vários lotes (ex: "Leite" pode ter lotes de datas diferentes)
- Isso reflete a realidade de um restaurante!

---

## ✨ **Funcionalidades Implementadas**

### **🏷️ Gestão de Categorias**
- ✅ Criar, listar e deletar categorias
- ✅ Validação de dados obrigatórios
- ✅ Tratamento de exceções personalizado

### **📦 Gestão de Produtos**
- ✅ CRUD completo com DTOs
- ✅ Associação automática com categorias
- ✅ Validação de campos obrigatórios
- ✅ Logs estruturados para debugging

### **📋 Gestão de Lotes**
- ✅ Controle de quantidade por lote
- ✅ Validação de datas (entrada vs validade)
- ✅ Consumo inteligente de estoque
- ✅ Conversão completa de entidade para DTO

### **🔍 Sistema de Status de Validade**
- ✅ Cálculo automático baseado em dias restantes
- ✅ Status: `NORMAL`, `ATENCAO`, `URGENTE`, `VENCIDO`
- ✅ Lógica de negócio centralizada no service

---

## 🎓 **Desafios que enfrentei**

### **1. O problema dos relacionamentos**
**Desafio:** Entender como conectar Categoria → Produto → Lotes
**Solução:** Muitos testes, leitura da documentação do Hibernate, e principalmente: **prática**

### **2. Conversão de entidade → DTO**
**Desafio:** No início, retornava entidades direto do banco
**Solução:** Aprendi que DTOs são "traduções" e implementei conversão manual completa

### **3. Tratamento de exceções**
**Desafio:** Stack traces gigantes e mensagens confusas
**Solução:** Implementei `GlobalExceptionHandler` com respostas estruturadas

### **4. Validação de dados**
**Desafio:** Como garantir que dados válidos entrem no sistema
**Solução:** Bean Validation com `@NotBlank`, `@NotNull` e mensagens personalizadas

### **5. Logs Estruturados**
**Desafio:** Debugging sem mensagens claras
**Solução:** SLF4J com logs informativos e contexto rico

---

## 🚀 **Como Executar o Projeto**

### **Pré-requisitos:**
- Java 21+
- Maven 3.x+
- PostgreSQL 15+
- IDE (IntelliJ IDEA)

### **Passo a Passo:**

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/andreyrsy/kitchen-flow.git
   cd kitchen-flow
   ```

2. **Configure o banco:**
   - Crie um banco PostgreSQL chamado `db_kitchen`
   - Atualize as credenciais em `application.properties` se necessário

3. **Execute:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Teste a API:**
   - Acesse `http://localhost:8080/api/v1/`
   - Use Postman ou Insomnia para testar os endpoints

---

## 📖 **API Endpoints**

### **🏷️ Categorias** (`/api/v1/categoria`)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/v1/categoria` | Criar categoria |
| `GET` | `/api/v1/categoria` | Listar categorias |
| `DELETE` | `/api/v1/categoria/delete/{id}` | Deletar categoria |

### **📦 Produtos** (`/api/v1/produto`)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/v1/produto` | Listar produtos |
| `POST` | `/api/v1/produto` | Criar produto |
| `DELETE` | `/api/v1/produto/deletar/{id}` | Deletar produto |

### **📋 Lotes** (`/api/v1/lotes`)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/v1/lotes` | Listar lotes |
| `POST` | `/api/v1/lotes` | Criar lote |
| `PUT` | `/api/v1/lotes/{id}/consumir/{qtd}` | Consumir produto |
| `DELETE` | `/api/v1/lotes/deletar/{id}` | Deletar lote |

---

## 💡 **Exemplos de Uso**

### **Criar um Produto:**
```bash
POST /api/v1/produto
{
  "nome": "Leite Integral",
  "unidadeMedida": "litros",
  "categoriaId": 1
}
```

### **Criar um Lote:**
```bash
POST /api/v1/lotes
{
  "quantidade": 50,
  "dataValidade": "25-12-2024",
  "dataEntrada": "20-12-2024",
  "produtoId": 1
}
```

### **Consumir Produto:**
```bash
PUT /api/v1/lotes/1/consumir/10
```

---

## 🔧 **Configurações Importantes**

### **Application Properties:**
```properties
# Log personalizado
logging.pattern.console=%d{HH:mm} %-5level %F.%M:%L >>> %cyan(%msg%n)

# Banco de dados
spring.datasource.url=jdbc:postgresql://localhost:5432/db_kitchen

# Formatação de datas
spring.jackson.date-format=dd-MM-yyyy
spring.jackson.time-zone=America/Sao_Paulo

# Flyway (migrações)
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
```

---

## 🎯 **O que aprendi com o projeto**

### **1. Testes São Essenciais**
- Sempre testando as APIs com Postman/Insomnia
- Um endpoint que funciona no código pode falhar na prática

### **2. Logs Salvam Vidas**
- Implementei logs estruturados e o debugging ficou mais fácil
- `log.info()`, `log.error()`, `log.debug()` - cada um tem seu propósito

### **3. Validação Previne Problemas**
- Validar dados na entrada, não na saída
- Bean Validation é mais poderoso do que parece

---

## 🔮 **Próximos passos do projeto**

### **Curto Prazo:**
- [ ] Implementar testes unitários (ainda não sei como fazer direito)
- [ ] Adicionar Swagger/OpenAPI para documentação interativa
- [ ] Melhorar tratamento de exceções específicas

### **Médio Prazo:**
- [ ] Implementar autenticação com Spring Security
- [ ] Adicionar cache para consultas frequentes
- [ ] Implementar paginação nas listagens

### **Longo Prazo:**
- [ ] Containerizar com Docker
- [ ] Implementar CI/CD com GitHub Actions
- [ ] Criar frontend em React para consumir a API
---

**Andreyrsy**

- 💼 **LinkedIn:** [andreyrsy](https://linkedin.com/in/andreyrsy)
- 🐙 **GitHub:** [andreyrsy](https://github.com/andreyrsy)
- 📧 **Email:** [andreyrsy@gmail.com]
---

## 📄 **Licença**

Este projeto está sob a licença MIT. Sinta-se livre para usar, modificar e distribuir!

---

