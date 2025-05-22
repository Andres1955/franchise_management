package com.franchise.management.service;

import com.franchise.management.model.Franchise;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranchiseService {
    Mono<Franchise> saveFranchise(Franchise franchise);
    Mono<Franchise> updateFranchiseName(Long id, String newName);
    Mono<Franchise> addBranchToFranchise(Long franchiseId, String branchName);
    Flux<Franchise> getAllFranchises();
    Mono<Franchise> getFranchiseById(Long id);
}