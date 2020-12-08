package fr.training.samples.spring.shop.exposition.order.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.exposition.customer.rest.CustomerDto;
import fr.training.samples.spring.shop.exposition.item.rest.ItemDto;

public class OrderDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderId;

	private CustomerDto customer;

	private List<ItemDto> items;

	private int total;


	/**
	 * No-arg constructor for JavaBean tools
	 */
	public OrderDto() {

	}

	public OrderDto(final String orderId, final CustomerDto customer) {
		this.orderId = orderId;
		this.customer = customer;
		items = new ArrayList<>();
	}

	public void addItem(final ItemDto itemDto) {
		items.add(itemDto);
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(final String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the customerId
	 */
	public CustomerDto getCustomer() {
		return customer;
	}

	/**
	 * @param  the customerId to set
	 */
//	public void setCustomerId(final String customerId) {
//		this.customerId = customerId;
//	}

	public void setCustomer(final CustomerDto customer) {
		this.customer = customer;
	}

	/**
	 * @return the items
	 */
	public List<ItemDto> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(final List<ItemDto> items) {
		this.items = items;
	}

	public void setTotal(final int total) {
		this.total = total;
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("OrderDTO [orderId=");
		builder.append(orderId);
		builder.append(", customerId=");
		builder.append(customer);
		builder.append(", items=");
		builder.append(items);
		builder.append("]");
		return builder.toString();
	}

}
