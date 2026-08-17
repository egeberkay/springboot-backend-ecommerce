package kodlamaio.northwind.entities.concretes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
    @Id//Primery Key alanı olduğunu belirtir
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id nin nasıl oluşacağı
    @Column(name = "product_id")
    private Integer id;

   // @Column(name = "category_id")
    //private int categoryId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "units_in_stock")
    private Integer unitsInStock;

    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {}
    public Product(Integer unitsInStock, String quantityPerUnit, Double unitPrice,
                   String productName, Integer categoryId, Integer id) {
        super();
        this.unitsInStock = unitsInStock;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.productName = productName;
        //this.categoryId = categoryId;
        this.id = id;
    }

}
