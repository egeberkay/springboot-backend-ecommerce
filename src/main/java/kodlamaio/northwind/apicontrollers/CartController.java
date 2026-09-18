package kodlamaio.northwind.apicontrollers;

import kodlamaio.northwind.business.abstracts.CartService;
import kodlamaio.northwind.core.utilities.result.DataResult;
import kodlamaio.northwind.core.utilities.result.Result;
import kodlamaio.northwind.entities.dtos.Cart;
import kodlamaio.northwind.entities.dtos.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/cart")
public class CartController {
    private final CartService cartService;
    @Autowired
    public CartController(CartService cartService){
        super();
        this.cartService = cartService;
    }

    @GetMapping("/{sessionId}")
    public DataResult<Cart> getCart(@PathVariable String sessionId){
        return cartService.getCart(sessionId);
    }
    @PostMapping("/{sessionId}/add")
    public Result addItemToCart(@PathVariable String sessionId, @RequestBody CartItem item){
        return cartService.addItemToCart(sessionId, item);
    }
    @DeleteMapping("/{sessionId}/remove")
    Result removeItenToCart(@PathVariable String sessionId, @RequestBody CartItem item){
            return cartService.removeItenToCart(sessionId, item);
    }
    @DeleteMapping("{sessionId}/clear")
    Result clearCart(@PathVariable String sessionId){
        return cartService.clearCart(sessionId);
    }

}
