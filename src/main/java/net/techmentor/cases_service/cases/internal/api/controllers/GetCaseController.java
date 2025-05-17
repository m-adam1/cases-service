package net.techmentor.cases_service.cases.internal.api.controllers;

import net.techmentor.cases_service.cases.internal.application.queries.GetCase.GetCaseQuery;
import net.techmentor.cases_service.cases.internal.application.queries.GetCase.IGetCaseHandler;
import net.techmentor.cases_service.shared.api.DeferredResults;
import net.techmentor.cases_service.shared.auth.AccessTokenPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class GetCaseController {

    private final IGetCaseHandler getCaseHandler;

    public GetCaseController(IGetCaseHandler getCaseHandler) {
        this.getCaseHandler = getCaseHandler;
    }

    @GetMapping("/v1/cases/{caseCode}")
    @PreAuthorize("hasAnyAuthority('FULL_ACCESS')")
    public DeferredResult<ResponseEntity<?>> getCase(
            @PathVariable int caseCode,
            @AuthenticationPrincipal AccessTokenPayload accessTokenPayload) {

        return DeferredResults.from(
                getCaseHandler.handle(new GetCaseQuery(caseCode, accessTokenPayload))
                        .thenApply(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
        );
    }
}
