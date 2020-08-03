package br.com.empresa.entitiesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.empresa.entities.Telefone;

class TelefoneTest {
	private Telefone telefone;
	private String mensagemEsperada = "Valor Inválido";
	
	@BeforeEach
	void estancia_telefone() {
		telefone = new Telefone("11", "111111111", "abc");
	}

	@Test
	void deve_retornar_ddd_valido_costrutor() {
		assertEquals("11", telefone.getDdd());
	}
	
	@Test
	void deve_retornar_ddd_valido() {
		telefone.setDdd("22");
		assertEquals("22", telefone.getDdd());
	}
	
	@Test
	void nao_deve_aceitar_null_ddd_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Telefone(null, "111111111", "abc");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_null_ddd() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			telefone.setDdd(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_vazio_ddd_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Telefone("", "111111111", "abc");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_vazio_ddd() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			telefone.setDdd("");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_aceitar_caractere_especial_ddd_construtor() {
		Telefone telefoneAux = new Telefone("(11)", "111111111", "abc");
		assertEquals("11", telefoneAux.getDdd());
	}
	
	@Test
	void nao_deve_aceitar_caractere_letras_ddd() {
		telefone.setDdd("(11)");
		assertEquals("11", telefone.getDdd());
	}

	@Test
	void deve_retornar_numero_valido_costrutor() {
		assertEquals("111111111", telefone.getNumero());
	}

	@Test
	void deve_retornar_numero_valido() {
		telefone.setNumero("222222222");
		assertEquals("222222222", telefone.getNumero());
	}
	
	@Test
	void nao_deve_aceitar_null_numero_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Telefone("22", null, "abc");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_null_numero() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			telefone.setNumero(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_vazio_numero_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Telefone("22", "", "abc");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_vazio_numero() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			telefone.setNumero("");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_aceitar_caractere_especial_numero_construtor() {
		Telefone telefoneAux = new Telefone("11", "11111-1111", "abc");
		assertEquals("111111111", telefoneAux.getNumero());
	}
	
	@Test
	void deve_aceitar_caractere_especial_numero() {
		telefone.setNumero("11111-1111");
		assertEquals("111111111", telefone.getNumero());
	}

	@Test
	void deve_retornar_operadora_valida_costrutor() {
		assertEquals("abc", telefone.getOperadora());
	}

	@Test
	void deve_retornar_operadora_valida() {
		assertEquals("abc", telefone.getOperadora());
	}
	
	@Test
	void nao_deve_aceitar_vazio_operadora_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Telefone("22", "222222222", "");;
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}

	
	@Test
	void nao_deve_aceitar_vazio_operadora() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			telefone.setOperadora("");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_null_operadora_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Telefone("22", "222222222", null);;
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}

	
	@Test
	void nao_deve_aceitar_null_operadora() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			telefone.setOperadora(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_to_string_valido() {
		assertEquals("Telefone (11) 111111111 abc", telefone.toString());
	}
	
	@Test
	void deve_retornar_hash_code_valido() {
		assertEquals("11111111111".hashCode(), telefone.hashCode());
	}
	
	@Test
	void deve_retornar_true_para_empresa_igual() {
		assertEquals(telefone, new Telefone("11", "111111111", "abc"));
	}
}
