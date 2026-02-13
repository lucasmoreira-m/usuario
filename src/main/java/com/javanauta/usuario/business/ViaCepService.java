package com.javanauta.usuario.business;

import com.javanauta.usuario.infrastructure.Clients.ViaCepClient;
import com.javanauta.usuario.infrastructure.Clients.ViaCepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

private final ViaCepClient Client;

public ViaCepDTO buscarDadosEndereco(String cep){
    return  Client.buscaDadosEndereco(processarCep(cep));

}

private String processarCep(String cep) {
    String cepFormatado =cep.replace(" ", "").
            replace("-", "");

            if (!cepFormatado.matches("\\d+")
           || !Objects.equals(cepFormatado.length(),8)){

                throw new IllegalArgumentException(" O cep contém caracteres inválidos");
            }
            return cepFormatado;
}
}
