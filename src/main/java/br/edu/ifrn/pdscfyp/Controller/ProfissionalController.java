/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.pdscfyp.Controller;

import br.edu.ifrn.pdscfyp.Model.Profissional;
import br.edu.ifrn.pdscfyp.Model.Usuario;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public String ListProfissionais(Model model) {

        Set<Profissional> profissionais = Profissional.getProfissionais();

        model.addAttribute("profissionais", profissionais);

        return "listProfissionais";
    }

    @RequestMapping("/profissional/{username}")
    public String getProfissional(@PathVariable("username") String username, Model model) {
        Profissional p = Profissional.getProfissionalByLogin(username);

        model.addAttribute("profissional", p);

        return "showProfissional";
    }

    @RequestMapping("/ranking")
    public String RankingProfissionais(Model model) {
        Set<Profissional> profissionais = Profissional.getProfissionais();
        Set<Profissional> profissionaisOrdenados = new TreeSet();

        for (Profissional p : profissionais) {
            profissionaisOrdenados.add(p);
        }

        model.addAttribute("profissionaisOrdenados", profissionaisOrdenados);

        return "rankingProfissionais";
    }

    @RequestMapping("/cadastro")
    public String CadastrarProfissional() {

        return "cadastrarProfissional";
    }

//    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
//    public String CadastroProfissional(@RequestParam("nome") String nome,
//            @RequestParam("email") String email, @RequestParam("login") String login,
//            @RequestParam("senha") String senha, @RequestParam("descricao") String descricao,
//            @RequestParam("profissao") String profisao) {
//
//        Profissional p = new Profissional(nome, email, login, senha, 0, descricao, profisao, 0, 0, 0);
//        Profissional.addProfissional(p);
//
//        return "cadastrarProfissional";
//    }
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

        return "cadastrarProfissional";
    }

    @RequestMapping(value = "/secadastrar", method = RequestMethod.GET)
    public String Cadastro() {

        return "cadastroSecreto";
    }

//    @RequestMapping(value = "/scadastrar", method = RequestMethod.POST)
//    public String CadastroProfissionalSecreto(@RequestParam("nome") String nome,
//            @RequestParam("email") String email, @RequestParam("login") String login,
//            @RequestParam("senha") String senha, @RequestParam("idNacional") int idNacional,
//            @RequestParam("descricao") String descricao, @RequestParam("profissao") String profisao,
//            @RequestParam("latitude") float latitude, @RequestParam("longitude") float longitude,
//            @RequestParam("pontuacao") int pontuacao) {
//
//        Profissional p = new Profissional(nome, email, login, senha, idNacional, descricao, profisao, latitude, longitude, pontuacao);
//
//        Profissional.addProfissionalSecreto(p);
//
//        return "cadastroSecreto";
//    }

    @RequestMapping("/sprofissionais")
    public String ListProfissionaisSecretos(Model model) {

        Set<Profissional> profissionais = Profissional.getProfissionais();

        model.addAttribute("profissionais", profissionais);

        return "listProfissionaisSecretos";
    }

}
