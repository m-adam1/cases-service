package net.techmentor.cases_service.cases.internal.application.queries.GetCase;

import java.util.concurrent.CompletableFuture;

public interface IGetCaseHandler {
    CompletableFuture<GetCaseResponse> handle(GetCaseQuery query);
}
