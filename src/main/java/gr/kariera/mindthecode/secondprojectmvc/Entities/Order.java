package gr.kariera.mindthecode.secondprojectmvc.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gr.kariera.mindthecode.secondprojectmvc.DTOs.ProductWithQuantityDto;
import gr.kariera.mindthecode.secondprojectmvc.DTOs.ProductWithQuantityExtendedDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Integer id;
    private Double discountPercentage = 0d;
    private String address;
    private BigDecimal totalCost;
    private Collection<ProductWithQuantityDto> products;

    public Order() {
        this.orderProducts = new ArrayList<>();
    }

    public Order(Collection<OrderProduct> products) {
        this.orderProducts = products;
    }

    public Order(String address) {
        this.address = address;
        this.orderProducts = new ArrayList<>();
    }

    public Order(Collection<OrderProduct> products, String address) {
        this.orderProducts = products;
        this.address = address;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore


    @Transient
    private Collection<OrderProduct> orderProducts = new ArrayList<>();
    public Collection<ProductWithQuantityDto>  getProducts() {
        return orderProducts
                .stream()
                .map(op -> {
                    ProductWithQuantityExtendedDto pwq = new ProductWithQuantityExtendedDto();
                    pwq.setProductId(op.getProduct().getId());
                    pwq.setQuantity(op.getQuantity());
                    pwq.setDescription(op.getProduct().getDescription());
                    pwq.setPrice(op.getProduct().getPrice());
                    return pwq;
                })
                .collect(Collectors.toList());
    }

    @Transient
    public BigDecimal getTotalCost() {
        BigDecimal total = orderProducts
                .stream()
                .map(op -> {
                    return op.getProduct().getPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            op.getQuantity()
                                    )
                            );
                })
                .reduce((acc, cur) -> acc.add(cur))
                .orElseThrow();

        return total.multiply(BigDecimal.valueOf(1-discountPercentage));
    }

}
