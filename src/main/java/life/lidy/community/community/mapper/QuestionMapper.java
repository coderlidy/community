package life.lidy.community.community.mapper;

import life.lidy.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (id,title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) values (#{id},#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void create(Question question);
    @Select("select * from question limit #{i},#{size}")
    List<Question> list(@Param("i") Integer i,@Param("size") Integer size);
    @Select("select count(1) from question")
    Integer count();
}
