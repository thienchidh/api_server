package com.example.api_server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "CARTS")
public class Cart {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private List<Product> products;

<<<<<<< HEAD
    @OneToOne(cascade = CascadeType.MERGE)
=======
    @OneToOne(cascade = CascadeType.ALL)
>>>>>>> de5be0322c67d9011a2f7e2496040bd6cd26fc6a
    @JoinColumn(name = "userId")
    private User user;
}
