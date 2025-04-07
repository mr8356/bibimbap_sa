package memo.common;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.NonNull;


@Builder
public record ApiResponse<T>(
        int status,
        @NonNull String message,
        @JsonInclude(value = NON_NULL) T data
) {
    public static <T> ApiResponse<T> success(final SuccessCode successCode, @Nullable final T data) {
        return ApiResponse.<T>builder()
                .status(successCode.getStatus().value())
                .message(successCode.getMessage())
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success(final SuccessCode successCode) {
        return ApiResponse.<T>builder()
                .status(successCode.getStatus().value())
                .message(successCode.getMessage())
                .data(null)
                .build();
    }

    public static <T> ApiResponse<T> fail(final ErrorCode errorCode) {
        return ApiResponse.<T>builder()
                .status(errorCode.getStatus().value())
                .message(errorCode.getMessage())
                .data(null)
                .build();
    }
}