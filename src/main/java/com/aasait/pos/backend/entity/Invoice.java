package com.aasait.pos.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private String  saleInvoiceId;

//     OPTIONAL duplicates – keep only if you truly need the columns

       @Column(name = "batch_id")
       private String batchId;
       @Column(name = "item_id")
       private Long itemId;


    /* -------- owning side Order‑›Invoice -------- */
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "order_id")    // FK column in invoice
//    private Order order;
}