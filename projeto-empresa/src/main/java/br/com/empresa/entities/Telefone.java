package br.com.empresa.entities;

import br.com.empresa.entities.error.ErroValorInvalido;

public class Telefone {

	private String ddd;
	private String numero;
	private String operadora;

	public Telefone(String ddd, String numero, String operadora) {
		if(operadora != null && !operadora.isEmpty() && ddd != null && !ddd.replaceAll("[^0-9]", "").isEmpty() && numero != null && !numero.replaceAll("[^0-9]", "").isEmpty()) {
			this.numero = numero.replaceAll("[^0-9]", "");
			this.ddd = ddd.replaceAll("[^0-9]", "");
			this.operadora = operadora;
		}else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		if(ddd != null && !ddd.replaceAll("[^0-9]", "").isEmpty()) {
			this.ddd = ddd.replaceAll("[^0-9]", "");
		}else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		if(numero != null && !numero.replaceAll("[^0-9]", "").isEmpty()) {
			this.numero = numero.replaceAll("[^0-9]", "");
		}else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		if(operadora != null && !operadora.isEmpty()) {
			this.operadora = operadora;
		}else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	@Override
	public String toString() {
		return "Telefone (" + ddd + ") " + numero + " " + operadora;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Telefone) {
			Telefone telefoneAux = (Telefone) obj;
			if (this.ddd.equals(telefoneAux.ddd) && this.numero.equals(telefoneAux.numero)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (this.ddd + this.numero).hashCode();
	}
}
