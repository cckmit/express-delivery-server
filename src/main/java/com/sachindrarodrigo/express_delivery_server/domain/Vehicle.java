package com.sachindrarodrigo.express_delivery_server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true, length = 45)
    private int vehicleId;

    @NotEmpty(message = "Vehicle number cannot be empty")
    @Column(nullable = false)
    private String vehicleNumber;

    @NotEmpty(message = "Vehicle type cannot be empty")
    @Column(nullable = false)
    private String vehicleType;

    private String status;

    @JsonBackReference(value = "driver-vehicle")
    @OneToOne(mappedBy = "vehicle", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private DriverDetail driverDetail;
}
