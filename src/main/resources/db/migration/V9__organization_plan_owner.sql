alter table company_plan rename to organization_plan;
alter table organization_plan add column owner_ bigint references ai_assist_user(id_);