package com.springboot.exercise.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Getter
@Setter
@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="STUDENT_SEQ")
    @SequenceGenerator(name="STUDENT_SEQ", sequenceName="STUDENT_SEQ", initialValue = 1, allocationSize=1)
    private Long id;

    @NotEmpty(message = "Name must not be empty")
    private String name;

    @NotEmpty(message = "Email must not be empty.")
    @Email(message = "Email must be a valid email.")
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Pattern(regexp = "^\\+959-\\d{3}-\\d{3}-\\d{3}", message = "Must be a valid phone number.")
    private String phone;

    private String address;
    private String township;
    private String state;
    private Boolean activeFlag;
    private Double schoolFee;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name= "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name= "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", township='" + township + '\'' +
                ", state='" + state + '\'' +
                ", activeFlag=" + activeFlag +
                ", schoolFee=" + schoolFee +
                ", registrationDate=" + registrationDate +
                '}';
    }

    public String getActiveStatus() {
        return this.activeFlag != null ? "Active" : "Inactive";
    }
}
