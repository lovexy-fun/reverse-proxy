package fun.lovexy.reverseproxy.aspect;

import fun.lovexy.reverseproxy.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
@Aspect
public class ControllerAspect {

    @Pointcut("execution(* fun.lovexy.reverseproxy.controller..*(..))")
    public void requestLog() {
    }

    @Around("requestLog()")
    public Object around(ProceedingJoinPoint pjp) {
        long beginTime = System.currentTimeMillis();

        HttpServletRequest request = ServletUtils.getRequest();
        String addr = request.getRemoteAddr();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String methodName = pjp.getSignature().getName();
        String clazzName = pjp.getTarget().getClass().getName();
        String[] argNames = ((MethodSignature) pjp.getSignature()).getParameterNames();
        Object[] args = pjp.getArgs();

        Object proceed = null;
        try {
            proceed = pjp.proceed();
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
        }

        long endTime = System.currentTimeMillis();
        log.info(String.format("[%s][%s] from [%s] - %s::%s(%s) takes %d ms",
                method,
                uri,
                addr,
                clazzName,
                methodName,
                argsToString(argNames, args),
                endTime - beginTime));
        return proceed;
    }

    private String argsToString(String[] argNames, Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < argNames.length; i++) {
            sb.append(argNames[i]);
            sb.append("=");
            if (args[i] instanceof HttpSession
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof ServletRequest
                    || args[i] instanceof ModelMap) {
                sb.append(args[i].getClass().getSimpleName());
                sb.append("@");
                sb.append(Integer.toHexString(args[i].hashCode()));
            } else {
                sb.append(args[i]);
            }
            sb.append(",");
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
