package com.franchise.management.repository;

import com.franchise.management.model.Branch;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BranchRepository extends R2dbcRepository<Branch, Long> {
    Flux<Branch> findByFranchiseId(Long franchiseId);
    Mono<Branch> findByNameAndFranchiseId(String name, Long franchiseId);
}