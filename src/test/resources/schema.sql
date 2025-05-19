-- 活动配置表
DROP TABLE IF EXISTS demo_activity_config;
CREATE TABLE IF NOT EXISTS demo_activity_config (
  id int NOT NULL AUTO_INCREMENT,
  activity_id varchar(32) NOT NULL,
  start_time datetime NOT NULL,
  end_time datetime NOT NULL,
  board_size int NOT NULL DEFAULT 30,
  max_chances_per_roll int NOT NULL DEFAULT 10,
  daily_play_limit int NOT NULL DEFAULT 120,
  is_active boolean NOT NULL DEFAULT FALSE,
  version int NOT NULL DEFAULT 1,
  effective_time datetime DEFAULT NULL,
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  is_deleted boolean NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id),
  CONSTRAINT uk_activity_id UNIQUE (activity_id)
);

-- 活动用户表
DROP TABLE IF EXISTS demo_activity_user;
CREATE TABLE IF NOT EXISTS demo_activity_user (
  id int NOT NULL AUTO_INCREMENT,
  user_id varchar(32) NOT NULL,
  activity_id varchar(32) NOT NULL,
  current_position int NOT NULL DEFAULT 0,
  game_chances int NOT NULL DEFAULT 0,
  points int NOT NULL DEFAULT 0,
  daily_plays int NOT NULL DEFAULT 0,
  last_play_time datetime DEFAULT NULL,
  unlocked_items varchar(1000) DEFAULT NULL,
  redeemed_chests varchar(1000) DEFAULT NULL,
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  is_deleted boolean NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id),
  CONSTRAINT uk_user_activity UNIQUE (user_id, activity_id)
);

-- 棋盘配置表
DROP TABLE IF EXISTS demo_board_config;
CREATE TABLE IF NOT EXISTS demo_board_config (
  id int NOT NULL AUTO_INCREMENT,
  activity_id varchar(32) NOT NULL,
  position int NOT NULL,
  square_type varchar(20) NOT NULL,
  name varchar(50) NOT NULL,
  icon varchar(50) DEFAULT NULL,
  points_value int DEFAULT NULL,
  item_key varchar(100) DEFAULT NULL,
  version int NOT NULL DEFAULT 1,
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  is_deleted boolean NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id),
  CONSTRAINT uk_activity_position UNIQUE (activity_id, position, is_deleted)
);

-- 宝箱配置表
DROP TABLE IF EXISTS demo_chest_config;
CREATE TABLE IF NOT EXISTS demo_chest_config (
  id int NOT NULL AUTO_INCREMENT,
  chest_id varchar(50) NOT NULL,
  activity_id varchar(32) NOT NULL,
  name varchar(50) NOT NULL,
  points_required int NOT NULL,
  reward varchar(200) DEFAULT NULL,
  icon varchar(100) DEFAULT NULL,
  css_class varchar(50) DEFAULT NULL,
  version int NOT NULL DEFAULT 1,
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  is_deleted boolean NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id),
  CONSTRAINT uk_activity_chest UNIQUE (activity_id, chest_id, is_deleted)
);

-- 游戏记录表
DROP TABLE IF EXISTS demo_game_record;
CREATE TABLE IF NOT EXISTS demo_game_record (
  id bigint NOT NULL AUTO_INCREMENT,
  user_id bigint NOT NULL,
  activity_id varchar(32) NOT NULL,
  chances_used int NOT NULL,
  dice_results varchar(500) DEFAULT NULL,
  start_position int NOT NULL,
  end_position int NOT NULL,
  points_gained int NOT NULL DEFAULT 0,
  rewards_gained varchar(500) DEFAULT NULL,
  request_id varchar(64) DEFAULT NULL,
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  is_deleted boolean NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id)
);
CREATE INDEX idx_user_activity ON demo_game_record(user_id, activity_id);
CREATE INDEX idx_request_id ON demo_game_record(request_id);
CREATE INDEX idx_create_time ON demo_game_record(create_time);

-- 道具配置表
DROP TABLE IF EXISTS demo_item_config;
CREATE TABLE IF NOT EXISTS demo_item_config (
  id int NOT NULL AUTO_INCREMENT,
  item_key varchar(50) NOT NULL,
  activity_id varchar(32) NOT NULL,
  item_name varchar(50) NOT NULL,
  item_type varchar(20) NOT NULL,
  points_equivalent int NOT NULL,
  is_limited_edition boolean NOT NULL DEFAULT FALSE,
  stock_limit int NOT NULL DEFAULT 0,
  current_stock int NOT NULL DEFAULT 0,
  version int NOT NULL DEFAULT 1,
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  is_deleted boolean NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id),
  CONSTRAINT uk_activity_item_key UNIQUE (activity_id, item_key, is_deleted)
);