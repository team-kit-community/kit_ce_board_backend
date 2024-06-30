package com.creativedesignproject.kumoh_board_backend.Board.dto.object;

import com.creativedesignproject.kumoh_board_backend.Board.resultSet.GetFavoriteListResultSet;

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

    public FavoriteListItem(GetFavoriteListResultSet resultSet) {
        this.userId = resultSet.getUserId();
    }

    public static List<FavoriteListItem> copyList(List<GetFavoriteListResultSet> resultSets) {
        List<FavoriteListItem> list = new ArrayList<>();
        for(GetFavoriteListResultSet resultSet : resultSets) {
            list.add(new FavoriteListItem(resultSet));
        }
        return list;
    }
}
