package br.maiarasilva.demo.service;

import br.maiarasilva.demo.exceptions.BadRequestException;
import br.maiarasilva.demo.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import br.maiarasilva.demo.model.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.maiarasilva.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel getUserById(Long id) {
        Optional<UserModel> model = userRepository.findById(id);
        return model.orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
    }

    public UserModel saveUser(UserModel userModel){
        userModel.setSenha(encoder.encode(userModel.getSenha()));
        userModel = userRepository.save(userModel);
        return userModel;
    }

    public UserModel updateUser(UserModel userModel) {
        userRepository.findById(userModel.getId())
                .orElseThrow(() -> new BadRequestException("Usuário não encontrado."));
        if (userModel.getSenha() != null) {
            userModel.setSenha(encoder.encode(userModel.getSenha()));
        }
        return userRepository.save(userModel);
    }

    public void deleteUserById(long id)
    {
        Optional<UserModel> model = userRepository.findById(id);
        UserModel userModel = model.orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
        userRepository.delete(userModel);
    }

}
