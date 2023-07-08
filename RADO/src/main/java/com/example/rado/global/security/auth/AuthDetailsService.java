package com.example.onepiece.User.global.security.auth;

import com.example.onepiece.User.domain.entity.User;
import com.example.onepiece.User.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("아이디를 찾을 수 없습니다."));
        return new AuthDetails(user);
    }
}
