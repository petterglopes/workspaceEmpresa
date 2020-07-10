package br.com.empresa.entidades;

import java.time.LocalDate;

public class Empresa {

	private String nome;
	private String cnpj;
	private Funcionario representanteLegal;
	private Telefone telefone;
	private Endereco endereco;
	private LocalDate dataAbertura;

	public Empresa(String nome, String cnpj) {
		this.nome = nome;
		this.cnpj = cnpj;
	}
	
	public Empresa(String nome, String cnpj, Funcionario representanteLegal, Telefone telefone, Endereco endereco) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.representanteLegal = representanteLegal;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		if(cnpj != null && cnpj.length() != 14) {
			this.cnpj = cnpj;
		}else {
			throw new IllegalArgumentException("Valor invalido");
		}
	}
	
	public Funcionario getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(Funcionario representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	@Override
	public String toString() {
		return "Empresa nome: " + nome + ", cnpj: " + cnpj + "\nrepresentanteLegal: " + representanteLegal.toString()
				+ ", telefone: " + telefone.toString() + "\nendereco: " + endereco.toString();
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