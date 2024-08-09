package com.inventor.management.inventor_management.customerOrder.entity;

import com.inventor.management.inventor_management.customer.entity.Customer;
import com.inventor.management.inventor_management.customerOrderLine.entity.CustomerOrderLine;
import com.inventor.management.inventor_management.core.enums.StateOrder;
import com.inventor.management.inventor_management.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "commandeClients")
public class CustomerOrder extends AbstractEntity {
    @Column(name = "code_commande_client")
    private String codeCustomerOrder;

    @Column(name = "date_commande")
    private Instant dateOrder;

    @Column(name = "etat_commande")
    private StateOrder stateOrder;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Customer customer;

    @OneToMany(mappedBy = "customerOrder")
    private  List<CustomerOrderLine> customerOrderLines;
}
