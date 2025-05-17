package net.techmentor.cases_service.cases.internal.api.controllers;

import net.techmentor.cases_service.cases.internal.api.dtos.CreateCaseRequest;
import net.techmentor.cases_service.cases.internal.application.commands.CreateCase.CreateCase;
import net.techmentor.cases_service.cases.internal.application.commands.CreateCase.CreateCaseHandler;
import net.techmentor.cases_service.shared.api.DeferredResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class CreateCaseController {

    private final CreateCaseHandler createCaseHandler;

    public CreateCaseController(CreateCaseHandler createCaseHandler) {
        this.createCaseHandler = createCaseHandler;
    }

    @PostMapping("/v1/cases")
    @PreAuthorize("hasAnyAuthority('CREATE_CASES', 'FULL_ACCESS')")
    public DeferredResult<ResponseEntity<?>> createCase(@RequestBody CreateCaseRequest request) {
        CreateCase createCommand = new CreateCase(
                request.title(),
                request.description(),
                request.goal(),
                request.publish(),
                request.acceptZakat(),
                request.documents()
        );

        return DeferredResults.from(createCaseHandler.handle(createCommand)
                .thenApply(response -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(response)));
    }
}
