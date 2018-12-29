package org.labsse.core.validate.code;

import org.apache.commons.lang.StringUtils;
import org.labsse.core.properties.SecurityProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lijiechu
 * @create on 2018/12/28
 * @description
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<UrlWithHttpMethods> handleUrls = new HashSet<>();

    // @Autowired
    // validateCodeFilter不是一个spring容器管理的bean 因此无法注入
    // initializingBean也无法自动执行
    private SecurityProperties securityProperties;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        // 根据逗号切分url路径
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(
                securityProperties.getValidateCode().getImage().getUrl(), ",");
        for(String configUrl : configUrls) {
            // 根据冒号识别是否配置了具体的http方法
            String[] parts = StringUtils.splitByWholeSeparatorPreserveAllTokens(configUrl, ":");
            String url = parts[0];
            Set<String> methodSet = new HashSet<>();
            if(parts.length == 2) {
                // 根据分号切分http方法
                String[] methods = StringUtils.splitByWholeSeparatorPreserveAllTokens(configUrl, ";");
                for(String method: methods) {
                    methodSet.add(method);
                }
            }
            handleUrls.add(new UrlWithHttpMethods(url, methodSet));
        }

        Set<String> methodSet = new HashSet<>();
        methodSet.add("post");
        handleUrls.add(new UrlWithHttpMethods("/authentication/form", methodSet));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean needToValidate = false;

        AntPathMatcher pathMatcher = new AntPathMatcher();

        for(UrlWithHttpMethods urlWithHttpMethods: handleUrls) {
            if(pathMatcher.match(urlWithHttpMethods.getUrl(), request.getRequestURI())) {
                Set<String> methods = urlWithHttpMethods.getHttpMethods();

                // 如果methods集合为空 默认所有http方法都需要验证
                if(methods.size() <= 0) {
                    needToValidate = true;
                    break;
                }

                // 否则就要判断该method是不是在需要验证的范围之内
                for(String method: methods) {
                    if(StringUtils.equalsIgnoreCase(request.getMethod(), method)) {
                        needToValidate = true;
                    }
                }
            }
        }

        if(needToValidate) {
            try {
                String inputCode = request.getParameter("validateCode");
                validate(request, inputCode);
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    private void validate(HttpServletRequest request, String inputCode) {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);

        if(StringUtils.isBlank(inputCode)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        if(null == codeInSession) {
            throw new ValidateCodeException("验证码不存在");
        }

        if(codeInSession.isExpired()) {
            throw new ValidateCodeException("验证码已过期");
        }

        if(!StringUtils.equalsIgnoreCase(inputCode, codeInSession.getCode())) {
            throw new ValidateCodeException("验证码错误");
        }

        sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
    }
}
