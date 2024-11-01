package com.pick.nalsoom.service;

import com.pick.nalsoom.domain.User;
import com.pick.nalsoom.jwt.JwtTokenProvider;
import com.pick.nalsoom.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    UserService userService;

    //각 테스트 전에 수행하는 메서드 -> 의존 관계 주입
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //각 테스트가 끝날 때마다 수행하는 메서드 -> 데이터 초기화
    @AfterEach
    public void afterEach() {

    }

    @Test
    public void 회원가입() {

    }

    @Test
    public void 중복_회원_예외() {

    }

    @Test
    public void 로그인() {

    }

    @Test
    public void 로그인_아이디_비밀번호_불일치횟수_5회_예외() {

    }

}
