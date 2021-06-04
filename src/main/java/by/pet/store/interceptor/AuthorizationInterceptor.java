package by.pet.store.interceptor;

import by.pet.store.storage.inmemory.TokenStorageInMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenStorageInMemory tokenStorage;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("X-Api-Token");
        if (tokenStorage.validate(header)) {
            return true;
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalidate token");
            return false;
        }
    }
}
