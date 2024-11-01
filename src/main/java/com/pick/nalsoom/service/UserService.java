package com.pick.nalsoom.service;

import java.util.Optional;

import com.pick.nalsoom.utils.InvalidTokenException;
import com.pick.nalsoom.utils.NoSuchUserException;
import com.pick.nalsoom.utils.UserDuplicateException;
import org.apache.el.parser.TokenMgrError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pick.nalsoom.domain.User;
import com.pick.nalsoom.dto.UserDto;
import com.pick.nalsoom.repository.UserRepository;
import com.pick.nalsoom.jwt.JwtToken;
import com.pick.nalsoom.jwt.JwtTokenProvider;
import com.pick.nalsoom.jwt.UserDetailsImpl;

@Service("userService")
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 아이디 중복검사
    public void idDuplicateCheck(String userId) {
        userRepository.findByUserId(userId).ifPresent(user -> {
            throw new UserDuplicateException("User Already Exist" + user.getUserId());
        });
    }
    
    // 회원가입
    public void join(UserDto userDto) {
        idDuplicateCheck(userDto.getUserId());
        String encodedPassword = passwordEncoder.encode(userDto.getUserPw());
        userDto.setUserPw(encodedPassword);
        userRepository.save(userDto.toEntity());
    }

    // 로그인
    public JwtToken login(UserDto userDto) {
        Optional<User> user = userRepository.findByUserId(userDto.getUserId());
        user.orElseThrow(() -> new NoSuchUserException("No such User"));

        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user.get());
        boolean isMatch = passwordEncoder.matches(userDto.getUserPw(), userDetailsImpl.getPassword());

        if(!isMatch) {
            throw new InvalidTokenException("Invalid Token");
        }

        return jwtTokenProvider.createJwtToken(userDetailsImpl);
    }

    //ID로 userProperNum 검색
    public Long getUserProperNum(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new NoSuchUserException("No Such User"))
                .getUserProperNum();
    }
}
