package cz.cvut.fit.sp.chipin.base.usergroup.mapper;
import cz.cvut.fit.sp.chipin.base.debt.mapper.DebtReadUserDebtsResponse;
import cz.cvut.fit.sp.chipin.base.log.mapper.LogReadLogResponse;
import cz.cvut.fit.sp.chipin.base.member.mapper.MemberReadMemberResponse;
import cz.cvut.fit.sp.chipin.base.transaction.mapper.TransactionReadGroupTransactionsResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class GroupReadGroupResponse {

    @NotBlank
    private String name;
    @NotNull
    private String currency;
    @NotNull
    private List<MemberReadMemberResponse> members;
    @NotNull
    private List<TransactionReadGroupTransactionsResponse> transactions;
    @NotNull
    private List<DebtReadUserDebtsResponse> debts;
    @NotNull
    private List<LogReadLogResponse> logs;
}
