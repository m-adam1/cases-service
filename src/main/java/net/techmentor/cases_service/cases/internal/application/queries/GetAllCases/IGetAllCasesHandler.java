package net.techmentor.cases_service.cases.internal.application.queries.GetAllCases;

import java.util.concurrent.CompletableFuture;

public interface IGetAllCasesHandler {
    CompletableFuture<GetCasesQueryResult> handle(GetAllCasesQuery query);
}
