package org.labsse.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.labsse.LoginType;
import org.labsse.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lijiechu
 * @create on 2018/12/27
 * @description
 */
@Component
public class LabSSESuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * SpringBoot容器启动的时候会自动初始化一个ObjectMapper Bean
     */
    @Autowired
    private ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 自定义登录成功的响应逻辑
     * @param request
     * @param response
     * @param authentication authentication是SpringSecurity重要的接口，通过传不同的实现类可以实现返回信息的定制化
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        logger.info("登录成功");

        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {

            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));

        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
