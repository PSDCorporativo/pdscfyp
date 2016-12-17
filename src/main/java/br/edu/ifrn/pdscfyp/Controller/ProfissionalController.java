/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.pdscfyp.Controller;

import br.edu.ifrn.pdscfyp.Model.Profissional;
import br.edu.ifrn.pdscfyp.Model.Usuario;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mateus
 */
@Controller
public class ProfissionalController {

    @RequestMapping("/profissionais")
    public String ListProfissionais(HttpSession session, Model model) {
        Usuario u = (Usuario) session.getAttribute("usuarioLogado");

        model.addAttribute("usuarioLogado", u);

        Set<Profissional> profissionais = Profissional.getProfissionais();

        model.addAttribute("profissionais", profissionais);

        return "listar";
    }

    @RequestMapping("/ranking")
    public String RankingProfissionais(HttpSession session, Model model) {
        Usuario u = (Usuario) session.getAttribute("usuarioLogado");

        model.addAttribute("usuarioLogado", u);

        Map<Profissional, Integer> indexes = new HashMap();
        int indice = 0;

        Set<Profissional> profissionais = Profissional.getProfissionais();
        Set<Profissional> profissionaisOrdenados = new TreeSet();

        indice = profissionais.size();

        for (Profissional p : profissionais) {
            profissionaisOrdenados.add(p);
            indexes.put(p, indice--);
        }

        model.addAttribute("profissionaisOrdenados", profissionaisOrdenados);
        model.addAttribute("indexes", indexes);

        return "ranking";
    }

    @RequestMapping("/mapa")
    public String MostrarMapa(HttpSession session, Model model) {
        Usuario u = (Usuario) session.getAttribute("usuarioLogado");

        model.addAttribute("usuarioLogado", u);

        return "mapa";
    }

    @RequestMapping("/cadastro")
    public String CadastrarProfissional(HttpSession session, Model model) {
        Usuario u = (Usuario) session.getAttribute("usuarioLogado");

        model.addAttribute("usuarioLogado", u);

        return "cadastro";
    }

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public String Cadastro(@RequestParam("nome") String nome,
            @RequestParam("email") String email, @RequestParam("login") String login,
            @RequestParam("senha") String senha, @RequestParam("isProfissional") String isProfissional,
            @RequestParam("rua") String rua, @RequestParam("cep") String cep, @RequestParam("numero") String numero,
            @RequestParam("complemento") String complemento, @RequestParam("cidade") String cidade,
            @RequestParam("estado") String estado, @RequestParam("descricao") String descricao, @RequestParam("profissao") String profissao) {

        if ("profissional".equals(isProfissional)) {

            Profissional p = new Profissional(nome, email, login, senha, true,
                    rua, cep, numero, complemento, cidade, estado, descricao, profissao);

            Profissional.addProfissional(p);
        } else {
            Usuario u = new Usuario(nome, email, login, senha, false, rua, cep,
                    numero, complemento, cidade, estado);

            Usuario.addUsuario(u);
        }

        return "redirect:index";
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.GET)
    public String Buscar(HttpSession session, Model model, @RequestParam("profissao") String profissao) {
        Usuario u = (Usuario) session.getAttribute("usuarioLogado");

        model.addAttribute("usuarioLogado", u);

        Set<Profissional> profissionais = Profissional.getProfissionaisByProfissao(profissao);
        Set<Profissional> profissionaisOrder = new TreeSet();

        for (Profissional profissional : profissionais) {
            profissionaisOrder.add(profissional);
        }

        model.addAttribute("profissionais", profissionaisOrder);

        return "buscar";
    }

    @RequestMapping(value = "/profissional", method = RequestMethod.GET)
    public String BuscarProfissionalById(HttpSession session, Model model, @RequestParam("id") String id) {
        Usuario u = (Usuario) session.getAttribute("usuarioLogado");

        model.addAttribute("usuarioLogado", u);

        Profissional p = Profissional.getProfissionalById(id);
        
        model.addAttribute("profissional", p);
        
        return "perfil";
    }
}
