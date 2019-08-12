package life.lidy.community.advice;

import com.alibaba.fastjson.JSON;
import life.lidy.community.dto.ResultDTO;
import life.lidy.community.exception.CustomizeErrorCode;
import life.lidy.community.exception.CustomizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@ControllerAdvice
//@Slf4j
//public class CustomizeExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
//        String contentType=request.getContentType();
//        if ("application/json".equals(contentType)) {
//            // 返回 JSON
//            ResultDTO resultDTO;
//            if (e instanceof CustomizeException) {
//                resultDTO = ResultDTO.errorOf((CustomizeException) e);
//            } else {
//                log.error("handle error", e);
//                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
//            }
//            try {
//                response.setContentType("application/json");
//                response.setStatus(200);
//                response.setCharacterEncoding("UTF-8");
//                PrintWriter writer=response.getWriter();
//                writer.write(JSON.toJSONString(resultDTO));
//                writer.close();
//            } catch (IOException ex) {
//            }
//            return null;
//        }else{
//            //错误页面
//            if (e instanceof CustomizeException) {
//                model.addAttribute("message",e.getMessage());
//            } else {
//                log.error("handle error", e);
//                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
//            }
//            return new ModelAndView("error");
//        }
//    }
//    private HttpStatus getStatus(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if (statusCode == null) {
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return HttpStatus.valueOf(statusCode);
//    }
//}