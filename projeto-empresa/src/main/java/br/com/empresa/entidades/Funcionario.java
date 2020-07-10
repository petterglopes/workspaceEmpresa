package br.com.empresa.entidades;

public class Funcionario {

	private String nome;
	private String cpf;
	private Long salario;
	private Telefone telefone;
	private Endereco end;

	public Funcionario(String nome, String cpf, Long salario) {
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getSalario() {
		return salario;
	}

	public void setSalario(Long salario) {
		this.salario = salario;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Funcionario\nnome: " + nome + "\ncpf: " + cpf + "\nsalario: " + salario +
				"\ntelefone: " + telefone + "\nend: " + end;
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
