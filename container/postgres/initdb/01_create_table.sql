-- Project Name : noname
-- Date/Time    : 2023/03/05 16:19:06
-- Author       : TAKAHIRO
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

/*
  << 注意！！ >>
  BackupToTempTable, RestoreFromTempTable疑似命令が付加されています。
  これにより、drop table, create table 後もデータが残ります。
  この機能は一時的に $$TableName のような一時テーブルを作成します。
  この機能は A5:SQL Mk-2でのみ有効であることに注意してください。
*/

-- ユーザーメタ情報マスタ
--* BackupToTempTable
DROP TABLE if exists user_meta_m CASCADE;

--* RestoreFromTempTable
CREATE TABLE user_meta_m (
  user_id integer NOT NULL
  , user_meta_key integer NOT NULL
  , user_meta_value varchar(100) NOT NULL
  , created_at timestamp NOT NULL
  , updated_at timestamp
  , CONSTRAINT user_meta_m_PKC PRIMARY KEY (user_id,user_meta_key)
) ;

-- ユーザーロールマスタ
--* BackupToTempTable
DROP TABLE if exists user_role_m CASCADE;

--* RestoreFromTempTable
CREATE TABLE user_role_m (
  user_role_id smallint NOT NULL
  , user_role_name varchar(100) NOT NULL
  , created_at timestamp NOT NULL
  , updated_at timestamp
  , CONSTRAINT user_role_m_PKC PRIMARY KEY (user_role_id)
) ;

-- ユーザーマスタ
--* BackupToTempTable
DROP TABLE if exists user_m CASCADE;

--* RestoreFromTempTable
CREATE TABLE user_m (
  user_id serial NOT NULL
  , user_name varchar(100) NOT NULL
  , user_email varchar(100)
  , user_role_id smallint NOT NULL
  , user_password varchar(100) NOT NULL
  , created_at timestamp NOT NULL
  , updated_at timestamp
  , CONSTRAINT user_m_PKC PRIMARY KEY (user_id)
) ;

ALTER TABLE user_m
  ADD CONSTRAINT user_m_FK1 FOREIGN KEY (user_role_id) REFERENCES user_role_m(user_role_id)
  on delete cascade
  on update cascade;

ALTER TABLE user_meta_m
  ADD CONSTRAINT user_meta_m_FK1 FOREIGN KEY (user_id) REFERENCES user_m(user_id)
  on delete cascade
  on update cascade;

COMMENT ON TABLE user_meta_m IS 'ユーザーメタ情報マスタ:ユーザに関する情報で、めったに使用されることのない情報を保持する。';
COMMENT ON COLUMN user_meta_m.user_id IS 'ユーザID';
COMMENT ON COLUMN user_meta_m.user_meta_key IS 'メタキー';
COMMENT ON COLUMN user_meta_m.user_meta_value IS 'メタ値';
COMMENT ON COLUMN user_meta_m.created_at IS '登録日時';
COMMENT ON COLUMN user_meta_m.updated_at IS '更新日時';

COMMENT ON TABLE user_role_m IS 'ユーザーロールマスタ';
COMMENT ON COLUMN user_role_m.user_role_id IS 'ロールID';
COMMENT ON COLUMN user_role_m.user_role_name IS 'ロール名';
COMMENT ON COLUMN user_role_m.created_at IS '登録日時';
COMMENT ON COLUMN user_role_m.updated_at IS '更新日時';

COMMENT ON TABLE user_m IS 'ユーザーマスタ';
COMMENT ON COLUMN user_m.user_id IS 'ユーザーID';
COMMENT ON COLUMN user_m.user_name IS 'ユーザー名';
COMMENT ON COLUMN user_m.user_email IS 'ユーザーEメール';
COMMENT ON COLUMN user_m.user_role_id IS 'ユーザーロールID';
COMMENT ON COLUMN user_m.user_password IS 'ユーザーパスワード';
COMMENT ON COLUMN user_m.created_at IS '登録日時';
COMMENT ON COLUMN user_m.updated_at IS '更新日時';
