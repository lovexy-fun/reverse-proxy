package fun.lovexy.reverseproxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.lovexy.reverseproxy.common.DataStatus;
import fun.lovexy.reverseproxy.entity.Access;
import fun.lovexy.reverseproxy.mapper.AccessMapper;
import fun.lovexy.reverseproxy.service.AccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class AccessServiceImpl extends ServiceImpl<AccessMapper, Access> implements AccessService {

    @Override
    public List<Access> enListByMId(int mappingId) {
        return this.query()
                .and(i -> i
                        .eq(Access.MAPPING_ID, mappingId)
                        .eq(Access.STATUS, DataStatus.NORMAL))
                .orderByAsc(Access.ALLOW_IP)
                .list();
    }

    @Override
    public List<Access> listByMId(int mappingId) {
        return this.query()
                .eq(Access.MAPPING_ID, mappingId)
                .orderByAsc(Access.ALLOW_IP)
                .list();
    }

    @Transactional
    @Override
    public boolean remove(Access access) {
        QueryWrapper<Access> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i
                .eq(Access.MAPPING_ID, access.getMappingId())
                .eq(Access.ALLOW_IP, access.getAllowIp())
        );
        return this.remove(wrapper);
    }

}




