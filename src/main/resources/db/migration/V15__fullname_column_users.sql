alter table medical_call_center_user add column fullname_ text;
update medical_call_center_user set fullname_ = lastname_ ||' '||firstname_;