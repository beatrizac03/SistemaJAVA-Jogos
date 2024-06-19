package classesObjetos;

import java.util.Arrays;

public class Jogo {
    private String titulo, genero, descricao;
    private double preco;
    private int idJogo;
    private byte[] imagem;

    public Jogo(int idJogo, String titulo, String genero, double preco, String descricao, byte[] imagem) {
        this.titulo = titulo;
        this.genero = genero;
        this.descricao = descricao;
        this.imagem = imagem;
        this.preco = preco;
        this.idJogo = idJogo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public Jogo(String genero, String titulo) {
        this.genero = genero;
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", idJogo=" + idJogo + "}";
    }
}
