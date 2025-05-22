package com.franchise.management.service;

import com.franchise.management.model.Franchise;
import com.franchise.management.repository.FranchiseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FranchiseServiceTest {

    @Mock
    private FranchiseRepository franchiseRepository;

    @InjectMocks
    private FranchiseServiceImpl franchiseService;

    @Test
    void saveFranchise_shouldReturnSavedFranchise() {
        Franchise franchise = Franchise.builder().name("Test Franchise").build();
        Franchise savedFranchise = Franchise.builder().id(1L).name("Test Franchise").build();

        when(franchiseRepository.save(any(Franchise.class))).thenReturn(Mono.just(savedFranchise));

        Mono<Franchise> result = franchiseService.saveFranchise(franchise);

        StepVerifier.create(result)
                .expectNext(savedFranchise)
                .verifyComplete();
    }
}
