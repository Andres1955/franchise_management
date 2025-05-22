package com.franchise.management.repository;

import com.franchise.management.model.Branch;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BranchRepository extends R2dbcRepository<Branch, Long> {


    Flux<Branch> findByFranchiseId(Long franchiseId);

    Mono<Branch> findByNameAndFranchiseId(String name, Long franchiseId);


    Mono<Branch> findById(Long id);

    @Modifying
    @Query("UPDATE branches SET name = :newName WHERE id = :branchId")
    Mono<Integer> updateBranchName(Long branchId, String newName);

    @Query("SELECT b.* FROM branches b JOIN franchises f ON b.franchise_id = f.id " +
            "WHERE f.id = :franchiseId AND b.name = :branchName")
    Mono<Branch> findByFranchiseAndBranchName(Long franchiseId, String branchName);

    @Query("SELECT COUNT(*) > 0 FROM branches WHERE franchise_id = :franchiseId AND name = :branchName")
    Mono<Boolean> existsByFranchiseIdAndName(Long franchiseId, String branchName);
}
