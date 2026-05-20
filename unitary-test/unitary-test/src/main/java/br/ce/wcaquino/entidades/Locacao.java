package br.ce.wcaquino.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Locacao {

	private Usuario usuario;
    List<Filme> filmes;
	private Date dataLocacao;
	private Date dataRetorno;
	private Double valor;
}