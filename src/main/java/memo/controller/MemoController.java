package memo.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import memo.common.ApiResponse;
import memo.common.ErrorCode;
import memo.common.SuccessCode;
import memo.dto.request.MemoRequestDto;
import memo.dto.response.MemoListResponseDto;
import memo.service.MemoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memos")
@RequiredArgsConstructor
public class MemoController {
	private final MemoService memoService;
	@GetMapping
	public ApiResponse<MemoListResponseDto> getMemos() {
		MemoListResponseDto responseDto = memoService.getMemos();
		return ApiResponse.success(SuccessCode.OK, responseDto);
	}

	@PostMapping
	public ApiResponse addMemo(@RequestBody MemoRequestDto memoRequestDto) {
		try {
			memoService.addMemo(memoRequestDto);
			return ApiResponse.success(SuccessCode.OK);
		} catch (EntityNotFoundException e) {
			return ApiResponse.fail(ErrorCode.NOT_FOUND);
		}
	}

}
