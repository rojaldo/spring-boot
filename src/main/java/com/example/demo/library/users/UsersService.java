package com.example.demo.library.users;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getUsers(String name, int ageGt, int ageLt) {
        if (name.isEmpty()) {
            return this.userEntitiesToDtos(this.userRepository.findByAgeGreaterThanAndAgeLessThan(ageGt, ageLt));
        }
        return this.userEntitiesToDtos(this.userRepository.findByNameIgnoreCaseAndAgeGreaterThanAndAgeLessThan(name, ageGt, ageLt));
    }

    public UserDto getUserById(Long id) {
        return this.userEntityToDto(this.userRepository.findById(id).get());
    }

    public IUserResponse createUser(UserDto user) {
        if (this.userRepository.findByEmail(user.getEmail()).isPresent()) {
            return UserErrorDto.builder().message("User with email " + user.getEmail() + " already exists").status(400).build();
        }
        return this.userEntityToDto(this.userRepository.save(this.userDtoToEntity(user)));
    }

    public IUserResponse updateUser(Long id, UserDto user) {
        if (!this.userRepository.existsById(id)) {
            return UserErrorDto.builder().message("User with id " + id + " does not exist").status(404).build();
        }else if (this.userRepository.findByEmail(user.getEmail()).isPresent() && this.userRepository.findByEmail(user.getEmail()).get().getId() != id) {
            return UserErrorDto.builder().message("User with email " + user.getEmail() + " already exists").status(400).build();
        }
        UserEntity userEntity = this.userDtoToEntity(user);
        userEntity.setId(id);
        UserEntity response = this.userRepository.save(userEntity);
        return this.userEntityToDto(response);
    }

    public IUserResponse patchUser(Long id, UserDto user) {
        if (!this.userRepository.existsById(id)) {
            return UserErrorDto.builder().message("User with id " + id + " does not exist").status(404).build();
        }else if (this.userRepository.findByEmail(user.getEmail()).isPresent() && this.userRepository.findByEmail(user.getEmail()).get().getId() != id) {
            return UserErrorDto.builder().message("User with email " + user.getEmail() + " already exists").status(400).build();
        }
        UserEntity userEntity = this.userRepository.findById(id).get();
        if (user.getName() != null) {
            userEntity.setName(user.getName());
        }
        if (user.getEmail() != null) {
            userEntity.setEmail(user.getEmail());
        }
        if (user.getAge() != 0) {
            userEntity.setAge(user.getAge());
        }
        UserEntity response = this.userRepository.save(userEntity);
        return this.userEntityToDto(response);
    }

    public IUserResponse deleteUser(Long id) {
        if (!this.userRepository.existsById(id)) {
            return UserErrorDto.builder().message("User with id " + id + " does not exist").status(404).build();
        }
        this.userRepository.deleteById(id);
        return UserDto.builder().id(id).build();
    }

    private List<UserDto> userEntitiesToDtos(List<UserEntity> users) {
        return users.stream().map(user -> UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .age(user.getAge())
                .build()).collect(Collectors.toList());
    }

    private UserDto userEntityToDto(UserEntity user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .age(user.getAge())
                .build();
    }

    private UserEntity userDtoToEntity(UserDto user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .age(user.getAge())
                .build();
    }


    
}
