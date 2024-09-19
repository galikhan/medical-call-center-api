insert into metrics_category(type_,code_,name_) values('sales', 'sales_volumes', 'Объемы продажи');
insert into metrics_category(type_,code_,name_) values
('sales', 'sales_plan_fulfillment', 'Выполнение плана продаж, %'),
('sales', 'actual_profit_by_direction', 'Фактическая прибыль по направлениям'),
('sales', 'payment_via_bank_or_cash', 'Оплата через банк/наличными'),
('sales', 'total_amount_can_be_done', 'Общая сумма - можно сделать'),
('sales', 'number_amount_of_concluded_contracts', 'Кол-во / сумма заключенных контрактов'),
('sales', 'average_check_for_transactions', 'Средний чек по сделкам (продуктам) - нету пока'),
('sales', 'top_10_popular_products', 'Топ 10 популярных товаров - можно выгружать'),
('sales', 'top_10_unpopular_products', 'Топ 10 непопулярных товаров');

update metrics_category set parent_ = (select id_ from metrics_category where code_ = 'sales_volumes') where code_ in(
'sales_plan_fulfillment',
'actual_profit_by_direction',
'payment_via_bank_or_cash',
'total_amount_can_be_done',
'number_amount_of_concluded_contracts',
'average_check_for_transactions',
'top_10_popular_products',
'top_10_unpopular_products');

insert into metrics_category(type_,code_,name_) values('sales', 'quality_of_managers_work', 'Качество работы менеджеров (общее и индивидуальное)');

insert into metrics_category(type_,code_,name_) values
('sales', 'plan_fulfillment', 'Выполнение плана | факт объема продаж по менеджерам сами клиенты пока не могут настраивать'),
('sales', 'number_of_received_applications', 'Кол-во поступивших заявок'),
('sales', 'number_of_processed_applications', 'Кол-во обработанных заявок'),
('sales', 'number_of_sales', 'Кол-во продаж'),
('sales', 'sales_conversion', 'Конверсия продаж, %'),
('sales', 'response_time', 'Время ответа'),
('sales', 'processing_time', 'Время отработки'),
('sales', 'nps', 'NPS');

update metrics_category set parent_ = (select id_ from metrics_category where code_ = 'quality_of_managers_work') where code_ in(
'plan_fulfillment'
'number_of_received_applications'
'number_of_processed_applications'
'number_of_sales'
'sales_conversion'
'response_time'
'processing_time'
'nps');


insert into metrics_category(type_,code_,name_) values ('marketing', 'advertising_campaign_statistics' ,'Статистика рекламных кампаний');

insert into metrics_category(type_,code_,name_) values
('marketing', 'reach_by_campaigns', 'Охват по кампаниям (сайт, соц сети, реклама)'),
('marketing', 'impressions', 'Показы'),
('marketing', 'clicks', 'Клики'),
('marketing', 'cost_per_lead', 'Стоимость лида'),
('marketing', 'total_number_of_leads', 'Количество лидов общее / по каналам'),
('marketing', 'lead_conversion', 'Конверсия лидов');

update metrics_category set parent_ = (select id_ from metrics_category where code_ = 'advertising_campaign_statistics') where code_ in(
'reach_by_campaigns'
'impressions'
'clicks'
'cost_per_lead'
'total_number_of_leads'
'lead_conversion');

insert into metrics_category(type_,code_,name_) values ('marketing', 'marketing_expenses' ,'Расходы на маркетинг');

insert into metrics_category(type_,code_,name_) values
('marketing', 'marketing_on_social_networks', 'Маркетинг по соц. сетям (таргет, контекстная реклама, создание контента)'),
('marketing', 'event_marketing', 'Ивент маркетинг (Мероприятия и спонсорство)'),
('marketing', 'return_on_marketing_investments', 'Возврат на маркетинговые инвестиции'),
('marketing', 'salary_for_marketers', 'Зарплата маркетологам'),
('marketing', 'expenses_at_launch_for_research_and_development', 'Расходы при запуске на рисерчи и девэлопмент'),
('marketing', 'how_much_money_to–launch_product', 'сколько денег, чтобы запустить продукт');

update metrics_category set parent_ = (select id_ from metrics_category where code_ = 'marketing_expenses') where code_ in(
'marketing_on_social_networks',
'event_marketing',
'return_on_marketing_investments',
'salary_for_marketers',
'expenses_at_launch_for_research_and_development',
'how_much_money_to–launch_product');