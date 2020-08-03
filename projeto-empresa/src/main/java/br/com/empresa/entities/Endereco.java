package br.com.empresa.entities;

import br.com.empresa.entities.error.ErroValorInvalido;

public class Endereco {

	private String logradouro;
	private String enderecoLogradouro;
	private Integer numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private String cep;


	public Endereco(Integer numero, String pais, String cep) {
		if (numero != null && numero > 0 && pais != null && !"".equals(pais) && cep != null && cep.replaceAll("[^0-9]", "").length() == 9) {
			this.numero = numero;
			this.pais = pais;
			this.cep = cep.replaceAll("[^0-9]", "");
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		if (logradouro != null && !"".equals(logradouro)) {
			this.logradouro = logradouro;
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public String getEndereco() {
		return enderecoLogradouro;
	}

	public void setEndereco(String endereco) {
		if (endereco != null && !"".equals(endereco)) {
			this.enderecoLogradouro = endereco;
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		if (numero != null && numero > 0) {
			this.numero = numero;
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		if (bairro != null && !"".equals(bairro)) {
			this.bairro = bairro;
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		if (cidade != null && !"".equals(cidade)) {
			this.cidade = cidade;
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		if (estado != null && !"".equals(estado)) {
			this.estado = estado;
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		if (pais != null && !"".equals(pais)) {
			this.pais = pais;
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		if(cep != null && cep.replaceAll("[^0-9]", "").length() == 9) {
			this.cep = cep.replaceAll("[^0-9]", "");
		}else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	@Override
	public String toString() {
		StringBuilder toStringEndereco = new StringBuilder();
		toStringEndereco.append("Endereço:");
		if(logradouro != null && enderecoLogradouro != null) {
			toStringEndereco.append("\n" + logradouro + " " + enderecoLogradouro);
		}
		toStringEndereco.append("\nNúmero: " + numero);
		if(bairro != null) {
			toStringEndereco.append("\nBairro: " + bairro);
		}
		if(cidade != null) {
			toStringEndereco.append("\nCidade " + cidade);
		}
		if(estado != null) {
			toStringEndereco.append("\nEstado: " + estado);
		}
		toStringEndereco.append("\nPais: " + pais);
		toStringEndereco.append("\nCEP: " + cep);
		
		return toStringEndereco.toString();
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
		return (this.pais + this.cep + this.numero).hashCode();
	}

}
