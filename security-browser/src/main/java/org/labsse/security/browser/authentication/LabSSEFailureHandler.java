package org.labsse.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.labsse.LoginType;
import org.labsse.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
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
public class LabSSEFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

//    {
//        this.setUseForward(true);
//    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(
                    objectMapper.writeValueAsString(exception.getMessage())
            );
        } else {
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.getWriter().print("suck why no html");
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
