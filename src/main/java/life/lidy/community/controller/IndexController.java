package life.lidy.community.controller;

import life.lidy.community.dto.PaginationDTO;
import life.lidy.community.mapper.QuestionMapper;
import life.lidy.community.model.QuestionExample;
import life.lidy.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 */
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name="page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size,
                        @RequestParam(name = "search",required = false)String search){



        Integer totalPage;
        Integer totalCount=(int)questionMapper.countByExample(new QuestionExample());
        //Integer totalCount=questionMapper.count();
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }
        if(page<1)
            page=1;
        if(page>totalPage)
            page=totalPage;
        PaginationDTO pagination=questionService.listAndSearch(search,page,size);
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",search);
        return "index";
    }
}
