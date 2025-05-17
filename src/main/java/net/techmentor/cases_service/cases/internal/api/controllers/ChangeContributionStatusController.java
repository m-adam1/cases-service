package net.techmentor.cases_service.cases.internal.api.controllers;

import net.techmentor.cases_service.cases.internal.application.commands.ChangeContributionStatus.ChangeContributionStatus;
import net.techmentor.cases_service.cases.internal.application.commands.ChangeContributionStatus.ChangeContributionStatusHandler;
import net.techmentor.cases_service.shared.api.DeferredResults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;

@RestController
public class ChangeContributionStatusController {

    private final ChangeContributionStatusHandler contributionStatusHandler;

    public ChangeContributionStatusController(ChangeContributionStatusHandler contributionStatusHandler) {
        this.contributionStatusHandler = contributionStatusHandler;
    }

    @PostMapping("/v1/contributions/{contributionId}/pay")
    public DeferredResult<ResponseEntity<?>> pay(@PathVariable UUID contributionId) {
        return DeferredResults.from(contributionStatusHandler
                .handle(new ChangeContributionStatus(contributionId, true))
                .thenApply(ResponseEntity::ok));
    }

    @PostMapping("/v1/contributions/{contributionId}/confirm")
    public DeferredResult<ResponseEntity<?>> confirm(@PathVariable UUID contributionId) {
        return DeferredResults.from(contributionStatusHandler
                .handle(new ChangeContributionStatus(contributionId, false))
                .thenApply(ResponseEntity::ok));
    }
}
