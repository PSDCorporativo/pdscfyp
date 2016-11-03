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
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mateus
 */
@Controller
public class ProfissionalController {
    
    @RequestMapping("/ListProfissionais")
    public String ListProfissionais(Model model) {
        
        Set<Profissional> profissionais = Profissional.getProfissionais();
        
        model.addAttribute("profissionais", profissionais);
     
        return "listProfissionais";
    }
}
