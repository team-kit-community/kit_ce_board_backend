package com.creativedesignproject.kumoh_board_backend.Category.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.entity.CategoryEntity;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;

import lombok.Getter;

@Getter
public class GetCategoryListResponseDto extends ResponseDto{
    private final List<CategoryEntity> categoryList;

    private GetCategoryListResponseDto(List<CategoryEntity> categoryList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.categoryList = categoryList;
    }

    public static ResponseEntity<GetCategoryListResponseDto> success(List<CategoryEntity> categoryList) {
        GetCategoryListResponseDto responseBody = new GetCategoryListResponseDto(categoryList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistedCategory() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_CATEGORY, ResponseMessage.NOT_EXISTED_CATEGORY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
