package life.lidy.community.controller;

import life.lidy.community.dto.CommentDTO;
import life.lidy.community.dto.ResultDTO;
import life.lidy.community.exception.CustomizeErrorCode;
import life.lidy.community.mapper.CommentMapper;
import life.lidy.community.model.Comment;
import life.lidy.community.model.User;
import life.lidy.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value ="/comment",method = RequestMethod.POST)//post 进来一个json
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentid(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModefied(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getAccountId());
        comment.setLikeCount(0L);
        commentService.insert(comment);
//        Map<Object,Object> objectObjectHashMap=new HashMap<>();
//        objectObjectHashMap.put("message","成功");
//
        return ResultDTO.okOf();
    }
}
