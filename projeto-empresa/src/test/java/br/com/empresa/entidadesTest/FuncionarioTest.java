package br.com.empresa.entidadesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.empresa.entidades.Funcionario;

class FuncionarioTest {

	@Test
	void test_quantidade_de_caracteres_cpf() {
		Funcionario funcionario = new Funcionario("Antonio", "111.111.111-1", 3000L);
		assertEquals(14, funcionario.getCpf().length());
	}

}
