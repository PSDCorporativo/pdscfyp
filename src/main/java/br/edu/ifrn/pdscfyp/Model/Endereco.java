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
public class Endereco implements Comparable<Endereco> {
    private Long id;
    private String rua;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private float longintude;
    private float latitude;

    public Endereco() {
        super();
    }

    public Endereco(String rua, String cep, String numero, String complemento,
            String cidade, String estado, float longitude, float latitude) {
        super();
        this.rua = rua;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        this.longintude = longitude;
        this.latitude = latitude;
    }

    @Override
    public int compareTo(Endereco o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
