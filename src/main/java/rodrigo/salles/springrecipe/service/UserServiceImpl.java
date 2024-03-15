package rodrigo.salles.springrecipe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rodrigo.salles.springrecipe.config.JwtProvider;
import rodrigo.salles.springrecipe.model.User;
import rodrigo.salles.springrecipe.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws Exception {
        @SuppressWarnings("null")
        Optional<User> opt = userRepo.findById(userId);
        if (opt.isPresent()){
            return opt.get();
        } throw new Exception("User not found with id "+ userId);
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        if(email==null) {
            throw new Exception("provide valid jwt token...");
        }

        User user = userRepo.findByEmail(email);
        if(user==null) {
            throw new Exception("user not found with email "+ email);
        }
        
        return user;
    }
    
    

    

    
}
