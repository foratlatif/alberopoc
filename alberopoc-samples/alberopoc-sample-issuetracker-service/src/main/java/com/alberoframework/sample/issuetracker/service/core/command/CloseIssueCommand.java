package com.alberoframework.sample.issuetracker.service.core.command;

import com.alberoframework.component.command.contract.AbstractCommand;
import com.alberoframework.lang.VoidUnit;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class CloseIssueCommand extends AbstractCommand<VoidUnit> {

    private String projectId;
    private String issueId;
}
