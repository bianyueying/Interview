package bml.controller;


import bml.entity.Handle;
import bml.entity.Note;
import bml.entity.User;
import bml.others.BmlResult;
import bml.service.HandleService;
import bml.service.NoteService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 边月白
 * @since 2020-05-16
 */
@RestController
public class NoteController {

    @Resource
    NoteService noteService;

    @Resource
    HandleService handleService;

    Map<String, Object> resultMap = new HashMap<>(1000);

    @PostMapping("admin/note")
    public BmlResult<Object> addNote(@RequestBody Note note) {
        try {
            noteService.save(note);
            return new BmlResult<>(200,"添加成功！");
        } catch (Exception e) {
            return new BmlResult<>(202,"添加失败！");
        }
    }

    @GetMapping("admin/note")
    public BmlResult<Object> getMyNotes(Integer page, Integer limit) {
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        try {
            resultMap.put("count",noteService.count(new QueryWrapper<Note>().eq("user_id",user.getId())));
            resultMap.put("data",noteService.noteList(page, limit, user.getId()));
            return new BmlResult<>(resultMap,0);
        } catch (Exception e) {
            e.printStackTrace();
            return new BmlResult<>(null,1);
        }
    }

    @GetMapping("admin/note/{id}")
    public Note getOne(@PathVariable String id) {
        return noteService.getById(id);
    }

    @PutMapping("admin/note")
    public BmlResult<Object> updateNote(@RequestBody Note note) {
        try {
            // 修改状态码
            note.setNoteStatus(301);
            noteService.updateById(note);
            return new BmlResult<>(200,"修改成功！");
        } catch (Exception e) {
            return new BmlResult<>(202,"修改失败！");
        }
    }

    @GetMapping("admin/history/{id}")
    public BmlResult<Object> noteHistory(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, @PathVariable String id) {
        try {
            // 获取历史
            resultMap.put("count", handleService.count(new QueryWrapper<Handle>().eq("note_id",Integer.parseInt(id))));
            resultMap.put("data", handleService.handleList(page, limit, Integer.parseInt(id)));
            return new BmlResult<>(resultMap,0);
        } catch (Exception e) {
            e.printStackTrace();
            return new BmlResult<>(null,1);
        }
    }
}
