package net.techmentor.cases_service.cases.internal.application.eventHandlers;

import net.techmentor.cases_service.accounts.shared.AccountEventDto;
import net.techmentor.cases_service.cases.internal.application.loggers.FCMTokenLogger;
import net.techmentor.cases_service.cases.internal.domain.contracts.INotificationService;
import net.techmentor.cases_service.shared.domain.IEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class FCMTokenUpdatedHandler {
    private final IEventBus eventBus;
    private final INotificationService notificationService;
    private final FCMTokenLogger logger;

    public FCMTokenUpdatedHandler(IEventBus eventBus, INotificationService notificationService, FCMTokenLogger baseLogger) {
        this.eventBus = eventBus;
        this.notificationService = notificationService;
        this.logger = baseLogger;
    }

    @Bean("FCMTokenUpdatedListener")
    public CompletableFuture<Void> start() {
        logger.handlerRegistered();
        eventBus.subscribe(this, AccountEventDto.FCMTokenUpdatedDTO.class, this::handle);
        return CompletableFuture.completedFuture(null);
    }

    public CompletableFuture<Void> handle(AccountEventDto.FCMTokenUpdatedDTO event) {
        return CompletableFuture.runAsync(() -> {
            if (event.deviceFCMToken() == null) {
                logger.nullTokenReceived();
                return;
            }

            logger.processingToken(event.deviceFCMToken());
            
            try {
                notificationService.subscribeAccountToCaseUpdates(event.deviceFCMToken()).join();
                logger.tokenSubscribed(event.deviceFCMToken());
            } catch (Exception e) {
                logger.tokenSubscriptionFailed(event.deviceFCMToken(), e);
            }
        });
    }
}
