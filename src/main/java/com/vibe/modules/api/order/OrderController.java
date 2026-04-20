package com.vibe.modules.api.order;

import com.vibe.core.auth.AuthUser;
import com.vibe.core.auth.LoginUser;
import com.vibe.core.dto.ApiResponse;
import com.vibe.core.storage.StorageProvider;
import com.vibe.modules.application.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Order", description = "주문 관리 API")
@RestController
@RequestMapping("/api/v1/private/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final StorageProvider storageProvider;

    @Operation(summary = "주문 생성 및 영수증 업로드")
    @PostMapping
    public ApiResponse<String> createOrder(
            @LoginUser AuthUser user,
            @RequestPart("receipt") MultipartFile receipt
    ) {
        // 1. 유저 정보를 @LoginUser로 안전하게 획득 (Traceability)
        String userId = user.getUserId();

        // 2. 공통 스토리지를 통한 파일 업로드 (Abstraction)
        String fileUrl = storageProvider.upload(receipt, "orders/" + userId);

        // 3. 서비스 로직 수행 (Transaction)
        orderService.approveOrder("ORDER_" + System.currentTimeMillis());

        return ApiResponse.success("주문이 완료되었습니다. 영수증 경로: " + fileUrl);
    }

    @Operation(summary = "내 주문 내역 조회")
    @GetMapping("/me")
    public ApiResponse<Object> getMyOrders(@LoginUser AuthUser user) {
        return ApiResponse.success(orderService.getOrder("MY_ORDER_ID"));
    }
}
