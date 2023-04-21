package com.zhbit.noticebackend.mapper;

import com.zhbit.noticebackend.entity.NoticeRecord;
import com.zhbit.noticebackend.entity.NoticeType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoticeRecordMapper {

    @Select("SELECT * FROM Notice_Record WHERE id = #{id}")
    @Results(id = "noticeRecordMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "editor", column = "editor"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "typeId", column = "type_id"),
            @Result(property = "noticeType", column = "type_id", javaType = NoticeType.class,
                    one = @One(select = "com.zhbit.noticebackend.mapper.NoticeTypeMapper.findById"))
    })
    NoticeRecord findById(@Param("id") Integer id);

    @Select("SELECT * FROM Notice_Record")
    @ResultMap("noticeRecordMap")
    List<NoticeRecord> findAll();

    @Insert("INSERT INTO Notice_Record(title, content, editor, create_time, type_id) " +
            "VALUES(#{noticeRecord.title}, #{noticeRecord.content}, #{noticeRecord.editor}, #{noticeRecord.createTime}, #{noticeRecord.typeId})")
    @Options(useGeneratedKeys = true, keyProperty = "noticeRecord.id", keyColumn = "id")
    void save(@Param("noticeRecord") NoticeRecord noticeRecord);

    @Update("UPDATE Notice_Record SET title = #{noticeRecord.title}, content = #{noticeRecord.content}, editor = #{noticeRecord.editor}, " +
            "create_time = #{noticeRecord.createTime}, type_id = #{noticeRecord.typeId} WHERE id = #{noticeRecord.id}")
    void update(@Param("noticeRecord") NoticeRecord noticeRecord);

    @Delete("DELETE FROM Notice_Record WHERE id = #{id}")
    void deleteById(@Param("id") Integer id);

}
