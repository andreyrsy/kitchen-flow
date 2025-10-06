# Kitchen Flow API 🍳

![Status](https://img.shields.io/badge/status-ativo-success.svg)
![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

## 🎯 O Problema

O desperdício de alimentos é um problema sério em restaurantes, resultando em:
- Perdas financeiras significativas
- Impacto ambiental negativo
- Gestão ineficiente de recursos

**Kitchen Flow** é uma solução tecnológica para monitorar proativamente datas de validade e gerenciar o estoque de forma inteligente, ajudando restaurantes a:
- Reduzir o desperdício de alimentos
- Economizar recursos financeiros
- Melhorar a eficiência operacional
- Tomar decisões baseadas em dados

## ✨ Funcionalidades Principais

### 🏷️ Gestão Completa de Categorias, Produtos e Lotes
- Cadastro, listagem e exclusão de categorias de alimentos
- CRUD completo de produtos com associação a categorias
- Controle detalhado de lotes com datas de entrada e validade

### 🔍 Sistema Inteligente de Status de Validade
- Monitoramento automático de datas de validade
- Alertas por status: `NORMAL`, `ATENCAO`, `URGENTE`, `VENCIDO`
- Priorização inteligente de consumo baseada na validade

### 📦 Controle de Estoque Eficiente
- Registro de entrada de novos lotes
- Consumo inteligente priorizando lotes mais próximos ao vencimento
- Rastreabilidade completa do fluxo de produtos

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 21**: Linguagem de programação moderna e robusta
- **Spring Boot 3.5.3**: Framework para desenvolvimento rápido e eficiente
- **Spring Data JPA**: Simplificação do acesso a dados
- **PostgreSQL 15**: Banco de dados relacional confiável
- **Flyway**: Controle de versão para banco de dados
- **Lombok**: Redução de código boilerplate
- **Bean Validation**: Validação declarativa de dados

### Ferramentas de Desenvolvimento
- **Maven**: Gerenciamento de dependências e build
- **Spring DevTools**: Produtividade no desenvolvimento
- **Postman**: Testes de API

## 🚀 Como Executar o Projeto

### Pré-requisitos
- Java 21+
- Maven 3.x+
- PostgreSQL 15+
- IDE (recomendado: IntelliJ IDEA)

### Passo a Passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/andreyrsy/kitchen-flow.git
   cd kitchen-flow
   ```

2. **Configure o banco de dados**
   - Crie um banco PostgreSQL chamado `db_kitchen`
   - Atualize as credenciais em `application.properties` se necessário

3. **Execute a aplicação**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Acesse a API**
   - A API estará disponível em `http://localhost:8080/api/v1/`
   - Use Postman ou Insomnia para testar os endpoints

## 📖 Documentação da API

### 🏷️ Categorias
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/v1/categoria` | Criar categoria |
| `GET` | `/api/v1/categoria` | Listar categorias |
| `DELETE` | `/api/v1/categoria/delete/{id}` | Deletar categoria |

### 📦 Produtos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/v1/produto` | Listar produtos |
| `POST` | `/api/v1/produto` | Criar produto |
| `DELETE` | `/api/v1/produto/deletar/{id}` | Deletar produto |

### 📋 Lotes
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/v1/lotes` | Listar lotes |
| `POST` | `/api/v1/lotes` | Criar lote |
| `PUT` | `/api/v1/lotes/{id}/consumir/{qtd}` | Consumir produto |
| `DELETE` | `/api/v1/lotes/deletar/{id}` | Deletar lote |

## 💡 Exemplos de Uso

### Criar um Produto
```bash
POST /api/v1/produto
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
PUT /api/v1/lotes/1/consumir/10
```

## 🔮 Roadmap do Projeto

### Próximas Implementações
- [ ] Documentação interativa com Swagger/OpenAPI
- [ ] Testes unitários e de integração
- [ ] Autenticação e autorização com Spring Security
- [ ] Cache para consultas frequentes
- [ ] Paginação nas listagens
- [ ] Sistema de notificações para produtos próximos ao vencimento
- [ ] Relatórios e estatísticas de consumo
- [ ] Containerização com Docker
- [ ] CI/CD com GitHub Actions
- [ ] Frontend para consumir a API

## 👨‍💻 

**Andreyrsy**

- 💼 **LinkedIn:** [andreyrsy](https://linkedin.com/in/andreyrsy)
- 🐙 **GitHub:** [andreyrsy](https://github.com/andreyrsy)
- 📧 **Email:** [andreyrsy@gmail.com]

## 📄 Licença

Este projeto está sob a licença MIT. Sinta-se livre para usar, modificar e contribuir!