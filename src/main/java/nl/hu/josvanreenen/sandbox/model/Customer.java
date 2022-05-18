package nl.hu.josvanreenen.sandbox.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Customer implements Serializable {
	private int id;
	private String name;

	
	private Customer(String name) {
		if(!name.isBlank()) {
			this.name = name;
		}
		else throw new IllegalArgumentException("Not all required fields were properly specified");
	}

	public static Customer createCustomer(String name){
			return new Customer(name);
	}

}
