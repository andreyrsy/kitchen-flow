# 🍳 Kitchen Flow

![Status](https://img.shields.io/badge/status-ativo-success.svg)
![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)
![H2 Database](https://img.shields.io/badge/H2-Database-blue.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)
# Kitchen Flow - Sistema de Gestão de Estoque

Kitchen Flow é um sistema robusto para gerenciamento inteligente de estoque em restaurantes, desenvolvido com Spring Boot, focado na redução de desperdício através do controle preciso de validade e lotes de produtos.

## 🚀 Funcionalidades

### Core Features
- ✅ **Gestão de Produtos**: Cadastro completo com unidades de medida e categorização
- ✅ **Controle de Lotes**: Rastreabilidade total de entradas com data de validade
- ✅ **Prevenção de Perdas**: Monitoramento de itens próximos ao vencimento
- ✅ **Consumo Inteligente**: Baixa de estoque otimizada por lotes (FIFO/FEFO)
- ✅ **Categorização**: Organização flexível de produtos
- ✅ **API REST**: Endpoints padronizados e documentados

### Recursos Técnicos
- 🔄 **API RESTful**: Design de API seguindo melhores práticas (Richardson Maturity Model)
- 📚 **Documentação Viva**: Swagger UI/OpenAPI para exploração interativa
- 🗄️ **Persistência**: JPA/Hibernate com suporte a H2 (dev) e PostgreSQL (prod)
- 🔄 **Migração de Dados**: Flyway para versionamento do esquema de banco de dados
- �️ **Validação**: Bean Validation para integridade dos dados
- 🏗️ **Arquitetura em Camadas**: Separação clara de responsabilidades (Controller, Service, Repository)

## 🏗️ Arquitetura

### Estrutura do Projeto
```
src/main/java/dev/andreyrsy/kitchen/flow/
├── config/          # Configurações (Swagger, etc)
├── controller/      # Controllers REST
├── dto/             # Data Transfer Objects (Request/Response)
├── exception/       # Tratamento global de exceções
├── mapper/          # Mappers para conversão Entity <-> DTO
├── model/           # Entidades JPA
├── repository/      # Repositórios JPA
├── service/         # Lógica de negócio
└── KitchenFlowApplication.java
```

### Fluxo de Operação
1. **Cadastro**: Definição de Categorias e Produtos
2. **Entrada**: Registro de Lotes com quantidade e validade
3. **Estoque**: Produtos ficam disponíveis no inventário
4. **Consumo**: Baixa de itens (sistema prioriza lotes com vencimento mais próximo)
5. **Monitoramento**: Acompanhamento de validades e níveis de estoque

## 🛠️ Tecnologias

- **Java 21** - Linguagem principal
- **Spring Boot 3.5.3** - Framework base
- **Spring Data JPA** - Persistência
- **Spring Web** - API REST
- **Flyway** - Migração de Banco de Dados
- **SpringDoc OpenAPI** - Documentação (Swagger)
- **H2 Database** - Banco em memória (Dev)
- **PostgreSQL** - Banco de dados (Prod)
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciamento de dependências

## 🚦 Endpoints

### Categorias
```http
# Criar categoria
POST /api/v1/categorias
Content-Type: application/json

# Listar categorias
GET /api/v1/categorias
```

### Produtos
```http
# Criar produto
POST /api/v1/produtos
Content-Type: application/json

# Listar produtos
GET /api/v1/produtos
```

### Estoque e Lotes
```http
# Registrar entrada de lote
POST /api/v1/lotes
Content-Type: application/json

# Consumir item do estoque
POST /api/v1/lotes/{id}/consumos
Content-Type: application/json
```

## 📋 Configuração

### Variáveis de Ambiente (Opcional para Prod)
```bash
# Banco de Dados (Profile: postgres)
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/db_kitchen
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=root
```

### Profiles
- **default**: Desenvolvimento com H2 em memória (Zero Config)
- **postgres**: Produção com PostgreSQL e persistência em disco

## � Execução

### Desenvolvimento (H2)
```bash
# Clonar repositório
git clone https://github.com/andreyrsy/kitchen-flow.git
cd kitchen-flow

# Executar com Maven (Profile default)
./mvnw spring-boot:run
```

### Produção (PostgreSQL)
```bash
# 1. Subir banco de dados
docker run --name db_kitchen -e POSTGRES_PASSWORD=root -p 5432:5432 -d postgres

# 2. Executar aplicação
./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres
```

## � Monitoramento e Docs

### Swagger UI (Documentação)
```
URL: http://localhost:8080/swagger-ui.html
```

### Console H2 (Apenas Profile Default)
```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:db_kitchen
User: sa
Password: (vazio)
```

## 🧪 Testes

```bash
# Executar todos os testes
./mvnw test
```

## � Exemplo de Uso

### 1. Criar Categoria
```bash
curl -X POST http://localhost:8080/api/v1/categorias \
  -H "Content-Type: application/json" \
  -d '{"nome": "Hortifruti"}'
```

### 2. Criar Produto
```bash
curl -X POST http://localhost:8080/api/v1/produtos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Tomate Italiano",
    "unidadeMedida": "kg",
    "categoriaId": 1
  }'
```

### 3. Registrar Lote
```bash
curl -X POST http://localhost:8080/api/v1/lotes \
  -H "Content-Type: application/json" \
  -d '{
    "quantidade": 100,
    "dataValidade": "2024-12-31",
    "dataEntrada": "2024-11-24",
    "produtoId": 1
  }'
```

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## � Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 📞 Suporte

Para suporte e dúvidas:
- 📧 Email: andreyrsy@gmail.com
- 📱 GitHub Issues: [Criar Issue](https://github.com/andreyrsy/kitchen-flow/issues)
