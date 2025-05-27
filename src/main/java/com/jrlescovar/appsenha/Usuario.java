package com.jrlescovar.appsenha;

public class Usuario {
    private String nome;
    private String senhaCriptografada;

    public Usuario() {}

    public Usuario(String nome, String senhaCriptografada) {
        this.nome = nome;
        this.senhaCriptografada = senhaCriptografada;
    }

    public String getNome() {
        return nome;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenhaCriptografada(String senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }
}
