package com.green.security;

import com.green.exception.AppException;
import com.green.model.User;
import com.green.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.green.constants.LabelKey.ERROR_NOT_EXIST;
import static com.green.constants.LabelKey.LABEL_EMAIL;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_EMAIL))
        );
        return UserDetailsImpl.build(user);
    }
}