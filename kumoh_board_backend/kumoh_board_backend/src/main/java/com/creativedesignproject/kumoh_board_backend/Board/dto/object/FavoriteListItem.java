package com.creativedesignproject.kumoh_board_backend.Board.dto.object;

import com.creativedesignproject.kumoh_board_backend.Board.repository.query.FavoriteListDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteListItem {
    private String userId;

    public FavoriteListItem(FavoriteListDto resultSet) {
        this.userId = resultSet.getUserId();
    }

    public static List<FavoriteListItem> copyList(List<FavoriteListDto> resultSets) {
        List<FavoriteListItem> list = new ArrayList<>();
        for(FavoriteListDto resultSet : resultSets) {
            list.add(new FavoriteListItem(resultSet));
        }
        return list;
    }
}
