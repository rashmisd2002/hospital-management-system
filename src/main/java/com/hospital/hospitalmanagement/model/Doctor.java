package com.hospital.hospitalmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Doctor name is required")
    private String name;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    private String contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Doctor name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Doctor name is required") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Specialization is required") String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(@NotBlank(message = "Specialization is required") String specialization) {
        this.specialization = specialization;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}

