package pl.limescode.gkspringrest.dto;

import lombok.Data;
import pl.limescode.gkspringrest.model.Product;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Integer price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}

