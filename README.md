# 🛒 SalesApp — CBTSWE1

Aplicação web desenvolvida com **Java EE (Servlets)** e **Maven** para cadastro de clientes, vendedores e ordens de venda.

> **Disciplina:** CBTSWE1 – ADS 571  
> **Professor:** Wellington Tuler Moraes  
> **Instituto:** IFSP Campus Cubatão  
> **Dupla:** Adriano Júnior de Souza Almeida · Arthur Lanzilotti Farjanes

---

## 📋 Funcionalidades

- ✅ Cadastro, edição e exclusão de **Vendedores** (SalesMan)
- ✅ Cadastro, edição e exclusão de **Clientes** (Customer)
- ✅ Cadastro, edição e exclusão de **Ordens de Venda** (Orders)
- ✅ Listagem de todos os registros em tabela
- ✅ Relacionamento entre entidades (Customer → SalesMan, Orders → Customer + SalesMan)

---

## 🏗️ Estrutura do Projeto

```
SalesApp/
├── src/
│   └── main/
│       ├── java/
│       │   ├── model/
│       │   │   ├── SalesMan.java
│       │   │   ├── Customer.java
│       │   │   └── Orders.java
│       │   ├── dao/
│       │   │   ├── SalesManDAO.java
│       │   │   ├── CustomerDAO.java
│       │   │   └── OrdersDAO.java
│       │   ├── servlet/
│       │   │   ├── SalesManServlet.java
│       │   │   ├── CustomerServlet.java
│       │   │   └── OrdersServlet.java
│       │   └── util/
│       │       ├── ConnectionFactory.java
│       │       └── DatabaseInit.java
│       └── webapp/
│           ├── WEB-INF/
│           ├── views/
│           │   ├── salesman/
│           │   │   ├── list.jsp
│           │   │   └── form.jsp
│           │   ├── customer/
│           │   │   ├── list.jsp
│           │   │   └── form.jsp
│           │   └── orders/
│           │       ├── list.jsp
│           │       └── form.jsp
│           └── index.jsp
├── pom.xml
└── README.md
```

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|---|---|---|
| Java | 11 | Linguagem principal |
| Jakarta Servlet API | 6.0.0 | Camada de controle (MVC) |
| JSP + JSTL | 3.1 / 3.0 | Camada de visão |
| Maven | 3.x | Gerenciamento de dependências e build |
| MySQL | 8.x | Banco de dados |
| Apache Tomcat | 09.x | Servidor de aplicação |
| Eclipse IDE | Ambiente de desenvolvimento |

---

## ⚙️ Como Executar

### Pré-requisitos

- Java JDK 11 ou superior instalado
- Apache Tomcat 09 configurado no Eclipse
- MySQL rodando localmente
- Maven instalado (ou usar o Maven embutido do Eclipse)

### Passo a passo

**1. Clone o repositório**
```bash
git clone https://github.com/adriano-aj/SalesApp.git
cd SalesApp
```

**2. Crie o banco de dados**
```sql
CREATE DATABASE salesapp;
USE salesapp;
-- Execute os scripts SQL da seção "Modelo de Banco de Dados" acima
```

**3. Configure a conexão**

Edite o arquivo `src/main/java/util/ConnectionFactory.java` com suas credenciais:
```java
private static final String URL  = "jdbc:mysql://localhost:3306/salesapp";
private static final String USER = "seu_usuario";
private static final String PASS = "sua_senha";
```

**4. Compile o projeto**
```bash
mvn clean package
```

**5. Implante no Tomcat**

- No Eclipse: clique com o botão direito no projeto → *Run As* → *Run on Server*
- Ou copie o `.war` gerado em `target/SalesApp.war` para a pasta `webapps/` do Tomcat

**6. Acesse no navegador**
```
http://localhost:8080/SalesApp
```

---

## 🗂️ Arquitetura — Camadas

```
[ JSP (View) ]
      ↕
[ Servlet (Controller) ]   ←  recebe HttpRequest, chama DAO, encaminha para JSP
      ↕
[ DAO (Data Access Object) ]  ←  executa SQL via JDBC
      ↕
[ Model (POJO) ]              ←  representa as entidades do banco
      ↕
[ ConnectionFactory ]         ←  fornece a Connection JDBC
      ↕
[ MySQL Database ]
```

---

## 🔗 Endpoints da Aplicação

| Método | URL | Ação |
|---|---|---|
| GET | `/salesman` | Lista todos os vendedores |
| POST | `/salesman?action=save` | Cadastra ou edita vendedor |
| POST | `/salesman?action=delete` | Remove vendedor |
| GET | `/customer` | Lista todos os clientes |
| POST | `/customer?action=save` | Cadastra ou edita cliente |
| POST | `/customer?action=delete` | Remove cliente |
| GET | `/orders` | Lista todas as ordens |
| POST | `/orders?action=save` | Cadastra ou edita ordem |
| POST | `/orders?action=delete` | Remove ordem |

---

## 📄 Licença

Projeto acadêmico desenvolvido para a disciplina CBTSWE1 — IFSP Campus Cubatão.
