package com.jrlescovar.appsenha;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    private static final File arquivo = new File("usuarios.json");
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Usuario> carregarUsuarios() {
        try {
            if (!arquivo.exists()) return new ArrayList<>();
            return mapper.readValue(arquivo, new TypeReference<List<Usuario>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void salvarUsuarios(List<Usuario> usuarios) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, usuarios);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void adicionarUsuario(Usuario novo) {
        List<Usuario> lista = carregarUsuarios();
        lista.add(novo);
        salvarUsuarios(lista);
    }

    public static boolean usuarioExiste(String nome) {
        return carregarUsuarios().stream()
                .anyMatch(u -> u.getNome().equalsIgnoreCase(nome));
    }

    public static Usuario buscarUsuarioPorNome(String nome) {
        List<Usuario> usuarios = carregarUsuarios(); // Lê os usuários do JSON

        String nomeProcurado = nome.trim(); // Remove espaços antes/depois

        for (Usuario u : usuarios) {
            System.out.println("Comparando: '" + u.getNome() + "' com '" + nomeProcurado + "'");
            if (u.getNome().equalsIgnoreCase(nomeProcurado)) {
                return u;
            }
        }
        return null; // Se não encontrou
    }
}
