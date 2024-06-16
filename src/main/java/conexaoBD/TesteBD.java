package conexaoBD;

import java.sql.*;

import classesObjetos.Jogo;

public class TesteBD {
    private static String URL = "jdbc:sqlite:src/main/resources/testando.bd";
    private static String usuario = "root";
    private static String senha = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void cadastrarJogo(Jogo jogo) {
        String insertJogo = "INSERT INTO jogos(id_jogo, titulo_jogo, genero_jogo, preco_jogo, descricao_jogo, imagem_jogo)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(insertJogo)) {
            stm.setInt(1, jogo.getIdJogo());
            stm.setString(2, jogo.getTitulo());
            stm.setString(3, jogo.getGenero());
            stm.setDouble(4, jogo.getPreco());
            stm.setString(5, jogo.getDescricao());
            stm.setString(6, jogo.getImagem());
            stm.executeUpdate();
            System.out.println("Jogo " + jogo.getTitulo() + " adicionado ao banco de dados.");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public static void main(String[] args) {
        getConnection();
    }
}
