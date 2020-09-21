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

/**
 * The Class Telefone.
 *
 * @author Petter
 */
public class Telefone {
    
    /** The id. */
    @EqualsExclude
    @HashCodeExclude
    @Null(message = "Esse campo só pode ser atribuido pela api do banco de dados.")
    private Long id;

	/** The ddd. */
    @NotNull(message = "O ddd não pode ser nulo.")
    @Pattern(regexp = "[0-9]{2}", message = "O ddd deve ter o formato \"XX\".")
	private String ddd;
	
	/** The numero. */
    @NotNull(message = "O numero não pode ser nulo.")
    @Pattern(regexp = "[0-9]{5}[-][0-9]{4}", message = "O número deve ter o fotmato \"XXXXX-XXXX\".")
	private String numero;
	
	/** The operadora. */
	@EqualsExclude
    @HashCodeExclude
    @NotNull(message = "A operadora não pode ser nulo.")
    @Pattern(regexp = "[a-zA-Z_0-9 ]{1,}", message = "Permitido apenas letras para operadora.")
	private String operadora;

	/** The tipo telefone. */
	@EqualsExclude
    @HashCodeExclude
    @NotNull(message = "O tipo de telefone não pode ser nulo.")
	private TipoTelefone tipoTelefone;
    
    /**
     * Instantiates a new telefone.
     */
    public Telefone() {}

    /**
     * Instantiates a new telefone.
     *
     * @param ddd the ddd
     * @param numero the numero
     * @param operadora the operadora
     * @param tipoTelefone the tipo telefone
     */
	public Telefone(String ddd, String numero, String operadora, TipoTelefone tipoTelefone) {
			this.numero = numero;
			this.ddd = ddd;
			this.operadora = operadora;
			this.tipoTelefone = tipoTelefone;
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
	 * Gets the ddd.
	 *
	 * @return the ddd
	 */
	public String getDdd() {
		return ddd;
	}

	/**
	 * Sets the ddd.
	 *
	 * @param ddd the new ddd
	 */
	public void setDdd(String ddd) {
			this.ddd = ddd;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(String numero) {
			this.numero = numero;
	}

	/**
	 * Gets the operadora.
	 *
	 * @return the operadora
	 */
	public String getOperadora() {
		return operadora;
	}

	/**
	 * Sets the operadora.
	 *
	 * @param operadora the new operadora
	 */
	public void setOperadora(String operadora) {
			this.operadora = operadora;
	}
	
	/**
	 * Gets the tipo telefone.
	 *
	 * @return the tipo telefone
	 */
	public TipoTelefone getTipoTelefone() {
        return this.tipoTelefone;
    }
	
	/**
	 * Sets the tipo enderecamento.
	 *
	 * @param tipoTelefone the new tipo enderecamento
	 */
	public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
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
