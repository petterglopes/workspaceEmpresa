package br.com.empresa.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.joda.time.LocalDate;

import static com.google.common.base.Preconditions.*;

import br.com.caelum.stella.bean.validation.CNPJ;

/**
 * The Class Empresa.
 */
public class Empresa {

    /** The id. */
    @EqualsExclude
    @HashCodeExclude
    @Null(message = "Esse campo só pode ser atribuido pela api do banco de dados")
    private Long id;

    /** The nome. */
    @EqualsExclude
    @HashCodeExclude
    @Pattern(regexp = "[^0-9]*", message = "Nome não deve conter caractere numérico.")
    @NotBlank(message = "O nome não pode ser vazio nem nulo.")
    @Length(min = 3, max = 100, message = "Nome não pode ter menos que 3 ou mais que 100 caracteres.")
    private String nome;

    /** The cnpj. */
    @NotBlank(message = "O CNPJ não pode ser vazio nem nulo.")
    @CNPJ(message = "CNPJ inválido.")
    private String cnpj;

    /** The representante legal. */
    @EqualsExclude
    @HashCodeExclude
    @Valid
    @NotNull(message = "O Representante Legal não pode ser nulo.")
    private Funcionario representanteLegal;

    /** The telefones. */
    @EqualsExclude
    @HashCodeExclude
    @Size(min = 1, max = 2)
    @NotEmpty(message = "A lista de telefones não pode estar vazia.")
    private Set<Telefone> telefones;

    /** The endereco. */
    @EqualsExclude
    @HashCodeExclude
    @Size(min = 1, max = 2)
    @NotEmpty(message = "A lista de endereços não pode estar vazia.")
    private Set<Endereco> enderecos;

    /** The data abertura. */
    @EqualsExclude
    @HashCodeExclude
    @NotNull(message = "A data de abertura não pode ser nulo.")
    @PastOrPresent(message = "A data de abertura não pode estar no futuro")
    private LocalDate dataAbertura;

    /** The site. */
    @EqualsExclude
    @HashCodeExclude
    @URL(message = "O texte informado não é um site.")
    @NotBlank(message = "O site não pode ser vazio nem nulo.")
    private String site;

    /**
     * Instantiates a new empresa.
     */
    public Empresa() {
    }

    /**
     * Instantiates a new empresa.
     *
     * @param nome the nome
     * @param cnpj the cnpj
     * @param representanteLegal the representante legal
     * @param telefones the telefones
     * @param enderecos the enderecos
     * @param dataAbertura the data abertura
     * @param site the site
     */
    public Empresa(String nome, String cnpj, Funcionario representanteLegal, Set<Telefone> telefones, Set<Endereco> enderecos, LocalDate dataAbertura, String site) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.representanteLegal = representanteLegal;
        setTelefones(telefones);
        setEnderecos(enderecos);
        this.dataAbertura = dataAbertura;
        this.site = site;
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
        return this.nome;
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
     * Gets the cnpj.
     *
     * @return the cnpj
     */
    public String getCnpj() {
        return this.cnpj;
    }

    /**
     * Sets the cnpj.
     *
     * @param cnpj the new cnpj
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Gets the representante legal.
     *
     * @return the representante legal
     */
    public Funcionario getRepresentanteLegal() {
        return this.representanteLegal;
    }

    /**
     * Sets the representante legal.
     *
     * @param representanteLegal the new representante legal
     */
    public void setRepresentanteLegal(Funcionario representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    /**
     * Gets the telefones.
     *
     * @return the telefones
     */
    public Set<Telefone> getTelefones() {
        return this.telefones;
    }

    /**
     * Sets the telefones.
     *
     * @param telefones the new telefones
     */
    public void setTelefones(Set<Telefone> telefones) {
        checkNotNull(telefones, "A lista de telefones não pode estar nula.");
        checkArgument(telefones.size() <= 2, "Lista não pode conter telefones de mesmo tipo.");
        List<Telefone> listaAux = new ArrayList<>();
        telefones.forEach(item -> listaAux.add(item));
        checkArgument(telefones.size() <= 1 || listaAux.get(0).getTipoTelefone() != listaAux.get(1).getTipoTelefone(), "Lista não pode conter telefones de mesmo tipo.");
        this.telefones = telefones;
    }

    /**
     * Gets the endereco.
     *
     * @return the endereco
     */
    public Set<Endereco> getEnderecos() {
        return this.enderecos;
    }

    /**
     * Sets the endereco.
     *
     * @param enderecos the new enderecos
     */
    public void setEnderecos(Set<Endereco> enderecos) {
        checkNotNull(enderecos, "A lista de endereços não pode estar nula.");
        checkArgument(enderecos.size() <= 2, "Lista não pode conter endereços de mesmo tipo.");
        List<Endereco> listaAux = new ArrayList<>();
        enderecos.forEach(endereco -> listaAux.add(endereco));
        checkArgument(enderecos.size() <= 1 || listaAux.get(0).getTipoEndereco() != listaAux.get(1).getTipoEndereco(), "Lista não pode conter endereços de mesmo tipo.");
        this.enderecos = enderecos;
    }

    /**
     * Gets the data abertura.
     *
     * @return the data abertura
     */
    public LocalDate getDataAbertura() {
        return this.dataAbertura;
    }

    /**
     * Sets the data abertura.
     *
     * @param dataAbertura the new data abertura
     */
    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    /**
     * Gets the site.
     *
     * @return the site
     */
    public String getSite() {
        return this.site;
    }

    /**
     * Sets the site.
     *
     * @param site the new site
     */
    public void setSite(String site) {
        this.site = site;
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
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
