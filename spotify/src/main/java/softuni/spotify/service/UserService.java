package softuni.spotify.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.spotify.model.dtos.RegisterDTO;
import softuni.spotify.model.entity.User;
import softuni.spotify.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public boolean registerUser(RegisterDTO registerDTO) {

        if (checkUsernameUniqueness(registerDTO.getUsername())) {

            if (checkEmailUniqueness(registerDTO.getEmail())) {

                if (checkPasswordEquality(registerDTO.getPassword(), registerDTO.getConfirmPassword())) {
                    User userToRegister = modelMapper.map(registerDTO, User.class);
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
}
