package com.invertor.management.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "commandeClients")
@EqualsAndHashCode(callSuper = true)
public class CustomerOrder extends AbstractEntity {

    @Column(name = "codeCommandeClient")
    private String codeOrderCustomer;

    @Column(name = "dateCommande")
    private Instant dateOrder;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Customer customer;

    @OneToMany(mappedBy = "customerOrder")
    private  List<CustomerOrderLine> customerOrderLines;
}
