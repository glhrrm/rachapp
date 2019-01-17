package rachapp;

public class Administrador extends Usuario {
    
    private String login;
    private String senha;

    public Administrador(String nome, Evento evento, String login, String senha) {
        super(nome, evento);
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
