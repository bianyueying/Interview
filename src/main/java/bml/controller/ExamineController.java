package bml.controller;

import bml.entity.Handle;
import bml.entity.Note;
import bml.entity.User;
import bml.others.BmlResult;
import bml.service.HandleService;
import bml.service.NoteService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 边月白
 * Date 2020/5/17 10:43
 */
@RestController
public class ExamineController {

    @Resource
    NoteService noteService;

    @Resource
    HandleService handleService;

    @GetMapping("/admin/examines")
    public BmlResult<Object> listMyNote(Integer page, Integer limit) {
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        Map<String, Object> resultMap = new HashMap<>(1000);
        try {
            resultMap.put("count",noteService.countByRole(user.getRole()));
            resultMap.put("data", noteService.noteListByUser(page,limit,user.getRole()));
            return new BmlResult<>(resultMap,0);
        } catch (Exception e) {
            e.printStackTrace();
            return new BmlResult<> (null,1);
        }
    }

    @PutMapping("admin/examine")
    public BmlResult<Object> examine(@RequestParam("id") String id) {
        try {
            // 1.驳回则把状态码改成300
            Note note = noteService.getById(id);
            note.setNoteStatus(300);
            noteService.updateById(note);
            // 2.获取当前处理用户
            Subject subject = SecurityUtils.getSubject();
            User user= (User) subject.getPrincipal();
            // 3.添加处理意见
            Handle handle = new Handle();
            handle.setNoteId(Integer.parseInt(id));
            handle.setUsername(user.getUsername());
            handle.setResult("驳回");
            handleService.save(handle);
            return new BmlResult<>(200,"驳回成功");
        } catch (Exception e) {
            return new BmlResult<>(201,"驳回失败");
        }
    }

    @PostMapping("admin/examine/{id}")
    public BmlResult<Object> examineNext(@PathVariable("id") String id) {
        try {
            // 1.同意则把状态码+1
            Note note = noteService.getById(id);
            note.setNoteStatus(note.getNoteStatus()+1);
            noteService.updateById(note);

            // 2.获取当前处理用户
            Subject subject = SecurityUtils.getSubject();
            User user= (User) subject.getPrincipal();

            // 3.添加处理人意见
            Handle handle = new Handle();
            handle.setNoteId(Integer.parseInt(id));
            handle.setUsername(user.getUsername());
            handle.setResult("同意");
            handleService.save(handle);
            return new BmlResult<>(200,"同意成功");
        } catch (Exception e) {
            return new BmlResult<>(201,"同意失败");
        }
    }
}
