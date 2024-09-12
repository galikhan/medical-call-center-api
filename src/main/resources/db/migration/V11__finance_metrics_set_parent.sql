update metrics_category set parent_ = (select id_ from metrics_category where code_ = 'income_outcome_statement') where code_ in(
'total_revenue',
'temporary_outcome',
'permanent_outcome',
'ebitda',
'net_profit_for_period',
'operating_profit_margin');

update metrics_category set parent_ = (select id_ from metrics_category where code_ = 'accounts_receivable_and_payable') where code_ in(
'accounts_receivable',
'accounts_receivable_turnover',
'accounts_payable',
'accounts_payable_turnover'
);

update metrics_category set parent_ = (select id_ from metrics_category where code_ = 'plan_fact') where code_ in(
'plan_fact_deviation',
'account_balance',
'turnover',
'break_even_point',
'employee_productivity'
);

update metrics_category set parent_ = (select id_ from metrics_category where code_ = 'cashflow') where code_ in(
'operating_cashflow',
'investment_cashflow',
'financial_cashflow',
'net_cashflow',
'free_cashflow',
'profit_quality_ratio',
'debt_coverage_ratio'
);