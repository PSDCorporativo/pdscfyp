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

    private int id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private int idNacional;
    private String descricao;
    private String profissao;
    private float logintude;
    private float latitude;
    private int pontuacao;

    public Profissional(String nome, String email, String login, String senha,
            int idNacional, String descricao, String profissao, float latitude,
            float logintude, int pontuacao) {
        this.senha = senha;
        this.profissao = profissao;
        this.email = email;
        this.idNacional = idNacional;
        this.profissao = profissao;
        this.login = login;
        this.nome = nome;
        this.latitude = latitude;
        this.logintude = logintude;
        this.pontuacao = pontuacao;
    }

    @Override
    public int compareTo(Profissional p) {
        if (this.pontuacao > p.pontuacao) {
            return -1;
        } else {
            return 1;
        }
    }

//    public static Set<Profissional> getProfissionais() {
//        Client c = Client.create();
//        WebResource wr = c.resource("http://apifindyourpro-pdsfyp.rhcloud.com/API_FindYourPro/webresources/ws/Profissionais/list");
//        String json = wr.get(String.class);
//
//        Gson gson = new Gson();
//        return gson.fromJson(json, new TypeToken<Set<Profissional>>() {
//        }.getType());
//    }
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
