package com.pick.nalsoom.Service.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pick.nalsoom.Domain.User.User;
import com.pick.nalsoom.Dto.User.UserDto;
import com.pick.nalsoom.Repository.User.UserRepository;
import com.pick.nalsoom.jwt.JwtToken;
import com.pick.nalsoom.jwt.JwtTokenProvider;
import com.pick.nalsoom.jwt.UserDetailsImpl;

@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Value("${custom.jwt.secret}")
    private String jwtSecretKey;

    // 아이디 중복검사
    public Boolean idDuplicateCheck(String userId) {
        Optional<User> result = userRepository.findByUserId(userId);
        if (result.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
    // 회원가입
    public Boolean join(UserDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getUserPw());
        userDto.setUserPw(encodedPassword);
        User result = userRepository.save(userDto.toEntity());
        if (result != null) {
            return true;
        } else {
            return false;
        }
    }

    // 로그인
    public JwtToken login(UserDto userDto) {
        Optional<User> user = userRepository.findByUserId(userDto.getUserId());
        if (user.isPresent()) {
            UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user.get());
            if (passwordEncoder.matches(userDto.getUserPw(), userDetailsImpl.getPassword())) {
                return jwtTokenProvider.createJwtToken(userDetailsImpl);
            }
        }
        return null;
    }

    //ID로 userProperNum 검색
    public Long getUserProperNum(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        return user.get().getUserProperNum();
    }
}
