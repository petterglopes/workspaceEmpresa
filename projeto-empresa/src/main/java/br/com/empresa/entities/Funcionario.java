package br.com.empresa.entities;

import br.com.empresa.entities.error.ErroValorInvalido;

public class Funcionario {

	private String nome;
	private String cpf;
	private Long salario;
	private Telefone telefone;
	private Endereco endereco;

	public Funcionario(String nome, String cpf, Long salario) {
		if (nome != null && !nome.isEmpty()
				&& cpf != null && cpf.replaceAll("[^0-9]", "").length() == 11
				&& salario != null && salario > 0) {
			this.nome = nome;
			this.cpf = cpf.replaceAll("[^0-9]", "");
			this.salario = salario;
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome != null && !nome.isEmpty()) {
			this.nome = nome;
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (cpf != null && cpf.replaceAll("[^0-9]", "").length() == 11) {
			this.cpf = cpf.replaceAll("[^0-9]", "");
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public Long getSalario() {
		return salario;
	}

	public void setSalario(Long salario) {
		if (salario != null && salario > 0) {
			this.salario = salario;
		} else {
			throw ErroValorInvalido.VALOR_INVALIDO;
		}
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		if (telefone != null) {
			this.telefone = telefone;
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

	@Override
	public String toString() {
		StringBuilder toStringFuncionario = new StringBuilder();
		toStringFuncionario.append("Funcionario\nNome: " + nome + "\nCPF: " + cpf + "\nSalário: " + salario);
		if (telefone != null)
			toStringFuncionario.append("\ntelefone: " + telefone);
		if (endereco != null)
			toStringFuncionario.append("\nEndereço: " + endereco);
		return toStringFuncionario.toString();
	}

	@Override
	public int hashCode() {
		return this.cpf.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Funcionario) {
			Funcionario funcionarioAux = (Funcionario) obj;
			return this.cpf.equals(funcionarioAux.cpf);
		}
		return false;
	}
}
