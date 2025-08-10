# LiterAlura

[](https://www.google.com/search?q=)
[](https://www.google.com/search?q=)
[](https://www.google.com/search?q=)

-----

### Descrição do Projeto

**LiterAlura** é uma aplicação de linha de comando desenvolvida como parte do programa **ONE - Oracle Next Education**. O objetivo principal é interagir com a API [Gutendex](https://gutendex.com/) para buscar e gerenciar informações sobre livros e autores, persistindo os dados em um banco de dados relacional.

A aplicação permite ao usuário realizar as seguintes operações:

* **Buscar livros** por título.
* **Listar todos os livros** registrados no banco de dados.
* **Listar todos os autores** registrados.
* **Listar autores vivos** em um determinado ano.
* **Listar livros por idioma**.

-----

### Tecnologias Utilizadas

* **Java 17**: Linguagem de programação.
* **Spring Boot**: Framework para o desenvolvimento da aplicação.
* **Spring Data JPA**: Facilita a interação com o banco de dados.
* **Hibernate**: Implementação da especificação JPA.
* **PostgreSQL**: Banco de dados relacional utilizado para persistência.
* **Maven**: Gerenciador de dependências e build.
* **API Gutendex**: Fonte de dados para a busca de livros.

-----

### Estrutura do Projeto

* **`model`**: Contém as classes de entidades (`Livro` e `Autor`) e DTOs para mapeamento da API externa.
* **`repository`**: Interfaces que estendem `JpaRepository` para operações de persistência.
* **`service`**: Camada de serviço que contém a lógica de negócio, como buscar livros na API e salvá-los no banco.
* **`main`**: Classe principal que contém o menu de interação e coordena as chamadas aos serviços.
* **`LiterAluraApplication`**: Classe principal da aplicação Spring Boot.

-----

### Como Rodar o Projeto

1.  **Clone o repositório:**

    ```bash
    git clone https://github.com/GabryelJ/LiterAlura.git
    cd LiterAlura
    ```

2.  **Configuração do Banco de Dados com Variáveis de Ambiente:**

    * Certifique-se de ter o **PostgreSQL** instalado e em execução.
    * Crie um banco de dados chamado `literalura`.
    * Configure as credenciais do seu banco de dados usando **variáveis de ambiente**. Isso é uma prática de segurança recomendada para não expor dados sensíveis no código. No arquivo `application.properties`, adicione as seguintes linhas:
      ```properties
      spring.datasource.url=${DB_URL}
      spring.datasource.username=${DB_USERNAME}
      spring.datasource.password=${DB_PASSWORD}
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=true
      spring.jpa.properties.hibernate.format_sql=true
      ```
    * Antes de executar a aplicação, defina as variáveis de ambiente com as credenciais do seu banco de dados.

    **No Linux/macOS:**

    ```bash
    export DB_URL=jdbc:postgresql://localhost:5432/literalura
    export DB_USERNAME=seu_usuario
    export DB_PASSWORD=sua_senha
    ```

    **No Windows (prompt de comando):**

    ```bash
    set DB_URL=jdbc:postgresql://localhost:5432/literalura
    set DB_USERNAME=seu_usuario
    set DB_PASSWORD=sua_senha
    ```

3.  **Executar a Aplicação:**

    * Você pode rodar a aplicação através da sua IDE ou usando o Maven:

    <!-- end list -->

    ```bash
    mvn spring-boot:run
    ```

    Ao iniciar, um menu será exibido no console, permitindo a interação com o sistema.
-----

### Autor

* **GabryelJ**
