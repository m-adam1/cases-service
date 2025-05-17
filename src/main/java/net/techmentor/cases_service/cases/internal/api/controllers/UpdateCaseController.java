package net.techmentor.cases_service.cases.internal.api.controllers;

import net.techmentor.cases_service.cases.internal.api.dtos.UpdateCaseRequest;
import net.techmentor.cases_service.cases.internal.application.commands.UpdateCase.UpdateCase;
import net.techmentor.cases_service.cases.internal.application.commands.UpdateCase.UpdateCaseHandler;
import net.techmentor.cases_service.shared.api.DeferredResults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class UpdateCaseController {

    private final UpdateCaseHandler updateCaseHandler;

    public UpdateCaseController(UpdateCaseHandler updateCaseHandler) {
        this.updateCaseHandler = updateCaseHandler;
    }

    @PutMapping("/v1/cases/{caseCode}")
    public DeferredResult<ResponseEntity<?>> handle(
            @PathVariable int caseCode,
            @RequestBody UpdateCaseRequest request) {
        UpdateCase command = new UpdateCase(
                caseCode,
                request.title(),
                request.description(),
                request.goal(),
                request.acceptZakat(),
                request.documents()
        );
        return DeferredResults.from(updateCaseHandler.handle(command).thenApply(ResponseEntity::ok));
    }
}
