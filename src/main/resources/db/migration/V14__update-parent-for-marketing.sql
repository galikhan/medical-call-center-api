update metrics_category set parent_ = (select id_ from metrics_category where code_ = 'advertising_campaign_statistics') where code_ in(
'reach_by_campaigns',
'impressions',
'clicks',
'cost_per_lead',
'total_number_of_leads',
'lead_conversion');

