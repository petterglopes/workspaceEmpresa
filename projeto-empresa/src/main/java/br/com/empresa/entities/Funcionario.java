package br.com.empresa.entities;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import br.com.caelum.stella.bean.validation.CPF;

/**
 * The Class Funcionario.
 */
public class Funcionario {

    /** The Constant MAXIMO_CARACTERES_NOME. */
    private static final int MAXIMO_CARACTERES_NOME = 100;

    /** The Constant MINIMO_CARACTERES_NOME. */
    private static final int MINIMO_CARACTERES_NOME = 3;

    /** The Constant MINIMO_SALARIO. */
    private static final int MINIMO_SALARIO = 0;

    /** The Constant MAXIMO_QUANTIDADE_TELEFONE. */
    private static final int MAXIMO_QUANTIDADE_TELEFONE = 2;

    /** The Constant MINIMO_QUANTIDADE_TELEFONE. */
    private static final int MINIMO_QUANTIDADE_TELEFONE = 1;

    /** The Constant MAXIMO_QUANTIDADE_ENDERECO. */
    private static final int MAXIMO_QUANTIDADE_ENDERECO = 2;

    /** The Constant MINIMO_QUANTIDADE_ENDERECO. */
    private static final int MINIMO_QUANTIDADE_ENDERECO = 1;

    /** The id. */
    @EqualsExclude
    @HashCodeExclude
    @Null(message = "Esse campo só pode ser atribuido pela api do banco de dados")
    private Long id;

    /** The nome. */
    @EqualsExclude
    @HashCodeExclude
    @NotBlank(message = "O nome não pode ser vazio nem nulo.")
    @Length(min = MINIMO_CARACTERES_NOME, max = MAXIMO_CARACTERES_NOME, message = "Nome não pode ter menos que 3 ou mais que 100 caracteres.")
    @Pattern(regexp = "\\D*", message = "Nome não deve conter caractere numérico.")
    private String nome;

    /** The cpf. */
    @CPF(message = "CPF inválido")
    @NotBlank(message = "O CPF não pode ser vazio nem nulo.")
    private String cpf;

    /** The salario. */
    @EqualsExclude
    @HashCodeExclude
    @NotNull(message = "O salario não pode ser nulo.")
    @Min(value = MINIMO_SALARIO, message = "O salário não pode ser menor que zero.")
    private Long salario;

    /** The telefones. */
    @EqualsExclude
    @HashCodeExclude
    @Size(min = MINIMO_QUANTIDADE_TELEFONE, max = MAXIMO_QUANTIDADE_TELEFONE)
    @NotEmpty(message = "A lista de telefones não pode estar vazia.")
    private Set<Telefone> telefones;

    /** The enderecos. */
    @EqualsExclude
    @HashCodeExclude
    @Size(min = MINIMO_QUANTIDADE_ENDERECO, max = MAXIMO_QUANTIDADE_ENDERECO)
    @NotNull(message = "A lista de endereços não pode estar vazia.")
    private Set<Endereco> enderecos;

    /** The email. */
    @EqualsExclude
    @HashCodeExclude
    @Email(message = "O texto deve ter formato de e-mail.")
    @NotBlank(message = "O email não pode ser vazio nem nulo.")
    private String email;

    /**
     * Instantiates a new funcionario.
     */
    public Funcionario() {
    }

    /**
     * Instantiates a new funcionario.
     *
     * @param nome the nome
     * @param cpf the cpf
     * @param salario the salario
     * @param telefone the telefone
     * @param endereco the endereco
     * @param email the email
     */
    public Funcionario(String nome, String cpf, Long salario, Telefone telefone, Endereco endereco, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        setTelefones(telefone);
        setEnderecos(endereco);
        this.email = email;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the nome.
     *
     * @param nome the new nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the cpf.
     *
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Sets the cpf.
     *
     * @param cpf the new cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Gets the salario.
     *
     * @return the salario
     */
    public Long getSalario() {
        return salario;
    }

    /**
     * Sets the salario.
     *
     * @param salario the new salario
     */
    public void setSalario(Long salario) {
        this.salario = salario;
    }

    /**
     * Gets the telefones.
     *
     * @return the telefones
     */
    public Set<Telefone> getTelefones() {
        return telefones;
    }

    /**
     * Sets the telefones.
     *
     * @param telefone the new telefones
     */
    public void setTelefones(Telefone telefone) {
        checkNotNull(telefone, "O telefone não pode ser nulo.");
        this.telefones.forEach(
            telefoneAux ->
                checkArgument(telefoneAux.getTipoTelefone() != telefone.getTipoTelefone(), 
                "Lista não pode conter telefones de mesmo tipo."));
        this.telefones.add(telefone);
    }

    /**
     * Gets the enderecos.
     *
     * @return the enderecos
     */
    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    /**
     * Sets the enderecos.
     *
     * @param endereco the new enderecos
     */
    public void setEnderecos(Endereco endereco) {
        checkNotNull(endereco, "O endereço não pode ser nulo.");
        this.enderecos.forEach(
            enderecoAux ->
                checkArgument(enderecoAux.getTipoEndereco() != endereco.getTipoEndereco(), 
                "Lista não pode conter endereços de mesmo tipo."));
        this.enderecos.add(endereco);
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * Equals.
     *
     * @param object the object
     * @return true, if successful
     */
    @Override
    public boolean equals(Object object) {
        return EqualsBuilder.reflectionEquals(this, object);
    }
}
