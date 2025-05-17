package net.techmentor.cases_service.cases.internal.application.commands.Contribute;

import net.techmentor.cases_service.shared.abstractions.Command;

import java.util.UUID;

public record Contribute(int amount, UUID userId, int caseCode) implements Command {
}
