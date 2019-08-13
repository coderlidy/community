package life.lidy.community.controller;

import life.lidy.community.dto.CommentDTO;
import life.lidy.community.dto.ResultDTO;
import life.lidy.community.enums.CommentTypeEnum;
import life.lidy.community.exception.CustomizeErrorCode;
import life.lidy.community.mapper.CommentMapper;
import life.lidy.community.model.Comment;
import life.lidy.community.model.User;
import life.lidy.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value ="/comment",method = RequestMethod.POST)//post 进来一个json
    public Object post(@RequestBody CommentDTO commentCreateDTO,
                       HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentid(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getAccountId());
        comment.setLikeCount(0L);

        commentService.insert(comment,user);
//        Map<Object,Object> objectObjectHashMap=new HashMap<>();
//        objectObjectHashMap.put("message","成功");
//
        return ResultDTO.okOf();
    }
    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
