package com.example.product.controller;

import com.example.product.dto.ProductDTO;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @GetMapping("/getproducts")
    public List<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }
    
    @PostMapping("/addproduct")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }
    
    @PutMapping("/updateproduct")
    public ProductDTO updateProduct(@RequestBody  ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }
    
    @DeleteMapping("/deleteproduct/{productId}")
    public String deleteProduct(@PathVariable  int productId) {
        return productService.deleteProduct(productId);
    }
    
    @GetMapping("/product/{productId}")
    public ProductDTO getProductByProductId(@PathVariable int productId) {
        return productService.getProductByProductId(productId);
    }
}
