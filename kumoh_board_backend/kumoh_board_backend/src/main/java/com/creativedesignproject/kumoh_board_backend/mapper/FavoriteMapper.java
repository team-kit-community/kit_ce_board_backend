package com.creativedesignproject.kumoh_board_backend.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.creativedesignproject.kumoh_board_backend.Board.entity.FavoriteEntity;
import com.creativedesignproject.kumoh_board_backend.Board.entity.primary.FavoritePk;
import com.creativedesignproject.kumoh_board_backend.Board.resultSet.GetFavoriteListResultSet;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    List<GetFavoriteListResultSet> getFavoriteList(@Param("post_number") int post_number);

    @Select("SELECT COUNT(*) > 0 FROM favorite WHERE user_id = #{user_id} AND post_number = #{post_number}")
    public boolean findByBoardNumberAndUserId(@Param("user_id") int user_id, @Param("post_number") int post_number);

    @Insert("INSERT INTO favorite (user_id, post_number) VALUES (#{user_id}, #{post_number})")
    public void save(FavoriteEntity favoriteEntity);

    @Delete("DELETE FROM favorite WHERE post_number = #{post_number} AND user_id = #{user_id}")
    public void delete(@Param("user_id") int user_id, @Param("post_number") int post_number);

    @Select("SELECT * FROM favorite WHERE user_id = #{user_id} AND post_number = #{post_number}")
    public FavoriteEntity findById(FavoritePk favoritePk);
}
