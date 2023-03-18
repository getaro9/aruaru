UPDATE user_m
  SET
    user_id = /* userDto.userId */0,
    user_name = /* userDto.userName */'userName',
    user_email = /* userDto.userEmail */'userEmail',
    user_role_id = (SELECT user_role_m.user_role_id  FROM user_role_m WHERE user_role_name =/* userDto.userRoleName */'一般ユーザー' ),
    user_password = /* userDto.userPassword */'userPassword',
    updated_at = current_timestamp
  WHERE
    user_id = /* userDto.userId */0
