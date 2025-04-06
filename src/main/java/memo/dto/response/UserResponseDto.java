package memo.dto.response;

import lombok.Builder;

@Builder
public record UserResponseDto(
    Long id,
    String name,
    String email
) {
}