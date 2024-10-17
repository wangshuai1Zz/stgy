package WebAdmin.security;
import Common.exception.MyApiError;
import Common.utils.ThreadUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import Common.utils.jwtUtils;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final String TOKEN_HEADER = "access-token";

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response,@NonNull Object handler) {
        String token = request.getHeader(TOKEN_HEADER);
        if (token != null ) {
            Jws<Claims> claim = jwtUtils.parseClaim(token);
            Claims payload = claim.getPayload();
            Long password = payload.get("UserId", Long.class);
            ThreadUtils.setLocal(password);
            return true;
        }
        // JWT验证失败
        throw new MyApiError("err",200);
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) {
        ThreadUtils.delLocal();
    }
}