package fun.lovexy.reverseproxy.controller;

import fun.lovexy.reverseproxy.common.DataStatus;
import fun.lovexy.reverseproxy.common.R;
import fun.lovexy.reverseproxy.entity.Mapping;
import fun.lovexy.reverseproxy.service.MappingService;
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
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    private MappingService mappingService;

    @GetMapping
    public String manageGet() {
        return "manage";
    }

    @PostMapping
    @ResponseBody
    public R<List<Mapping>> managePost() {
        R<List<Mapping>> success = R.success();
        success.setData(mappingService.list());
        return success;
    }

    @GetMapping("/save")
    public String saveGet(Mapping mapping, ModelMap modelMap) {
        if (mapping.getId() != null) {
            modelMap.addAttribute("mapping", mappingService.getById(mapping.getId()));
        }
        return "saveMapping";
    }

    @PostMapping("/save")
    @ResponseBody
    public R<String> savePost(Mapping mapping) {
        if (mapping.getId() == null) {
            mapping.setStatus(DataStatus.NORMAL);
            mapping.setCreateTimestamp(new Date());
        } else {
            mapping.setModifyTimestamp(new Date());
        }
        if (mappingService.saveOrUpdate(mapping)) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    @RequestMapping("/update_status")
    @ResponseBody
    public R<String> updateStatus(int id, int status) {
        Mapping mapping = new Mapping();
        mapping.setId(id);
        mapping.setStatus(status);
        mapping.setModifyTimestamp(new Date());
        if (mappingService.updateById(mapping)) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public R<String> delete(int id) {
        if (mappingService.removeByIdCascade(id)) {
            return R.success();
        } else {
            return R.fail();
        }
    }

}
