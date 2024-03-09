package rodrigo.salles.springrecipe.service;

import java.util.Optional;

import rodrigo.salles.springrecipe.model.User;
import rodrigo.salles.springrecipe.repo.UserRepo;

public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        @SuppressWarnings("null")
        Optional<User> opt = userRepo.findById(userId);
        if (opt.isPresent()){
            return opt.get();
        } throw new Exception("User not found with id "+ userId);
    }
    

    

    
}
