package life.lidy.community.controller;

import com.alibaba.fastjson.JSON;
import life.lidy.community.cache.TagCache;
import life.lidy.community.dto.QuestionDTO;
import life.lidy.community.dto.ResultDTO;
import life.lidy.community.exception.CustomizeErrorCode;
import life.lidy.community.model.Question;
import life.lidy.community.model.User;
import life.lidy.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;
    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name="id") Long id,
                       HttpServletRequest request,
                       HttpServletResponse response,
                       Model model){
        QuestionDTO question=questionService.getById(id);
        User user=(User)request.getSession().getAttribute("user");
        if(question==null ||question.getUser()==null || user!=null ||
                question.getUser().getAccountId()!=user.getAccountId()){
            model.addAttribute("message",CustomizeErrorCode.INVALID_OPERATION.getMessage());
            return "error";
        }
        if(question.getCommentCount()==null){
            question.setCommentCount(0);
        }
        if(question.getLikeCount()==null){
            question.setLikeCount(0);
        }
        if(question.getViewCount()==null){
            question.setViewCount(0);
        }
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "id",required = false) Long id,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags", TagCache.get());
        if(title ==null || title.trim().equals("")){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if(description ==null || description.trim().equals("")){
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if(tag ==null || tag.trim().equals("")){
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalid)) {
            model.addAttribute("error", "输入非法标签:" + invalid);
            return "publish";
        }

        User user=(User)request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setTag(tag);
        question.setDescription(description);
        question.setCreator(user.getAccountId());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
