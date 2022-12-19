package pl.limescode.gkspringrest.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.limescode.gkspringrest.dto.ProductCreateDto;
import pl.limescode.gkspringrest.dto.ProductDto;
import pl.limescode.gkspringrest.model.Product;
import pl.limescode.gkspringrest.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getProductById(@PathVariable Long id) {
        return new ProductDto(productService.findProductById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductDto> getProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "name_part", required = false) String namePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(minPrice, maxPrice, namePart, page)
                .map(product -> new ProductDto(product));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> addProduct(@RequestBody ProductCreateDto productCreateDto) {
        return ResponseEntity.ok(productService.createProduct(productCreateDto));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(new ProductDto(productService.saveProduct(product)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
