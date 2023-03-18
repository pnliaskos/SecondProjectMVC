package gr.kariera.mindthecode.secondprojectmvc.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

@Data
@Entity
@Table(name = "Order_Product")
public class OrderProduct {
    @EmbeddedId
    private OrderProductPK id;

    @ManyToOne
    @Lazy(false)
    @MapsId("order_id")
    private Order order;

    @ManyToOne
    @Lazy(false)
    @MapsId("product_id")
    private Product product;

    private Integer quantity;
}
