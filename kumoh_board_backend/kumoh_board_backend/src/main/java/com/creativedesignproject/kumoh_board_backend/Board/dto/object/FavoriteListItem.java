package com.creativedesignproject.kumoh_board_backend.board.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.creativedesignproject.kumoh_board_backend.board.repository.query.FavoriteListDto;


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
