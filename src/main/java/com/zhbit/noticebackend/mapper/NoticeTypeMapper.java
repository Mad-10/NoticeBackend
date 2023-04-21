package com.zhbit.noticebackend.mapper;

import com.zhbit.noticebackend.entity.NoticeType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoticeTypeMapper {

    @Select("SELECT * FROM notice_type WHERE id = #{id}")
    @ResultMap("noticeTypeMap")
    NoticeType findById(@Param("id") Integer id);

    @Select("SELECT * FROM notice_type WHERE type_name = #{typeName}")
    @ResultMap("noticeTypeMap")
    NoticeType findByTypeName(@Param("typeName") String typeName);

    @Select("SELECT id, type_name FROM notice_type")
    @Results(id = "noticeTypeMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "typeName", column = "type_name")
    })
    List<NoticeType> findAll();

    @Insert("INSERT INTO notice_type(type_name) VALUES(#{typeName})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(NoticeType noticeType);

    @Update("UPDATE notice_type SET type_name = #{typeName} WHERE id = #{id}")
    void update(NoticeType noticeType);

    @Delete("DELETE FROM notice_type WHERE id = #{id}")
    void deleteById(@Param("id") Integer id);

    @Delete("DELETE FROM notice_type WHERE type_name = #{typeName}")
    void deleteByTypeName(@Param("typeName") String typeName);
}
