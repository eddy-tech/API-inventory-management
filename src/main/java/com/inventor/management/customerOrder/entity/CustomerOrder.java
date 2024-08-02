package com.inventor.management.customerOrder.entity;

import com.inventor.management.customer.entity.Customer;
import com.inventor.management.core.entities.AbstractEntity;
import com.inventor.management.customerOrderLine.entity.CustomerOrderLine;
import com.inventor.management.core.enums.StateOrder;
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
    private String codeCustomerOrder;

    @Column(name = "date_commande")
    private Instant dateOrder;

    @Column(name = "etat_commande")
    private StateOrder stateOrder;

    @Column(name = "id_enterprise")
    private Long id_enterprise;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Customer customer;

    @OneToMany(mappedBy = "customerOrder")
    private  List<CustomerOrderLine> customerOrderLines;
}
