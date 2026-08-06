package kodlamaio.northwind.apicontrollers;
import org.springframework.web.bind.annotation.CrossOrigin;
import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@CrossOrigin
public class ProductsController {
    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        super();
        this.productService = productService;
    }

    @GetMapping("/getall")//Eğer şöyle bir istek gelirse --> kodlama.io/api/products/getall bu alt taraftaki operasyon(fonksiyon) çalışır.
    public DataResult<List<Product>> getAll(){
        return this.productService.getAll();//Aslında burada ProductManagerın getAll fonksiyonu çalışıyor.
    }
    @PostMapping("/add")
    public Result add(@RequestBody Product product) {//map etme yani eşleştirme işlemi için işe yarar requestbody anatasyonu
        return this.productService.add(product);
    }
    @GetMapping("/getByProductName")
    public DataResult<Product> getByProductName(@RequestParam String productName) {
        return this.productService.getByProductName(productName);
   }
    @GetMapping("/getByProductNameAndCategory")
    public DataResult<Product> getByProductNameAndCategoryId(@RequestParam("productName") String productName, @RequestParam("categoryId") int categoryId) {
        return this.productService.getByProductNameAndCategoryId(productName, categoryId);
    }
    @GetMapping("/getByProductNameOrCategory")
    public DataResult<List<Product>> getByProductNameOrCategoryId(@RequestParam("productName") String productName, @RequestParam("categoryId") int categoryId) {
        return this.productService.getByProductNameOrCategoryId(productName, categoryId);
    }
    @GetMapping("/getByProductNameContains")
    public DataResult<List<Product>> getByProductNameContains(@RequestParam("productName") String productName) {
             return this.productService.getByProductNameContains(productName) ;
    }
    @GetMapping("/getAllByPage")
    public DataResult<List<Product>> getAll(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        return this.productService.getAll(pageNo,pageSize);
    }

    @GetMapping("/getAllDesc")
    public DataResult<List<Product>> getAllSorted(){
        return this.productService.getAllSorted();
    }

    @GetMapping("/getBypProductAndCategoryDetails")
    public DataResult<List<ProductWithCategoryDto>> getByProductWithCategoryDetails() {
        return this.productService.getByProductWithCategoryDetails();
    }

}