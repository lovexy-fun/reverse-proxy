package fun.lovexy.reverseproxy.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import fun.lovexy.reverseproxy.entity.Access;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AccessService extends IService<Access> {

    List<Access> enListByMId(int mappingId);

    List<Access> listByMId(int mappingId);

    boolean remove(Access access);
}
