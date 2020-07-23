package ua.kiev.minaeva.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

}
