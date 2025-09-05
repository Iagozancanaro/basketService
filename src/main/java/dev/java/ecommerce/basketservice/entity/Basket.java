package dev.java.ecommerce.basketservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "basket")
public class Basket {

    @Id
    private String id;

    @Field("clientId")
    private Long clientId;

    private BigDecimal totalPrice;

    private List<Product> products;

    private Status status;

    public void calculateTotalPrice() {
        this.totalPrice = products.stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
