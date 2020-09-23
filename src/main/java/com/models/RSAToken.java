package com.models;

/**
 * https://localhost:9001/logTimee?lid=lid&rsa=rsa&expirydate=expirydate&name=name&email=email
 * 
 * @author BALRAJDASHRATHRAJ
 *
 */
public class RSAToken {

	private String lid;
	private String rsa;
	private String email;
	private String name;
	private String expirydate;

	public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	public String getRsa() {
		return rsa;
	}

	public void setRsa(String rsa) {
		this.rsa = rsa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	@Override
	public String toString() {
		return "RSAToken{" + "lid='" + lid + '\'' + ", rsa='" + rsa + '\'' + ", email='" + email + '\'' + ", name='"
				+ name + '\'' + ", expirydate='" + expirydate + '\'' + '}';
	}
}