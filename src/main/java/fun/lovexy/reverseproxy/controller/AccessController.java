package fun.lovexy.reverseproxy.controller;

import fun.lovexy.reverseproxy.common.DataStatus;
import fun.lovexy.reverseproxy.common.R;
import fun.lovexy.reverseproxy.entity.Access;
import fun.lovexy.reverseproxy.service.AccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/access")
public class AccessController {

    @Autowired
    private AccessService accessService;

    @GetMapping
    public String accessGet(int id, ModelMap modelMap) {
        modelMap.addAttribute("accesses", accessService.enListByMId(id));
        return "access";
    }

    @PostMapping
    @ResponseBody
    public R<List<Access>> accessPost(int mappingId) {
        List<Access> list = accessService.listByMId(mappingId);
        R<List<Access>> success = R.success();
        success.setData(list);
        return success;
    }

    @GetMapping("/manage")
    public String accessManage() {
        return "accessManage";
    }

    @PostMapping("/add")
    @ResponseBody
    public R<String> addPost(Access access) {
        access.setStatus(DataStatus.NORMAL);
        access.setCreateTimestamp(new Date());
        if (accessService.save(access)) {
            return R.success();
        } else {
            return R.error();
        }
    }

    @RequestMapping("/del")
    @ResponseBody
    public R<String> del(Access access) {
        if (accessService.remove(access)) {
            return R.success();
        } else {
            return R.error();
        }
    }

}
