package br.com.empresa.entitiesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import br.com.empresa.entities.Empresa;
import br.com.empresa.entities.Endereco;
import br.com.empresa.entities.Funcionario;
import br.com.empresa.entities.Telefone;


class EmpresaTest {
	
	private Empresa empresa = new Empresa("EmpresaTeste", "00.000.000/0000-00");
	private String mensagemEsperada = "Valor Inválido";
	
	@BeforeEach
	void estanciaEmpresa() {
		empresa = new Empresa("EmpresaTeste", "00.000.000/0000-00");
	}

	@Test
	void deve_retornar_nome_valido_construtor() {
		assertEquals("EmpresaTeste", empresa.getNome());
	}

	@Test
	void deve_retornar_nome_valido() {
		empresa.setNome("EmpresaTeste1");
		assertEquals("EmpresaTeste1", empresa.getNome());
	}
	
	@Test
	void nao_deve_aceitar_nome_null_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Empresa(null, "00000000000000");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_nome_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setNome(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_nome_vazio_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Empresa("", "00000000000000");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_nome_vazio() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setNome("");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_cnpj_valido_construtor() {
		assertEquals("00000000000000", empresa.getCnpj());
	}
	
	@Test
	void deve_retornar_cnpj_valido() {
		empresa.setCnpj("00000000000001");
		assertEquals("00000000000001", empresa.getCnpj());
	}
	
	@Test
	void deve_retornar_quantidade_valida_de_caracteres_cnpj_construtor() {
		assertEquals(14, empresa.getCnpj().length());
	}
	
	@Test
	void deve_retornar_quantidade_valida_de_caracteres_cnpj() {
		empresa.setCnpj("00000000000001");
		assertEquals(14, empresa.getCnpj().length());
	}
	
	@Test
	void deve_aceitar_caracteres_especiais_para_cnpj_construtor() {
		assertEquals(14, empresa.getCnpj().length());
	}
	
	@Test
	void deve_aceitar_caracteres_especiais_para_cnpj() {
		empresa.setCnpj("00.000.000/0000-00");
		assertEquals(14, empresa.getCnpj().length());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_null_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Empresa("EmpresaTeste", null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setCnpj(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_vazio_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Empresa("EmpresaTeste", "");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_vazio() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setCnpj("");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_com_quantidade_caracteres_maior_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Empresa("EmpresaTeste", "000000000000000");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_com_quantidade_caracteres_maior() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setCnpj("000000000000000");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_com_quantidade_caracteres_menor_construtor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Empresa("EmpresaTeste", "0000000000000");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_cnpj_com_quantidade_caracteres_menor() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setCnpj("0000000000000");
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_representantelegal_valido() {
		Funcionario funcionarioAux = new Funcionario("Antonio", "111.111.111-11", 3000L);
		empresa.setRepresentanteLegal(funcionarioAux);
		assertEquals(funcionarioAux, empresa.getRepresentanteLegal());
	}
	
	@Test
	void nao_deve_aceitar_representantelegal_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setRepresentanteLegal(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_telefone_valido() {
		empresa.setTelefone(new Telefone("11", "11111111", "abc"), 1);
		assertEquals(new Telefone("11", "11111111", "abc"), empresa.getTelefone(1));
	}
	
	@Test
	void nao_deve_aceitar_telefone_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setTelefone(null, 1);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	@Disabled("Desabilitado até que seja corrigido a info S5778")
	void nao_deve_aceitar_index_menor_um_set_telefone() {
		Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
			empresa.setTelefone(new Telefone("11", "11111111", "abc"), 0);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
		
	@Test
	@Disabled("Desabilitado até que seja corrigido a info S5778")
	void nao_deve_aceitar_index_maior_tres_set_telefone() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setTelefone(new Telefone("11", "11111111", "abc"), 4);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
		
	@Test
	@Disabled("Desabilitado até que seja corrigido a info S5778")
	void nao_deve_aceitar_index_null_set_telefone() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setTelefone(new Telefone("11", "11111111", "abc"), null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void nao_deve_aceitar_index_menor_um_get_telefone() {
		empresa.setTelefone(new Telefone("11", "11111111", "abc"), 1);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.getTelefone(0);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
		
	@Test
	void nao_deve_aceitar_index_maior_tres_get_telefone() {
		empresa.setTelefone(new Telefone("11", "11111111", "abc"), 1);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.getTelefone(4);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
		
	@Test
	void nao_deve_aceitar_index_null_get_telefone() {
		empresa.setTelefone(new Telefone("11", "11111111", "abc"), 1);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.getTelefone(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_endereco_valido() {
		Endereco enderecoAux = new Endereco(280, "Brasil", "000000-000");
		empresa.setEndereco(enderecoAux);
		assertEquals(enderecoAux, empresa.getEndereco());
	}
	

	@Test
	void nao_deve_aceitar_endereco_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setEndereco(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_data_valida() {
		LocalDate data = LocalDate.of(2020, 01, 1);
		empresa.setDataAbertura(data);
		assertEquals(data, empresa.getDataAbertura());
	}
	
	@Test
	void nao_deve_aceitar_data_null() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			empresa.setDataAbertura(null);
		});
		assertEquals(mensagemEsperada, exception.getMessage());
	}
	
	@Test
	void deve_retornar_to_string_valido() {
		assertEquals("Empresa nome: EmpresaTeste, cnpj: 00000000000000", empresa.toString());
	}
	
	@Test
	void deve_retornar_hash_code_valido() {
		assertEquals("00000000000000".hashCode(), empresa.hashCode());
	}
	
	@Test
	void deve_retornar_true_para_empresa_igual() {
		assertEquals(empresa, new Empresa("EmpresaTeste", "00000000000000"));
	}
}
