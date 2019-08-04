package life.lidy.community.community.mapper;

import life.lidy.community.community.dto.QuestionDTO;
import life.lidy.community.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (id,title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) values (#{id},#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void create(Question question);
    @Select("select * from question limit #{i},#{size}")
    List<Question> list(@Param("i") Integer i,@Param("size") Integer size);
    @Select("select count(1) from question")
    Integer count();
    @Select("select * from question where creator = #{account_id} limit #{i},#{size}")
    List<Question> listByAccountId(@Param("account_id")String account_id,@Param("i") Integer i,@Param("size") Integer size);
    @Select("select * from question where id = #{id}")
    Question getById(@Param("id") Integer id);
    @Update("update question set title = #{title},description = #{description},gmt_modified = #{gmtModified},tag = #{tag} where id = #{id}")
    void update(Question question);
}
