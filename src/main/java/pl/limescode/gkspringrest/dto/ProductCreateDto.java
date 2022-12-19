package pl.limescode.gkspringrest.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ProductCreateDto {
    private String name;
    private Integer price;
}

