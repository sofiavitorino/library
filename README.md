# 📚 Sistema de Biblioteca

Um sistema de biblioteca simples desenvolvido em Java, com funcionalidades para gerenciamento de livros, cadastro de usuários, empréstimos e devoluções, com cálculos de multas para livros devolvidos com atraso.

**Tecnologias Usadas**
- **Java 17+**

 **Funcionalidades**
- **Cadastro de livros**: Adiciona livros ao catálogo da biblioteca com informações como título, autor, ISBN e quantidade disponível.
- **Cadastro de usuários**: Registra novos usuários da biblioteca com dados como nome, CPF, e-mail e telefone.
- **Empréstimo de livros**: Permite que um usuário faça um empréstimo de até 5 livros de uma vez.
- **Devolução de livros**: Permite que os usuários devolvam livros e calcula a multa caso o livro seja devolvido após o prazo de devolução.
- **Consulta de empréstimos ativos**: Exibe todos os livros emprestados para um usuário, mostrando as datas de início e devolução.
- **Listagem de livros e usuários cadastrados**: Exibe o catálogo de livros disponíveis e a lista de usuários cadastrados no sistema.

**Interface**  
O sistema é baseado em terminal, onde o usuário interage por meio de menus numéricos para escolher a ação desejada.

### Exemplo de menu:
```
------------ Biblioteca ------------
Escolha a opção desejada:
[1] Registrar livro
[2] Emprestar livro
[3] Devolver livro
[4] Checar catálogo de livros
[5] Cadastrar usuário
[6] Conferir empréstimos ativos
[7] Listar usuários cadastrados
[0] Sair
```

**Como Executar**

1. Clone o repositório:
   ```bash
   git clone https://github.com/sofiavitorino/library.git
   ```

2. Navegue até a pasta do projeto:
   ```bash
   cd library
   ```

3. Compile os arquivos Java dentro da pasta `src`:
   ```bash
   javac src/*.java
   ```

4. Execute a aplicação:
   ```bash
   java -cp src Main
   ```

**Contribuições & Sugestões**  
Contribuições são bem-vindas! 
Sinta-se à vontade para abrir uma issue com sugestões de melhoria, correções ou novas funcionalidades que possam tornar o projeto mais completo.

💡 **Melhorias Futuras**
- Adicionar integração com banco de dados MySQL para persistência de dados.
- Adicionar interface gráfica.
- Expandir a funcionalidade de cálculo de multas, incluindo descontos ou isenções.
