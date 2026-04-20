package com.vibe.modules.order.api;

import com.vibe.core.auth.AuthUser;
import com.vibe.core.auth.LoginUser;
import com.vibe.core.storage.StorageProvider;
import com.vibe.modules.common.api.dto.PageResponse;
import com.vibe.modules.order.api.dto.OrderCreateRequest;
import com.vibe.modules.order.api.dto.OrderResponse;
import com.vibe.modules.order.application.OrderService;
import com.vibe.modules.order.application.dto.OrderCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Order", description = "주문 관리 API")
@Slf4j
@RestController
@RequestMapping("/api/v1/private/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final StorageProvider storageProvider;

    @Operation(summary = "주문 생성 및 영수증 업로드")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public OrderResponse createOrder(
            @Parameter(hidden = true) @LoginUser AuthUser user, // 스웨거에서 user 파트가 안 보이게 숨김
            
            @Parameter(description = "주문 상세 정보 (JSON)", 
                       content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            @RequestPart("request") OrderCreateRequest request,
            
            @Parameter(description = "영수증 파일", 
                       content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            @RequestPart("receipt") MultipartFile receipt
    ) {
        log.info("[API] 주문 생성 요청: {} / 상품: {}", user.getUserId(), request.getProductName());
        
        String fileUrl = storageProvider.upload(receipt, "orders/" + user.getUserId());
        
        OrderCommand command = OrderCommand.builder()
                .userId(user.getUserId())
                .productName(request.getProductName())
                .quantity(request.getQuantity())
                .build();
        
        orderService.approveOrder(command);

        return OrderResponse.builder()
                .orderId("ORDER_" + System.currentTimeMillis())
                .status("COMPLETED")
                .receiptUrl(fileUrl)
                .build();
    }

    @Operation(summary = "내 주문 내역 조회 (페이징)")
    @GetMapping("/me")
    public PageResponse<OrderResponse> getMyOrders(
            @Parameter(hidden = true) @LoginUser AuthUser user,
            @ParameterObject Pageable pageable
    ) {
        log.info("[API] 내 주문 내역 조회 요청: {} (Page: {})", user.getUserId(), pageable.getPageNumber());
        Page<OrderResponse> orders = orderService.getOrders(user.getUserId(), pageable);
        return PageResponse.of(orders);
    }
}
