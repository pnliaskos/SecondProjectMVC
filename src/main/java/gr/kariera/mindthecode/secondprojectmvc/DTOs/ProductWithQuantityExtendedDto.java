package gr.kariera.mindthecode.secondprojectmvc.DTOs;

import java.math.BigDecimal;

public class ProductWithQuantityExtendedDto extends ProductWithQuantityDto {
    private BigDecimal price;

    private String description;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
