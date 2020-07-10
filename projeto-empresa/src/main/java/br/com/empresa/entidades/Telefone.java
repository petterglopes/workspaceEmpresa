package br.com.empresa.entidades;

public class Telefone {

	private String ddd;
	private String numero;
	private String operadora;

	public Telefone(String ddd, String numero, String operadora) {
		this.ddd = ddd;
		this.numero = numero;
		this.operadora = operadora;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	@Override
	public String toString() {
		return "Telefone\nddd: " + ddd + ", numero: " + numero + ", operadora: " + operadora;
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
