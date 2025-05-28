package com.jrlescovar.appsenha;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {
    private static final String CAMINHO_SENHAS = "senhas.json";

    private static final String CAMINHO = "senhas.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Senha> carregarSenhas() {
        ObjectMapper mapper = new ObjectMapper();
        File arquivo = new File("senhas.json");

        try {
            if (arquivo.length() == 0) {
                return new ArrayList<>();
            }
            return mapper.readValue(arquivo, new TypeReference<List<Senha>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void salvarSenha(Senha novaSenha) {
        ObjectMapper mapper = new ObjectMapper();
        List<Senha> lista = carregarSenhas(); // carrega as já existentes
        lista.add(novaSenha); // adiciona a nova

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(CAMINHO_SENHAS), lista);
            System.out.println("Nova senha adicionada com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
