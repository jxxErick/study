package br.ce.wcaquino.entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Filme {

	private String nome;
	private Integer estoque;
	private Double precoLocacao;
}