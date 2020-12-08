package fr.training.samples.spring.shop.exposition.customer.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(value="Customer", description="Contient les informations des clients")
@Validated
public class CustomerLightDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String password;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public CustomerLightDto() {

	}

	/**
	 * @param name
	 * @param password
	 */
	public CustomerLightDto(final String name, final String password) {
		super();
		this.name = name;
		this.password = password;
	}

	/**
	 * @return name
	 */
	@ApiModelProperty(value = "Customer name", example = "Michel Martin", required = true)
	@NotBlank
	public String getName() {
		return name;
	}

	/**
	 * @return password
	 */
	@ApiModelProperty(value = "Customer pwd", example = "MyPwd!", required = true)
	@NotBlank
	public String getPassword() {
		return password;
	}

	/**
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

}
