package life.lidy.community.community.controller;

import life.lidy.community.community.dto.QuestionDTO;
import life.lidy.community.community.mapper.QuestionMapper;
import life.lidy.community.community.model.Question;
import life.lidy.community.community.service.QuestionService;
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
    public String question(@PathVariable(name = "id") Integer id, Model model){
        QuestionDTO questionDTO=questionService.getById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
