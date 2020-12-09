package fr.training.samples.spring.shop.domain.customer;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer extends AbstractBaseEntity {

	private String name;

	private String password;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	Set<RoleTypeEnum> roles = new HashSet<>();


	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	@Override
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	@Override
	public void setVersion(final Integer version) {
		this.version = version;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	public Set<RoleTypeEnum> getRoles() {
		return roles;
	}

	public void addRole(RoleTypeEnum roleUser) {
		roles.add(roleUser);
	}
}
