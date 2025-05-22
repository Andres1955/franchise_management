package controller;

import com.franchise.management.model.Branch;
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

    @PostMapping //Exponer endpoint para agregar una nueva franquicia
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Franchise> createFranchise(@RequestBody Franchise franchise) {
        return franchiseService.saveFranchise(franchise);
    }

    @PutMapping("/{id}/name") //expone endpoint que permita actualizar el nombre de la franquicia.
    public Mono<Franchise> updateFranchiseName(@PathVariable Long id, @RequestParam String newName) {
        return franchiseService.updateFranchiseName(id, newName);
    }

    @PostMapping("/{franchiseId}/branches") //Exponer endpoint para agregar una nueva sucursal a la franquicia
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Franchise> addBranchToFranchise(
            @PathVariable Long franchiseId,
            @RequestParam String branchName) {
        return franchiseService.addBranchToFranchise(franchiseId, branchName);
    }

    @GetMapping //Exponer endpoint para Obtener Todas las Franquicias
    public Flux<Franchise> getAllFranchises() {
        return franchiseService.getAllFranchises();
    }

    @GetMapping("/{id}")//Exponer endpoint para Obtener Franquicia por ID
    public Mono<Franchise> getFranchiseById(@PathVariable Long id) {
        return franchiseService.getFranchiseById(id);
    }

    @GetMapping("/{franchiseId}/branches") //Exponer endpoint para Obtener Sucursales de Franquicia
    public Flux<Branch> getBranchesByFranchiseId(@PathVariable Long franchiseId) {
        return franchiseService.getBranchesByFranchiseId(franchiseId);
    }

}



















