package com.franchise.management.repository;

import com.franchise.management.model.Product;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends R2dbcRepository<Product, Long> {
    Flux<Product> findByBranchId(Long branchId);

    @Query("SELECT * FROM products WHERE branch_id = :branchId ORDER BY stock DESC LIMIT 1")
    Mono<Product> findFirstByBranchIdOrderByStockDesc(Long branchId);

    @Modifying
    @Query("UPDATE products SET stock = :stock WHERE id = :id")
    Mono<Integer> updateStock(Long id, int stock);

    @Query("SELECT p.* FROM products p JOIN branches b ON p.branch_id = b.id WHERE b.franchise_id = :franchiseId ORDER BY p.stock DESC LIMIT 1")
    Mono<Product> findProductWithHighestStockByFranchise(Long franchiseId);
}