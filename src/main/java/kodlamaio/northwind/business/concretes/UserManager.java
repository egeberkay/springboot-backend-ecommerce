package kodlamaio.northwind.business.concretes;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.dataAccess.RoleDao;
import kodlamaio.northwind.core.entities.Role;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.security.JwtService;
import kodlamaio.northwind.core.utilities.result.*;
import kodlamaio.northwind.dataAccess.abstracts.UserDao;
import kodlamaio.northwind.entities.dtos.UserForLoginDTO;
import kodlamaio.northwind.entities.dtos.UserForRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserManager(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        super();
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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

        String hashedPassword = passwordEncoder.encode(userForRegisterDTO.getPassword());
        newUser.setPassword(hashedPassword);

        Role defaultRole = roleDao.findByName("ROLE_USER");
        if (defaultRole != null){
            newUser.getRoles().add(defaultRole);
        }
        return this.add(newUser);
    }

    @Override
    public Result logIn(UserForLoginDTO userForLoginDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userForLoginDTO.getEmail(),
                    userForLoginDTO.getPassword()
            ));
        }catch (Exception e){
            return new ErrorResult("E-mail or password is incorrect - E-posta veya şifre hatalı");
        }

        String jwtToken = jwtService.generateToken(userForLoginDTO.getEmail());

        return new SuccessDataResult<String>(jwtToken,"Login successful - Giris islemi Basarili");
    }


}
