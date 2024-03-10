package rodrigo.salles.springrecipe.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rodrigo.salles.springrecipe.model.User;
import rodrigo.salles.springrecipe.repo.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class UserController {

    private UserRepo userRepo;
    
    

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws Exception {
        User isExist = userRepo.findByEmail(user.getEmail());
        if (isExist!=null) {
            throw new Exception("user is exist with: "+ user.getEmail());
        }

        User savedUser = userRepo.save(user);
        return savedUser;
    }

    
    @SuppressWarnings("null")
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) throws Exception {
        userRepo.deleteById(userId);
        return "User deleted successfully";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() throws Exception {
        List<User> users = userRepo.findAll();
        return users;
    }
    

}
