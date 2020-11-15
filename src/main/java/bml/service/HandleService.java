package bml.service;

import bml.entity.Handle;
import bml.entity.Note;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 边月白
 * @since 2020-05-17
 */
public interface HandleService extends IService<Handle> {

    /**
     * 自定义分页
     * @param page page
     * @param limit limit
     * @param id 假条id
     * @return 集合
     */
    List<Handle> handleList(Integer page, Integer limit, Integer id);

}
