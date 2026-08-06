package kodlamaio.northwind.entities.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForLoginDTO {
    @NotBlank(message = "E-posta alanı boş bırakılamaz")
    @NotNull(message = "E-posta alanı boş bırakılamaz")
    @Email(message = "Geçerli bir e-posta adresi giriniz")
    private String email;

    @NotBlank(message = "Şifre alanı boş bırakılamaz")
    @NotNull(message = "Şifre alanı boş bırakılamaz")
    @Size(min = 6 ,max = 20, message = "Sifre alani Min 6 hane max 20 hane")
    private String password;
}
