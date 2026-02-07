package com.javanauta.usuario.business.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTO {

    private long id;
    private String nome;
    private String ddd;
    private String numero;
}
