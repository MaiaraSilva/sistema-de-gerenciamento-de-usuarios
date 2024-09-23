package service;

import exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import model.UserModel;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel getUserById(Long id) {
        Optional<UserModel> model = userRepository.findById(id);
        UserModel userModel = validateUserModel(model);
        return userModel;
    }

    public UserModel saveUser(UserModel userModel){
        userModel = userRepository.save(userModel);
        return userModel;
    }

    public UserModel updateUser(UserModel userModel) {
        Optional<UserModel> model= userRepository.findById(userModel.getId());
        if (model.isEmpty()) {
            throw new NotFoundException("Usuário informado não encontrado.");
        }
        return userRepository.save(userModel);
    }

    public void deleteUserById(long id)
    {
        Optional<UserModel> model = userRepository.findById(id);
        UserModel userModel = validateUserModel(model);
        userRepository.delete(userModel);
    }

    private UserModel validateUserModel(Optional<UserModel> userModel) {
        if (userModel.isEmpty()){
            throw new NotFoundException("Usuário não encontrado.");
        }
        return userModel.get();
    }
}
