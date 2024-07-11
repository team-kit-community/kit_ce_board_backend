package com.creativedesignproject.kumoh_board_backend.category.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.common.ResponseMessage;
import com.creativedesignproject.kumoh_board_backend.auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.category.domain.Category;

import lombok.Getter;

@Getter
public class GetCategoryListResponseDto extends ResponseDto{
    private final List<Category> categoryList;

    private GetCategoryListResponseDto(List<Category> categoryList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.categoryList = categoryList;
    }

    public static ResponseEntity<GetCategoryListResponseDto> success(List<Category> categoryList) {
        GetCategoryListResponseDto responseBody = new GetCategoryListResponseDto(categoryList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistedCategory() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_CATEGORY, ResponseMessage.NOT_EXISTED_CATEGORY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
