package fr.training.samples.spring.shop.exposition.customer.rest;

import java.io.Serializable;

public class CustomerDto implements Serializable {


	private static final long serialVersionUID = 1L;


	private String customerID;


	private String name;


	private String password;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public CustomerDto() {
		super();
	}

	/**
	 * @param name
	 * @param password
	 */
	public CustomerDto(final String name, final String password) {
		super();
		this.name = name;
		this.password = password;
	}

	/**
	 * @param customerID
	 * @param name
	 * @param password
	 */
	public CustomerDto(final String customerID, final String name, final String password) {
		this.customerID = customerID;
		this.name = name;
		this.password = password;
	}

	/**
	 * @return
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param customerID
	 */
	public void setCustomerID(final String customerID) {
		this.customerID = customerID;
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
