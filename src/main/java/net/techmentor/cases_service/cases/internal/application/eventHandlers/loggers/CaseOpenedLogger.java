package net.techmentor.cases_service.cases.internal.application.eventHandlers.loggers;

import net.techmentor.cases_service.cases.shared.dtos.CaseOpenedDTO;
import net.techmentor.cases_service.shared.domain.ILogger;
import org.springframework.stereotype.Component;

@Component
public class CaseOpenedLogger {
    private final ILogger logger;

    public CaseOpenedLogger(ILogger logger) {
        this.logger = logger;
    }

    public void handlerRegistered() {
        logger.info("Registering CaseOpenedHandler");
    }

    public void processingEvent(CaseOpenedDTO case_) {
        logger.info("Processing CaseOpenedEvent - Case Code: {}, Title: {}", 
            case_.caseCode(), case_.title());
    }

    public void notificationSent(int caseCode) {
        logger.info("Successfully sent notification for case opened - Case Code: {}", 
            caseCode);
    }

    public void notificationFailed(int caseCode, Exception e) {
        logger.error("Failed to send notification for case opened - Case Code: {} - Error: {}",
            caseCode, e.getMessage(), e);
    }
} 
