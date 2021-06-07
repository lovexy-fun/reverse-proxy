package fun.lovexy.reverseproxy.exception;

import fun.lovexy.reverseproxy.common.R;
import fun.lovexy.reverseproxy.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(Exception e, HttpServletResponse response){
        log.error(e.getMessage(), e);
        R<String> excep = R.exception();
        excep.setData(e.getMessage());
        ServletUtils.render(response, excep.toString());
    }
}
