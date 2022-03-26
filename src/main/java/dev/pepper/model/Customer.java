package dev.pepper.model;

public class Customer {

    public Long id;
    public String name;
    public String email;
    public String phone;
    public String address;
    public String zipCode;
    public String cpf;

    public Customer() {
    }

    public Customer(Long id, String name, String email, String phone, String address, String zipCode, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.zipCode = zipCode;
        this.cpf = cpf;
    }
}
