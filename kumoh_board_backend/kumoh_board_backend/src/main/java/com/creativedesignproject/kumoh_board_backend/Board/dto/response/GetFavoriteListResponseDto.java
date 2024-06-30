package com.creativedesignproject.kumoh_board_backend.Board.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.object.FavoriteListItem;
import com.creativedesignproject.kumoh_board_backend.Board.resultSet.GetFavoriteListResultSet;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;

import lombok.Getter;

@Getter
public class GetFavoriteListResponseDto extends ResponseDto {
    private List<FavoriteListItem> favoriteList;
    
    private GetFavoriteListResponseDto(List<GetFavoriteListResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.favoriteList = FavoriteListItem.copyList(resultSets);
    }
    
    public static ResponseEntity<GetFavoriteListResponseDto> success(List<GetFavoriteListResultSet> resultSets) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetFavoriteListResponseDto(resultSets));
    }
    
    public static ResponseEntity<ResponseDto> notExistBoard() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD));
    }
}
