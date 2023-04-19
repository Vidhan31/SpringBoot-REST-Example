package me.spring.boot3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Customer {

    @Id
    @SequenceGenerator(name = "userid_sequence_number", sequenceName = "userid_sequence_number", allocationSize = 1)
    @GeneratedValue(generator = "userid_sequence_number", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String email;
    private Integer age;

    public Customer() {
    }

    public Customer(Integer id, String name, String email, Integer age) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", age=" + age + '}';
    }
}
