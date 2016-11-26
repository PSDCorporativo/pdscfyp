/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.pdscfyp.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author mateus
 */
@Getter
@Setter
public class Profissional implements Comparable<Profissional> {

    private Long id;
    private Usuario usuario;
    private String descricao;
    private String profissao;
    private int pontuacao;

    public Profissional() {
    }

    public Profissional(String nome, String email, String login, String senha,
            boolean isProfissional, String rua, String cep, String numero,
            String complemento, String cidade, String estado,
            String descricao, String profissao) {

        this.usuario = new Usuario(nome, email, login, senha, isProfissional,
                rua, cep, numero, complemento, cidade, estado);
        this.profissao = profissao;
        this.descricao = descricao;
        this.pontuacao = 0;
    }

    @Override
    public int compareTo(Profissional p) {
        return 1;
    }
    public static Profissional getProfissionalByLogin(String login) {
        Client c = Client.create();
        WebResource wr = c.resource("https://apifyp.herokuapp.com/GetProfissionalByLogin?login=" + login);
        String json = wr.get(String.class);

        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<Profissional>() {
        }.getType());
    }

    public static Set<Profissional> getProfissionais() {
        Client c = Client.create();
        WebResource wr = c.resource("https://apifyp.herokuapp.com/ListProfissionais");
        String json = wr.get(String.class);

        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<Set<Profissional>>() {
        }.getType());
    }

    public static void addProfissional(Profissional p) {
        Client c = Client.create();

        Gson gson = new Gson();

        WebResource wr = c.resource("https://apifyp.herokuapp.com/AdicionarProfissional");

        ClientResponse cr = wr.accept("application/json").
                type("application/json").post(ClientResponse.class, gson.toJson(p));
    }

    public static void addProfissionalSecreto(Profissional p) {
        Client c = Client.create();

        Gson gson = new Gson();

        WebResource wr = c.resource("https://apifyp.herokuapp.com/AdicionarProfissional");

        ClientResponse cr = wr.accept("application/json").
                type("application/json").post(ClientResponse.class, gson.toJson(p));
    }
}
