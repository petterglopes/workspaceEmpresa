package br.com.empresa.entities;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

class EmpresaTest {

    private Empresa empresa;
    private Set<ConstraintViolation<Empresa>> restricoes;
    private StringBuilder mensagensRestricoes;
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @BeforeAll
    static void inicio_dos_testes() {
        System.out.println("\n\n\nOs testes da classe Empresa foram iniciados.");
        FixtureFactoryLoader.loadTemplates("br.com.empresa.template");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Esse teste foi iniciado");
        empresa = Fixture.from(Empresa.class).gimme("empresa_valida");
        mensagensRestricoes = new StringBuilder();
    }

    @AfterEach
    void apos_cada_teste() {
        System.out.println("Esse teste foi concluidado");
    }

    @AfterAll
    static void fim_dos_testes() {
        System.out.println("Os testes da classe Empresa foram concluidados.\n\n\n");
    }

    @Test
    void deve_renornar_id_null() {
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        assertEquals(null, empresa.getId());
    }

    @Test
    void deve_restringir_set_id() {
        empresa.setId(1l);
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Esse campo só pode ser atribuido pela api do banco de dados", mensagensRestricoes.toString());
    }

    @Test
    void deve_retornar_nome_valido_construtor() {
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        assertEquals("Empresa", empresa.getNome());
    }

    @Test
    void deve_retornar_nome_valido() {
        empresa.setNome("EmpresaTeste");
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        assertEquals("EmpresaTeste", empresa.getNome());
    }

    @Test
    void nao_deve_aceitar_nome_null_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_nome_null");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O nome não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_nome_null() {
        empresa.setNome(null);
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O nome não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_caractere_numerico_no_nome_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_nome_caractere_numerico");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Nome não deve conter caractere numérico.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_caractere_numerico_no_nome() {
        empresa.setNome("Empresa1");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Nome não deve conter caractere numérico.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_nome_com_menos_de_3_caracteres_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_nome_menos_de_3_caracteres");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Nome não pode ter menos que 3 ou mais que 100 caracteres.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_nome_com_menos_de_3_caracteres() {
        empresa.setNome("Em");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Nome não pode ter menos que 3 ou mais que 100 caracteres.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_nome_com_mais_de_100_caracteres_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_nome_mais_de_100_caracteres");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Nome não pode ter menos que 3 ou mais que 100 caracteres.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_nome_com_mais_de_100_caracteres() {
        StringBuilder nome = new StringBuilder();
        while (nome.length() < 101) {
            nome.append("abcd");
        }
        empresa.setNome(nome.toString());
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Nome não pode ter menos que 3 ou mais que 100 caracteres.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_nome_vazio_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_nome_vazio");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(2, restricoes.size());
        assertTrue(mensagensRestricoes.toString().contains("Nome não pode ter menos que 3 ou mais que 100 caracteres."));
        assertTrue(mensagensRestricoes.toString().contains("O nome não pode ser vazio nem nulo."));
    }

    @Test
    void nao_deve_aceitar_nome_vazio() {
        empresa.setNome("");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(2, restricoes.size());
        assertTrue(mensagensRestricoes.toString().contains("Nome não pode ter menos que 3 ou mais que 100 caracteres."));
        assertTrue(mensagensRestricoes.toString().contains("O nome não pode ser vazio nem nulo."));
    }

    @Test
    void deve_retornar_cnpj_valido_construtor() {
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        assertEquals(14, empresa.getCnpj().length());
    }

    @Test
    void deve_retornar_cnpj_valido() {
        empresa.setCnpj("48745264000109");
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        assertEquals(14, empresa.getCnpj().length());
    }

    @Test
    void deve_aceitar_caracteres_especiais_para_cnpj_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_cnpj_caracteres_especiais");
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        assertEquals(14, empresa.getCnpj().replaceAll("[^0-9]", "").length());
    }

    @Test
    void deve_aceitar_caracteres_especiais_para_cnpj() {
        empresa.setCnpj("48.745.264/0001-09");
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        assertEquals(14, empresa.getCnpj().replaceAll("[^0-9]", "").length());
    }

    @Test
    void nao_deve_aceitar_cnpj_invalido_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_cnpj_invalido");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("CNPJ inválido.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cnpj_invalido() {
        empresa.setCnpj("48745264000105");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("CNPJ inválido.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cnpj_null_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_cnpj_null");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O CNPJ não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cnpj_null() {
        empresa.setCnpj(null);
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O CNPJ não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cnpj_vazio_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_cnpj_vazio");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O CNPJ não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cnpj_vazio() {
        empresa.setCnpj("");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O CNPJ não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cnpj_com_quantidade_caracteres_maior_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_cnpj_quantidade_maior");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("CNPJ inválido.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cnpj_com_quantidade_caracteres_maior() {
        empresa.setCnpj("48.745.264/0001-091");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("CNPJ inválido.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cnpj_com_quantidade_caracteres_menor_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_cnpj_quantidade_menor");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("CNPJ inválido.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cnpj_com_quantidade_caracteres_menor() {
        empresa.setCnpj("48.745.264/0001-0");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("CNPJ inválido.", mensagensRestricoes.toString());
    }

    @Test
    void deve_retornar_representante_legal_valido_construtor() {
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        assertThat(empresa.getRepresentanteLegal(), instanceOf(Funcionario.class));
    }

    @Test
    void deve_retornar_representante_legal_valido() {
        Funcionario funcionarioAux = Fixture.from(Funcionario.class).gimme("funcionario_valido");
        empresa.setRepresentanteLegal(funcionarioAux);
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        assertThat(empresa.getRepresentanteLegal(), instanceOf(Funcionario.class));
    }

    @Test
    void nao_deve_aceitar_representante_legal_invalido_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_representante_legal_invalido");
        restricoes = validator.validate(empresa);
        assertThat(restricoes, hasSize(6));
        for(ConstraintViolation<Empresa> restricao : restricoes) {
            assertTrue(restricao.toString().contains("propertyPath=representanteLegal"));
        }

    }

    @Test
    void nao_deve_aceitar_representante_legal_invalido() {
        empresa.setRepresentanteLegal(new Funcionario());
        restricoes = validator.validate(empresa);
        assertThat(restricoes, hasSize(6));
        for(ConstraintViolation<Empresa> restricao : restricoes) {
            assertTrue(restricao.toString().contains("propertyPath=representanteLegal"));
        }

    }

    @Test
    void nao_deve_aceitar_representante_legal_null_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_representante_legal_null");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O Representante Legal não pode ser nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_representante_legal_null() {
        empresa.setRepresentanteLegal(null);
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O Representante Legal não pode ser nulo.", mensagensRestricoes.toString());
    }

    @Test
    void deve_retornar_telefones_valido_construtor() {
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        for(Telefone telefone : empresa.getTelefones()) {
            assertThat(telefone, instanceOf(Telefone.class));
        }
    }

    @Test
    void deve_retornar_telefones_valido() {
        empresa.setTelefones(Fixture.from(Telefone.class).gimme("telefone_valido_comercial"));
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        for(Telefone telefone : empresa.getTelefones()) {
            assertThat(telefone, instanceOf(Telefone.class));
        }
    }

    @Test
    void deve_aceitar_telefones_tipo_diferente() {
        assertDoesNotThrow(() -> empresa.setTelefones(Fixture.from(Telefone.class).gimme("telefone_valido_comercial")));
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        for(Telefone telefone : empresa.getTelefones()) {
            assertThat(telefone, instanceOf(Telefone.class));
        }
    }

    @Test
    void nao_deve_aceitar_telefones_tipo_iguais() {
        Telefone telefone = Fixture.from(Telefone.class).gimme("telefone_valido_residencial");
        assertThrows(IllegalArgumentException.class,
            () -> empresa.setTelefones(telefone));
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
    }

    @Test
    void nao_deve_aceitar_telefones_null_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_telefones_null");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("A lista de telefones não pode estar vazia.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_telefones_null() {
        Exception exception = assertThrows(NullPointerException.class, () -> empresa.setTelefones(null));
        assertEquals("O telefone não pode ser nulo.", exception.getMessage());
    }

    @Test
    void deve_retornar_enderecos_valido_construtor() {
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        for(Endereco endereco : empresa.getEnderecos())
            assertThat(endereco, instanceOf(Endereco.class));
    }

    @Test
    void deve_retornar_enderecos_validos() {
        empresa.setEnderecos(Fixture.from(Endereco.class).gimme("endereco_valido_comercial"));
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        for(Endereco endereco : empresa.getEnderecos())
            assertThat(endereco, instanceOf(Endereco.class));
    }

    @Test
    void deve_aceitar_enderecos_tipo_diferente() {
        assertDoesNotThrow(() -> empresa.setEnderecos(Fixture.from(Endereco.class).gimme("endereco_valido_comercial")));
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        for(Endereco endereco : empresa.getEnderecos()) {
            assertThat(endereco, instanceOf(Endereco.class));
        }
    }

    @Test
    void nao_deve_aceitar_enderecos_tipo_iguais() {
        Endereco endereco = Fixture.from(Endereco.class).gimme("endereco_valido_residencial");
        assertThrows(IllegalArgumentException.class, () -> empresa.setEnderecos(endereco));
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
    }

    @Test
    void nao_deve_aceitar_enderecos_null_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_enderecos_null");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("A lista de endereços não pode estar vazia.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_enderecos_null() {
        Exception exception = assertThrows(NullPointerException.class, () -> empresa.setEnderecos(null));
        assertEquals("O endereço não pode ser nulo.", exception.getMessage());
    }

    @Test
    void deve_retornar_data_valida_construtor() {
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        assertThat(empresa.getDataAbertura(), instanceOf(LocalDate.class));
    }

    @Test
    void deve_retornar_data_valida() {
        empresa.setDataAbertura(LocalDate.now());
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        assertThat(empresa.getDataAbertura(), instanceOf(LocalDate.class));
    }

    @Test
    void nao_deve_aceitar_data_null_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_dataAbertura_null");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("A data de abertura não pode ser nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_data_null() {
        empresa.setDataAbertura(null);
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("A data de abertura não pode ser nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_data_no_futuro_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_dataAbertura_futuro");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("A data de abertura não pode estar no futuro", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_data_no_futuro() {
        empresa.setDataAbertura(LocalDate.now().plusDays(1));
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("A data de abertura não pode estar no futuro", mensagensRestricoes.toString());
    }

    void deve_retornar_site_valido_construtor() {
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        assertEquals("https://contmatic.com.br/", empresa.getSite());
    }

    void deve_retornar_site_valido() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_valida");
        empresa.setSite("https://contmatic.com.br/");
        restricoes = validator.validate(empresa);
        assertTrue(restricoes.isEmpty());
        assertEquals("https://contmatic.com.br/", empresa.getSite());
    }

    @Test
    void nao_deve_aceitar_site_null_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_site_null");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O site não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_site_null() {
        empresa.setSite(null);
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O site não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_site_vazio_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_site_vazio");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O site não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_site_vazio() {
        empresa.setSite("");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O site não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_texto_incompativel_com_site_construtor() {
        empresa = Fixture.from(Empresa.class).gimme("empresa_site_texto_compativel");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O texte informado não é um site.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_texto_incompativel_com_site() {
        empresa.setSite("contmatic");
        restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O texte informado não é um site.", mensagensRestricoes.toString());
    }

    @Test
    void deve_retornar_to_string_valido() {
        assertTrue(empresa.toString().contains("id"));
        assertTrue(empresa.toString().contains("nome"));
        assertTrue(empresa.toString().contains("cnpj"));
        assertTrue(empresa.toString().contains("representanteLegal"));
        assertTrue(empresa.toString().contains("telefones"));
        assertTrue(empresa.toString().contains("enderecos"));
        assertTrue(empresa.toString().contains("dataAbertura"));
        assertTrue(empresa.toString().contains("site"));
    }

    @Test
    void deve_retornar_hash_code_valido() {
        Empresa empresaAux = Fixture.from(Empresa.class).gimme("empresa_valida");
        empresaAux.setCnpj(empresa.getCnpj());
        assertEquals(empresaAux.hashCode(), empresa.hashCode());
    }

    @Test
    void deve_retornar_true_para_empresa_igual() {
        Empresa empresaAux = Fixture.from(Empresa.class).gimme("empresa_valida");
        empresaAux.setCnpj(empresa.getCnpj());
        boolean igual = empresa.equals(empresaAux);
        assertTrue(igual);
    }
}
