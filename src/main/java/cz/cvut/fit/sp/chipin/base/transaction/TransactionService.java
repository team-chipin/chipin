package cz.cvut.fit.sp.chipin.base.transaction;

import cz.cvut.fit.sp.chipin.authentication.user.User;
import cz.cvut.fit.sp.chipin.base.amount.Amount;
import cz.cvut.fit.sp.chipin.base.amount.AmountService;
import cz.cvut.fit.sp.chipin.base.group.Group;
import cz.cvut.fit.sp.chipin.base.transaction.mapper.TransactionCreateTransactionRequest;
import cz.cvut.fit.sp.chipin.base.transaction.mapper.TransactionMapper;
import cz.cvut.fit.sp.chipin.base.transaction.mapper.TransactionReadGroupTransactionResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AmountService amountService;
    private final TransactionMapper transactionMapper;

    public Transaction create(TransactionCreateTransactionRequest request, User payer, Group group) throws Exception {
        Transaction transaction = transactionMapper.createTransactionRequestToEntity(request);
        transaction.setCurrency(group.getCurrency());
        transaction.setPayer(payer);
        transaction.setGroup(group);
        transaction.setDateTime(LocalDateTime.now());

        try {
            List<Amount> amounts = amountService.setAmounts(transaction, request.getSpenders(), request.getSplitStrategy());
            transactionRepository.save(transaction);
            amountService.saveAll(amounts);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return transaction;
    }

    public Optional<Transaction> read(Long transactionId, Long groupId) throws Exception {
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if (transaction.isEmpty()) {
            throw new Exception("Transaction not found.");
        }
        if (!Objects.equals(transaction.get().getGroup().getId(), groupId)) {
            throw new Exception("Transaction does not belong to this group.");
        }
        return transaction;
    }

    public TransactionReadGroupTransactionResponse readGroupTransaction(Long transactionId, Long groupId) throws Exception {
        Transaction transaction = read(transactionId, groupId)
                .orElseThrow(() -> new Exception("Transaction not found"));
        return transactionMapper.entityToReadGroupTransactionResponse(transaction);
    }

    @Transactional
    public void update(Transaction transaction, TransactionUpdateRequest request, User nextPayer) throws Exception {
        try {
            amountService.deleteAllByTransactionId(transaction.getId());
            transaction.setName(request.getName());
            transaction.setDateTime(parseDateTime(request.getDateTime()));
            transaction.setAmount(request.getAmount());
            transaction.setPayer(nextPayer);
            transaction.setCategory(request.getCategory());

            List<Amount> amounts = amountService.setAmounts(transaction, request.getSpenders(), request.getSplitStrategy());
            amountService.saveAll(amounts);
            transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void delete(Transaction transaction) throws Exception {
        amountService.deleteAllByIds(transaction.getAmounts().stream().map(Amount::getId).collect(Collectors.toList()));
        transactionRepository.deleteById(transaction.getId());
    }

    private static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TransactionMapper.DATETIME_FORMAT);
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static List<Transaction> filterTransactions(
            List<Transaction> transactions,
            Optional<String> categoriesString,
            Optional<String> dateTimeFrom,
            Optional<String> dateTimeTo,
            Optional<String> memberIdsString
    ) {
        Stream<Transaction> transactionStream = transactions.stream();

        if (categoriesString.isPresent()) {
            List<String> categories = Arrays.asList(categoriesString.get().toUpperCase().split(","));
            transactionStream = transactionStream.filter(transaction ->
                    categories.contains(transaction.getCategory().name())
            );
        }

        if (dateTimeFrom.isPresent() && dateTimeTo.isPresent()) {
            LocalDateTime from = parseDateTime(dateTimeFrom.get());
            LocalDateTime to = parseDateTime(dateTimeTo.get());
            transactionStream = transactionStream.filter(transaction ->
                    !transaction.getDateTime().isBefore(from) && !transaction.getDateTime().isAfter(to)
            );
        }

        if (memberIdsString.isPresent()) {
            List<String> memberIds = Arrays.asList(memberIdsString.get().split(","));
            transactionStream = transactionStream.filter(transaction ->
                    memberIds.contains(transaction.getPayer().getId())
            );
        }

        return transactionStream.toList();
    }
}
