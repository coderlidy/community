-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Host: 10.9.1.140:3306
-- Generation Time: 2019-11-11 18:30:52
-- 服务器版本： 5.6.20-ucloudrel1-log
-- PHP Version: 5.4.41

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qiusuo`
--

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id` bigint(20) NOT NULL,
  `parentid` bigint(20) NOT NULL,
  `type` int(11) NOT NULL,
  `commentator` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modified` bigint(20) NOT NULL,
  `like_count` bigint(20) DEFAULT '0',
  `content` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL,
  `comment_count` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- 转存表中的数据 `comment`
--

INSERT INTO `comment` (`id`, `parentid`, `type`, `commentator`, `gmt_create`, `gmt_modified`, `like_count`, `content`, `comment_count`) VALUES
(11, 5, 1, '45039358', 1565786369861, 1565786369861, 0, '回复', 1),
(12, 11, 2, '45039358', 1565786379238, 1565786379238, 0, '回复的回复', 0),
(13, 5, 1, '45039358', 1565786390641, 1565786390641, 0, 'ok', 0),
(14, 9, 1, '47490129', 1565791321470, 1565791321470, 0, 'xixii', 1),
(15, 14, 2, '47490129', 1565791327460, 1565791327460, 0, 'xixixi', 0),
(16, 8, 1, '45039358', 1565797062967, 1565797062966, 0, '谢谢啦', 1),
(17, 16, 2, '45039358', 1565798097212, 1565798097212, 0, 'xx', 0),
(18, 8, 1, '45039358', 1565798105759, 1565798105759, 0, 'xx', 0),
(19, 13, 1, '45039358', 1565833566210, 1565833566210, 0, 'hello', 0),
(20, 9, 1, '45039358', 1565833575480, 1565833575480, 0, 'hello', 0),
(21, 9, 1, '45039358', 1565833648581, 1565833648581, 0, '这个能详细说说吗，是怎么触发这个bug的', 0),
(22, 14, 1, '45039358', 1565833799092, 1565833799092, 0, '欢迎大家测试', 0),
(23, 15, 1, '45039358', 1565835500311, 1565835500311, 0, '秀啊，非常感谢', 1),
(24, 23, 2, '47490129', 1565835566166, 1565835566166, 0, '嘻嘻嘻嘻~', 0),
(25, 15, 1, '49272812', 1565840629655, 1565840629655, 0, '为什么会变成我的了', 0),
(44, 20, 1, '45039358', 1565876359620, 1565876359620, 0, '非常感谢，修复好了', 0),
(45, 19, 1, '45039358', 1565876378559, 1565876378559, 0, '非常感谢，修复好了', 0),
(46, 17, 1, '45039358', 1565876436228, 1565876436228, 0, '最近有事，这个得拖了。。', 0),
(47, 9, 1, '45039358', 1565876868252, 1565876868252, 0, '非常感谢，修复好了', 0),
(48, 22, 1, '45039358', 1565876886469, 1565876886469, 0, '稳住兄弟', 0),
(49, 19, 1, '49272812', 1565880380877, 1565880380877, 0, 'fff', 0),
(50, 20, 1, '49272812', 1565880428821, 1565880428821, 0, '哈哈，不客气', 0),
(65, 24, 1, '45039358', 1566367290260, 1566367290260, 0, '我还没有备案', 0),
(66, 25, 1, '45039358', 1566367306653, 1566367306653, 0, 'O(∩_∩)O哈哈~', 0),
(67, 25, 1, '45039358', 1566367350472, 1566367350472, 0, '谢谢', 0);

-- --------------------------------------------------------

--
-- 表的结构 `notification`
--

CREATE TABLE IF NOT EXISTS `notification` (
  `id` bigint(20) NOT NULL,
  `notifier` bigint(20) NOT NULL,
  `receiver` bigint(20) NOT NULL,
  `outerid` bigint(20) NOT NULL,
  `type` int(11) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `notifier_name` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `outer_title` varchar(256) COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- 转存表中的数据 `notification`
--

INSERT INTO `notification` (`id`, `notifier`, `receiver`, `outerid`, `type`, `gmt_create`, `status`, `notifier_name`, `outer_title`) VALUES
(8, 45039358, 45039358, 5, 1, 1565786369930, 1, 'Lideyi', '图片'),
(9, 45039358, 45039358, 5, 2, 1565786379255, 1, 'Lideyi', '图片'),
(10, 45039358, 45039358, 5, 1, 1565786390654, 1, 'Lideyi', '图片'),
(11, 47490129, 47490129, 9, 1, 1565791321536, 1, '章鱼会咬人', '编辑问题没有验证，你的问题现在成了我的。。'),
(12, 47490129, 47490129, 9, 2, 1565791327467, 1, '章鱼会咬人', '编辑问题没有验证，你的问题现在成了我的。。'),
(13, 45039358, 47490129, 8, 1, 1565797062973, 1, 'Lideyi', '问题提交验证没做'),
(14, 45039358, 45039358, 8, 2, 1565798097243, 1, 'Lideyi', '问题提交验证没做'),
(15, 45039358, 47490129, 8, 1, 1565798105767, 1, 'Lideyi', '问题提交验证没做'),
(16, 45039358, 45039358, 13, 1, 1565833566297, 1, 'Lideyi', '谢谢啦，没想到bug怎么多，我还得再改改'),
(17, 45039358, 47490129, 9, 1, 1565833575494, 1, 'Lideyi', '编辑问题没有验证，你的问题现在成了我的。。'),
(18, 45039358, 47490129, 9, 1, 1565833648592, 1, 'Lideyi', '编辑问题没有验证，你的问题现在成了我的。。'),
(19, 45039358, 45039358, 14, 1, 1565833799101, 1, 'Lideyi', '修复了一些bug，大多是前端显示问题，数据库基本没有问题'),
(20, 45039358, 47490129, 15, 1, 1565835500343, 1, 'Lideyi', '先查看问题/question/12 在将question改成publish，就可以修改别人的问题'),
(21, 47490129, 45039358, 15, 2, 1565835566173, 1, '章鱼会咬人', '先查看问题/question/12 在将question改成publish，就可以修改别人的问题'),
(22, 49272812, 49272812, 15, 1, 1565840629665, 1, '30号的小学生', '厉害厉害'),
(23, 45039358, 49272812, 20, 1, 1565876359651, 1, 'Lideyi', '匿名回复跳转登录有问题'),
(24, 45039358, 47490129, 19, 1, 1565876378579, 1, 'Lideyi', '前端问题的标签，标题，和内容tirm（）一下'),
(25, 45039358, 47490129, 17, 1, 1565876436233, 1, 'Lideyi', '建议添加回复的分页，和问题的删除，标签验证还没做咯~'),
(26, 45039358, 47490129, 9, 1, 1565876868258, 1, 'Lideyi', '编辑问题没有验证，你的问题现在成了我的。。'),
(27, 45039358, 37759623, 22, 1, 1565876886505, 0, 'Lideyi', '厉害啊'),
(28, 49272812, 47490129, 19, 1, 1565880380882, 1, '30号的小学生', '前端问题的标签，标题，和内容tirm（）一下'),
(29, 49272812, 49272812, 20, 1, 1565880428827, 1, '30号的小学生', '匿名回复跳转登录有问题'),
(30, 45039358, 47490129, 24, 1, 1566367290309, 1, 'Lideyi', '可以和我说一下你是咋备案的吗'),
(31, 45039358, 50944308, 25, 1, 1566367306662, 0, 'Lideyi', '你这个网站好像有点问题'),
(32, 45039358, 50944308, 25, 1, 1566367350487, 0, 'Lideyi', '你这个网站好像有点问题');

-- --------------------------------------------------------

--
-- 表的结构 `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `id` int(11) NOT NULL,
  `title` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `description` text COLLATE utf8mb4_bin,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `creator` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `comment_count` int(11) DEFAULT '0',
  `view_count` int(11) DEFAULT '0',
  `like_count` int(11) DEFAULT '0',
  `tag` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- 转存表中的数据 `question`
--

INSERT INTO `question` (`id`, `title`, `description`, `gmt_create`, `gmt_modified`, `creator`, `comment_count`, `view_count`, `like_count`, `tag`) VALUES
(5, '图片', '图片fdsafadfasf\r\n![漫画图片](http://qiusuo.cn-bj.ufileos.com/61042337-0ea7-49b4-b468-fea3db035994.jpg?UCloudPublicKey=TOKEN_cb67e293-e227-4d7c-b347-3ff546435b9b&Signature=llyHQisSeHiAPPYg5MRM5LcdX5Q%3D&Expires=1881146275 "漫画图片")', 1565786322469, 1565786322469, '45039358', 2, 39, 0, 'git'),
(6, '我帮你找找bug', '找bug', 1565791068067, 1565791068067, '47490129', 0, 10, 0, 'java'),
(7, '我找到些bug', '有些bug', 1565791081726, 1565791081726, '47490129', 0, 10, 0, 'java'),
(8, '问题提交验证没做', '', 1565791103256, 1565791103256, '47490129', 2, 27, 0, ''),
(9, '编辑问题没有验证，你的问题现在成了我的。。', '', 1565791199454, 1565791199454, '47490129', 4, 24, 0, 'c++'),
(10, '地方撒发', '', 1565791294753, 1565791294753, '47490129', 0, 21, 0, 'java,css,c++'),
(11, '通知问题数永远显示为0', '', 1565791383375, 1565791383375, '47490129', 0, 14, 0, 'php'),
(12, '通知问题数永远显示为0，建议自己评论自己就不用通知了', '', 1565791482430, 1565791482430, '47490129', 0, 15, 0, 'php'),
(13, '谢谢啦，没想到bug怎么多，我还得再改改', '谢谢啦', 1565796580889, 1565796580889, '45039358', 1, 23, 0, 'css'),
(14, '修复了一些bug，大多是前端显示问题，数据库基本没有问题.', '![](http://qiusuo.cn-bj.ufileos.com/de432779-9d21-49eb-904c-70278a5a9c5a.jpg?UCloudPublicKey=TOKEN_cb67e293-e227-4d7c-b347-3ff546435b9b&Signature=hQ2VWzETBnHBZNxN5NPR%2BH8LV58%3D&Expires=1881193769)', 1565833783770, 1565833783770, '45039358', 1, 27, 0, 'css'),
(15, '厉害厉害', '原因是应为你修改的时候，直接将当前session中的user设置为了问题的发布者，正确的做法是，先根据这个question的id查数据库，查出问题的发布者，\r\n比较问题的发布者是否为当前session中的user，如果一样在修改，如果不一样抛异常或返回错误json\r\n```java\r\n@Override\r\n    public ResultTypeDTO saveOrUpdate(Question question, Integer userid) {\r\n        if(question.getId()==null){\r\n            question.setViewCount(0);\r\n            question.setCommentCount(0);\r\n            question.setLikeCount(0);\r\n            questionMapper.insert(question);\r\n            return new ResultTypeDTO().okOf().addMsg("result", QuestionErrorEnum.QUESTION_PUBLISH_SUCCESS.getMsg());\r\n        }else{\r\n            Question dbQuestion = questionExtMapper.findQuestionWithUserById(question.getId());\r\n            if(dbQuestion!=null&&dbQuestion.getCreator()!=userid){\r\n                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_IS_YOURS);\r\n            }\r\n            questionMapper.updateByPrimaryKeySelective(question);\r\n            return new ResultTypeDTO().okOf().addMsg("result", QuestionErrorEnum.QUESTION_UPDATE_SUCCESS.getMsg());\r\n        }\r\n    }\r\n	```', 1565835015341, 1565835015341, '47490129', 2, 40, 0, 'java'),
(16, 'gfdgs', '', 1565835198375, 1565835198375, '47490129', 0, 16, 0, ''),
(17, '建议添加回复的分页，和问题的删除，标签验证还没做咯~', '', 1565835745524, 1565835745524, '47490129', 1, 22, 0, ''),
(18, '样式不错', '可以', 1565835767299, 1565835767299, '47490129', 0, 18, 0, 'java'),
(19, '前端问题的标签，标题，和内容tirm（）一下', '', 1565835872381, 1565835872381, '47490129', 2, 55, 0, 'javascript'),
(20, '匿名回复跳转登录有问题', '可能是js文件里的跳转链接没有修改', 1565840803495, 1565840803495, '49272812', 2, 58, 0, 'javascript'),
(21, 'ddd', 'dd', 1565858376920, 1565858376920, '16053250', 0, 27, 0, 'javascript'),
(22, '厉害啊', '兄弟厉害啊', 1565860754346, 1565860754346, '37759623', 1, 69, 0, 'java'),
(23, '12312', '123123', 1565968228107, 1565968228107, '52896963', 0, 32, 0, 'php,css,html5,less,asp.net'),
(24, '你备案了吗', '和我说一下你是咋备案的吗', 1566088615089, 1566088615089, '47490129', 1, 58, 0, 'java'),
(25, '这网站做的不错', '666', 1566141877338, 1566141877338, '50944308', 2, 61, 0, 'html'),
(26, '厉害厉害', '牛牛牛', 1566379638329, 1566379638329, '47490129', 0, 50, 0, 'java');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(11) NOT NULL,
  `account_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `token` varchar(36) COLLATE utf8mb4_bin DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `avatar_url` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `account_id`, `name`, `token`, `gmt_create`, `gmt_modified`, `avatar_url`) VALUES
(2, '45039358', 'Lideyi', 'b66e264d-5470-44fc-b3d0-d7647c614412', 1566367280153, 1566367280153, 'https://avatars0.githubusercontent.com/u/45039358?v=4'),
(3, '47490129', '章鱼会咬人', '3832bcbd-99be-440d-b16c-fa5fca5f924b', 1566453918231, 1566453918231, 'https://avatars3.githubusercontent.com/u/47490129?v=4'),
(4, '52950234', '南城花已开', '4e0299b9-3929-44e4-bea8-df586ef44d56', 1565826047898, 1565826047898, 'https://avatars3.githubusercontent.com/u/52950234?v=4'),
(5, '49272812', '30号的小学生', '21043aff-47dd-417f-94c1-44f8cf3ee79f', 1565880375038, 1565880375038, 'https://avatars1.githubusercontent.com/u/49272812?v=4'),
(6, '43103543', '努力学习天天向上', 'bcc33a90-025f-40ab-95b3-1e97ea55c074', 1565849044478, 1565849044478, 'https://avatars2.githubusercontent.com/u/43103543?v=4'),
(7, '45685085', NULL, '2bab3051-fcb3-4a09-974b-a8b085f25677', 1565851840218, 1565851840218, 'https://avatars3.githubusercontent.com/u/45685085?v=4'),
(8, '16053250', 'zdw', '0ce3d4fa-19de-4225-9f7d-5dd50f75038f', 1565858354251, 1565858354251, 'https://avatars2.githubusercontent.com/u/16053250?v=4'),
(9, '37759623', 'bigdemon', '4467c27a-645c-4d40-8195-4e6cac6d1c96', 1565860639393, 1565860639393, 'https://avatars3.githubusercontent.com/u/37759623?v=4'),
(10, '46421732', NULL, '0b1c9f64-1286-4d08-ae59-e7f9599c69af', 1565958156374, 1565958156374, 'https://avatars2.githubusercontent.com/u/46421732?v=4'),
(11, '52896963', '偷捧时间煮酒喝', '654be912-768e-41ce-8855-26d07eb0b2df', 1565968153574, 1565968153574, 'https://avatars3.githubusercontent.com/u/52896963?v=4'),
(12, '48867374', '萌新的日常', '3328b4bf-7d3e-475a-9905-cb7775859394', 1566109311504, 1566109311504, 'https://avatars0.githubusercontent.com/u/48867374?v=4'),
(13, '50944308', NULL, '4b4461a5-db22-4b44-b94c-0ce379b3d6f5', 1566141998602, 1566141998602, 'https://avatars0.githubusercontent.com/u/50944308?v=4'),
(14, '21058164', '不正', '2e5df95b-3561-4e08-a4c9-a2703600b2b2', 1566915544717, 1566915544717, 'https://avatars0.githubusercontent.com/u/21058164?v=4'),
(15, '11855310', 'Halo', 'f714c904-d1dc-4242-ae65-5fa47fefc9cc', 1567161456694, 1567161456694, 'https://avatars0.githubusercontent.com/u/11855310?v=4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=68;
--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
