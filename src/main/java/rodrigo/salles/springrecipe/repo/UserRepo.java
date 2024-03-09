package rodrigo.salles.springrecipe.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import rodrigo.salles.springrecipe.model.User;

public interface UserRepo extends JpaRepository <User, Long> {

    public User findByEmail (String email);
    
}
