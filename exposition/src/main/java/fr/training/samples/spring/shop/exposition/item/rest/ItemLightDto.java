package fr.training.samples.spring.shop.exposition.item.rest;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Validated
public class ItemLightDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String description;

	private int price;

	/**
	 *
	 */
	public ItemLightDto() {
	}

	public ItemLightDto(final String description, final int price) {
		super();
		this.description = description;
		this.price = price;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	@NotNull
	@Positive
	public int getPrice() {
		return price;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setPrice(final int price) {
		this.price = price;
	}

}
