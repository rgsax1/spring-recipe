package rodrigo.salles.springrecipe.service;

import rodrigo.salles.springrecipe.model.User;

public interface UserService {

    public User findUserById (Long userId) throws Exception;
    
}
