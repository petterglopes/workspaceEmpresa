package br.com.empresa.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.empresa.entities.error.ErroValorInvalido;



public class Empresa {

	private String nome;
	private String cnpj;
	private Funcionario representanteLegal;
	private Telefone [] telefone = new Telefone [3];
	private Endereco endereco;
	private LocalDate dataAbertura;

	public Empresa(String nome, String cnpj) {
		
		if(nome != null && !"".equals(nome) && cnpj != null && cnpj.replaceAll("[^0-9]", "").length() == 14) {
			this.nome = nome;
			this.cnpj = cnpj.replaceAll("[^0-9]", "");
		}else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}
	
	public Empresa(String nome, String cnpj, Funcionario representanteLegal, Telefone telefone, Endereco endereco, LocalDate dataAbertura) {
		if(nome != null && !"".equals(nome) && cnpj != null && cnpj.replaceAll("[^0-9]", "").length() == 14
				&& representanteLegal != null && telefone != null && endereco != null && dataAbertura != null) {
			this.nome = nome;
			this.cnpj = cnpj.replaceAll("[^0-9]", "");
			this.representanteLegal = representanteLegal;
			this.telefone [1] = telefone;
			this.endereco = endereco;
			this.dataAbertura = dataAbertura;
		}else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome != null && !"".equals(nome)) {
			this.nome = nome;
		}else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		if(cnpj != null && cnpj.replaceAll("[^0-9]", "").length() == 14) {
			this.cnpj = cnpj.replaceAll("[^0-9]", "");
		}else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}
	
	public Funcionario getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(Funcionario representanteLegal) {
		if(representanteLegal != null) {
			this.representanteLegal = representanteLegal;
		}else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public Telefone getTelefone(Integer index) {
		if (index != null && index > 0 && index < 4) {
			return telefone[index - 1];
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public void setTelefone(Telefone telefone, Integer index) {
		if (telefone != null && index != null && index > 0 && index < 4) {
			this.telefone[index - 1] = telefone;
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		if (endereco != null) {
			this.endereco = endereco;
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}
	
	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		if (dataAbertura != null) {
			this.dataAbertura = dataAbertura;
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	@Override
	public String toString() {
		StringBuilder toStringEmpresa = new StringBuilder();
		toStringEmpresa.append("Empresa nome: " + nome + ", cnpj: " + cnpj);
		if(representanteLegal != null) {
			toStringEmpresa.append("\nRepresentante Legal: " + representanteLegal.toString());
		}
		for(int i=0; i < telefone.length; i++) {
			if(telefone[i] != null)
				toStringEmpresa.append("\nTelefone "+ i + ": " + telefone[i].toString());
		}
		if(endereco != null) {
			toStringEmpresa.append("\nEndereco: " + endereco.toString());
		}
		if(dataAbertura != null) {
			toStringEmpresa.append("\nEmpresa aberta em: " + dataAbertura.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		}
		return toStringEmpresa.toString();
	}

	@Override
	public int hashCode() {
		return this.cnpj.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if(object instanceof Empresa) {
				Empresa empresaAux = (Empresa) object;
				return this.cnpj.equals(empresaAux.cnpj);
		}
		return false;
	}
}
