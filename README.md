# Kitchen Flow API 🍳

![Status](https://img.shields.io/badge/status-ativo-success.svg)
![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

## 🎯 **Minha Jornada com Este Projeto**

Olá! Sou o **Andreyrsy** e este é meu primeiro projeto Spring Boot "de verdade". Quando comecei, mal sabia o que era um DTO ou como fazer relacionamentos entre entidades. Hoje, olhando para trás, vejo o quanto aprendi e cresci como desenvolvedor.

Este projeto nasceu de uma necessidade real: **combater o desperdício de comida em restaurantes**. Quantas vezes você já viu comida estragando na geladeira porque ninguém sabia que estava próxima do vencimento? Pois é, eu também! 

O **Kitchen Flow** é minha tentativa de resolver esse problema com tecnologia, criando uma API que monitora proativamente as datas de validade e ajuda restaurantes a saberem exatamente o que precisa ser consumido com urgência.

---

## 🚀 **O Que Este Projeto Me Ensinou**

### **📚 Conceitos que Dominei:**

**1. Arquitetura em Camadas**
- Aprendi que separar Controller → Service → Repository não é só "boa prática", é **essencial** para manter código limpo
- Descobri que cada camada tem sua responsabilidade específica e não deve "invadir" a outra

**2. Relacionamentos JPA (O Desafio!)**
- Confesso: no começo, `@OneToMany` e `@ManyToOne` me confundiam muito
- Mas quando entendi que `Categoria (1) → Produto (N) → Lotes (N)`, tudo fez sentido!
- Aprendi que relacionamentos são sobre **como os dados se conectam no mundo real**

**3. DTOs - A Grande Descoberta**
- No início, retornava entidades direto do banco (erro clássico de iniciante!)
- Descobri que DTOs são como "traduções" entre o que está no banco e o que o cliente precisa
- A conversão manual de entidade para DTO me ensinou muito sobre estrutura de dados

**4. Validação de Dados**
- `@NotBlank`, `@NotNull` - essas anotações pequenas fazem uma diferença ENORME
- Aprendi que validar na entrada é melhor que descobrir o erro depois

**5. Tratamento de Exceções**
- Meu `GlobalExceptionHandler` foi um marco! 
- Antes, erros apareciam como stack traces gigantes
- Agora, retorno mensagens bonitas e estruturadas

**6. Logs Estruturados**
- Descobri que logs não são só `System.out.println()`
- SLF4J me ensinou a diferença entre `log.info()`, `log.error()` e `log.debug()`
- Agora consigo debugar problemas muito mais rápido

---

## 🛠️ **Tecnologias que Dominei**

### **Backend Stack:**
- **Java 21** - A linguagem que escolhi para minha carreira
- **Spring Boot 3.5.3** - O framework que me fez amar desenvolvimento web
- **Spring Data JPA** - Para persistência de dados (Hibernate por baixo)
- **PostgreSQL** - Banco relacional robusto para dados estruturados
- **Flyway** - Controle de versão do banco (migrações são vida!)

### **Ferramentas que Me Ajudaram:**
- **Lombok** - Menos código boilerplate = mais tempo para lógica de negócio
- **Bean Validation** - Validações automáticas e consistentes
- **Maven** - Gerenciamento de dependências (ainda estou aprendendo)

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

## 🎓 **Desafios que Superei**

### **1. O Problema dos Relacionamentos**
**Desafio:** Entender como conectar Categoria → Produto → Lotes
**Solução:** Muitos testes, leitura da documentação do Hibernate, e principalmente: **prática!**

### **2. A Conversão Entidade → DTO**
**Desafio:** No início, retornava entidades direto do banco
**Solução:** Aprendi que DTOs são "traduções" e implementei conversão manual completa

### **3. Tratamento de Exceções**
**Desafio:** Stack traces gigantes e mensagens confusas
**Solução:** Implementei `GlobalExceptionHandler` com respostas estruturadas

### **4. Validação de Dados**
**Desafio:** Como garantir que dados válidos entrem no sistema?
**Solução:** Bean Validation com `@NotBlank`, `@NotNull` e mensagens personalizadas

### **5. Logs Estruturados**
**Desafio:** Debugging era um pesadelo
**Solução:** SLF4J com logs informativos e contexto rico

---

## 🚀 **Como Executar o Projeto**

### **Pré-requisitos:**
- Java 21+ (recomendo usar SDKMan para gerenciar versões)
- Maven 3.x+
- PostgreSQL 15+
- IDE (uso IntelliJ IDEA, mas VS Code também funciona)

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
# Logs personalizados (minha configuração favorita!)
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

## 🎯 **O Que Aprendi Sobre Desenvolvimento**

### **1. Persistência é Tudo**
- Não desista quando algo não funcionar na primeira tentativa
- Cada erro é uma oportunidade de aprender algo novo

### **2. Documentação é Sua Amiga**
- Spring Boot tem documentação excelente
- Quando em dúvida, leia a documentação oficial

### **3. Testes São Essenciais**
- Sempre teste suas APIs com Postman/Insomnia
- Um endpoint que funciona no código pode falhar na prática

### **4. Logs Salvam Vidas**
- Implementei logs estruturados e agora debugging é muito mais fácil
- `log.info()`, `log.error()`, `log.debug()` - cada um tem seu propósito

### **5. Validação Previne Problemas**
- Valide dados na entrada, não na saída
- Bean Validation é mais poderoso do que parece

---

## 🔮 **Próximos Passos (Minha Roadmap)**

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

## 📊 **Estatísticas do Projeto**

- **Linhas de Código:** ~800 linhas
- **Tempo de Desenvolvimento:** 2 meses (com muito estudo paralelo)
- **Commits:** 47 commits (cada um representando uma pequena vitória!)
- **Bugs Corrigidos:** Incontáveis (mas cada um me ensinou algo)

---

## 🎉 **Conclusão**

Este projeto representa muito mais que código - representa minha evolução como desenvolvedor. Cada erro, cada dúvida, cada linha de código escrita foi um passo em direção ao meu objetivo: **conseguir um estágio em desenvolvimento**.

Se você está começando como eu estava, meu conselho é: **não tenha medo de errar**. Cada erro é uma lição. Cada dúvida é uma oportunidade de crescer. E cada linha de código é um passo em direção ao seu objetivo.

O Spring Boot pode parecer complexo no início, mas com persistência e prática, você consegue dominar. Eu consegui, e você também consegue!

---

## 👨‍💻 **Sobre Mim**

**Andreyrsy** - Desenvolvedor Java em formação

- 💼 **LinkedIn:** [andreyrsy](https://linkedin.com/in/andreyrsy)
- 🐙 **GitHub:** [andreyrsy](https://github.com/andreyrsy)
- 📧 **Email:** [seu-email@exemplo.com]

*"Cada linha de código é um passo em direção ao meu sonho de trabalhar com tecnologia."*

---

## 📄 **Licença**

Este projeto está sob a licença MIT. Sinta-se livre para usar, modificar e distribuir!

---

*Desenvolvido com ❤️, muito ☕ e algumas 😅 durante o processo de aprendizado*