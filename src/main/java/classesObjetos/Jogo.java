package classesObjetos;
public class Jogo {
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
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

    private String titulo, genero, descricao, imagem;
    private double preco;
    private int idJogo;

    public Jogo(int idJogo, String titulo, String genero, double preco, String descricao, String imagem) {
        this.titulo = titulo;
        this.genero = genero;
        this.descricao = descricao;
        this.imagem = imagem;
        this.preco = preco;
        this.idJogo = idJogo;
    }
}
