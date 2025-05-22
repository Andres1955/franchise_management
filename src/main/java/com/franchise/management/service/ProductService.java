package com.franchise.management.service;

import com.franchise.management.model.Product;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> saveProduct(Product product);
    Mono<Void> deleteProduct(Long productId);
    Mono<Product> updateProductStock(Long productId, int stock);
    Mono<Product> updateProductName(Long productId, String name);
    Mono<Product> findProductWithHighestStockByBranch(Long branchId);
}
