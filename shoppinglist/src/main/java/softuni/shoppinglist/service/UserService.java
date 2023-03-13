package softuni.shoppinglist.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.shoppinglist.model.dtos.UserLoginDTO;
import softuni.shoppinglist.model.dtos.UserRegisterDTO;
import softuni.shoppinglist.model.entity.User;
import softuni.shoppinglist.model.helper.LoggedUser;
import softuni.shoppinglist.repository.UserRepository;

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

    public boolean LoginUser(UserRegisterDTO userRegisterDTO) {


        if (checkUsernameUniqueness(userRegisterDTO.getUsername())) {

            if (checkPasswordEquality(userRegisterDTO.getPassword(), userRegisterDTO.getConfirmPassword())) {

                User userToRegister = modelMapper.map(userRegisterDTO, User.class);
                userToRegister
                        .setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

                userRepository.save(userToRegister);

                return true;
            }
        }
        return false;
    }

    private boolean checkPasswordEquality(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean checkUsernameUniqueness(String username) {
        return findUserByUsername(username) == null;
    }

    public UserLoginDTO findUserDTOByUsername(String username) {
        return modelMapper.map(findUserByUsername(username), UserLoginDTO.class);
    }

    private User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }

    public boolean userToLogin(UserLoginDTO userLoginDTO) {

        User userByUsername = findUserByUsername(userLoginDTO.getUsername());

        if (userByUsername != null) {
            String rawPassword = userLoginDTO.getPassword();
            String hashedPass = userByUsername.getPassword();

            if (passwordEncoder.matches(rawPassword, hashedPass)) {
                loggedUser.setId(userByUsername.getId());
                loggedUser.setUsername(userByUsername.getUsername());
                return true;
            }
        }
        return false;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
