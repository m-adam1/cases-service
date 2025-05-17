package net.techmentor.cases_service.cases.internal.application.commands.ChangeCaseStatus;

import net.techmentor.cases_service.shared.abstractions.Command;

public record ChangeCaseStatus(int caseCode, boolean isActionOpen) implements Command {
}
