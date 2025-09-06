package dev.java.ecommerce.basketservice.controller;

import dev.java.ecommerce.basketservice.controller.request.BasketRequest;
import dev.java.ecommerce.basketservice.controller.request.PaymentRequest;
import dev.java.ecommerce.basketservice.entity.Basket;
import dev.java.ecommerce.basketservice.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
@Tag(name = "Carrinho", description = "Recurso responsável pelo gerenciamento do carrinho de compras" )
public class BasketController {

    private final BasketService basketService;

    @Operation(summary = "Buscar carrinho por ID", description = "Método responsável por buscar um carrinho de compras por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrinho encontrado com sucesso", content = @Content(schema = @Schema(implementation = Basket.class))),
            @ApiResponse(responseCode = "404", description = "Carrinho de compras não encontrado", content = @Content())})
    @GetMapping("/{id}")
    public ResponseEntity<Basket> getBasketById(@PathVariable String id) {
        return ResponseEntity.ok(basketService.getBasketById(id));
    }

    @Operation(summary = "Criar carrinho", description = "Método responsável por criar um carrinho de compras")
    @ApiResponse(responseCode = "201", description = "Carrinho criado com sucesso",
            content = @Content(schema = @Schema(implementation = Basket.class)))
    @PostMapping
    public ResponseEntity<Basket> createBasket(@RequestBody BasketRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.createBasket(request));
    }

    @Operation(summary = "Atualizar carrinho", description = "Método responsável por atualizar um carrinho de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrinho encontrado com sucesso", content = @Content(schema = @Schema(implementation = Basket.class))),
            @ApiResponse(responseCode = "404", description = "Carrinho de compras não encontrado", content = @Content())})
    @PutMapping("/{id}")
    public ResponseEntity<Basket> updateBasket(@PathVariable String id, @RequestBody BasketRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(basketService.updateBasket(id, request));
    }

    @Operation(summary = "Definir pagamento", description = "Método responsável definir o tipo de pagamento de um carrinho")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento definido com sucesso", content = @Content(schema = @Schema(implementation = Basket.class))),
            @ApiResponse(responseCode = "404", description = "Carrinho de compras não encontrado", content = @Content())})
    @PutMapping("/{id}/payment")
    public ResponseEntity<Basket> payBasket(@PathVariable String id, @RequestBody PaymentRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(basketService.payBasket(id, request));
    }

    @Operation(summary = "Excluir carrinho", description = "Método responsável por excluir um carrinho de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Carrinho deletado com sucesso", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Carrinho de compras não encontrado", content = @Content())})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBasket(@PathVariable String id) {
        basketService.deleteBasket(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
