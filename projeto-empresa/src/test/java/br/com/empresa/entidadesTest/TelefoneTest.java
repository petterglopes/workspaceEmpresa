package br.com.empresa.entidadesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.empresa.entidades.Telefone;

class TelefoneTest {
	private Telefone telefone;
	
	@BeforeEach
	void estancia_telefone() {
		this.telefone = new Telefone("11", "11111111", "abc");
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
	void deve_retornar_numero_valido_costrutor() {
		assertEquals("11111111", telefone.getNumero());
	}

	@Test
	void deve_retornar_numero_valido() {
		telefone.setNumero("222222222");
		assertEquals("222222222", telefone.getNumero());
	}

	@Test
	void deve_retornar_operadora_valida_costrutor() {
		assertEquals("abc", telefone.getOperadora());
	}

	@Test
	void deve_retornar_operadora_valida() {
		Telefone telefone = new Telefone("11", "11111111", "abc");
		assertEquals("abc", telefone.getOperadora());
	}
}
