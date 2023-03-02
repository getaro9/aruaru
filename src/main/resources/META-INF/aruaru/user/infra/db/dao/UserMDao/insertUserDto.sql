INSERT  INTO user_m
    (user_id , user_name, user_email, user_role_id, user_password, created_at, updated_at)
  VALUES
    (
      nextval('user_m_user_id_seq'),
      /* userDto.userName */'userName',
      /* userDto.userEmail */'userEmail',
      (SELECT user_role_m.user_role_id  FROM user_role_m WHERE user_role_name =/* userDto.userRoleName */'一般ユーザー' ),
      /* userDto.userPassword */'userPassword',
      current_timestamp,
      NULL
    )
