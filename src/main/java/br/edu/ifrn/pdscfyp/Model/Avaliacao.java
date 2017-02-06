/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.pdscfyp.Model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mateus
 */
@Getter
@Setter
public class Avaliacao implements Serializable, Comparable<Avaliacao> {

    private Long id;
    private Usuario usuario;
    private Profissional profissional;
    private int nota;

    public Avaliacao() {
    }

    public Avaliacao(Usuario usuario, Profissional profissional, int nota) {
        this.usuario = usuario;
        this.profissional = profissional;
        this.nota = nota;
    }

    @Override
    public int compareTo(Avaliacao a) {
        if (this.usuario.compareTo(a.getUsuario()) == 0
                && this.profissional.compareTo(a.getProfissional()) == 0) {
            return 0;
        }

        return 1;
    }
}
