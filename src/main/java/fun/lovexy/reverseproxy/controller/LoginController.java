package fun.lovexy.reverseproxy.controller;

import fun.lovexy.reverseproxy.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @Value("${admin.username}")
    private String username;

    @Value("${admin.password}")
    private String password;

    @GetMapping
    public String loginGet() {
        return "login";
    }

    @PostMapping
    @ResponseBody
    public R<String> loginPost(String username, String password, HttpSession session, HttpServletRequest request) {
        log.info("user login from [{}]", request.getRemoteAddr());
        if(this.username.equals(username) && this.password.equals(password)) {
            session.setAttribute("user", username);
            R<String> success = R.success();
            success.setData("manage");
            return success;
        }
        return R.fail();
    }

}
