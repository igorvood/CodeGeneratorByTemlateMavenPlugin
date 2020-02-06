package ru.vood.freemarker.ext.processor;

import freemarker.template.Template;
import freemarker.template.TemplateMethodModelEx;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;
import ru.vood.freemarker.ext.sql.ConnectionAdapter;

import javax.sql.DataSource;
import java.io.Writer;
import java.util.Map;

public class SpringFtlProcessor extends SimpleFtlProcessor {
    private final ConnectionAdapter defaultConnection;

    public SpringFtlProcessor(JdbcTemplate jdbcOperations) {
        super();
        this.defaultConnection = new ConnectionAdapter(this, jdbcOperations);
        registerSharedVar("default_connection", getGetDefaultConnectionMethod());
    }

    public SpringFtlProcessor(JdbcTemplate jdbcOperations, Map<String, Object> param) {
        this(jdbcOperations);
        param.forEach(this::registerSharedVar);
    }

    private TemplateMethodModelEx getGetDefaultConnectionMethod() {
        return
                args -> {
                    Assert.isTrue(
                            args.isEmpty(),
                            () -> "Wrong number of arguments: expected 0, got " + args.size()
                    );
                    return defaultConnection;
                };
    }

    @Override
    public void process(@NotNull Template template, @NotNull Writer dest, @Nullable Object... args) {
        final DataSource dataSource = defaultConnection.getJdbcOperations().getDataSource();
        TransactionTemplate tt = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
        tt.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        tt.execute(
                transactionStatus -> {
                    SpringFtlProcessor.super.process(template, dest, args);
                    return dest;
                }
        );
    }
}