package nl.hu.josvanreenen.sandbox.model;

import java.io.Serializable;
import java.util.*;

public class Company implements Serializable {
    private List<Customer> allCustomers = new ArrayList<>();

    private static Company myCompany = new Company();

    public static Company getCompany() {
        return myCompany;
    }

    public static void setCompany(Company company) {
        myCompany = company;
    }

    private Company() {
         }

    public List<Customer> getAllCustomers() {
        return allCustomers;
    }


    public boolean loadDummyData(){
        allCustomers.add(Customer.createCustomer("Keanu Reeves"));
        allCustomers.add(Customer.createCustomer("Jim Carrey"));
        allCustomers.add(Customer.createCustomer("Bruce Willis"));
        return true;
    }

    public Customer getCustomerById(int id) {
        return id >0  ? allCustomers.stream().filter(customer -> customer.getId() == id).findFirst().orElse(null) : null;
    }

    public Customer getCustomerByName(String name) {
        return !name.isBlank() ? allCustomers.stream().filter(customer -> customer.getName().equals(name)).findFirst().orElse(null) : null;
    }

    public boolean addCustomer(String name){
        if (getCustomerByName(name) == null) { //countryCode must not be in use
            try {
                Customer toCreate = Customer.createCustomer(name);
                return allCustomers.add(toCreate);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
