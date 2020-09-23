package com.models;


/**
 * 
 * @author BALRAJDASHRATHRAJ
 *
 */
public class AddressDetails {
	private String lid;
    private String name;
    private String phone;
    private String address1;
    private String address2;
    private String landmark;
    private String city;
    private String state;
    private String pin;
    
    public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}



    @Override
    public String toString() {
        return "AddressDetails{" +
                "lid='" + lid + '\'' +
                ", Name='" + name + '\'' +
                ", Address1='" + address1 + '\'' +
                ", Address1='" + address1 + '\'' +
                ", Landmark='" + landmark + '\'' +
                ", Phone='" + phone + '\'' +
                ", City='" + city + '\'' +                
                ", State='" + state+ '\'' +
                ", Pin='" + pin + '\'' +
                '}';
    }
}