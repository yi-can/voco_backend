package com.voco_task.service;


import com.voco_task.dto.LoginRequestDto;
import com.voco_task.dto.RegisterRequestRestaurantDto;
import com.voco_task.dto.RegisterRequestUserDto;
import com.voco_task.exceptions.AuthServiceException;
import com.voco_task.exceptions.ErrorType;
import com.voco_task.mapper.IAuthMapper;
import com.voco_task.rabbitmq.model.AuthRestaurantModel;
import com.voco_task.rabbitmq.producer.AuthRestaurantProducer;
import com.voco_task.repository.entity.Auth;
import com.voco_task.repository.IAuthRepository;
import com.voco_task.repository.enums.ERole;
import com.voco_task.repository.enums.EStatus;
import com.voco_task.utility.JwtTokenManager;
import com.voco_task.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, String> {
    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final AuthRestaurantProducer authRestaurantProducer;


    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager, AuthRestaurantProducer authRestaurantProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.authRestaurantProducer = authRestaurantProducer;
    }

    public Boolean registerUser(RegisterRequestUserDto dto) {
        Auth auth = IAuthMapper.INSTANCE.toRegisterUser(dto);
        auth.setRole(ERole.USER);
        save(auth);
        return true;
    }

    public Boolean registerRestaurant(RegisterRequestRestaurantDto dto) {
        Auth auth = IAuthMapper.INSTANCE.toRegisterRestaurant(dto);
        auth.setRole(ERole.RESTAURANT);
        save(auth);
        authRestaurantProducer.createRestaurant(AuthRestaurantModel.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .avatar(dto.getAvatar()).build());
        return true;
    }

    public String login(LoginRequestDto dto) {
        Optional<Auth> authOptional = authRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (authOptional.isEmpty())
            throw new AuthServiceException(ErrorType.LOGIN_ERROR);
        if (authOptional.get().getStatus().equals(EStatus.INACTIVE)) {
            throw new AuthServiceException(ErrorType.STATUS_NOT_ACTIVE);
        }
        return jwtTokenManager.createToken(authOptional.get().getAuthId(), authOptional.get().getRole())
                .orElseThrow(() -> new AuthServiceException(ErrorType.TOKEN_NOT_CREATED));
    }
}
