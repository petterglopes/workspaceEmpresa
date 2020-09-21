package br.com.empresa.entities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class FuncionarioTest {

    Funcionario funcionario;
    private Set<ConstraintViolation<Funcionario>> restricoes;
    private StringBuilder mensagensRestricoes;
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @BeforeAll
    static void inicio_dos_testes() {
        System.out.println("Os testes da classe Funcionário foram iniciados");
        FixtureFactoryLoader.loadTemplates("br.com.empresa.template");
    }

    @BeforeEach
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void antes_de_cada_teste() {
        System.out.println("Esse teste foi iniciado");
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_valido");
        mensagensRestricoes = new StringBuilder();
    }

    @AfterEach
    void apos_cada_teste() {
        System.out.println("Esse teste foi concluidado");
    }

    @AfterAll
    static void fim_dos_testes() {
        System.out.println("Os testes da classe Funcionário foram concluidados");
    }

    @Test
    void deve_renornar_id_null() {
        restricoes = validator.validate(funcionario);
        assertTrue(restricoes.isEmpty());
        assertEquals(null, funcionario.getId());
    }

    @Test
    void deve_restringir_set_id() {
        funcionario.setId(1l);
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Esse campo só pode ser atribuido pela api do banco de dados", mensagensRestricoes.toString());
    }

    @Test
    @Order(3)
    void deve_retornar_nome_valido_construtor() {
        restricoes = validator.validate(funcionario);
        assertTrue(restricoes.isEmpty());
        assertEquals("Antonio", funcionario.getNome());
    }

    @Test
    @Order(2)
    void deve_retornar_nome_valido() {
        funcionario.setNome("Joao");
        restricoes = validator.validate(funcionario);
        assertTrue(restricoes.isEmpty());
        assertEquals("Joao", funcionario.getNome());
    }

    @Test
    @Order(1)
    void nao_deve_aceitar_nome_null_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_nome_null");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O nome não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_nome_null() {
        funcionario.setNome(null);
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O nome não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_caractere_numerico_no_nome_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_nome_caractere_numerico");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Nome não deve conter caractere numérico.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_caractere_numerico_no_nome() {
        funcionario.setNome("Funcionario1");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Nome não deve conter caractere numérico.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_nome_com_menos_de_3_caracteres_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_nome_menos_de_3_caracteres");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Nome não pode ter menos que 3 ou mais que 100 caracteres.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_nome_com_menos_de_3_caracteres() {
        funcionario.setNome("Em");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Nome não pode ter menos que 3 ou mais que 100 caracteres.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_nome_com_mais_de_100_caracteres_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_nome_mais_de_100_caracteres");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
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
        funcionario.setNome(nome.toString());
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("Nome não pode ter menos que 3 ou mais que 100 caracteres.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_nome_vazio_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_nome_vazio");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(2, restricoes.size());
        assertTrue(mensagensRestricoes.toString().contains("Nome não pode ter menos que 3 ou mais que 100 caracteres."));
        assertTrue(mensagensRestricoes.toString().contains("O nome não pode ser vazio nem nulo."));
    }

    @Test
    void nao_deve_aceitar_nome_vazio() {
        funcionario.setNome("");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(2, restricoes.size());
        assertTrue(mensagensRestricoes.toString().contains("Nome não pode ter menos que 3 ou mais que 100 caracteres."));
        assertTrue(mensagensRestricoes.toString().contains("O nome não pode ser vazio nem nulo."));
    }

    @Test
    void deve_retornar_cpf_valido() {
        funcionario.setCpf("11164291602");
        restricoes = validator.validate(funcionario);
        assertTrue(restricoes.isEmpty());
        assertEquals(11, funcionario.getCpf().length());
    }

    @Test
    void deve_aceitar_caracteres_especiais_para_cpf_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_cpf_caracteres_especiais");
        restricoes = validator.validate(funcionario);
        assertTrue(restricoes.isEmpty());
        assertEquals(11, funcionario.getCpf().replaceAll("[^0-9]", "").length());
    }

    @Test
    void deve_aceitar_caracteres_especiais_para_cpf() {
        funcionario.setCpf("111.642.916-02");
        restricoes = validator.validate(funcionario);
        assertTrue(restricoes.isEmpty());
        assertEquals(11, funcionario.getCpf().replaceAll("[^0-9]", "").length());
    }

    @Test
    void nao_deve_aceitar_cpf_null_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_cpf_null");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O CPF não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cpf_invalido_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_cpf_invalido");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("CPF inválido", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cpf_invalido() {
        funcionario.setCpf("111.642.916-03");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("CPF inválido", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cpf_null() {
        funcionario.setCpf(null);
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O CPF não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cpf_vazio_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_cpf_vazio");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O CPF não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cpf_vazio() {
        funcionario.setCpf("");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O CPF não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cpf_com_quantidade_caracteres_maior_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_cpf_quantidade_maior");
        restricoes = validator.validate(funcionario);

        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("CPF inválido", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cpf_com_quantidade_caracteres_maior() {
        funcionario.setCpf("111.642.916-021");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("CPF inválido", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cpf_com_quantidade_caracteres_menor_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_cpf_quantidade_menor");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("CPF inválido", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_cpf_com_quantidade_caracteres_menor() {
        funcionario.setCpf("111.642.916.0");
        restricoes = validator.validate(funcionario);

        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("CPF inválido", mensagensRestricoes.toString());
    }

    @Test
    void deve_retornar_telefones_valido_construtor() {
        restricoes = validator.validate(funcionario);
        assertTrue(restricoes.isEmpty());
        for(Telefone telefone : funcionario.getTelefones()) {
            assertThat(telefone, instanceOf(Telefone.class));
        }
    }

    @Test
    void deve_retornar_telefones_valido() {
        Set<Telefone> telefones = new HashSet<Telefone>();
        telefones.add(Fixture.from(Telefone.class).gimme("telefone_valido"));
        funcionario.setTelefones(telefones);
        restricoes = validator.validate(funcionario);
        assertTrue(restricoes.isEmpty());
        for(Telefone telefone : funcionario.getTelefones()) {
            assertThat(telefone, instanceOf(Telefone.class));
        }
    }

    @Test
    void deve_aceitar_telefones_tipo_diferente() {
        Set<Telefone> telefones = new HashSet<Telefone>();
        telefones.add(Fixture.from(Telefone.class).gimme("telefone_valido_residencial"));
        telefones.add(Fixture.from(Telefone.class).gimme("telefone_valido_comercial"));
        assertDoesNotThrow(() -> funcionario.setTelefones(telefones));
        restricoes = validator.validate(funcionario);
        assertTrue(restricoes.isEmpty());
        for(Telefone telefone : funcionario.getTelefones()) {
            assertThat(telefone, instanceOf(Telefone.class));
        }
    }

    @Test
    void nao_deve_aceitar_telefones_tipo_iguais() {
        Set<Telefone> telefones = new HashSet<Telefone>();
        telefones.add(Fixture.from(Telefone.class).gimme("telefone_valido_comercial"));
        telefones.add(Fixture.from(Telefone.class).gimme("telefone_valido_comercial"));
        assertThrows(IllegalArgumentException.class, () -> funcionario.setTelefones(telefones));
    }

    @Test
    void nao_deve_aceitar_telefones_null_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_telefones_null");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("A lista de telefones não pode estar vazia.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_telefones_null() {
        Exception exception = assertThrows(NullPointerException.class, () -> funcionario.setTelefones(null));
        assertEquals("A lista de telefones não pode estar nula.", exception.getMessage());
    }

    @Test
    void deve_retornar_enderecos_valido_construtor() {
        restricoes = validator.validate(funcionario);
        assertTrue(restricoes.isEmpty());
        for(Endereco endereco : funcionario.getEnderecos())
            assertThat(endereco, instanceOf(Endereco.class));
    }

    @Test
    void deve_retornar_enderecos_validos() {
        Set<Endereco> enderecos = new HashSet<Endereco>();
        enderecos.add(Fixture.from(Endereco.class).gimme("endereco_valido"));
        funcionario.setEnderecos(enderecos);
        restricoes = validator.validate(funcionario);
        assertTrue(restricoes.isEmpty());
        for(Endereco endereco : funcionario.getEnderecos())
            assertThat(endereco, instanceOf(Endereco.class));
    }

    @Test
    void deve_aceitar_enderecos_tipo_diferente() {
        Set<Endereco> enderecos = new HashSet<Endereco>();
        enderecos.add(Fixture.from(Endereco.class).gimme("endereco_valido_residencial"));
        enderecos.add(Fixture.from(Endereco.class).gimme("endereco_valido_comercial"));
        assertDoesNotThrow(() -> funcionario.setEnderecos(enderecos));
        restricoes = validator.validate(funcionario);
        assertTrue(restricoes.isEmpty());
        for(Endereco endereco : funcionario.getEnderecos()) {
            assertThat(endereco, instanceOf(Endereco.class));
        }
    }

    @Test
    void nao_deve_aceitar_enderecos_tipo_iguais() {
        Set<Endereco> enderecos = new HashSet<Endereco>();
        Endereco enderecoAux = Fixture.from(Endereco.class).gimme("endereco_valido_comercial");
        enderecos.add(enderecoAux);
        enderecoAux.setNumero(2000);
        enderecos.add(enderecoAux);
        assertThrows(IllegalArgumentException.class, () -> funcionario.setEnderecos(enderecos));
    }

    @Test
    void nao_deve_aceitar_enderecos_null_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_enderecos_null");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("A lista de endereços não pode estar vazia.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_enderecos_null() {
        Exception exception = assertThrows(NullPointerException.class, () -> funcionario.setEnderecos(null));
        assertEquals("A lista de endereços não pode estar nula.", exception.getMessage());
    }

    @Test
    void deve_retornar_email_valido_construtor() {
        restricoes = validator.validate(funcionario);
        assertTrue(restricoes.isEmpty());
        assertEquals("asdakjm@asdkjs.com", funcionario.getEmail());
    }

    @Test
    void deve_retornar_email_valido() {
        funcionario.setNome("asdakjm@asdkjs.com");
        restricoes = validator.validate(funcionario);
        assertTrue(restricoes.isEmpty());
        assertEquals("asdakjm@asdkjs.com", funcionario.getEmail());
    }
    
    @Test
    void nao_deve_aceitar_email_invalido_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_email_invalido");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O texto deve ter formato de e-mail.", mensagensRestricoes.toString());
    }
    
    @Test
    void nao_deve_aceitar_email_invalido() {
        funcionario.setEmail("askldj.asdf");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O texto deve ter formato de e-mail.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_email_null_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_email_null");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O email não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_email_null() {
        funcionario.setEmail(null);
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertEquals("O email não pode ser vazio nem nulo.", mensagensRestricoes.toString());
    }

    @Test
    void nao_deve_aceitar_email_vazio_construtor() {
        funcionario = Fixture.from(Funcionario.class).gimme("funcionario_email_vazio");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertTrue(mensagensRestricoes.toString().contains("O email não pode ser vazio nem nulo."));
    }

    @Test
    void nao_deve_aceitar_email_vazio() {
        funcionario.setEmail("");
        restricoes = validator.validate(funcionario);
        for(ConstraintViolation<Funcionario> constraint : restricoes) {
            mensagensRestricoes.append(constraint.getMessage());
        }
        assertEquals(1, restricoes.size());
        assertTrue(mensagensRestricoes.toString().contains("O email não pode ser vazio nem nulo."));
    }

    @Test
    void deve_retornar_to_string_valido() {
        assertTrue(funcionario.toString().contains("id"));
        assertTrue(funcionario.toString().contains("nome"));
        assertTrue(funcionario.toString().contains("cpf"));
        assertTrue(funcionario.toString().contains("salario"));
        assertTrue(funcionario.toString().contains("telefones"));
        assertTrue(funcionario.toString().contains("enderecos"));
        assertTrue(funcionario.toString().contains("email"));
    }

    @Test
    void deve_retornar_hash_code_valido() {
        Funcionario funcionarioAux = Fixture.from(Funcionario.class).gimme("funcionario_valido");
        funcionarioAux.setCpf(funcionario.getCpf());
        assertEquals(funcionarioAux.hashCode(), funcionario.hashCode());
    }

    @Test
    void deve_retornar_true_para_funcionario_igual() {
        Funcionario funcionarioAux = Fixture.from(Funcionario.class).gimme("funcionario_valido");
        funcionarioAux.setCpf(funcionario.getCpf());
        boolean igual = funcionario.equals(funcionarioAux);
        assertTrue(igual);
    }
}
