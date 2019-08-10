package life.lidy.community.service;

import life.lidy.community.dto.PaginationDTO;
import life.lidy.community.dto.QuestionDTO;
import life.lidy.community.exception.CustomizeErrorCode;
import life.lidy.community.exception.CustomizeException;
import life.lidy.community.mapper.QuestionExtMapper;
import life.lidy.community.mapper.QuestionMapper;
import life.lidy.community.mapper.UserMapper;
import life.lidy.community.model.Question;
import life.lidy.community.model.QuestionExample;
import life.lidy.community.model.User;
import life.lidy.community.model.UserExample;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    public PaginationDTO list(Integer page, Integer size) {
        //List<Question> questions=questionMapper.list(size*(page-1),size);
        QuestionExample questionExample=new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");
        List<Question> questions=questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(size*(page-1),size));

        List<QuestionDTO> questionDTOList=new ArrayList<>();
        PaginationDTO paginationDTO=new PaginationDTO();
        for(Question question:questions){
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(question.getCreator());
            User user=userMapper.selectByExample(userExample).get(0);
           // User user=userMapper.findByAccountId(question.getCreator());
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
        paginationDTO.setData(questionDTOList);
        paginationDTO.setPagination((int)questionMapper.countByExample(new QuestionExample()),page,size);
        return paginationDTO;
    }

   public PaginationDTO list(String accountId,Integer page,Integer size){
       //List<Question> questions=questionMapper.listByAccountId(accountId,size*(page-1),size);
       QuestionExample example = new QuestionExample();
       example.createCriteria().andCreatorEqualTo(accountId);
       List<Question> questions=questionMapper.selectByExampleWithRowbounds(example,new RowBounds(size*(page-1),size));
       List<QuestionDTO> questionDTOList=new ArrayList<>();
       PaginationDTO paginationDTO=new PaginationDTO();
       for(Question question:questions){
           QuestionDTO questionDTO=new QuestionDTO();
           BeanUtils.copyProperties(question,questionDTO);
           UserExample userExample = new UserExample();
           userExample.createCriteria().andAccountIdEqualTo(question.getCreator());
           User user=userMapper.selectByExample(userExample).get(0);
           //User user=userMapper.findByAccountId(question.getCreator());
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
       paginationDTO.setData(questionDTOList);

       //paginationDTO.setPagination(questionMapper.count(),page,size);
       paginationDTO.setPagination((int)questionMapper.countByExample(new QuestionExample()),page,size);
       return paginationDTO;
   }

    public QuestionDTO getById(Long id) {
        //Question question=questionMapper.getById(id);
        Question question=questionMapper.selectByPrimaryKey(id);
        if(question==null)
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(question.getCreator());
        List<User> users=userMapper.selectByExample(userExample);
        //User user=userMapper.findByAccountId(question.getCreator());
        questionDTO.setUser(users.get(0));
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);

            questionMapper.insert(question);
            //questionMapper.create(question);
        }else {
            question.setGmtModified(question.getGmtCreate());
            QuestionExample example=new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updated=questionMapper.updateByExampleSelective(question,example);
            //questionMapper.update(question);
            if(updated!=1)
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
    }

    public void incView(Long id) {
        Question question=new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(queryDTO.getTag(), ",");
        //格式化
        String regexpTag = Arrays
                .stream(tags)
                .filter(StringUtils::isNotBlank)
                .map(t -> t.replace("+", "").replace("*", ""))
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining("|"));

        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);

        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }
}
