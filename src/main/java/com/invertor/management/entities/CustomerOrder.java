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

    @Column(name = "code_commande_client")
    private String codeOrderCustomer;

    @Column(name = "date_commande")
    private Instant dateOrder;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Customer customer;

    @OneToMany(mappedBy = "customerOrder")
    private  List<CustomerOrderLine> customerOrderLines;
}
