package conexaoBD;

import java.sql.*;
import java.util.*;

import classesObjetos.Jogo;

import javax.swing.*;

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
            stm.setBytes(6, jogo.getImagem());

            stm.executeUpdate();
            System.out.println("Jogo " + jogo.getTitulo() + " adicionado ao banco de dados.");
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Erro de conexão ao BD", "ERRO SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    // método para obter jogos do BD
    public static List<Jogo> getJogosCadastrados() {
        List<Jogo> jogos = new ArrayList<>();
        String selectQuery = "SELECT genero_jogo, titulo_jogo FROM jogos";

        try (Connection conn = getConnection();
             PreparedStatement stm = conn.prepareStatement(selectQuery);
             ResultSet rs = stm.executeQuery()) {

            while(rs.next()) {
                String titulo = rs.getString("titulo_jogo");
                String genero = rs.getString("genero_jogo");
                Jogo jogo = new Jogo(genero, titulo); // Supondo que o construtor de Jogo aceite genero e titulo
                jogos.add(jogo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Jogos recuperados do banco de dados: " + jogos.size());
        for (Jogo jogo : jogos) {
            System.out.println("Gen: " + jogo.getGenero() + ", Título: " + jogo.getTitulo());
        }

        return jogos;
    }
    public static void main(String[] args) {
        getConnection();
    }
}
