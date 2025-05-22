package controller;

import com.franchise.management.model.Branch;
import com.franchise.management.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PatchMapping("/{branchId}/name") //expone endpoint que permita actualizar el nombre de la sucursal.
    public Mono<Branch> updateBranchName(@PathVariable Long branchId,
                                         @RequestParam String newName) {
        return branchService.updateBranchName(branchId, newName);
    }
}
