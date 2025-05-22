package com.franchise.management.service;

import com.franchise.management.model.Branch;
import com.franchise.management.model.Franchise;
import com.franchise.management.repository.BranchRepository;
import com.franchise.management.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranchiseServiceImpl implements FranchiseService {

    @Override
    public Flux<Branch> getBranchesByFranchiseId(Long franchiseId) {
        return branchRepository.findByFranchiseId(franchiseId);
    }

    private final FranchiseRepository franchiseRepository;
    private final BranchRepository branchRepository;

    @Override
    public Mono<Franchise> saveFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    @Override
    public Mono<Franchise> updateFranchiseName(Long id, String newName) {
        return franchiseRepository.findById(id)
                .flatMap(franchise -> {
                    franchise.setName(newName);
                    return franchiseRepository.save(franchise);
                });
    }

    @Override
    public Mono<Franchise> addBranchToFranchise(Long franchiseId, String branchName) {
        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(new RuntimeException("Franquicia no encontrada")))
                .flatMap(franchise -> {
                    Branch newBranch = Branch.builder()
                            .name(branchName)
                            .franchiseId(franchiseId)
                            .build();
                    return branchRepository.save(newBranch)
                            .thenReturn(franchise);
                });
    }

    @Override
    public Flux<Franchise> getAllFranchises() {
        return franchiseRepository.findAll();
    }

    @Override
    public Mono<Franchise> getFranchiseById(Long id) {
        return franchiseRepository.findById(id);
    }
}
