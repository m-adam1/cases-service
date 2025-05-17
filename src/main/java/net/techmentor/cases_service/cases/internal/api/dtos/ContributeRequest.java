package net.techmentor.cases_service.cases.internal.api.dtos;

import net.techmentor.cases_service.shared.abstractions.Request;

public record ContributeRequest(int amount) implements Request {
}
