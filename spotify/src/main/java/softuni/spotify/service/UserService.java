package softuni.spotify.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.spotify.model.dtos.LoginDTO;
import softuni.spotify.model.dtos.RegisterDTO;
import softuni.spotify.model.entity.User;
import softuni.spotify.model.helper.LoggedUser;
import softuni.spotify.repository.UserRepository;

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

    public boolean registerUser(RegisterDTO registerDTO) {

        if (checkUsernameUniqueness(registerDTO.getUsername())) {

            if (checkEmailUniqueness(registerDTO.getEmail())) {

                if (checkPasswordEquality(registerDTO.getPassword(), registerDTO.getConfirmPassword())) {
                    User userToRegister = modelMapper.map(registerDTO, User.class);

                    userToRegister.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

                    userRepository.save(userToRegister);

                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkPasswordEquality(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean checkEmailUniqueness(String email) {
        return userRepository.findUserByEmail(email).isEmpty();
    }

    private boolean checkUsernameUniqueness(String username) {
        return userRepository.findUserByUsername(username).isEmpty();
    }

    public boolean loginUser(LoginDTO loginDTO) {
        Optional<User> userByUsername = userRepository.findUserByUsername(loginDTO.getUsername());

        if (userByUsername.isPresent()) {

            String rawPassword = loginDTO.getPassword();
            String encrypted = userByUsername.get().getPassword();

            if (checkPasswordValidity(rawPassword, encrypted)) {
                loggedUser.setId(userByUsername.get().getId());
                loggedUser.setUsername(userByUsername.get().getUsername());
                return true;
            }
        }
        return false;
    }

    private boolean checkPasswordValidity(String rawPassword, String encrypted) {
        return passwordEncoder.matches(rawPassword, encrypted);
    }

    private boolean checkUserPresents(String username) {

        return userRepository.findUserByUsername(username).isPresent();
    }
}
