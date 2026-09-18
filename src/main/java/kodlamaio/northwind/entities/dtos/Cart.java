package kodlamaio.northwind.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {
    private String sessionId; // anonim user icin uuid veya user email
    private List<CartItem> items = new ArrayList<>();
    private double totalPrice;

}