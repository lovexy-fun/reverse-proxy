package fun.lovexy.reverseproxy.service;

import fun.lovexy.reverseproxy.entity.Mapping;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MappingService extends IService<Mapping> {

    List<Mapping> enabledList();

    boolean removeByIdCascade(int id);

}
