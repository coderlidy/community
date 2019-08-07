package life.lidy.community.provider;

import com.alibaba.fastjson.JSON;
import life.lidy.community.dto.AccessTokenDTO;
import life.lidy.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        System.out.println("GithubProvider");
        //使用OKhttp来模拟post请求
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            //拿到Response回来的数据access_token=08a6561530e319836af0b825f4a9c35105f7de7d&scope=user&token_type=bearer
            String string = response.body().string();
            String token=string.split("&")[0].split("=")[1];
            System.out.println(string);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public GithubUser getUser(String accessToken){
        //使用OKhttp来模拟get请求
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string=response.body().string();
            //获取到的json数据转换为实体对象
            GithubUser githubUser=JSON.parseObject(string,GithubUser.class);//.class获得类型
            return githubUser;
        } catch (IOException e) {
            return null;
        }
    }
}
