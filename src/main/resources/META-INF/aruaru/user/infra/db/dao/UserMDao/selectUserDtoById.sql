select
  user_m.user_id,
  user_m.user_name,
  user_m.user_password,
  user_m.user_email,
  user_role_m.user_role_name
from
  user_m
inner join
  user_role_m
on
  user_m.user_role_id=user_role_m.user_role_id
where
  user_id = /* userId */1
