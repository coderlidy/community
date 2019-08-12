package life.lidy.community.controller;

import life.lidy.community.dto.AccessTokenDTO;
import life.lidy.community.dto.GithubUser;
import life.lidy.community.mapper.UserMapper;
import life.lidy.community.model.User;
import life.lidy.community.model.UserExample;
import life.lidy.community.provider.GithubProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * created by lidy
 */
@Controller
@Slf4j
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        System.out.println("AuthorizeController");
        //设置对象模型
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken=githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser=githubProvider.getUser(accessToken);
        if(githubUser!=null && githubUser.getId()!=null){
            //登录成功
            User user=new User();
            String token=UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatar_url());
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
            if(userMapper.selectByExample(userExample).get(0)==null)
                userMapper.insert(user);
            else{
                //更新用户token 用token密钥来验证登录而不是AccountId
                UserExample userExample1 = new UserExample();
                userExample1.createCriteria().andAccountIdEqualTo(user.getAccountId());
                userMapper.updateByExampleSelective(user,userExample1);
                //userMapper.updateTokenByAccountId(user.getAccountId(),user.getToken());
            }
            System.out.println(user.getName());
            //写cookie和session
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else{
            //登录失败
            log.error("callback get github error,{}", githubUser);
            return "redirect:/";
        }
    }
    @GetMapping("logout")
    private  String logout(HttpServletRequest request,
                           HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
