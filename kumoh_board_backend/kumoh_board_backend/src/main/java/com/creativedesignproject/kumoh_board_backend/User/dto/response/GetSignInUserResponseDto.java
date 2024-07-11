package com.creativedesignproject.kumoh_board_backend.user.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.auth.domain.User;
import com.creativedesignproject.kumoh_board_backend.auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.common.ResponseMessage;

import lombok.Getter;

@Getter
public class GetSignInUserResponseDto extends ResponseDto{
    private String userId;
    private String nickName;
    private String profileImage;

    private GetSignInUserResponseDto(User userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = userEntity.getUser_id();
        this.nickName = userEntity.getNickname();
        this.profileImage = userEntity.getProfile_image();
    }

    public static ResponseEntity<GetSignInUserResponseDto> success(User userEntity) {
        GetSignInUserResponseDto responseBody = new GetSignInUserResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER));
    }
}
