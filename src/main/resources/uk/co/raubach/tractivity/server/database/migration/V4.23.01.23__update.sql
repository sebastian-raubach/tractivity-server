ALTER TABLE `measures`
    MODIFY COLUMN `type` enum('integer','decimal','single_cat','multi_cat','boolean','date','text','truth_value') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'text' AFTER `name`;

UPDATE `measures` SET `type` = 'truth_value' WHERE `type` = 'boolean';

ALTER TABLE `measures`
    MODIFY COLUMN `type` enum('integer','decimal','single_cat','multi_cat','date','text','truth_value') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'text' AFTER `name`;