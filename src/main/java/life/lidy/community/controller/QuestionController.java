package life.lidy.community.controller;

import life.lidy.community.dto.QuestionDTO;
import life.lidy.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model){
        QuestionDTO questionDTO=questionService.getById(id);
        if(questionDTO.getCommentCount()==null){
            questionDTO.setCommentCount(0);
        }
        if(questionDTO.getLikeCount()==null){
            questionDTO.setLikeCount(0);
        }
        if(questionDTO.getViewCount()==null){
            questionDTO.setViewCount(0);
        }
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
