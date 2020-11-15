package bml.service;

import bml.entity.Note;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 边月白
 * @since 2020-05-16
 */
public interface NoteService extends IService<Note> {

    /**
     * 自定义分页
     * @param page page
     * @param limit limit
     * @param id 用户id
     * @return 集合
     */
    List<Note> noteList(Integer page, Integer limit, Integer id);


    /**
     * 根据用户角色查询出需要审核的集合
     * @param page page
     * @param limit limit
     * @param roleId 自己的权限
     * @return 集合
     */
    List<Note> noteListByUser(Integer page, Integer limit, Integer roleId);


    /**
     * 根据角色id查询出需要审核的数量
     * @param roleId 角色id
     * @return 数量
     */
    Integer countByRole(Integer roleId);


}
