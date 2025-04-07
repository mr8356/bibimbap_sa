package memo.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import memo.common.ApiResponse;
import memo.common.ErrorCode;
import memo.common.SuccessCode;
import memo.dto.request.MemoRequestDto;
import memo.dto.response.MemoListResponseDto;
import memo.dto.response.MemoResponseDto;
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
	public ApiResponse<Void> addMemo(@RequestBody MemoRequestDto memoRequestDto) {
		try {
			memoService.createMemo(memoRequestDto);
			return ApiResponse.success(SuccessCode.OK);
		} catch (Exception e) {
			return ApiResponse.fail(ErrorCode.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ApiResponse<MemoResponseDto> getMemoById(@PathVariable Long id) {
		MemoResponseDto memo = memoService.getMemoById(id);
		return ApiResponse.success(SuccessCode.OK, memo);
	}

	@DeleteMapping("/{id}")
	public ApiResponse<Void> deleteMemo(@PathVariable Long id) {
		memoService.deleteMemo(id);
		return ApiResponse.success(SuccessCode.OK);
	}

}
