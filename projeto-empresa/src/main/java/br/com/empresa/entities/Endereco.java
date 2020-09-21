package br.com.empresa.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Range;

/**
 * The Class Endereco.
 */
public class Endereco {

    /** The id. */
    @EqualsExclude
    @HashCodeExclude
    @Null(message = "Esse campo só pode ser atribuido pela api do banco de dados.")
    private Long id;

    /** The logradouro. */
    @EqualsExclude
    @HashCodeExclude
    @NotNull(message = "O logradouro não pode ser nulo.")
    @Pattern(regexp = ("Rua|Avenida|Praça"), message = "Permitido apenas Rua, Avenida ou Praça para Logradouro.")
    private String logradouro;

    /** The endereco logradouro. */
    @EqualsExclude
    @HashCodeExclude
    @NotNull(message = "O Endereço logradouro não pode ser nulo.")
    @Pattern(regexp = "[a-zA-Z_0-9 ]{1,}", message = "Permitido apenas letras e numeros para endereço logradouro.")
    private String enderecoLogradouro;

    @NotNull(message = "O número não pode ser nulo.")
    @Range(min = 0, max = 99999, message = "O número não pode ser negativo.")
    private Integer numero;

    /** The bairro. */
    @EqualsExclude
    @HashCodeExclude
    @NotNull(message = "O bairro não pode ser nulo.")
    @Pattern(regexp = "[a-zA-Z ]{1,}", message = "Permitido apenas letras para bairro.")
    private String bairro;

    /** The cidade. */
    @EqualsExclude
    @HashCodeExclude
    @NotNull(message = "A cidade não pode ser nulo.")
    @Pattern(regexp = "[a-zA-Z ]{1,}", message = "Permitido apenas letras para cidade.")
    private String cidade;

    /** The estado. */
    @EqualsExclude
    @HashCodeExclude
    @NotNull(message = "O estado não pode ser nulo.")
    @Pattern(regexp = "[A-Z]{2}", message = "Permitido apenas sigla para estado no formato \"XX\".")
    private String estado;

    /** The pais. */
    @NotNull(message = "O pais não pode ser nulo.")
    @Pattern(regexp = "[a-zA-Z ]{1,}", message = "Permitido apenas letras para pais.")
    private String pais;

    /** The cep. */
    @NotNull(message = "O cep não pode ser nulo.")
    @Pattern(regexp = "[0-9]{5}[-][0-9]{3}", message = "O CEP deve ter o formato \"XXXXX-XXX\".")
    private String cep;

    /** The tipo enderecamento. */
    @EqualsExclude
    @HashCodeExclude
    @NotNull(message = "O tipo de endereço não pode ser nulo.")
    private TipoEndereco tipoEndereco;

    /**
     * Instantiates a new endereco.
     */
    public Endereco() {}

    /**
     * Instantiates a new endereco.
     *
     * @param logradouro the logradouro
     * @param enderecoLogradouro the endereco logradouro
     * @param numero the numero
     * @param bairro the bairro
     * @param cidade the cidade
     * @param estado the estado
     * @param pais the pais
     * @param cep the cep
     * @param tipoEndereco the tipo enderecamento
     */
    public Endereco(String logradouro, String enderecoLogradouro, Integer numero, String bairro, String cidade, String estado, String pais, String cep, TipoEndereco tipoEndereco) {
        this.logradouro = logradouro;
        this.enderecoLogradouro = enderecoLogradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
        this.tipoEndereco = tipoEndereco;
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
     * Gets the logradouro.
     *
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * Sets the logradouro.
     *
     * @param logradouro the new logradouro
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * Gets the endereco.
     *
     * @return the endereco
     */
    public String getEnderecoLogradouro() {
        return this.enderecoLogradouro;
    }

    /**
     * Sets the endereco.
     *
     * @param endereco the new endereco
     */
    public void setEnderecoLogradouro(String enderecoLogradouro) {
        this.enderecoLogradouro = enderecoLogradouro;
    }

    /**
     * Gets the numero.
     *
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * Sets the numero.
     *
     * @param numero the new numero
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * Gets the bairro.
     *
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Sets the bairro.
     *
     * @param bairro the new bairro
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * Gets the cidade.
     *
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Sets the cidade.
     *
     * @param cidade the new cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Gets the estado.
     *
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the estado.
     *
     * @param estado the new estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Gets the pais.
     *
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the pais.
     *
     * @param pais the new pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Gets the cep.
     *
     * @return the cep
     */
    public String getCep() {
        return this.cep;
    }

    /**
     * Sets the cep.
     *
     * @param cep the new cep
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * Gets the tipo enderecamento.
     *
     * @return the tipo enderecamento
     */
    public TipoEndereco getTipoEndereco() {
        return this.tipoEndereco;
    }

    /**
     * Sets the tipo enderecamento.
     *
     * @param tipoEndereco the new tipo enderecamento
     */
    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
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
}
