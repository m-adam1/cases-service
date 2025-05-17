package net.techmentor.cases_service.cases.internal.api.controllers;

import net.techmentor.cases_service.cases.internal.application.commands.DeleteDraftCase.DeleteDraftCase;
import net.techmentor.cases_service.cases.internal.application.commands.DeleteDraftCase.DeleteDraftCaseHandler;
import net.techmentor.cases_service.shared.api.DeferredResults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class DeleteDraftCaseController {

    private final DeleteDraftCaseHandler deleteDraftCaseHandler;

    public DeleteDraftCaseController(DeleteDraftCaseHandler deleteDraftCaseHandler) {
        this.deleteDraftCaseHandler = deleteDraftCaseHandler;
    }

    @DeleteMapping("/v1/cases/{caseCode}")
    @PreAuthorize("hasAnyAuthority('FULL_ACCESS')")
    public DeferredResult<ResponseEntity<?>> handle(@PathVariable int caseCode) {
        return DeferredResults.from(deleteDraftCaseHandler
                .handle(new DeleteDraftCase(caseCode))
                .thenApply(ResponseEntity::ok));
    }
}
