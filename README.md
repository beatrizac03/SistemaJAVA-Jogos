# GameHUB - Plataforma de Jogos em Java
Projeto desenvolvido em Java, utilizando a biblioteca Swing para a interface gráfica. O sistema permite que o administrador cadastre e gerencie jogos, enquanto o cliente pode visualizar o catálogo e efetuar compras.



## Diagrama de Classe
<div >
<img width="550px" height="400px" src="https://github.com/beatrizac03/Trabalho-Final-de-LP-POO/blob/main/arquivosREADME/diagramaClasse.jpeg" >
</div>

## Ficha Técnica

- **Build System**: Maven
- **JDK**: Amazon Corretto 17.0.11
- **Banco de Dados**: JDBC SQLite
- **Gerenciador de BD**: DBeaver

## Funcionalidades

### Painel Admin

- **Cadastrar Jogo**: Permite cadastrar jogos com ID, Título, Gênero, Descrição e Imagem.
- **Gerenciar Jogos**: Possibilita excluir e visualizar jogos cadastrados em uma tabela.
- **Logout**: Permite sair do painel administrativo.

### Painel Cliente

- **Visualizar Catálogo**: Exibe todos os jogos cadastrados.
- **Adicionar aos Favoritos**: Permite adicionar jogos aos favoritos.
- **Efetuar Compra**: Permite escolher o método de pagamento e realizar a compra de jogos.

## Estrutura do Código

O sistema foi dividido em pacotes para modularizar e organizar o código:

- **`classesObjeto`**: Contém classes que representam os objetos do sistema.
- **`conexaoBD`**: Contém a classe de conexão e métodos (queries) para interagir com o banco de dados.
- **`PainelAdmin`**: Contém a tela do administrador e seus componentes separados.
- **`PainelCliente`**: Contém a tela do cliente e seus componentes.
- **`TelaLogin`**: Contém a tela de login do sistema.
