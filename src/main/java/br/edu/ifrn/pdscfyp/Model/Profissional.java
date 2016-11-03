/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.pdscfyp.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
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
public class Profissional {

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

    public Profissional(String nome, String email, String login, String senha,
            int idNacional, String descricao, String profissao, float latitude,
            float logintude) {
        this.senha = senha;
        this.descricao = descricao;
        this.email = email;
        this.idNacional = idNacional;
        this.profissao = profissao;
        this.login = login;
        this.nome = nome;
        this.latitude = latitude;
        this.logintude = logintude;
    }

    public static Set<Profissional> getProfissionais() {
        Client c = Client.create();
        WebResource wr = c.resource("http://apifindyourpro-pdsfyp.rhcloud.com/API_FindYourPro/webresources/ws/Profissionais/list");
        String json = wr.get(String.class);

        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<Set<Profissional>>() {
        }.getType());
    }
}
