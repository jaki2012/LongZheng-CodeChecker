package org.labsse.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author lijiechu
 * @create on 2018/12/26
 * @description
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录用户名：" + username);
        // 根据用户名查找用户信息
        // 根据查找到的用户信息判断用户是否被冻结
        logger.info(new BCryptPasswordEncoder().encode("12345"));
        // $2a$10$JfjakbKmiJTeNhjkg/HFNuMU/zzBhk1qisrRoDWIwXLyGi3IFPZ.K
//        return new User(username, new BCryptPasswordEncoder().encode("12345"), true, true, true, true,
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return new User(username, "$2a$10$JfjakbKmiJTeNhjkg/HFNuMU/zzBhk1qisrRoDWIwXLyGi3IFPZ.K", true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
