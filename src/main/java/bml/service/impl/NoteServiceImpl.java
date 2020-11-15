package bml.service.impl;

import bml.entity.Note;
import bml.mapper.NoteMapper;
import bml.service.NoteService;
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
 * @since 2020-05-16
 */
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {

    @Resource
    NoteMapper noteMapper;

    final static int ROLE_ONE = 1;
    final static int ROLE_TWO = 2;
    final static int ROLE_THREE = 3;

    @Override
    public List<Note> noteList(Integer page, Integer limit, Integer id) {
        PageHelper.startPage(page,limit);
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        return noteMapper.selectList(wrapper);
    }

    @Override
    public List<Note> noteListByUser(Integer page, Integer limit, Integer roleId) {
        PageHelper.startPage(page,limit);
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        switch (roleId) {
            case ROLE_ONE:
                wrapper.eq("note_status", 301);
                break;
            case ROLE_TWO:
                wrapper.eq("note_status", 302);
                break;
            case ROLE_THREE:
                wrapper.eq("note_status", 303);
                break;
            default:
                wrapper.eq("note_status", 400);
                break;
        }
        return noteMapper.selectList(wrapper);
    }

    @Override
    public Integer countByRole(Integer roleId) {
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        switch (roleId) {
            case ROLE_ONE:
                wrapper.eq("note_status", 301);
                break;
            case ROLE_TWO:
                wrapper.eq("note_status", 302);
                break;
            case ROLE_THREE:
                wrapper.eq("note_status", 303);
                break;
            default:
                wrapper.eq("note_status", 400);
                break;
        }
        return noteMapper.selectCount(wrapper);
    }

}
