package com.franchise.management.service;

import com.franchise.management.model.Branch;
import com.franchise.management.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;



    @Override
    public Mono<Branch> updateBranchName(Long branchId, String newName) {
        return branchRepository.findById(branchId)
                .flatMap(branch -> {
                    branch.setName(newName);
                    return branchRepository.save(branch);
                });
    }
}
