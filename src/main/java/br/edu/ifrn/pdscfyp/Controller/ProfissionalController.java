/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.pdscfyp.Controller;

import br.edu.ifrn.pdscfyp.Model.Profissional;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
  
        return "rankingProfissionais";
    }
    
    @RequestMapping("/CadastrarProfissional")
    public String CadastrarProfissional() {
        return "cadastrarProfissional";
    }
    
    @RequestMapping(value="/CadastrarProfissional", method = RequestMethod.POST)
    public String CadastroProfissional() {
        return "cadastrarProfissional";
    }
}