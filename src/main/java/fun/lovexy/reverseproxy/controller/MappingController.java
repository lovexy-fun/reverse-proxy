package fun.lovexy.reverseproxy.controller;

import fun.lovexy.reverseproxy.service.MappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/")
public class MappingController {

    @Autowired
    private MappingService mappingService;

    @GetMapping()
    public String mapping(ModelMap modelMap, HttpServletRequest request) {
        log.info("[{}] view mappping", request.getRemoteAddr());
        String host = request.getHeader("host");
        int indexOf = host.indexOf(':');
        String hostname = indexOf == -1 ? host : host.substring(0, indexOf);
        modelMap.addAttribute("hostname", hostname);
        modelMap.addAttribute("mappings", mappingService.enabledList());
        return "mapping";
    }

}
