package controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.app.demo.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/most-commented")
    public List<String> getMostCommentedProducts(@RequestParam int limit) {
        return productService.getMostCommentedLimitedTo(limit);
    }
}
