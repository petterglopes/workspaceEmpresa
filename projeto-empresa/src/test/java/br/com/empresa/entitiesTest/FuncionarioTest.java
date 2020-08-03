package br.com.empresa.entitiesTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import br.com.empresa.entities.Endereco;
import br.com.empresa.entities.Funcionario;
import br.com.empresa.entities.Telefone;

class FuncionarioTest {
	
	Funcionario funcionario;
	private String mensagemEsperada = "Valor Inválido";
	
	@BeforeAll
	static void inicio_dos_testes() {
		System.out.println("Os teste foram iniciados");
	}

	@BeforeEach
	@Timeout(value = 500, unit = TimeUnit.MICROSECONDS)
	void antes_de_cada_teste() {
		System.out.println("O esse teste foi iniciado");
		funcionario = new Funcionario("Antonio", "11111111111", 3000L);
	}
	
	@AfterEach
	void apos_cada_teste() {
		System.out.println("O esse teste foi concluidado");
	}
	
	@AfterAll
	static void fim_dos_testes() {
		System.out.println("Os teste foram concluidados");
	}
	
	@Test
	void deve_retornar_nome_valido_construtor() {
		assertThat("falhou",funcionario.getNome(), equalTo("Antonio"));
	}
	
	@Test
	void deve_retornar_nome_valido() {
		funcionario.setNome("Joao");
		assertThat("falhou",funcionario.getNome(), equalTo("Joao"));
	}
	
	@Test
	void nao_deve_acecitar_nome_vazio_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Funcionario("",	 "11111111111", 1000L);
		});
		assertThat("falhou",exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void nao_deve_acecitar_nome_vazio() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			funcionario.setNome("");
		});
		assertThat("falhou",exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void nao_deve_acecitar_nome_null_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Funcionario(null, "11111111111", 1000L);
		});
		assertThat("falhou",exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void nao_deve_acecitar_nome_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			funcionario.setNome(null);
		});
		assertThat("falhou",exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void deve_retornar_cpf_valido_construtor() {
		assertThat("Falhou", funcionario.getCpf(), equalTo("11111111111"));
	}
		
	@Test
	void deve_retornar_cpf_valido() {
		funcionario.setCpf("11111111111");
		assertThat("Falhou", funcionario.getCpf(), equalTo("11111111111"));
	}
	
	@Test
	void nao_deve_aceitar_cpf_null_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Funcionario("Joao", null, 2000L);
		});
		assertThat("Falhou", exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void nao_deve_aceitar_cpf_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			funcionario.setCpf(null);
		});
		assertThat("Falhou", exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void nao_deve_aceitar_cpf_vazio_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Funcionario("Joao", "", 2000L);
		});
		assertThat("Falhou", exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void nao_deve_aceitar_cpf_vazio() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			funcionario.setCpf("");
		});
		assertThat("Falhou", exception.getMessage(), equalTo(mensagemEsperada));
	}

	@Test
	void deve_retornar_quantidade_valida_de_caracteres_cpf_construtor() {
		assertThat("Falhou", funcionario.getCpf().length(), equalTo(11));
	}
	
	@Test
	void deve_retornar_quantidade_valida_de_caracteres_cpf() {
		funcionario.setCpf("11111111111");
		assertThat("Falhou", funcionario.getCpf().length(), equalTo(11));
	}
	
	@Test
	void deve_aceitar_caracteres_especiais_para_cpf_construtor() {
		Funcionario funcionarioAux = new Funcionario("Maria", "111.111.111-11", 5000L);
		assertThat("Falhou", funcionarioAux.getCpf(), equalTo("11111111111"));
	}
	
	@Test
	void deve_aceitar_caracteres_especiais_para_cpf() {
		funcionario.setCpf("111.111.111-11");
		assertThat("Falhou", funcionario.getCpf(), equalTo("11111111111"));
	}
	
	@Test
	void nao_deve_aceitar_cpf_com_quantidade_caracteres_maior_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Funcionario("Maria", "111111111110", 5000L);
		});
		assertThat("Falhou", exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void nao_deve_aceitar_cpf_com_quantidade_caracteres_maior() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			funcionario.setCpf("111111111110");
		});
		assertThat("Falhou", exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void nao_deve_aceitar_cpf_com_quantidade_caracteres_menor_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Funcionario("Joana", "1111111111", 5000L);
		});
		assertThat("Falhou", exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void nao_deve_aceitar_cpf_com_quantidade_caracteres_menor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			funcionario.setCpf("1111111111");
		});
		assertThat("Falhou", exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void deve_retornar_salario_valido_construtor() {
		assertThat("Falhou", funcionario.getSalario(), equalTo(3000L));
	}
		
	@Test
	void deve_retornar_salario_valido() {
		funcionario.setSalario(3000L);
		assertThat("Falhou", funcionario.getSalario(), equalTo(3000L));
	}
	
	@Test
	void nao_deve_aceitar_salario_null_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Funcionario("Joao", "11111111111", null);
		});
		assertThat("Falhou", exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void nao_deve_aceitar_salario_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			funcionario.setSalario(null);
		});
		assertThat("Falhou", exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void nao_deve_aceitar_salario_menor_zero_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Funcionario("Joao", "11111111111", -2000L);
		});
		assertThat("Falhou", exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void nao_deve_aceitar_salario_menor_zero() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			funcionario.setSalario(-2000L);
		});
		assertThat("Falhou", exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void deve_retornar_telefone_valido() {
		Telefone telefoneAux = new Telefone("11", "11111111", "abc");
		funcionario.setTelefone(telefoneAux);
		assertThat("Falhou", funcionario.getTelefone(), equalTo(telefoneAux));
	}
	
	@Test
	void nao_deve_aceitar_telefone_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			funcionario.setTelefone(null);
		});
		assertThat("Falhou", exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void deve_retornar_endereco_valido() {
		Endereco enderecoAux = new Endereco(280, "Brasil", "000000-000");
		funcionario.setEndereco(enderecoAux);
		assertThat("Falhou", funcionario.getEndereco(), equalTo(enderecoAux));
	}
	
	@Test
	void nao_deve_aceitar_endereco_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			funcionario.setEndereco(null);
		});
		assertThat("Falhou", exception.getMessage(), equalTo(mensagemEsperada));
	}
	
	@Test
	void deve_retornar_to_string_valido() {
		assertEquals("Funcionario\nNome: Antonio\nCPF: 11111111111\nSalário: 3000", funcionario.toString());
	}
	
	@Test
	void deve_retornar_hash_code_valido() {
		assertEquals("11111111111".hashCode(), funcionario.hashCode());
	}
	
	@Test
	void deve_retornar_true_para_empresa_igual() {
		assertEquals(funcionario, new Funcionario("Antonio", "11111111111", 3000L));
	}
}