package br.com.empresa.entities;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

class TelefoneTest {
	private Telefone telefone;
    private Set<ConstraintViolation<Telefone>> restricoes;
    private StringBuilder mensagensRestricoes;
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @BeforeAll
    static void inicio_dos_testes() {
        System.out.println("Os testes da classe Telefone foram iniciados");
        FixtureFactoryLoader.loadTemplates("br.com.empresa.template");
    }

    @BeforeEach
    void antes_de_cada_teste() {
        System.out.println("Esse teste foi iniciado");
        telefone = Fixture.from(Telefone.class).gimme("telefone_valido");
        mensagensRestricoes = new StringBuilder();
    }

    @AfterEach
    void apos_cada_teste() {
        System.out.println("Esse teste foi concluidado");
    }

    @AfterAll
    static void fim_dos_testes() {
        System.out.println("Os testes da classe Telefone foram concluidados");
    }

    @Test
    void deve_renornar_id_null() {
        restricoes = validator.validate(telefone);
        assertTrue(restricoes.isEmpty());
        assertEquals(null, telefone.getId());
    }

    @Test
    void deve_restringir_set_id() {
        telefone.setId(1l);
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Esse campo só pode ser atribuido pela api do banco de dados.", mensagensRestricoes.toString());
    }

    @Test
    void deve_retornar_ddd_valido_construtor() {
        restricoes = validator.validate(telefone);
        assertTrue(restricoes.isEmpty());
        assertTrue(telefone.getDdd().matches("\\d{2}"));;
    }

    @Test
    void deve_retornar_ddd_valido() {
        telefone.setDdd("11");
        restricoes = validator.validate(telefone);
        assertTrue(restricoes.isEmpty());
        assertEquals("11", telefone.getDdd());
    }
    
    @Test
    void nao_deve_aceitar_ddd_diferente_formato_construtor() {
        telefone = Fixture.from(Telefone.class).gimme("telefone_ddd_diferente_formato");
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O ddd deve ter o formato \"XX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_ddd_diferente_formato() {
        telefone.setDdd("111");
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O ddd deve ter o formato \"XX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_ddd_vazio_construtor() {
        telefone = Fixture.from(Telefone.class).gimme("telefone_ddd_vazio");
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O ddd deve ter o formato \"XX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_ddd_vazio() {
        telefone.setDdd("");
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O ddd deve ter o formato \"XX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_ddd_null_construtor() {
        telefone = Fixture.from(Telefone.class).gimme("telefone_ddd_null");
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O ddd não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_ddd_null() {
        telefone.setDdd(null);
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O ddd não pode ser nulo.", mensagensRestricoes.toString());
    }

    @Test
    void deve_retornar_numero_valido_construtor() {
        restricoes = validator.validate(telefone);
        assertTrue(restricoes.isEmpty());
        assertTrue(telefone.getNumero().matches("\\d{5}-\\d{4}"));;
    }

    @Test
    void deve_retornar_numero_valido() {
        telefone.setNumero("22222-2222");
        restricoes = validator.validate(telefone);
        assertTrue(restricoes.isEmpty());
        assertEquals("22222-2222", telefone.getNumero());
    }
    
    @Test
    void nao_deve_aceitar_numero_diferente_formato_construtor() {
        telefone = Fixture.from(Telefone.class).gimme("telefone_numero_diferente_formato");
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O número deve ter o fotmato \"XXXXX-XXXX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_numero_diferente_formato() {
        telefone.setNumero("111111111");
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O número deve ter o fotmato \"XXXXX-XXXX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_numero_vazio_construtor() {
        telefone = Fixture.from(Telefone.class).gimme("telefone_numero_vazio");
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O número deve ter o fotmato \"XXXXX-XXXX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_numero_vazio() {
        telefone.setNumero("");
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O número deve ter o fotmato \"XXXXX-XXXX\".", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_numero_null_construtor() {
        telefone = Fixture.from(Telefone.class).gimme("telefone_numero_null");
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O numero não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_numero_null() {
        telefone.setNumero(null);
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O numero não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void deve_retornar_operadora_valida_construtor() {
        restricoes = validator.validate(telefone);
        assertTrue(restricoes.isEmpty());
        assertTrue(telefone.getOperadora().matches("[a-zA-Z ]{1,}"));;
    }
    
    @Test
    void deve_retornar_operadora_valida() {
        telefone.setOperadora("Telefonica");
        restricoes = validator.validate(telefone);
        assertTrue(restricoes.isEmpty());
        assertEquals("Telefonica", telefone.getOperadora());
    }
    
    @Test
    void nao_deve_aceitar_operadora_vazio_construtor() {
        telefone = Fixture.from(Telefone.class).gimme("telefone_operadora_vazio");
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas letras para operadora.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_operadora_vazio() {
        telefone.setOperadora("");
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Permitido apenas letras para operadora.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_operadora_null_construtor() {
        telefone = Fixture.from(Telefone.class).gimme("telefone_operadora_null");
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("A operadora não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_operadora_null() {
        telefone.setOperadora(null);
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("A operadora não pode ser nulo.", mensagensRestricoes.toString());
    }

    @Test
    void deve_retornar_tipo_telefone_valido_construtor() {
        restricoes = validator.validate(telefone);
        assertTrue(restricoes.isEmpty());
        assertThat(telefone.getTipoTelefone(), instanceOf(TipoTelefone.class));
    }

    @Test
    void deve_retornar_tipo_telefone_valido() {
        telefone.setTipoTelefone(TipoTelefone.COMERCIAL);
        restricoes = validator.validate(telefone);
        assertTrue(restricoes.isEmpty());
        assertEquals(TipoTelefone.COMERCIAL, telefone.getTipoTelefone());
    }
    
    @Test
    void nao_deve_aceitar_tipo_telefone_null_construtor() {
        telefone = Fixture.from(Telefone.class).gimme("telefone_tipo_telefone_null");
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O tipo de telefone não pode ser nulo.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_tipo_telefone_null() {
        telefone.setTipoTelefone(null);
        restricoes = validator.validate(telefone);
        for(ConstraintViolation<Telefone> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O tipo de telefone não pode ser nulo.", mensagensRestricoes.toString());
    }

    @Test
    void deve_retornar_to_string_valido() {
        assertTrue(telefone.toString().contains("id"));
        assertTrue(telefone.toString().contains("ddd"));
        assertTrue(telefone.toString().contains("numero"));
        assertTrue(telefone.toString().contains("operadora"));
        assertTrue(telefone.toString().contains("tipoTelefone"));
    }

    @Test
    void deve_retornar_hash_code_valido() {
        Telefone telefoneAux = Fixture.from(Telefone.class).gimme("telefone_valido");
        telefoneAux.setDdd(telefone.getDdd());
        telefoneAux.setNumero(telefone.getNumero());
        assertEquals(telefoneAux.hashCode(), telefone.hashCode());
    }

    @Test
    void deve_retornar_true_para_funcionario_igual() {
        Telefone telefoneAux = Fixture.from(Telefone.class).gimme("telefone_valido");
        telefoneAux.setDdd(telefone.getDdd());
        telefoneAux.setNumero(telefone.getNumero());
        boolean igual = telefone.equals(telefoneAux);
        assertTrue(igual);
    }
}
