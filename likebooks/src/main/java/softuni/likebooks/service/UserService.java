package softuni.likebooks.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.likebooks.model.dtos.UserLoginDTO;
import softuni.likebooks.model.dtos.UserRegisterDTO;
import softuni.likebooks.model.entity.User;
import softuni.likebooks.model.helper.LoggedUser;
import softuni.likebooks.repository.UserRepository;

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

    public boolean loginUser(UserLoginDTO userLoginDTO) {

        Optional<User> userByUsername = userRepository.findUserByUsername(userLoginDTO.getUsername());

        if (userByUsername.isPresent()) {

            String rawPassword = userLoginDTO.getPassword();
            String encodedPassword = userByUsername.get().getPassword();

            if (passwordEncoder.matches(rawPassword, encodedPassword)) {
                loggedUser.setId(userByUsername.get().getId());
                loggedUser.setUsername(userByUsername.get().getUsername());
                return true;
            }
        }
        return false;
    }

    public boolean registerUser(UserRegisterDTO userRegisterDTO) {

        if (checkUsernameUniqueness(userRegisterDTO.getUsername())) {

            if (checkEmailUniqueness(userRegisterDTO.getEmail())) {

                if (passwordEquality(userRegisterDTO.getPassword(), userRegisterDTO.getConfirmPassword())) {
                    User userToRegister = modelMapper.map(userRegisterDTO, User.class);
                    userToRegister.setPassword(passwordEncoder.encode(userToRegister.getPassword()));

                    userRepository.save(userToRegister);
                    return true;
                }
            }
        }
        return false;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    private boolean passwordEquality(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean checkEmailUniqueness(String email) {

        Optional<User> userByEmail = userRepository.findUserByEmail(email);
        return userByEmail.isEmpty();
    }

    private boolean checkUsernameUniqueness(String username) {
        Optional<User> userByUsername = userRepository.findUserByUsername(username);

        return userByUsername.isEmpty();
    }
}
