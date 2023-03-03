package exam.coffeshop.service;

import exam.coffeshop.model.dtos.LoginDTO;
import exam.coffeshop.model.dtos.RegisterDTO;
import exam.coffeshop.model.entity.User;
import exam.coffeshop.model.helper.LoggedUser;
import exam.coffeshop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    public boolean checkUniquenessAndRegister(RegisterDTO registerDTO) {

        if (registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {

            if (checkUsernameUniqueness(registerDTO.getUsername())) {

                if (checkEmailUniqueness(registerDTO.getEmail())) {

                    User userToRegister = modelMapper.map(registerDTO, User.class);
                    userToRegister.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

                    userRepository.save(userToRegister);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkEmailUniqueness(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        return byEmail.isEmpty();
    }

    private boolean checkUsernameUniqueness(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername.isEmpty();
    }

    public boolean loginSuccessfully(LoginDTO loginDTO) {

        Optional<User> userToLogin = userRepository.findByUsername(loginDTO.getUsername());

        if (userToLogin != null) {

            String rawPassword = loginDTO.getPassword();
            String encodedPassword = userToLogin.get().getPassword();

            if (passwordEncoder.matches(rawPassword, encodedPassword)) {
                loggedUser.setId(userToLogin.get().getId());
                loggedUser.setUsername(userToLogin.get().getUsername());
                return true;
            }
         }
        return false;
    }

    public User findUserByUserId(Long id) {
        return userRepository.findUserById(id);
    }
}
