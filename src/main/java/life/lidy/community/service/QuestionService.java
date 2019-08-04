package life.lidy.community.community.service;

import life.lidy.community.community.dto.PaginationDTO;
import life.lidy.community.community.dto.QuestionDTO;
import life.lidy.community.community.mapper.QuestionMapper;
import life.lidy.community.community.mapper.UserMapper;
import life.lidy.community.community.model.Question;
import life.lidy.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    public PaginationDTO list(Integer page, Integer size) {
        List<Question> questions=questionMapper.list(size*(page-1),size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        PaginationDTO paginationDTO=new PaginationDTO();
        for(Question question:questions){
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            User user=userMapper.findByAccountId(question.getCreator());
            questionDTO.setUser(user);
            if(questionDTO.getCommentCount()==null){
                questionDTO.setCommentCount(0);
            }
            if(questionDTO.getLikeCount()==null){
                questionDTO.setLikeCount(0);
            }
            if(questionDTO.getViewCount()==null){
                questionDTO.setViewCount(0);
            }
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOList);
        paginationDTO.setPagination(questionMapper.count(),page,size);
        return paginationDTO;
    }

   public PaginationDTO list(String accountId,Integer page,Integer size){
       List<Question> questions=questionMapper.listByAccountId(accountId,size*(page-1),size);
       List<QuestionDTO> questionDTOList=new ArrayList<>();
       PaginationDTO paginationDTO=new PaginationDTO();
       for(Question question:questions){
           QuestionDTO questionDTO=new QuestionDTO();
           BeanUtils.copyProperties(question,questionDTO);
           User user=userMapper.findByAccountId(question.getCreator());
           questionDTO.setUser(user);
           if(questionDTO.getCommentCount()==null){
               questionDTO.setCommentCount(0);
           }
           if(questionDTO.getLikeCount()==null){
               questionDTO.setLikeCount(0);
           }
           if(questionDTO.getViewCount()==null){
               questionDTO.setViewCount(0);
           }
           questionDTOList.add(questionDTO);
       }
       paginationDTO.setQuestionDTOS(questionDTOList);
       paginationDTO.setPagination(questionMapper.count(),page,size);
       return paginationDTO;
   }

    public QuestionDTO getById(Integer id) {
        Question question=questionMapper.getById(id);
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=userMapper.findByAccountId(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else {
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }
}
