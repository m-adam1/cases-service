package net.techmentor.cases_service.cases.internal.application.eventHandlers;

import net.techmentor.cases_service.cases.internal.application.eventHandlers.loggers.CaseOpenedLogger;
import net.techmentor.cases_service.cases.internal.domain.contracts.INotificationService;
import net.techmentor.cases_service.cases.shared.dtos.CaseOpenedDTO;
import net.techmentor.cases_service.shared.domain.IEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CaseOpenedHandler {
    private final IEventBus eventBus;
    private final INotificationService notificationService;
    private final CaseOpenedLogger logger;

    public CaseOpenedHandler(IEventBus eventBus, INotificationService notificationService, CaseOpenedLogger logger) {
        this.eventBus = eventBus;
        this.notificationService = notificationService;
        this.logger = logger;
    }

    @Bean("CaseOpenedListener")
    public CompletableFuture<Void> start() {
        logger.handlerRegistered();
        eventBus.subscribe(this, CaseOpenedDTO.class, this::handle);
        return CompletableFuture.completedFuture(null);
    }

    private CompletableFuture<Void> handle(CaseOpenedDTO case_) {
        return CompletableFuture.runAsync(() -> {
            logger.processingEvent(case_);

            try {
                notificationService.notifyCaseOpened(case_).join();
                logger.notificationSent(case_.caseCode());
            } catch (Exception e) {
                logger.notificationFailed(case_.caseCode(), e);
            }
        });
    }
} 
