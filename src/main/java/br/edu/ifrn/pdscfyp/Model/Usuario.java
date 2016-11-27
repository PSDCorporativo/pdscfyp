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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author mateus
 */
@Getter
@Setter
public class Usuario implements Comparable<Usuario> {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private boolean isProfissional;
    private Endereco endereco;

    public Usuario() {
    }

    public Usuario(String nome, String email, String login, String senha,
            boolean isProfissional, String rua, String cep, String numero,
            String complemento, String cidade, String estado) {
        this.senha = senha;
        this.email = email;
        this.login = login;
        this.nome = nome;
        this.endereco = new Endereco(rua, cep, numero, complemento, cidade, estado, 0, 0);
    }

    public static Usuario getProfissionalByLogin(String login) {
        Client c = Client.create();
        WebResource wr = c.resource("https://apifyp.herokuapp.com/GetProfissionalByLogin?login=" + login);
        String json = wr.get(String.class);

        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<Usuario>() {
        }.getType());
    }

    public static Set<Usuario> getProfissionais() {
        Client c = Client.create();
        WebResource wr = c.resource("https://apifyp.herokuapp.com/ListProfissionais");
        String json = wr.get(String.class);

        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<Set<Usuario>>() {
        }.getType());
    }

    public static void addUsuario(Usuario u) {
        Client c = Client.create();

        Gson gson = new Gson();

        WebResource wr = c.resource("https://apifyp.herokuapp.com/AdicionarUsuario");

        ClientResponse cr = wr.accept("application/json").
                type("application/json").post(ClientResponse.class, gson.toJson(u));
    }

    public static Usuario login(String login, String senha) {
        
        Client c = Client.create();

        WebResource wr = c.resource("https://apifyp.herokuapp.com/Login?login=" + login + "&senha=" + senha);
        String json = wr.get(String.class);

        Gson gson = new Gson();
        
        return gson.fromJson(json, new TypeToken<Usuario>() {
        }.getType());
    }

    @Override
    public int compareTo(Usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
