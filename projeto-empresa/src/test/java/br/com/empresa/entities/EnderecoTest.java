package br.com.empresa.entities;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import static org.hamcrest.MatcherAssert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

class EnderecoTest {
	
	private Endereco endereco;
    private Set<ConstraintViolation<Endereco>> restricoes;
    private StringBuilder mensagensRestricoes;
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @BeforeAll
    static void inicio_dos_testes() {
        System.out.println("Os testes da classe Endereço foram iniciados");
        FixtureFactoryLoader.loadTemplates("br.com.empresa.template");
    }

    @BeforeEach
    void antes_de_cada_teste() {
        System.out.println("Esse teste foi iniciado");
        endereco = Fixture.from(Endereco.class).gimme("endereco_valido");
        mensagensRestricoes = new StringBuilder();
    }

    @AfterEach
    void apos_cada_teste() {
        System.out.println("Esse teste foi concluidado");
    }

    @AfterAll
    static void fim_dos_testes() {
        System.out.println("Os testes da classe Endereço foram concluidados");
    }

    @Test
    void deve_renornar_id_null() {
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
        assertEquals(null, endereco.getId());
    }

    @Test
    void deve_restringir_set_id() {
        endereco.setId(1l);
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Esse campo só pode ser atribuido pela api do banco de dados.", mensagensRestricoes.toString());
    }
    
    @Test
    void deve_retornar_logradouro_valido_construtor() {
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
        assertEquals("Rua", endereco.getLogradouro()); 
    }
	
	@Test
	void deve_retornar_logradouro_valido() {
		endereco.setLogradouro("Avenida");
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
        assertEquals("Avenida", endereco.getLogradouro()); 
	}
    
    @Test
    void nao_deve_aceitar_logradou_null_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_logradouro_null");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O logradouro não pode ser nulo.", mensagensRestricoes.toString());
    }
	
	@Test
	void nao_deve_aceitar_logradou_null() {
		endereco.setLogradouro(null);
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O logradouro não pode ser nulo.", mensagensRestricoes.toString());
	}
	
	@Test
	void nao_deve_aceitar_logradou_vazio_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_logradouro_vazio");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas Rua, Avenida ou Praça para Logradouro.", mensagensRestricoes.toString());
	}
    
    @Test
    void nao_deve_aceitar_logradou_vazio() {
        endereco.setLogradouro("");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas Rua, Avenida ou Praça para Logradouro.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_logradou_diferente_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_logradouro_diferente");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas Rua, Avenida ou Praça para Logradouro.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_logradou_diferente() {
        endereco.setLogradouro("Estrada");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas Rua, Avenida ou Praça para Logradouro.", mensagensRestricoes.toString());
    }
    
    @Test
    void deve_retornar_endereco_logradouro_valido_construtor() {
        endereco.setEnderecoLogradouro("xv de novembro");
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
        assertEquals("xv de novembro", endereco.getEnderecoLogradouro());
    }
	
	@Test
	void deve_retornar_endereco_logradouro_valido() {
		endereco.setEnderecoLogradouro("xv de novembro");
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
		assertEquals("xv de novembro", endereco.getEnderecoLogradouro());
	}
    
    @Test
    void nao_deve_aceitar_endereco_logradou_null_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_endereco_logradouro_null");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O Endereço logradouro não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_endereco_logradou_null() {
        endereco.setEnderecoLogradouro(null);
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O Endereço logradouro não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_endereco_logradou_vazio_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_endereco_logradouro_vazio");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas letras e numeros para endereço logradouro.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_endereco_logradou_vazio() {
        endereco.setEnderecoLogradouro("");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas letras e numeros para endereço logradouro.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_endereco_logradou_diferente_letra_num_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_endereco_logradouro_diferente_letra_num");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas letras e numeros para endereço logradouro.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_endereco_logradou_diferente_letra_num() {
        endereco.setLogradouro("@#$");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas Rua, Avenida ou Praça para Logradouro.", mensagensRestricoes.toString());
    }
	
	@Test
	void deve_retornar_numero_valido_construtor() {
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
		assertThat(endereco.getNumero(), instanceOf(Integer.class));
	}
	
	@Test
	void deve_retornar_numero_valido() {
		endereco.setNumero(281);
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
		assertEquals(281, endereco.getNumero());
	}
	
	@Test
	void nao_deve_aceitar_numero_menor_que_zero_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_numero_menor_que_zero");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O número não pode ser negativo.", mensagensRestricoes.toString());
	}
	
	@Test
	void nao_deve_aceitar_numero_menor_que_zero() {
        endereco.setNumero(-5);
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O número não pode ser negativo.", mensagensRestricoes.toString());
	}
	
	@Test
	void nao_deve_aceitar_numero_null_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_numero_null");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O número não pode ser nulo.", mensagensRestricoes.toString());
    }
	
	@Test
	void nao_deve_aceitar_numero_null() {
        endereco.setNumero(null);
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O número não pode ser nulo.", mensagensRestricoes.toString());
	}
    
    @Test
    void deve_retornar_bairro_valido_construtor() {
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
        assertEquals("Centro", endereco.getBairro());
    }
	
	@Test
	void deve_retornar_bairro_valido() {
		endereco.setBairro("Guaianazes");
		restricoes = validator.validate(endereco);
		assertTrue(restricoes.isEmpty());
		assertEquals("Guaianazes", endereco.getBairro());
	}
	
	@Test
	void nao_deve_aceitar_bairro_vazio_construtor() {
		endereco = Fixture.from(Endereco.class).gimme("endereco_bairro_vazio");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas letras para bairro.", mensagensRestricoes.toString());
	}
    
    @Test
    void nao_deve_aceitar_bairro_vazio() {
        endereco.setBairro("");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas letras para bairro.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_bairro_null_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_bairro_null");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O bairro não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_bairro_null() {
        endereco.setBairro(null);
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O bairro não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void deve_retornar_cidade_valida_construtor() {
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
        assertEquals("Sao Paulo", endereco.getCidade());
    }
    
    @Test
    void deve_retornar_cidade_valida() {
        endereco.setCidade("Araraquara");
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
        assertEquals("Araraquara", endereco.getCidade());
    }
    
    @Test
    void nao_deve_aceitar_cidade_vazia_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_cidade_vazia");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas letras para cidade.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_cidade_vazia() {
        endereco.setCidade("");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas letras para cidade.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_cidade_null_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_cidade_null");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("A cidade não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_cidade_null() {
        endereco.setCidade(null);
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("A cidade não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void deve_retornar_estado_valido_construtor() {
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
        assertEquals("SP", endereco.getEstado());
    }
    
    @Test
    void deve_retornar_estado_valido() {
        endereco.setEstado("MG");
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
        assertEquals("MG", endereco.getEstado());
    }
    
    @Test
    void nao_deve_aceitar_estado_valor_diferente_sigla_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_estado_valor_diferente_sigla");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas sigla para estado no formato \"XX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_estado_valor_diferente_sigla() {
        endereco.setEstado("Minas Gerais");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas sigla para estado no formato \"XX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_estado_vazio_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_estado_vazio");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas sigla para estado no formato \"XX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_estado_vazio() {
        endereco.setEstado("");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas sigla para estado no formato \"XX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_estado_null_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_estado_null");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O estado não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_estado_null() {
        endereco.setEstado(null);
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O estado não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void deve_retornar_pais_valido_construtor() {
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
        assertEquals("Brasil", endereco.getPais());
    }
    
    @Test
    void deve_retornar_pais_valido() {
        endereco.setPais("Guatemala");
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
        assertEquals("Guatemala", endereco.getPais());
    }
    
    @Test
    void nao_deve_aceitar_pais_vazio_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_pais_vazio");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas letras para pais.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_pais_vazio() {
        endereco.setPais("");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas letras para pais.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_pais_null_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_pais_null");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O pais não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_pais_null() {
        endereco.setPais(null);
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O pais não pode ser nulo.", mensagensRestricoes.toString());
    }

    @Test
    void deve_retornar_cep_valido_construtor() {
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
        assertEquals("11111-111", endereco.getCep());
    }

    @Test
    void deve_retornar_cep_valido() {
        endereco.setCep("22222-222");
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
        assertEquals("22222-222", endereco.getCep());
    }
    
    @Test
    void nao_deve_aceitar_cep_diferente_formato_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_cep_diferente_formato");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O CEP deve ter o formato \"XXXXX-XXX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_cep_diferente_formato() {
        endereco.setCep("11111111");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O CEP deve ter o formato \"XXXXX-XXX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_cep_vazio_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_cep_vazio");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O CEP deve ter o formato \"XXXXX-XXX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_cep_vazio() {
        endereco.setCep("");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O CEP deve ter o formato \"XXXXX-XXX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_cep_null_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_cep_null");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O cep não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_cep_null() {
        endereco.setCep(null);
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O cep não pode ser nulo.", mensagensRestricoes.toString());
    }

    @Test
    void deve_retornar_tipo_endereco_valido_construtor() {
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
        assertThat(endereco.getTipoEndereco(), instanceOf(TipoEndereco.class));
    }

    @Test
    void deve_retornar_tipo_endereco_valido() {
        endereco.setTipoEndereco(TipoEndereco.COMERCIAL);
        restricoes = validator.validate(endereco);
        assertTrue(restricoes.isEmpty());
        assertEquals(TipoEndereco.COMERCIAL, endereco.getTipoEndereco());
    }
    
    @Test
    void nao_deve_aceitar_tipo_endereco_null_construtor() {
        endereco = Fixture.from(Endereco.class).gimme("endereco_tipo_endereco_null");
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O tipo de endereço não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_tipo_endereco_null() {
        endereco.setTipoEndereco(null);
        restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O tipo de endereço não pode ser nulo.", mensagensRestricoes.toString());
    }

    @Test
    void deve_retornar_to_string_valido() {
        assertTrue(endereco.toString().contains("id"));
        assertTrue(endereco.toString().contains("logradouro"));
        assertTrue(endereco.toString().contains("enderecoLogradouro"));
        assertTrue(endereco.toString().contains("numero"));
        assertTrue(endereco.toString().contains("bairro"));
        assertTrue(endereco.toString().contains("cidade"));
        assertTrue(endereco.toString().contains("estado"));
        assertTrue(endereco.toString().contains("pais"));
        assertTrue(endereco.toString().contains("cep"));
        assertTrue(endereco.toString().contains("tipoEndereco"));
    }

    @Test
    void deve_retornar_hash_code_valido() {
        Endereco enderecoAux = Fixture.from(Endereco.class).gimme("endereco_valido");
        enderecoAux.setLogradouro("Avenida");
        assertEquals(enderecoAux.hashCode(), endereco.hashCode());
    }

    @Test
    void deve_retornar_true_para_funcionario_igual() {
        Endereco enderecoAux = Fixture.from(Endereco.class).gimme("endereco_valido");
        enderecoAux.setNumero(endereco.getNumero());
        enderecoAux.setPais(endereco.getPais());
        enderecoAux.setCep(endereco.getCep());
        boolean igual = endereco.equals(enderecoAux);
        assertTrue(igual);
    }
}
