/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.pdscfyp.Controller;

import br.edu.ifrn.pdscfyp.Model.Usuario;
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
public class MainController {

    @RequestMapping("/index")
    public String index(HttpSession session, Model model) {

        Usuario u = (Usuario) session.getAttribute("usuarioLogado");

        model.addAttribute("usuarioLogado", u);
        model.addAttribute("sessao", session);

        if (session.getAttribute("jaPassou") != null && (boolean) session.getAttribute("jaPassou") == true) {
            String a = (String) session.getAttribute("falhaLogin");
            model.addAttribute("dadosInvalidos", a);
            session.setAttribute("jaPassou", false);
        } else {
            session.setAttribute("jaPassou", false);
        }

        return "index";
    }

    @RequestMapping("/")
    public String indexDois(HttpSession session, Model model) {
        return "redirect:index";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        session.setAttribute("usuarioLogado", null);

        return "redirect:index";
    }

    @RequestMapping(value = "/logon", method = RequestMethod.POST)
    public String logon(HttpSession session, @RequestParam("login") String login, @RequestParam("senha") String senha) {

        Usuario u = Usuario.login(login, senha);
        session.setAttribute("usuarioLogado", u);

        if (u != null) {
            return "redirect:mapa";
        } else {
            session.setAttribute("jaPassou", true);
            session.setAttribute("falhaLogin", "algumacoisa");
            return "redirect:index";
        }
    }

}
