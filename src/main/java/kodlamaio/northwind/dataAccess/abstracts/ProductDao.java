package kodlamaio.northwind.dataAccess.abstracts;

import kodlamaio.northwind.entities.concretes.Category;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    Product getByProductName(String productName);
    Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);

    List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);
    List<Product> getByCategoryIn(List<Integer> categories);

    List<Product> getByProductNameContains(String productName);
    List<Product> getByProductNameStartsWith(String productName);

    @Query("From Product where productName= :productName and category.categoryId=:categoryId")
    List<Product> getByNameAndCategory(String productName, int categoryId);

    @Query("Select new  kodlamaio.northwind.entities.dtos.ProductWithCategoryDto(p.id,p.productName,c.categoryName) From Category c Inner join c.products p")
    List<ProductWithCategoryDto> getProductWithCategoryDetails(int id, String productName, String categoryName);

    @Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto"
            + "(p.id, p.productName, c.categoryName) " // Buradaki parametreler eksik olduğu için hata alıyorsun
            + "From Category c Inner Join c.products p")
    List<ProductWithCategoryDto> getProductWithCategoryDetails();
//İsimlendirme standartı na(getBy-findBy) uyduğun zaman arkada bu kod çalışır
    //select * from products where product_name= abc or category_id=1;
    //select * from products where   category_id in(1,2,3,4);

}
