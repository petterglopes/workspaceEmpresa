package br.com.empresa.entidades;

public class Endereco {

	private String logradouro;
	private String end;
	private Integer numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private String cep;

	public Endereco(Integer numero, String pais, String cep) {
		this.numero = numero;
		this.pais = pais;
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		if (logradouro != null && !"".equals(logradouro)) {
			this.logradouro = logradouro;
		} else {
			throw new IllegalArgumentException("Valor inválido");
		}
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String endereco) {
		this.end = endereco;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "Endereço\n" + logradouro + " " + end + ", " + numero + "\n" + bairro + "\n" + cidade + "/" + estado
				+ " - " + pais + "\nCEP: " + cep;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Endereco) {
			Endereco enderecoAux = (Endereco) obj;
			return (this.pais.equalsIgnoreCase(enderecoAux.pais) && this.cep.equals(enderecoAux.cep)
					&& this.numero.equals(enderecoAux.numero));
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return (pais + cep + numero).hashCode();
	}

}
