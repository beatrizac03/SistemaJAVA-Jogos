package classesObjetos;

public class Usuario {
    private int idUsuario;
    private String tipoUsuario;
    private String nomeUsuario;
    private String senhaUsuario;
    private byte[] imagemUsuario;

    public Usuario(int idUsuario, String tipoUsuario, String nomeUsuario, String senhaUsuario, byte[] imagem) {
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
        this.imagemUsuario = imagem;
    }

    public Usuario(String nome, String senha) {
        this.nomeUsuario = nome;
        this.senhaUsuario = senha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public byte[] getImagemUsuario() {
        return imagemUsuario;
    }

    public void setImagemUsuario(byte[] img) {
        this.imagemUsuario = img;
    }

}
