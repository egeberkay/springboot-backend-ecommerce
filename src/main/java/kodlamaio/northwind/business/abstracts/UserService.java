package kodlamaio.northwind.business.abstracts;

import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.dtos.UserForLoginDTO;
import kodlamaio.northwind.entities.dtos.UserForRegisterDTO;

public interface UserService {
    Result add(User user);
    DataResult<User> findByEmail(String email);
    Result register(UserForRegisterDTO userForRegisterDTO);
    Result logIn(UserForLoginDTO userForLoginDTO);
}
