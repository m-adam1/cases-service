package net.techmentor.cases_service.cases.internal.api.controllers;

import net.techmentor.cases_service.cases.internal.api.dtos.GetCasesRequest;
import net.techmentor.cases_service.cases.internal.application.queries.GetAllCases.GetAllCasesQuery;
import net.techmentor.cases_service.cases.internal.infrastructure.queryhandlers.GetAllCasesHandler;
import net.techmentor.cases_service.shared.api.DeferredResults;
import net.techmentor.cases_service.shared.domain.ILogger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class GetAllCasesController {

    private final GetAllCasesHandler getAllCasesHandler;
    private final ILogger logger;

    public GetAllCasesController(GetAllCasesHandler getAllCasesHandler, ILogger logger) {
        this.getAllCasesHandler = getAllCasesHandler;
        this.logger = logger;
    }

    @GetMapping("/v1/cases")
    public DeferredResult<ResponseEntity<?>> getCases(@ModelAttribute GetCasesRequest request) {
        GetAllCasesQuery query = new GetAllCasesQuery(
                request.code(),
                request.tag(),
                request.content(),
                Math.max(request.offset(), 0),
                Math.min(Math.max(request.limit(), 1), 100)
        );

        logger.warn("adam: Getting all cases");

        return DeferredResults.from(getAllCasesHandler.handle(query).thenApply(ResponseEntity::ok));
    }
}
