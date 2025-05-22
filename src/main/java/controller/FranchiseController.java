package controller;

import com.franchise.management.model.Franchise;
import com.franchise.management.service.FranchiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franchises")
@RequiredArgsConstructor
public class FranchiseController {

    private final FranchiseService franchiseService;

    @PostMapping //Crear una nueva franquicia
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Franchise> createFranchise(@RequestBody Franchise franchise) {
        return franchiseService.saveFranchise(franchise);
    }

    @PutMapping("/{id}/name") //Actualizar el nombre de una franquicia
    public Mono<Franchise> updateFranchiseName(@PathVariable Long id, @RequestParam String newName) {
        return franchiseService.updateFranchiseName(id, newName);
    }

    @PostMapping("/{franchiseId}/branches") //Agregar una sucursal a una franquicia
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Franchise> addBranchToFranchise(@PathVariable Long franchiseId, @RequestParam String branchName) {
        return franchiseService.addBranchToFranchise(franchiseId, branchName);
    }

    @GetMapping //Obtener todas las franquicias
    public Flux<Franchise> getAllFranchises() {
        return franchiseService.getAllFranchises();
    }

    @GetMapping("/{id}") //Obtener una franquicia por ID
    public Mono<Franchise> getFranchiseById(@PathVariable Long id) {
        return franchiseService.getFranchiseById(id);
    }
}
