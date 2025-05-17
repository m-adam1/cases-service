package net.techmentor.cases_service.cases.internal.application.commands.ChangeContributionStatus;

import net.techmentor.cases_service.cases.internal.domain.contracts.ICaseRepo;
import net.techmentor.cases_service.shared.abstractions.CommandHandler;
import net.techmentor.cases_service.shared.domain.ILogger;
import net.techmentor.cases_service.shared.exceptions.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class ChangeContributionStatusHandler extends CommandHandler<ChangeContributionStatus, Void> {
    private final ICaseRepo caseRepo;
    private final ILogger logger;

    public ChangeContributionStatusHandler(ICaseRepo caseRepo, ILogger logger) {
        this.caseRepo = caseRepo;
        this.logger = logger;
    }

    @Override
    public CompletableFuture<Void> handle(ChangeContributionStatus command) {
        return CompletableFuture.runAsync(() -> {

            var contribution = caseRepo.getContributionById(command.contributionId()).join();
            if (contribution == null) {
                logger.error("Contribution not found with ID {} ", command.contributionId());
                throw new NotFoundException("Contribution not found with ID " + command.contributionId());
            }

            if (command.isPay()) {
                contribution.pay();
                caseRepo.save(contribution);
                logger.info("Contribution paid and saved with ID {} ", command.contributionId());
                return;
            }

            contribution.confirm();
            caseRepo.save(contribution);
            logger.info("Contribution confirmed and saved with ID {}", command.contributionId());
        });
    }
}
