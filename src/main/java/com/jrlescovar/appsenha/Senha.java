package com.jrlescovar.appsenha;

public class Senha {
    private String servico;
    private String usuario;
    private String senha;
    private String usuarioDono;

    public Senha() {
    }

    public Senha(String servico, String usuario, String senha, String usuarioDono) {
        this.servico = servico;
        this.usuario = usuario;
        this.senha = senha;
        this.usuarioDono = usuarioDono;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuarioDono() {
        return usuarioDono;
    }

    public void setUsuarioDono(String usuarioDono) {
        this.usuarioDono = usuarioDono;
    }

}
