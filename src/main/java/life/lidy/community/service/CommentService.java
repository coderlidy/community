package life.lidy.community.service;

import life.lidy.community.dto.CommentDTO;
import life.lidy.community.enums.CommentTypeEnum;
import life.lidy.community.enums.NotificationStatusEnum;
import life.lidy.community.enums.NotificationTypeEnum;
import life.lidy.community.exception.CustomizeErrorCode;
import life.lidy.community.exception.CustomizeException;
import life.lidy.community.mapper.*;
import life.lidy.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;
    @Autowired
    private NotificationMapper notificationMapper;
    @Transactional
    public void  insert(Comment comment, User commentator) {
        if(comment.getParentid()==null || comment.getParentid()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null||!CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            // 回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentid());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentid());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            comment.setCommentCount(0);
            commentMapper.insert(comment);
            //增加评论数
            Comment parentComment=new Comment();
            parentComment.setId(comment.getParentid());
            parentComment.setCommentCount(1);
            commentExtMapper.incCommentCount(parentComment);
            //评论通知
            createNotify(comment,Long.valueOf(dbComment.getCommentator()),commentator.getName(), question.getTitle(),question.getId(), NotificationTypeEnum.REPLY_COMMENT);
        }else {
            // 回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentid());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            comment.setCommentCount(0);
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
            //评论通知
            createNotify(comment,Long.valueOf(question.getCreator()), commentator.getName(), question.getTitle(), question.getId(),NotificationTypeEnum.REPLY_QUESTION);
        }
    }

    private void createNotify(Comment comment, Long receiver, String notifierName, String outerTitle, Long outerId,NotificationTypeEnum notificationType) {
        if(receiver==Long.valueOf(comment.getCommentator())){
            return;
        }
        //评论通知
        Notification notification=new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        //设置评论的id
        notification.setOuterid(outerId);
        //设置评论者id
        notification.setNotifier(Long.valueOf(comment.getCommentator()));
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        if(notification.getNotifier().equals(notification.getReceiver())){
            notification.setStatus(NotificationStatusEnum.READ.getStatus());
        }else {
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        }
        //设置被评论者id
        notificationMapper.insert(notification);
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentidEqualTo(id).andTypeEqualTo(type.getType());
        commentMapper.selectByExample(commentExample);
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments=commentMapper.selectByExample(commentExample);
        if(comments.size()==0){
            return new ArrayList<>();
        }
        //lambda
        // 获取去重的评论人
        Set<String> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<String> userAccountIds = new ArrayList();
        userAccountIds.addAll(commentators);
        // 获取评论人并转换为 Map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdIn(userAccountIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<String, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getAccountId(), user -> user));
        // 转换 comment 为 commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }
}
