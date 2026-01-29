select
    m.username_,
    count(case when a.type_ = 'complaint' then 1 else null end) as complaint_,
    count(case when a.type_ ='proposal' then 1 else null end) as proposal_,
    count(case when a.type_ ='thanks' then 1 else null end) as thanks_
from
    appeal a join medical_call_center_user m on m.id_ = a.owner_
    where is_removed_ = false
    group by m.username_;