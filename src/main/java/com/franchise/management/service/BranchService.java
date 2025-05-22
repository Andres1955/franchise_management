package com.franchise.management.service;

import com.franchise.management.model.Branch;
import reactor.core.publisher.Mono;

public interface BranchService {

    Mono<Branch> updateBranchName(Long branchId, String newName);
}
