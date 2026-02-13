package com.javanauta.usuario.business.converter;

import com.javanauta.usuario.business.DTO.EnderecoDTO;
import com.javanauta.usuario.business.DTO.TelefoneDTO;
import com.javanauta.usuario.business.DTO.UsuarioDTO;
import com.javanauta.usuario.infrastructure.entity.Endereco;
import com.javanauta.usuario.infrastructure.entity.Telefone;
import com.javanauta.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefones(usuarioDTO.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS) {
        // PROTEÇÃO: Se a lista for nula, retorna uma lista vazia
        if (enderecoDTOS == null) return new ArrayList<>();

        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDTO dto : enderecoDTOS){
            enderecos.add(paraEndereco(dto));
        }
        return enderecos;
    }

    public Endereco paraEndereco(EnderecoDTO dto){
        if (dto == null) return null;
        return Endereco.builder()
                .rua(dto.getRua())
                .numero(dto.getNumero())
                .cidade(dto.getCidade())
                .complemento(dto.getComplemento())
                .cep(dto.getCep())
                .estado(dto.getEstado())
                .build();
    }

    public List<Telefone> paraListaTelefones(List<TelefoneDTO> dtos){
        // PROTEÇÃO: Verifica nulo antes do stream
        if (dtos == null) return new ArrayList<>();
        return dtos.stream().map(this::paraTelefone).collect(Collectors.toList());
    }

    public Telefone paraTelefone(TelefoneDTO dto){
        if (dto == null) return null;
        return Telefone.builder()
                .numero(dto.getNumero())
                .ddd(dto.getDdd())
                .build();
    }

    // --- ENTIDADE para DTO ---

    public UsuarioDTO paraUsuarioDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraListaEnderecoDTO(usuario.getEnderecos()))
                .telefones(paraListaTelefonesDTO(usuario.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecos) {
        if (enderecos == null) return new ArrayList<>();
        List<EnderecoDTO> dtos = new ArrayList<>();
        for (Endereco endereco : enderecos){
            dtos.add(paraEnderecoDTO(endereco));
        }
        return dtos;
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco){
        if (endereco == null) return null;
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefonesDTO(List<Telefone> telefones){
        if (telefones == null) return new ArrayList<>();
        return telefones.stream().map(this::paraTelefoneDTO).collect(Collectors.toList());
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone){
        if (telefone == null) return null;
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }
    public Usuario updateUsuario(UsuarioDTO usuarioDTO,Usuario entity){
        return  Usuario.builder()
                .nome(usuarioDTO.getNome()!= null ? usuarioDTO.getNome() : entity.getNome())
                .id(entity.getId())
                .senha(usuarioDTO.getSenha() != null? usuarioDTO.getSenha() : entity.getSenha() )
                .email(usuarioDTO.getEmail()!= null? usuarioDTO.getEmail() : entity.getEmail())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }
    public Endereco updateEndereco(EnderecoDTO dto,Endereco entity){
        return Endereco.builder()
                .id(entity.getId())
                .rua(dto.getRua()!= null ? dto.getRua(): entity.getRua())
                .numero(dto.getNumero()!= null ? dto.getNumero(): entity.getNumero())
                .cidade(dto.getCidade()!= null ? dto.getCidade() :entity.getCidade())
                .cep(dto.getCep()!= null ? dto.getCep():entity.getCep())
                .complemento(dto.getComplemento()!= null ? dto.getComplemento(): entity.getComplemento())
                .estado(dto.getEstado()!= null ?dto.getEstado():entity.getEstado())
                .build();
    }

    public Endereco paraEnderecoEntity(EnderecoDTO dto, Long idUsuario){
        return  Endereco.builder()
                .rua(dto.getRua())
                .cidade(dto.getCidade())
                .cep(dto.getCep())
                .complemento(dto.getComplemento())
                .estado(dto.getEstado())
                .numero(dto.getNumero())
                .usuario_id(idUsuario)
                .build();
    }
    public Telefone paraTelefoneEntity(TelefoneDTO dto,Long idUsuario){
        return Telefone.builder()
                .numero(dto.getNumero())
                .ddd(dto.getDdd())
                .usuario_id(idUsuario)
                .build();
    }
    public Telefone updateTelefone(TelefoneDTO dto, Telefone entity) {
        return Telefone.builder()
                .id(entity.getId()) // Mantém o ID original para atualizar o registro correto
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .ddd(dto.getDdd() != null ? dto.getDdd() : entity.getDdd())
                .usuario_id(entity.getUsuario_id()) // Mantém o vínculo com o usuário
                .build();
    }
}
