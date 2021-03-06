package br.com.utilitarios.lista.Controller;

import br.com.utilitarios.lista.model.Convidado;
import br.com.utilitarios.lista.repository.ConvidadoRepository;
import br.com.utilitarios.lista.service.ConvidadoService;
import enviadoremail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConvidadoController {

    @Autowired
    private ConvidadoService convidadoService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("listavip")
    public String listaConvidados(Model model) {

        Iterable<Convidado> convidados = convidadoService.obterConvidados();
        model.addAttribute("convidados", convidados);
        return "listaconvidados";
    }

    @RequestMapping(value = "salvar", method = RequestMethod.POST)
    public String salvar(@RequestParam("nome") String nome, @RequestParam("email")
            String email, @RequestParam("telefone") String telefone, Model model) {

        Convidado convidado = new Convidado(nome, email, telefone);
        convidadoService.salvar(convidado);
        new EmailService().enviar(nome, email);
        Iterable<Convidado> convidados = convidadoService.obterConvidados();
        model.addAttribute("convidados", convidados);
        return "listaconvidados";
    }
}
