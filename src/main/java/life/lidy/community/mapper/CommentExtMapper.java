package life.lidy.community.mapper;

import life.lidy.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment record);
}