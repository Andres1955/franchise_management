package controller;

import com.franchise.management.model.Product;
import com.franchise.management.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/branches/{branchId}/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping //Agregar un producto a una sucursal
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> addProductToBranch(@PathVariable Long branchId, @RequestBody Product product) {
        product.setBranchId(branchId);
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{productId}") //Eliminar un producto
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable Long branchId, @PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

    @PatchMapping("/{productId}/stock") //Actualizar el stock de un producto
    public Mono<Product> updateProductStock(@PathVariable Long branchId,
                                            @PathVariable Long productId,
                                            @RequestParam int stock) {
        return productService.updateProductStock(productId, stock);
    }

    @PatchMapping("/{productId}/name") //Actualizar el nombre de un producto
    public Mono<Product> updateProductName(@PathVariable Long branchId,
                                           @PathVariable Long productId,
                                           @RequestParam String name) {
        return productService.updateProductName(productId, name);
    }

    @GetMapping("/highest-stock") //Obtener el producto con mayor stock
    public Mono<Product> getProductWithHighestStock(@PathVariable Long branchId) {
        return productService.findProductWithHighestStockByBranch(branchId);
    }
}
