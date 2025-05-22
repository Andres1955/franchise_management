package com.franchise.management.service;

import com.franchise.management.model.Product;
import com.franchise.management.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Mono<Product> saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Mono<Void> deleteProduct(Long productId) {
        return productRepository.deleteById(productId);
    }

    @Override
    public Mono<Product> updateProductStock(Long productId, int stock) {
        return productRepository.findById(productId)
                .flatMap(product -> {
                    product.setStock(stock);
                    return productRepository.save(product);
                });
    }

    @Override
    public Mono<Product> updateProductName(Long productId, String name) {
        return productRepository.findById(productId)
                .flatMap(product -> {
                    product.setName(name);
                    return productRepository.save(product);
                });
    }

    @Override
    public Mono<Product> findProductWithHighestStockByBranch(Long branchId) {
        return productRepository.findFirstByBranchIdOrderByStockDesc(branchId);
    }
}
