package me.gnoyes.advanced.app.v3;

import lombok.RequiredArgsConstructor;
import me.gnoyes.advanced.trace.TraceId;
import me.gnoyes.advanced.trace.TraceStatus;
import me.gnoyes.advanced.trace.hellotrace.HelloTraceV2;
import me.gnoyes.advanced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
