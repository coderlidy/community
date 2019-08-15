package life.lidy.community.controller;

import life.lidy.community.dto.NotificationDTO;
import life.lidy.community.dto.PaginationDTO;
import life.lidy.community.model.User;
import life.lidy.community.service.NotificationService;
import life.lidy.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/profile/{action}")
    public String publish(@PathVariable(name="action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name="page",defaultValue = "1")Integer page,
                          @RequestParam(name = "size",defaultValue = "10")Integer size){
        User user=(User)request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
            PaginationDTO pagination=questionService.list(user.getAccountId(),page,size);
            model.addAttribute("pagination",pagination);
        }else if("replies".equals(action)){
            PaginationDTO pagination=notificationService.list("replies",Long.valueOf(user.getAccountId()),page,size);
            model.addAttribute("section","replies");
            model.addAttribute("pagination",pagination);
            model.addAttribute("sectionName","收到回复");
        }else if("mycomments".equals(action)){
            PaginationDTO pagination=notificationService.list("mycomments",Long.valueOf(user.getAccountId()),page,size);
            model.addAttribute("section","mycomments");
            model.addAttribute("pagination",pagination);
            model.addAttribute("sectionName","我的动态");
        }

        return "profile";
    }
}
