package br.com.utilitarios.lista.service;

import br.com.utilitarios.lista.model.Convidado;
import br.com.utilitarios.lista.repository.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvidadoService {

    @Autowired
    private ConvidadoRepository repository;

    public Iterable<Convidado> obterConvidados(){

        Iterable<Convidado> convidados = repository.findAll();
        return convidados;
    }

    public void salvar(Convidado convidado){

        repository.save(convidado);

    }

}
