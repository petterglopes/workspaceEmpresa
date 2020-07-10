package br.com.empresa.entidadesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.com.empresa.entidades.Endereco;

class EnderecoTest {
	
	@Test
	void deve_retornar_logradouro_valido() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		endereco.setLogradouro("Rua");
		assertEquals("Rua", endereco.getLogradouro()); 
	}
	
	@Test
	void nao_deve_aceitar_logradou_null() {

		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setLogradouro(null);
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	

	@Test
	void nao_deve_aceitar_logradou_vazio() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setLogradouro("");
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_end_valido() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		endereco.setEnd("xv de novembro");
		assertEquals("xv de novembro", endereco.getEnd());
	}
	
	@Test
	void nao_deve_aceitar_end_null() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setEnd(null);
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_end_vazio() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setEnd("");
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_numero_valido_construtor() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		assertEquals(280, endereco.getNumero());
	}
	
	@Test
	void deve_retornar_numero_valido() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		endereco.setNumero(281);
		assertEquals(281, endereco.getNumero());
	}
	
	@Test
	void nao_deve_aceitar_numero_menor_que_zero_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(-1, "Brasil", "000000000");
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_numero_menor_que_zero() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setNumero(-1);
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_numero_null_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(null, "Brasil", "000000000");
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_numero_null() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setNumero(null);
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_bairo_valido() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		endereco.setBairro("Tatuapé");
		assertEquals("Tatuapé", endereco.getBairro());
	}
	
	@Test
	void nao_deve_aceitar_bairo_vazio() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setBairro("");
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_bairo_null() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setBairro(null);
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_cidade_valida() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		endereco.setCidade("São Paulo");
		assertEquals("São Paulo", endereco.getBairro());
	}
	
	@Test
	void nao_deve_aceitar_cidade_vazio() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setCidade("");
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cidade_null() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setCidade(null);
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_estado_valida() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		endereco.setEstado("SP");
		assertEquals("SP", endereco.getEstado());
	}
	
	@Test
	void nao_deve_aceitar_estado_vazio() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setEstado("");
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_estado_null() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setEstado(null);
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_pais_valida_construtor() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		assertEquals("Brasil", endereco.getPais());
	}
	
	@Test
	void deve_retornar_pais_valida() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		endereco.setPais("Portugal");
		assertEquals("Portugal", endereco.getPais());
	}
	
	@Test
	void nao_deve_aceitar_pais_vazio_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(280, "", "000000000");
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_pais_vazio() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setPais("");
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_pais_null_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(280, null, "000000000");
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_pais_null() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setPais(null);
		});
		String mensagemEsperada = "Valor Inválido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_cep_valido_construtor() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		assertEquals("000000000", endereco.getCep());
	}
	
	@Test
	void deve_retornar_cep_valido() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		endereco.setCep("000000001");
		assertEquals("000000001", endereco.getCep());
	}

	@Test
	void deve_retornar_quantidade_valida_de_caracteres_cep_construtor() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		assertEquals(9, endereco.getCep().length());
	}
	
	@Test
	void deve_retornar_quantidade_valida_de_caracteres_cep() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		endereco.setCep("000000001");
		assertEquals(9, endereco.getCep().length());
	}
	
	@Test
	void deve_aceitar_caracteres_especiais_para_cep_construtor() {
		Endereco endereco = new Endereco(280, "Brasil", "00000-000");
		assertEquals(9, endereco.getCep().length());
	}
	
	@Test
	void deve_aceitar_caracteres_especiais_para_cep() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		endereco.setCep("00000-000");
		assertEquals(9, endereco.getCep().length());
	}
	
	@Test
	void nao_deve_aceitar_cep_null_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(280, "Brasil", null);
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cep_null() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setCep(null);
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cep_vazio_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(280, "Brasil", "");
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cep_vazio() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setCep("");
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cep_com_quantidade_caracteres_maior_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(280, "Brasil", "0000000000");
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cep_com_quantidade_caracteres_maior() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setCep("0000000000");
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cep_com_quantidade_caracteres_menor_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(280, "Brasil", "0000000");
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cep_com_quantidade_caracteres_menor() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setCep("00000000");
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_to_string_valido() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		assertEquals("Endereço\n , " + 280 + "\n\n/ - " + "Brasil" + "\nCEP: " + "000000000", endereco.toString());
	}
	
	@Test
	void deve_retornar_hash_code_valido() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		assertEquals("000000000".hashCode(), endereco.hashCode());
	}
	
	@Test
	void deve_retornar_true_para_empresa_igual() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		assertTrue(endereco.equals(new Endereco(280, "Brasil", "000000000")));
	}
	
	@Test
	void deve_retornar_false_para_null() {
		Endereco endereco = new Endereco(280, "Brasil", "000000000");
		assertFalse(endereco.equals(null));
	}
}
