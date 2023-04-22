package com.example.gof.service.impl;

import com.example.gof.model.Cliente;
import com.example.gof.model.ClienteRepository;
import com.example.gof.model.Endereco;
import com.example.gof.model.EnderecoRepository;
import com.example.gof.service.IClienteService;
import com.example.gof.service.IViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService implements IClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    IViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> optCliente  = clienteRepository.findById(id);
        return optCliente.orElse(null);
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Cliente clienteBd = buscarPorId(id);
        if (clienteBd != null) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(() -> enderecoRepository.save(viaCepService.consultarCep(cep)));
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
