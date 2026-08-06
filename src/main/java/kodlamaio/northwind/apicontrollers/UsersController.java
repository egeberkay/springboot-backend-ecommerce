package kodlamaio.northwind.apicontrollers;
import kodlamaio.northwind.entities.dtos.UserForLoginDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import jakarta.validation.Valid;
import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.entities.dtos.UserForRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping(value = "/api/users")
public class UsersController {

    private UserService userService;
    @Autowired
    public UsersController(UserService userService) {
        super();
        this.userService = userService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody User user){
        return ResponseEntity.ok(this.userService.add(user)); //ok 200 demek işlem başarılı olduğu durum -?- anlamı eror da dönebilir, error yada success sonuca göre döndürür.
    }
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserForRegisterDTO userForRegisterDTO){
        return ResponseEntity.ok(this.userService.register(userForRegisterDTO));
    }
    @PostMapping(value = "/logIn")
    public ResponseEntity<?> logIn(@Valid @RequestBody UserForLoginDTO userForLoginDTO){
        return ResponseEntity.ok(this.userService.logIn(userForLoginDTO));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)//Default olarak bad(500) döndürür.
    public ErrorDataResult<Object> handleValidationExceptions(MethodArgumentNotValidException exceptions){
        Map<String,String> validationErrors = new HashMap<String,String>();
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new  ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
        return errors;
    }


}//Frontend geliştiricisinin İşlemin ne durumda olduğunu anlaması için önemli olan sayılar.

//200
//300
//400
//500