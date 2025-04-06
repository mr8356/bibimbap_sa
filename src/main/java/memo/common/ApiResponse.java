package memo.common;

import lombok.Builder;

@Builder
public record ApiResponse<T>(
    int status,
    String message,
    T data
) {
    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return ApiResponse.<T>builder()
            .status(status)
            .message(message)
            .data(data)
            .build();
    }

    public static <T> ApiResponse<T> fail(int status, String message) {
        return ApiResponse.<T>builder()
            .status(status)
            .message(message)
            .data(null)
            .build();
    }
}