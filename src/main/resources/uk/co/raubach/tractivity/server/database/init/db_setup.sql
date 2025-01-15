SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
CREATE TABLE `events` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `name` varchar(255) NOT NULL,
                          `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
                          `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `activity_participants`
--

DROP TABLE IF EXISTS `activity_participants`;
CREATE TABLE `activity_participants` (
                                         `activity_id` int(11) NOT NULL,
                                         `participant_id` int(11) NOT NULL,
                                         `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
                                         `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                         PRIMARY KEY (`activity_id`,`participant_id`),
                                         KEY `participant_id` (`participant_id`),
                                         CONSTRAINT `activity_participants_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                         CONSTRAINT `activity_participants_ibfk_2` FOREIGN KEY (`participant_id`) REFERENCES `participants` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `activity_types`
--

DROP TABLE IF EXISTS `activity_types`;
CREATE TABLE `activity_types` (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                                  `image` mediumblob,
                                  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
                                  `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
CREATE TABLE `locations` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `name` varchar(255) NOT NULL,
                             `latitude` decimal(10,8) DEFAULT NULL,
                             `longitude` decimal(11,8) DEFAULT NULL,
                             `elevation` decimal(10,2) DEFAULT NULL,
                             `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
                             `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `activity_measures`
--

DROP TABLE IF EXISTS `activity_measures`;
CREATE TABLE `activity_measures` (
                                     `id` int(11) NOT NULL AUTO_INCREMENT,
                                     `activity_id` int(11) NOT NULL,
                                     `participant_id` int(11) NOT NULL,
                                     `measure_id` int(11) NOT NULL,
                                     `measured_value` varchar(255) NOT NULL,
                                     `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
                                     `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     PRIMARY KEY (`id`) USING BTREE,
                                     KEY `activity_id` (`activity_id`),
                                     KEY `participant_id` (`participant_id`),
                                     KEY `measure_id` (`measure_id`),
                                     CONSTRAINT `activity_measures_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                     CONSTRAINT `activity_measures_ibfk_2` FOREIGN KEY (`participant_id`) REFERENCES `participants` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                     CONSTRAINT `activity_measures_ibfk_3` FOREIGN KEY (`measure_id`) REFERENCES `measures` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `activities`
--

DROP TABLE IF EXISTS `activities`;
CREATE TABLE `activities` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `activity_type_id` int(11) NOT NULL,
                              `event_id` int(11) NOT NULL,
                              `location_id` int(11) NOT NULL,
                              `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
                              `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              PRIMARY KEY (`id`),
                              KEY `event_id` (`event_id`),
                              KEY `location_id` (`location_id`),
                              KEY `activity_type_id` (`activity_type_id`),
                              CONSTRAINT `activities_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT `activities_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT `activities_ibfk_3` FOREIGN KEY (`activity_type_id`) REFERENCES `activity_types` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `measures`
--

DROP TABLE IF EXISTS `measures`;
CREATE TABLE `measures` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) NOT NULL,
                            `type` enum('integer','decimal','single_cat','multi_cat','boolean','date','text') NOT NULL DEFAULT 'text',
                            `image` mediumblob,
                            `restrictions` json DEFAULT NULL,
                            `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
                            `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `participants`
--

DROP TABLE IF EXISTS `participants`;
CREATE TABLE `participants` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `name` varchar(255) NOT NULL,
                                `dob` datetime DEFAULT NULL,
                                `gender` enum('female','male','other') DEFAULT NULL,
                                `image` mediumblob,
                                `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `updated_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 0;