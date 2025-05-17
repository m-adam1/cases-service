package net.techmentor.cases_service.shared.abstractions;

import java.util.concurrent.CompletableFuture;

public interface QueryHandler<TQuery extends Query, TResult> {
    CompletableFuture<TResult> handle(TQuery query);
}
