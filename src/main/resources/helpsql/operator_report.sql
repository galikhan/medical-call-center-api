select
    m.username_,
    count(1) as total_,
    count(case when a.type_ = 'complaint' then 1 else null end) as complaint_,
    count(case when a.type_ ='proposal' then 1 else null end) as proposal_,
    count(case when a.type_ ='thanks' then 1 else null end) as thanks_
from
    appeal a join medical_call_center_user m on m.id_ = a.owner_
    where is_removed_ = false
    and a.appeal_date_::date between '2026-01-01' and '2026-01-29'
    group by m.username_;


SELECT
a.appeal_date_,
extract(year from a.appeal_date_),
extract(month from a.appeal_date_)
from appeal a;

