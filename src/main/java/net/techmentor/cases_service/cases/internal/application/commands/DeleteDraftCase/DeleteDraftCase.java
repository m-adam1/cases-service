package net.techmentor.cases_service.cases.internal.application.commands.DeleteDraftCase;

import net.techmentor.cases_service.shared.abstractions.Command;

public record DeleteDraftCase(int caseCode) implements Command {
}
