package rodrigo.salles.springrecipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rodrigo.salles.springrecipe.config.JwtProvider;
import rodrigo.salles.springrecipe.model.User;
import rodrigo.salles.springrecipe.repo.UserRepo;
import rodrigo.salles.springrecipe.request.LoginRequest;
import rodrigo.salles.springrecipe.response.AuthResponse;
import rodrigo.salles.springrecipe.service.CustomeUserDetailService;

@RestController
@RequestMapping ("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CustomeUserDetailService customeUserDetailService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception{

        String email= user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();

        User isExistEmail = userRepo.findByEmail(email);
        if (isExistEmail!=null){
            throw new Exception("Email is already used with another account");
        }

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFullName(fullName);
        
        User savedUser = userRepo.save(createdUser);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse();
        res.setJwt(token);
        res.setMessage("signin success");

        return res;

    }

    @PostMapping ("/signin")
    public AuthResponse signinHandler(@RequestBody LoginRequest LoginRequest) {
        String username = LoginRequest.getEmail() ;
        String password = LoginRequest.getPassword();
        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse();
        res.setJwt(token);
        res.setMessage("signup success");
        return res;
    }

    private Authentication authenticate (String username, String password) {
        UserDetails userDetails = customeUserDetailService.loadUserByUsername(username);
        if(userDetails == null) {
            throw new BadCredentialsException("user not found.");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
