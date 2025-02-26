package com.ragheb.restful.service.product;

import com.ragheb.restful.dto.ProductDto;
import com.ragheb.restful.model.Product;
import com.ragheb.restful.request.AddProductRequest;
import com.ragheb.restful.request.ProductUpdatedRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdatedRequest product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String category, String name);
    Long countProductsByBrandAndName(String brand, String name);
    List<ProductDto> getConvertedProducts(List<Product> products);
    ProductDto convertToDto(Product product);

}
