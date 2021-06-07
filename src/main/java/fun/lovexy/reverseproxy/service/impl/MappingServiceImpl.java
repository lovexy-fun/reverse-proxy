package fun.lovexy.reverseproxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.lovexy.reverseproxy.common.DataStatus;
import fun.lovexy.reverseproxy.entity.Access;
import fun.lovexy.reverseproxy.entity.Mapping;
import fun.lovexy.reverseproxy.service.AccessService;
import fun.lovexy.reverseproxy.service.MappingService;
import fun.lovexy.reverseproxy.mapper.MappingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class MappingServiceImpl extends ServiceImpl<MappingMapper, Mapping> implements MappingService{

    @Autowired
    private AccessService accessService;

    @Override
    public List<Mapping> enabledList() {
        return this.query()
                .eq(Mapping.STATUS, DataStatus.NORMAL)
                .orderByAsc(Mapping.ID)
                .list();
    }

    @Override
    @Transactional
    public boolean removeByIdCascade(int id) {
        QueryWrapper<Access> wrapper = new QueryWrapper<>();
        wrapper.eq(Access.MAPPING_ID, id);
        accessService.remove(wrapper);
        return removeById(id);
    }
}




