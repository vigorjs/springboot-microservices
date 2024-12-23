package com.virgo.product.model.meta;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private Integer merchant_id;

    @Column()
    private Long price;

    @Column()
    private String product_name;

    @Column()
    private String product_description;
}