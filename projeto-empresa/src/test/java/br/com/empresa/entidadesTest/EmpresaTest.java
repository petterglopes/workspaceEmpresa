package br.com.empresa.entidadesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.empresa.entidades.Empresa;
import br.com.empresa.entidades.Endereco;
import br.com.empresa.entidades.Funcionario;
import br.com.empresa.entidades.Telefone;

class EmpresaTest {

	@Test
	void deve_retornar_nome_valido_construtor() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		assertEquals("EmpresaTeste", empresa.getNome());
	}

	@Test
	void deve_retornar_nome_valido() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		empresa.setNome("EmpresaTeste1");
		assertEquals("EmpresaTeste1", empresa.getNome());
	}
	
	@Test
	void nao_deve_aceitar_nome_null_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Empresa(null, "00000000000000");
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_nome_null() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setNome(null);
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_nome_vazio_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Empresa("", "00000000000000");
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_nome_vazio() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setNome("");
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_cnpj_valido_construtor() {
		Empresa empresa = new Empresa("EmpresaTeste", "000000000000000");
		assertEquals("000000000000000", empresa.getCnpj());
	}
	
	@Test
	void deve_retornar_cnpj_valido() {
		Empresa empresa = new Empresa("EmpresaTeste", "000000000000000");
		empresa.setCnpj("000000000000001");
		assertEquals("000000000000001", empresa.getCnpj());
	}
	
	@Test
	void deve_retornar_quantidade_valida_de_caracteres_cnpj_construtor() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		assertEquals(14, empresa.getCnpj().length());
	}
	
	@Test
	void deve_retornar_quantidade_valida_de_caracteres_cnpj() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		empresa.setCnpj("00000000000001");
		assertEquals(14, empresa.getCnpj().length());
	}
	
	@Test
	void deve_aceitar_caracteres_especiais_para_cnpj_construtor() {
		Empresa empresa = new Empresa("EmpresaTeste", "00.000.000/0000-00");
		assertEquals(14, empresa.getCnpj().length());
	}
	
	@Test
	void deve_aceitar_caracteres_especiais_para_cnpj() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		empresa.setCnpj("00.000.000/0000-00");
		assertEquals(14, empresa.getCnpj().length());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_null_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Empresa("EmpresaTeste", null);
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_null() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setCnpj(null);
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_vazio_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Empresa("EmpresaTeste", "");
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_vazio() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setCnpj("");
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_com_quantidade_caracteres_maior_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Empresa("EmpresaTeste", "000000000000000");
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_com_quantidade_caracteres_maior() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setCnpj("000000000000000");
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_com_quantidade_caracteres_menor_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Empresa("EmpresaTeste", "0000000000000");
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_com_quantidade_caracteres_menor() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setCnpj("0000000000000");
		});
		String mensagemEsperada = "Valor invalido";
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_representantelegal_valido() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		empresa.setRepresentanteLegal(new Funcionario("Antonio", "111.111.111-11", 3000L));
		assertEquals(new Funcionario("Antonio", "111.111.111-11", 3000L), empresa.getRepresentanteLegal());
	}
	
	@Test
	void não_deve_aceitar_representantelegal_null() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setRepresentanteLegal(null);
		});
		String mensagemEperada = "Valor invalido";
		assertEquals(mensagemEperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_telefone_valido() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		empresa.setTelefone(new Telefone("11", "11111111", "abc"));
		assertEquals(new Telefone("11", "11111111", "abc"), empresa.getTelefone());
	}
	
	
	@Test
	void não_deve_aceitar_telefone_null() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setTelefone(null);
		});
		String mensagemEperada = "Valor invalido";
		assertEquals(mensagemEperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_endereco_valido() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		Endereco enderecoAux = new Endereco(280, "Brasil", "00000-000");
		empresa.setEndereco(enderecoAux);
		assertEquals(enderecoAux, empresa.getEndereco());
	}
	

	@Test
	void não_deve_aceitar_endereco_null() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setTelefone(null);
		});
		String mensagemEperada = "Valor invalido";
		assertEquals(mensagemEperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_data_valida() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		empresa.setDataAbertura(LocalDate.of(2020, 01, 1));
		assertTrue(empresa.getDataAbertura() instanceof LocalDate);
		assertEquals(LocalDate.of(2020, 01, 1), empresa.getDataAbertura());
	}
	
	@Test
	void não_deve_aceitar_data_null() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setDataAbertura(null);
		});
		String mensagemEperada = "Valor invalido";
		assertEquals(mensagemEperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_to_string_valido() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		assertEquals("Empresa nome: EmpresaTeste, cnpj: 00000000000000\nrepresentanteLegal: , telefone: \nendereco: ", empresa.toString());
	}
	
	@Test
	void deve_retornar_hash_code_valido() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		assertEquals("00000000000000".hashCode(), empresa.hashCode());
	}
	
	@Test
	void deve_retornar_true_para_empresa_igual() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		assertTrue(empresa.equals(new Empresa("EmpresaTeste", "00000000000000")));
	}
	
	@Test
	void deve_retornar_false_para_null() {
		Empresa empresa = new Empresa("EmpresaTeste", "00000000000000");
		assertFalse(empresa.equals(null));
	}
}
