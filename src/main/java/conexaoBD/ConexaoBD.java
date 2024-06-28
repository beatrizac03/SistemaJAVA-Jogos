package conexaoBD;

import java.sql.*;
import java.util.*;

import classesObjetos.*;

import javax.swing.*;

public class ConexaoBD {
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

    public static List<Jogo> getJogosCadastrados() {
        List<Jogo> jogos = new ArrayList<>();
        String selectQuery = "SELECT id_jogo, titulo_jogo, genero_jogo, preco_jogo, descricao_jogo, imagem_jogo FROM jogos";

        try (Connection conn = getConnection();
             PreparedStatement stm = conn.prepareStatement(selectQuery);
             ResultSet rs = stm.executeQuery()) {

            while(rs.next()) {
                int idJogo = rs.getInt("id_jogo");
                String titulo = rs.getString("titulo_jogo");
                String genero = rs.getString("genero_jogo");
                double preco = rs.getDouble("preco_jogo");
                String descricao = rs.getString("descricao_jogo");
                byte[] imagem = rs.getBytes("imagem_jogo");
//                Blob blobimg = (Blob) rs.getBlob("imagem_jogo");
//                byte[] img = blobimg.getBytes(1, (int) blobimg.length());  // converte os dados binários para arquivo de imagem
////                BufferedImage imagem = null;
                try {
//                    imagem = ImageIO.read(new ByteArrayInputStream(img));
                } catch(Exception e) {
                    System.out.println(e);
                }

                Jogo jogo = new Jogo(idJogo,titulo, genero, preco, descricao, imagem);
                jogos.add(jogo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        System.out.println("Jogos recuperados do banco de dados: " + jogos.size());
//        for (Jogo jogo : jogos) {
//            System.out.println("ID: " + jogo.getIdJogo() + ", Título: " + jogo.getTitulo() + ", Genero: " + jogo.getGenero() +
//                    ", Preço: " + jogo.getPreco() + ", Descrição: " + jogo.getDescricao());
//        }

        return jogos;
    }

    public static List<Jogo> getJogosFavoritos() {
        List<Jogo> jogosFavoritos = new ArrayList<>();
        String query = "SELECT j.id_jogo, j.titulo_jogo, j.genero_jogo, j.preco_jogo, j.descricao_jogo, j.imagem_jogo " +
                "FROM jogos_favoritos jf " +
                "JOIN jogos j ON jf.id_jogo = j.id_jogo " +
                "WHERE jf.id_usuario = ?";

        try (Connection conn = getConnection();
             PreparedStatement stm = conn.prepareStatement(query)) {

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int idJogo = rs.getInt("id_jogo");
                    String titulo = rs.getString("titulo_jogo");
                    String genero = rs.getString("genero_jogo");
                    double preco = rs.getDouble("preco_jogo");
                    String descricao = rs.getString("descricao_jogo");
                    byte[] imagem = rs.getBytes("imagem_jogo");

                    Jogo jogo = new Jogo(idJogo, titulo, genero, preco, descricao, imagem);
                    jogosFavoritos.add(jogo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jogosFavoritos;
    }

    public static List<Usuario> autenticarUsuario() {
        List<Usuario> usuarios = new ArrayList<>();
        String usuariosQuery = "SELECT nome_usuario, senha_usuario FROM usuarios";

        try(Connection conn = getConnection();
            PreparedStatement stm = conn.prepareStatement(usuariosQuery);
            ResultSet rs = stm.executeQuery()){

            while(rs.next()) {
                String nomeUsuario = rs.getString("nome_usuario");
                String senhaUsuario = rs.getString("senha_usuario");


                Usuario usuario = new Usuario(nomeUsuario, senhaUsuario);
                usuarios.add(usuario);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public static void cadastrarUsuario(Usuario user) {
        String insertUser = "INSERT INTO usuario(nome_usuario, senha_usuario)" +
                " VALUES (?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(insertUser)) {
            stm.setString(3, user.getNomeUsuario());
            stm.setString(4, user.getSenhaUsuario());

            stm.executeQuery();

        } catch (SQLException e) {
            e.getStackTrace();
            e.getMessage();
        }
    }
     public static void main(String[] args) {
        getConnection();
    }
}
