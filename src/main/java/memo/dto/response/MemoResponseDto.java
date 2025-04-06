package memo.dto.response;

import lombok.Builder;

@Builder
public record MemoResponseDto(
    Long id,
    String title,
    String content
) {
}