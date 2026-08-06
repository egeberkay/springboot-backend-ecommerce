package kodlamaio.northwind.business.concretes;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.*;
import kodlamaio.northwind.dataAccess.abstracts.UserDao;
import kodlamaio.northwind.entities.dtos.UserForLoginDTO;
import kodlamaio.northwind.entities.dtos.UserForRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {
    private UserDao userDao;
    @Autowired
    public UserManager(UserDao userDao) {
        super();
        this.userDao = userDao;
    }

    @Override
    public Result add(User user) {
        this.userDao.save(user);
        return new SuccessResult("Kullanıcı Eklendi");
    }

    @Override
    public DataResult<User> findByEmail(String email) {
       User user = this.userDao.findByEmail(email);

       if (user == null){
           return new ErrorDataResult<>("Kullanici bulunamadi");//Burada false donduruyoruz.
       }

        return new SuccessDataResult<>(user,"Kullanici bulundu");
    }


    @Override
    public Result register(UserForRegisterDTO userForRegisterDTO){
        var userCheck = this.findByEmail(userForRegisterDTO.getEmail());
        if (userCheck.isSuccess()){
            return new ErrorResult("Bu e-posta adresi sistemde zaten kayitli");
        }
        User newUser = new User();
        newUser.setEmail(userForRegisterDTO.getEmail());
        newUser.setPassword(userForRegisterDTO.getPassword());

        return this.add(newUser);
    }

    @Override
    public Result logIn(UserForLoginDTO userForLoginDTO) {
        var userCheck = this.findByEmail(userForLoginDTO.getEmail());

        if (!userCheck.isSuccess()){
            return new ErrorResult("Kaydiniz bulunmamistir Lutfen Kayit/Register olunuz.");
        } else if (!userCheck.getData().getPassword().equals(userForLoginDTO.getPassword())) {
            return new ErrorResult("Sifreniz Yanlis.");
        }
        return new SuccessDataResult<User>(userCheck.getData(),"Giris islemi Basarili");
    }


}
