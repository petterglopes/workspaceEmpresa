package br.com.empresa.entitiesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.empresa.entities.Endereco;

class EnderecoTest {
	
	private String mensagemEsperada = "Valor Inválido";
	private Endereco endereco;
	
	@BeforeEach
	void estanciaEndereco() {
		endereco = new Endereco(280, "Brasil", "000000-000");
	}
	
	@Test
	void deve_retornar_logradouro_valido() {
		endereco.setLogradouro("Rua");
		assertEquals("Rua", endereco.getLogradouro()); 
	}
	
	@Test
	void nao_deve_aceitar_logradou_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setLogradouro(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	

	@Test
	void nao_deve_aceitar_logradou_vazio() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setLogradouro("");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_end_valido() {
		endereco.setEndereco("xv de novembro");
		assertEquals("xv de novembro", endereco.getEndereco());
	}
	
	@Test
	void nao_deve_aceitar_end_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setEndereco(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_end_vazio() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setEndereco("");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_numero_valido_construtor() {
		assertEquals(Integer.valueOf(280), endereco.getNumero());
	}
	
	@Test
	void deve_retornar_numero_valido() {
		endereco.setNumero(281);
		assertEquals(Integer.valueOf(281), endereco.getNumero());
	}
	
	@Test
	void nao_deve_aceitar_numero_menor_que_zero_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(-1, "Brasil", "000000000");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_numero_menor_que_zero() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setNumero(-1);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_numero_null_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(null, "Brasil", "000000000");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_numero_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setNumero(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_bairo_valido() {
		endereco.setBairro("Tatuapé");
		assertEquals("Tatuapé", endereco.getBairro());
	}
	
	@Test
	void nao_deve_aceitar_bairo_vazio() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setBairro("");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_bairo_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setBairro(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_cidade_valida() {
		endereco.setCidade("São Paulo");
		assertEquals("São Paulo", endereco.getCidade());
	}
	
	@Test
	void nao_deve_aceitar_cidade_vazio() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setCidade("");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cidade_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setCidade(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_estado_valida() {
		endereco.setEstado("SP");
		assertEquals("SP", endereco.getEstado());
	}
	
	@Test
	void nao_deve_aceitar_estado_vazio() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setEstado("");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_estado_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setEstado(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_pais_valida_construtor() {
		assertEquals("Brasil", endereco.getPais());
	}
	
	@Test
	void deve_retornar_pais_valida() {
		endereco.setPais("Portugal");
		assertEquals("Portugal", endereco.getPais());
	}
	
	@Test
	void nao_deve_aceitar_pais_vazio_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(280, "", "000000000");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_pais_vazio() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setPais("");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_pais_null_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(280, null, "000000000");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_pais_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setPais(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_cep_valido_construtor() {
		assertEquals("000000000", endereco.getCep());
	}
	
	@Test
	void deve_retornar_cep_valido() {
		endereco.setCep("000000001");
		assertEquals("000000001", endereco.getCep());
	}

	@Test
	void deve_retornar_quantidade_valida_de_caracteres_cep_construtor() {
		assertEquals(9, endereco.getCep().length());
	}
	
	@Test
	void deve_retornar_quantidade_valida_de_caracteres_cep() {
		endereco.setCep("000000001");
		assertEquals(9, endereco.getCep().length());
	}
	
	@Test
	void deve_aceitar_caracteres_especiais_para_cep_construtor() {
		assertEquals(9, endereco.getCep().length());
	}
	
	@Test
	void deve_aceitar_caracteres_especiais_para_cep() {
		endereco.setCep("000000-000");
		assertEquals(9, endereco.getCep().length());
	}
	
	@Test
	void nao_deve_aceitar_cep_null_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(280, "Brasil", null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cep_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setCep(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cep_vazio_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(280, "Brasil", "");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cep_vazio() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setCep("");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cep_com_quantidade_caracteres_maior_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(280, "Brasil", "0000000000");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cep_com_quantidade_caracteres_maior() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setCep("0000000000");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cep_com_quantidade_caracteres_menor_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Endereco(280, "Brasil", "0000000");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cep_com_quantidade_caracteres_menor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			endereco.setCep("00000000");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_to_string_valido() {
		assertEquals("Endereço:\nNúmero: " + 280 + "\nPais: " + "Brasil" + "\nCEP: " + "000000000", endereco.toString());
	}
	
	@Test
	void deve_retornar_hash_code_valido() {
		assertEquals("Brasil000000000280".hashCode(), endereco.hashCode());
	}
	
	@Test
	void deve_retornar_true_para_empresa_igual() {
		assertEquals(endereco, new Endereco(280, "Brasil", "000000000"));
	}
}
