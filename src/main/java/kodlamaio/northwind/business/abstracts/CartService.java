package kodlamaio.northwind.business.abstracts;

import kodlamaio.northwind.core.utilities.result.DataResult;
import kodlamaio.northwind.core.utilities.result.Result;
import kodlamaio.northwind.entities.dtos.Cart;
import kodlamaio.northwind.entities.dtos.CartItem;

public interface CartService {
    DataResult<Cart> getCart(String sessionId);
    Result addItemToCart(String sessionId, CartItem item);
    Result removeItenToCart(String sessionId, CartItem item);
    Result clearCart(String sessionId);
}