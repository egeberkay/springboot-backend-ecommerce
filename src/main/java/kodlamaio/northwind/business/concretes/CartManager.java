package kodlamaio.northwind.business.concretes;

import kodlamaio.northwind.business.abstracts.CartService;
import kodlamaio.northwind.core.utilities.result.DataResult;
import kodlamaio.northwind.core.utilities.result.Result;
import kodlamaio.northwind.core.utilities.result.SuccessDataResult;
import kodlamaio.northwind.core.utilities.result.SuccessResult;
import kodlamaio.northwind.entities.dtos.Cart;
import kodlamaio.northwind.entities.dtos.CartItem;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;

@Service
public class CartManager implements CartService {
     private final RedisTemplate<String,Cart> redisTemplate;
     private static final String CART_KEY_PREFIX = "cart:";
     private static final long CART_TTL_HOURS = 24;

     public CartManager(RedisTemplate<String, Cart> redisTemplate){
         this.redisTemplate = redisTemplate;
     }

    @Override
    public DataResult<Cart> getCart(String sessionId) {
         String key = CART_KEY_PREFIX + sessionId;
         Cart cart = redisTemplate.opsForValue().get(key);
         if (cart ==null){
             cart = new Cart();
             cart.setSessionId(sessionId);
         }
         return new SuccessDataResult<>(cart, "Sepet Getirildi");
    }

    @Override
    public Result addItemToCart(String sessionId, CartItem item) {
        String key = CART_KEY_PREFIX + sessionId;
        Cart cart = getCart(sessionId).getData();
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(i -> i.getProductId() == item.getProductId())
                .findFirst();
        if (existingItem.isPresent()){
            existingItem.get().setQuantity(existingItem.get().getQuantity() + item.getQuantity());
        }else {
            cart.getItems().add(item);
        }

        cart.setTotalPrice(calculateTotalPrice(cart));
        redisTemplate.opsForValue().set(key, cart, CART_TTL_HOURS, TimeUnit.HOURS);

        return new SuccessResult("Urun Sepete eklendi");
    }

    @Override
    public Result removeItenToCart(String sessionId, CartItem item) {
        String key = CART_KEY_PREFIX + sessionId;
        Cart cart = getCart(sessionId).getData();

        cart.getItems().removeIf(i -> i.getProductId() == item.getProductId());
        cart.setTotalPrice(calculateTotalPrice(cart));
        redisTemplate.opsForValue().set(key, cart, CART_TTL_HOURS, TimeUnit.HOURS);

        return new SuccessResult("Urun Seoetten silindi");
    }

    @Override
    public Result clearCart(String sessionId) {
         redisTemplate.delete(CART_KEY_PREFIX + sessionId);
         return new SuccessResult("Sepet Temizlendi.");
    }
    private double calculateTotalPrice(Cart cart){
         if (cart.getItems() == null || cart.getItems().isEmpty()){
             return 0.0;
         }
         return cart.getItems().stream()
                 .mapToDouble(i -> i.getUnitPrice() * i.getQuantity())
                 .sum();
    }
}