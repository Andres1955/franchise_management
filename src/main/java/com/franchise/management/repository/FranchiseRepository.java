package com.franchise.management.repository;

import com.franchise.management.model.Franchise;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FranchiseRepository extends R2dbcRepository<Franchise, Long> {
    Mono<Franchise> findByName(String name);
}