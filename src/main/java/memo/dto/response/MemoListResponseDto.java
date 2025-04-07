package memo.dto.response;
import lombok.Builder;

import java.util.List;

@Builder
public record MemoListResponseDto(
		List<MemoResponseDto> memos
) {}