package net.techmentor.cases_service.cases.internal.api.dtos;

import net.techmentor.cases_service.shared.abstractions.Request;

public record GetCasesRequest(int offset, int limit, Integer code, String tag, String content) implements Request {

}
