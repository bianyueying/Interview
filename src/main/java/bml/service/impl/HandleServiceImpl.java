package bml.service.impl;

import bml.entity.Handle;
import bml.mapper.HandleMapper;
import bml.service.HandleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 边月白
 * @since 2020-05-17
 */
@Service
public class HandleServiceImpl extends ServiceImpl<HandleMapper, Handle> implements HandleService {

    @Resource
    HandleMapper handleMapper;

    @Override
    public List<Handle> handleList(Integer page, Integer limit, Integer id) {
        PageHelper.startPage(page,limit);
        QueryWrapper<Handle> wrapper = new QueryWrapper<>();
        wrapper.eq("note_id", id);
        return handleMapper.selectList(wrapper);
    }

}
