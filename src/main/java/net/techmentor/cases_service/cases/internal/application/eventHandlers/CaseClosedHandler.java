package net.techmentor.cases_service.cases.internal.application.eventHandlers;

import net.techmentor.cases_service.cases.internal.application.eventHandlers.loggers.CaseClosedLogger;
import net.techmentor.cases_service.cases.internal.domain.contracts.INotificationService;
import net.techmentor.cases_service.cases.shared.dtos.CaseClosedDTO;
import net.techmentor.cases_service.shared.domain.IEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CaseClosedHandler {
    private final IEventBus eventBus;
    private final INotificationService notificationService;
    private final CaseClosedLogger logger;

    public CaseClosedHandler(IEventBus eventBus, INotificationService notificationService, CaseClosedLogger logger) {
        this.eventBus = eventBus;
        this.notificationService = notificationService;
        this.logger = logger;
    }

    @Bean("CaseClosedListener")
    public CompletableFuture<Void> start() {
        logger.handlerRegistered();
        eventBus.subscribe(this, CaseClosedDTO.class, this::handle);
        return CompletableFuture.completedFuture(null);
    }

    private CompletableFuture<Void> handle(CaseClosedDTO case_) {
        return CompletableFuture.runAsync(() -> {
            logger.processingEvent(case_);

            try {
                notificationService.notifyCaseClosed(case_).join();
                logger.notificationSent(case_.caseCode());
            } catch (Exception e) {
                logger.notificationFailed(case_.caseCode(), e);
            }
        });
    }
} 
