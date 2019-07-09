/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 5.5.53 : Database - office
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`office` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `office`;

/*Table structure for table `big_img` */

DROP TABLE IF EXISTS `big_img`;

CREATE TABLE `big_img` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `big_img_id` varchar(64) NOT NULL COMMENT '大图id',
  `page_id` varchar(64) NOT NULL,
  `big_img_img` varchar(255) DEFAULT NULL COMMENT '大图地址',
  `big_img_title` varchar(255) DEFAULT NULL,
  `big_img_subhead` varchar(255) DEFAULT NULL COMMENT '副标题',
  `big_img_desc` text,
  `big_img_status` int(11) NOT NULL DEFAULT '0' COMMENT '0 失效  ，1 有效',
  `big_img_create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`big_img_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `big_img` */

insert  into `big_img`(`id`,`big_img_id`,`page_id`,`big_img_img`,`big_img_title`,`big_img_subhead`,`big_img_desc`,`big_img_status`,`big_img_create_time`) values (1,'bi1','default_p1','/store/image/1558402991052.jpg','DO THE BEST ENTERPRISE SERVICE','做最好的企业服','让您不再为企业担心 让您不再为企业烦恼 让您不再为企业操劳',1,1523689294),(2,'bi2','default_p2','/store/image/1558332628992.jpg','公司业务企业服','产品标题','让您不再为企业担心 让您不再为企业烦恼 让您不再为企业操劳',1,1523689354),(3,'bi3','default_p3','/store/image/1558332579341.jpg','做最好的企业服','做最好的企业服','让您不再为企业担心 让您不再为企业烦恼 让您不再为企业操劳',1,1523689454),(4,'bi4','default_p4','/store/image/1558402171698.jpg','做最好的企业服','做最好的企业服','做最好的企业服',1,1523689554),(5,'bi5','default_p5','/store/image/1558332482235.jpg','做最好的服务领导者','做最好的服务领导者','让您不再为企业担心 让您不再为企业烦恼 让您不再为企业操劳',1,1523689654);

/*Table structure for table `head_food` */

DROP TABLE IF EXISTS `head_food`;

CREATE TABLE `head_food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hf_id` varchar(64) NOT NULL,
  `head_logo` varchar(255) NOT NULL,
  `head_menu` varchar(255) DEFAULT NULL,
  `foot_title` varchar(255) NOT NULL,
  `foot_dest` text NOT NULL,
  `contact_one_img` varchar(255) NOT NULL,
  `contact_one_text` varchar(255) NOT NULL,
  `contact_two_img` varchar(255) NOT NULL,
  `contact_two_text` varchar(255) NOT NULL,
  `hf_status` int(11) NOT NULL DEFAULT '0',
  `hf_create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`hf_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `head_food` */

insert  into `head_food`(`id`,`hf_id`,`head_logo`,`head_menu`,`foot_title`,`foot_dest`,`contact_one_img`,`contact_one_text`,`contact_two_img`,`contact_two_text`,`hf_status`,`hf_create_time`) values (1,'hf_1','\\store\\image\\1558325928614.png','1','成都企业服务管理咨询有限公司','这里会写下我们公司的一些寄予或者理念，又或者是对我们的自己的一些介绍，这里会写下我们公司的一些寄予或者理念，介绍','/store/image/1558275015240007070.png','微信','/store/image/1558275015240007070.png','公众号',1,1);

/*Table structure for table `item_advantage` */

DROP TABLE IF EXISTS `item_advantage`;

CREATE TABLE `item_advantage` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `advantage_id` varchar(64) NOT NULL,
  `iadvantage_img` varchar(255) DEFAULT NULL,
  `iadvantage_title` varchar(255) DEFAULT NULL,
  `iadvantage_desc` text,
  `article_id` varchar(64) DEFAULT NULL,
  `iadvantage_status` int(11) NOT NULL DEFAULT '0',
  `iadvantage_create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`advantage_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `item_advantage` */

insert  into `item_advantage`(`id`,`advantage_id`,`iadvantage_img`,`iadvantage_title`,`iadvantage_desc`,`article_id`,`iadvantage_status`,`iadvantage_create_time`) values (1,'ad_1','http://localhost:8080/image/ag1.png','实力雄厚1','提供企业一条龙服务，完成百万家企业服务',NULL,0,123),(2,'ad_2','/store/image/1558326253024.png','有担当','多渠道互联网在线免费咨询服务','w90v1231pRDHQd128MJ+Tw==',1,123),(3,'ad_3','/store/image/1558326262206.png','专业团队','100余人的专业化的服务精英团队','t/DvV2E8JxkET64/i6363Q==',1,123),(4,'ad_4','/store/image/1558326271251.png','安全保障','签订合同，真实可靠的保障企业利益',NULL,1,123),(5,'ad_5','/store/image/1558326278962.png','快速响应','24小时全面服务，及时解决您的问题',NULL,1,123),(6,'ad_6','/store/image/1558326288760.png','服务周到','提供专业的一对一服务顾问',NULL,1,123),(7,'NYYmiAthg2UdgY+0uCumQg==','/store/image/1558326296459.png','专业团队','100余人的专业化的服务精英团队',NULL,1,1558180036);

/*Table structure for table `item_article` */

DROP TABLE IF EXISTS `item_article`;

CREATE TABLE `item_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(64) NOT NULL,
  `article_title` varchar(255) NOT NULL,
  `article_text` text NOT NULL,
  `article_browser` int(11) NOT NULL DEFAULT '0',
  `article_status` int(11) NOT NULL DEFAULT '1',
  `article_create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`article_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `item_article` */

insert  into `item_article`(`id`,`article_id`,`article_title`,`article_text`,`article_browser`,`article_status`,`article_create_time`) values (1,'w90v1231pRDHQd128MJ+Tw==','从三场重要外交活动看习近平的全球视野','<p style=\"text-align: center;\"><span style=\"color: rgb(147, 137, 83); background-color: rgb(146, 208, 80); border: 1px solid rgb(0, 0, 0); font-size: 24px;\"><strong><span style=\"background-color: rgb(146, 208, 80); border: 1px solid rgb(0, 0, 0); color: rgb(255, 255, 255);\">从三场重要外交活动看习近平的全球视野</span></strong></span></p><p><span style=\"font-size: 18px;\">原标题：从三场重要外交活动看习近平的全球视野\n\n　　新华网记者 赵银平\n\n　　[学习进行时]2019年三场重要外交活动近期相继亮相：第二届“一带一路”国际合作高峰论坛、2019年中国北京世界园艺博览会、亚洲文明对话大会。<img src=\"http://img.baidu.com/hi/jx2/j_0048.gif\" width=\"209\" height=\"147\"/>三场活动，习近平都发表了重要讲话。从讲话中，我们可以清晰看到习近平的全球视野和宏大眼界。新华社《学习进行时》原创品牌栏目“讲习所”今天推出文章，与您一起感知。\n\n　　春和景明、花开灿烂之际，中国也迎来了主场外交的繁忙时刻。\n\n　　4月26日，习近平在第二届“一带一路”国际合作高峰论坛开幕式上发表主旨演讲；\n\n　　4月28日，习近平在2019年中国北京世界园艺博览会开幕式上发表重要讲话；\n\n　　5月15日，习近平在亚洲文明对话大会开幕式上发表主旨演讲。\n\n　　紧凑的鼓点，热烈的氛围，众宾云集让古都北京的春光更显明媚。宏大的视野，切实的主张，中国方案让“鸡犬之声相闻”的“地球村”更加亲密。\n\n　　“一带一路”——从“大写意”到“工笔画”\n\n　　“一带一路”倡议提出已近6年。\n\n　　筚路蓝缕，春华秋实，“一带一路”完成了总体布局，绘就了一幅“大写意”；桃李不言，下自成蹊，“一带一路”的“朋友圈”越来越大，成为“机遇之路、繁荣之路”。\n\n　　面向未来，“一带一路”建设怎样才能百尺竿头更进一步？\n\n　　习近平“胸有成竹”。\n\n　　在第二届“一带一路”国际合作高峰论坛开幕式的主旨演讲中，一方面，他着眼全球，提出3点主张：“要秉持共商共建共享原则”，“要坚持开放、绿色、廉洁理念”，“要努力实现高标准、惠民生、可持续目标”。\n\n　　另一方面，他立足中国，宣布下阶段中国对外开放5大措施：更广领域扩大外资市场准入；更大力度加强知识产权保护国际合作；更大规模增加商品和服务进口；更加有效实施国际宏观经济政策协调；更加重视对外开放政策贯彻落实。\n\n　　既有格局，又有布局；既有大气，更有静气。\n\n　　精谨细腻，笔笔有章法，这样一幅“工笔画”，怎能不让人期待？\n\n　　襟怀磊落，步步求共赢，这样一条“机遇之路、繁荣之路”，怎能不应者云集？\n\n　　写下了《丝绸之路：一部全新的世界史》一书的英国历史学家彼得·弗兰科潘说：“丝绸之路曾经塑造了过去的世界，甚至塑造了当今的世界，也将塑造未来的世界。”从2013年的那个秋天，到2017年的雁栖湖之聚，再到今年的春天之约。回望过去，我们有理由“登高赋新诗”，憧憬明天。面向未来，我们更有信心，“一定能够走出一条互利共赢的康庄大道”。\n\n　　北京世园会——“春风花草香”\n\n　　皮之不存，毛将焉附。如果地球的生态环境不再适合生存了，人类将何以自处？\n\n　　对生态环境问题，习近平高度重视，紧抓不放。\n\n　　在2019年中国北京世界园艺博览会开幕式上，习近平说：“地球是全人类赖以生存的唯一家园”，“我们要像保护自己的眼睛一样保护生态环境，像对待生命一样对待生态环境”。\n\n　　</span></p><p><span style=\"font-size: 18px;\"><img src=\"http://img.baidu.com/hi/jx2/j_0066.gif\" width=\"179\" height=\"187\"/></span></p><p><span style=\"font-size: 18px;\">读懂了这两句话，便读懂了习近平5个“追求”的苦心与眼光。\n\n　　——我们应该追求人与自然和谐。\n\n　　——我们应该追求绿色发展繁荣。\n\n　　——我们应该追求热爱自然情怀。\n\n　　——我们应该追求科学治理精神。\n\n　　——我们应该追求携手合作应对。\n\n　　5个“追求”，切中自然与人类关系的痛点，提出了可行的中国方案。言辞恳切、思虑长远。这是人类与自然的共生共赢之路，这是为人类谋长远之举。\n\n　　读懂了这5个“追求”，便读懂了生态文明建设的意义。\n\n　　亿万中国人，用行动在地球上刻画出一条绿色足迹：美国航天局的卫星数据显示，全球从2000年到2017年新增的绿化面积中，约四分之一来自中国，贡献比例居全球首位。在全球森林资源持续减少的背景下，中国森林面积和蓄积量持续双增长，成为全球森林资源增长最多的国家。\n\n　　一个个事例，证明了“绿水青山就是金山银山”不是虚言。曾让人“感冬寒秋肃，叹人稀鸟亡”的塞罕坝成为“华北绿肺”，资源总价值在2017年就达到200多亿元，投入与产出比1∶19.8；被称作“死亡之海”的库布其沙漠，原本黄沙漫漫的恩格贝成了生态示范区，一片片苜蓿散布其间，136座温室里种着各种瓜果蔬菜……\n\n　　“我希望，这片园区所阐释的绿色发展理念能传导至世界各个角落。”“让我们从自己、从现在做起，把接力棒一棒一棒传下去。”殷殷期望，为中国，为世界，为人类。\n\n　　“迟日江山丽，春风花草香。”这样的美丽世界，需要我们世世代代努力呵护。\n\n　　文明交流互鉴——“繁花春满园”\n\n　　5月的北京街头，形似盛开牡丹的亚洲文明对话大会标志分外醒目。这朵“文明之花”用六个花瓣将亚洲地理位置的六个分区包罗，瓣瓣互动、手手相牵，象征亚洲文明的绚丽多彩，也寓意不同文明的交流对话。\n\n　　为何要强调不同文明的交流对话？\n\n　　因为“文明因多样而交流，因交流而互鉴，因互鉴而发展”。各种文明在这片土地上交相辉映，“谱写了亚洲文明发展史诗”；中华文明更是在同其他文明不断交流互鉴中形成开放的体系，“始终在兼收并蓄中历久弥新”。\n\n　　因为应对共同挑战、迈向美好未来，“需要文化文明力量”。要建设一个和平安宁、共同繁荣、开放融通的亚洲，需要通过不同文化的交流互鉴，“消除隔阂和误解、促进民心相知相通”，“夯实共建亚洲命运共同体、人类命运共同体的人文基础”。\n\n　　对不同文明间的交流互鉴，习近平提出4点主张：第一，坚持相互尊重、平等相待；第二，坚持美人之美、美美与共；第三，坚持开放包容、互学互鉴；第四，坚持与时俱进、创新发展。\n\n　　习近平，正是文明交流互鉴的探索者。他说，我访问过世界上许多地方，最吸引我的就是韵味不同的文明。中亚的古城撒马尔罕、埃及的卢克索神庙、新加坡的圣淘沙、泰国的曼谷玉佛寺、希腊的雅典卫城，都留下了他探索文明交流互鉴的足迹和思考。\n\n　　中国，更是文明交流互鉴的践行者。此次亚洲文明对话大会就是一个最好的例证。这是一次激发文化共鸣点、维护文明多样性的旷古盛会，更为文明互学互鉴、共同发展提供了一个良好平台。\n\n　　面向未来，与4点主张相配合，习近平还以4个“中国愿……”表明了中国不懈推进文明交流互鉴的态度和行动。有言有行，有主张有谋划，推动文明交流互鉴，中国从不缺席。\n\n　　“繁花春满园，群生则锦绣。”每一种文明都是美的结晶，都彰显着创造之美。从中国而亚洲而世界，让姹紫嫣红的亚洲文化之花尽情绽放，让世界文明百花园群芳竞艳，这才是世界该有的样子！\n\n　　中国方案——“百事得其道者成”\n\n　　中国三场重要外交活动，高朋满座。习近平三次重要讲话，赢得喝彩。因为中国秉持公心，“治天下也，必先公，公则天下平矣”；因为中国秉持大道，“万物得其本者生，百事得其道者成”。\n\n　　这个公心，就是互利共赢、共同发展；这个大道，就是构建人类命运共同体。\n\n　　当今世界正经历百年未有之大变局。一方面，世界多极化进一步发展，新兴市场国家和发展中国家崛起成为不可阻挡的历史潮流，各国人民的命运也从未像今天这样紧密相连；另一方面，霸权主义、强权政治依然存在，保护主义、单边主义不断抬头，文明冲突论、文明优越论等错误论调不时沉渣泛起……\n\n　　合作还是对抗，冲突还是对话，开放还是封闭，互利共赢还是以邻为壑？\n\n　　2017年1月18日，瑞士日内瓦万国宫，习近平提出了中国方案：构建人类命运共同体，实现共赢共享。\n\n　　习近平从全局和全球视野，以哲学和历史高度，深刻回答了“人类社会何去何从”这个根本性问题，为世界和平发展指明了正确方向。\n\n　　大道至简，实干为要。有中国方案，更有具体的中国主张和实践。无论是共建“一带一路”倡议还是保护环境5点追求抑或文明交流互鉴4点主张，都是结合中国发展和国际合作大局，为中国谋也为世界谋。\n\n　　事实已经证明并将继续证明。有识之士也已经看到中国之“道”的历史眼光和天下情怀。“在处理国际事务中，中国站在历史的正确一边。”菲律宾总统杜特尔特这样评价。“建立一个没有纷争、所有国家都获得发展、所有人都幸福生活的世界，是人类的共同理想。”日本前首相福田康夫说，“将理想变为现实，需要政治家高瞻远瞩，并尽最大努力付诸行动。中国倡议构建人类命运共同体，正是基于这样的思路，并且中国具有实践的能力。”\n\n　　“志合者，不以山海为远。”因为相知，千山万水只等闲，四海宾朋聚北京。“和羹之美，在于合异。”为了共赢，八千里路云和月，中国不懈前行。</span></p>',13,1,1558320675),(2,'w90vUcipRDHQd128QMJ+Tw==','修改稿的标题','<p>123123阿萨德</p>',0,1,1558320675),(3,'w90vUcipRDHQdYq8QMJ+Tw==','123','<p>测<br/></p>',0,1,1558320538),(4,'t/DvV2E8JxkET64/i6363Q==','123','<p>测<br/></p>',2,1,1558320675);

/*Table structure for table `item_business` */

DROP TABLE IF EXISTS `item_business`;

CREATE TABLE `item_business` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_id` varchar(64) NOT NULL,
  `business_img` varchar(255) NOT NULL,
  `business_text` varchar(255) NOT NULL,
  `article_id` varchar(64) DEFAULT NULL,
  `business_status` int(11) NOT NULL DEFAULT '0',
  `business_create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`business_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `item_business` */

insert  into `item_business`(`id`,`business_id`,`business_img`,`business_text`,`article_id`,`business_status`,`business_create_time`) values (1,'td1ZhRQTrbk1m4v617t8Rg==','/store/image/1558326655183.png','这是业务','w90v1231pRDHQd128MJ+Tw==',1,1558180757),(2,'JlwFMD8YzzLYe5hsj2/0HQ==','/store/image/1558326663558.png','这是业务2','w90v1231pRDHQd128MJ+Tw==',1,1558181368),(3,'fveHSR4ywJNMF7k1jAngaA==','/store/image/1558326669909.png','这是业务3',NULL,1,1558181375),(4,'fUhXHCPyQKfD1aiPcbpxmw==','/store/image/1558326676690.png','这是业务4','w90v1231pRDHQd128MJ+Tw==',1,1558181383),(5,'bTGIldgfrFBTMaZa80cINg==','http://localhost:8080/image/1558181424275.png','这是业务5',NULL,0,1558181424);

/*Table structure for table `item_case` */

DROP TABLE IF EXISTS `item_case`;

CREATE TABLE `item_case` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `icase_id` varchar(64) NOT NULL,
  `icase_title` varchar(255) DEFAULT NULL,
  `icase_img` varchar(255) DEFAULT NULL,
  `icase_text` text,
  `icase_subhead` varchar(255) DEFAULT NULL,
  `business_use` int(11) NOT NULL DEFAULT '0',
  `index_use` int(11) NOT NULL DEFAULT '0',
  `article_id` varchar(64) DEFAULT NULL,
  `icase_status` int(11) NOT NULL DEFAULT '0',
  `icase_create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`icase_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `item_case` */

insert  into `item_case`(`id`,`icase_id`,`icase_title`,`icase_img`,`icase_text`,`icase_subhead`,`business_use`,`index_use`,`article_id`,`icase_status`,`icase_create_time`) values (11,'Dw1xO07VSjVa38iHeKF+sA==','Vue 中文本内容超出规定行数后展1','/store/image/1558331678910.png','若照相机侦测到人物拍摄对象的脸部，该脸部将以表示对焦点的琥珀色边框标识。若侦测到拍摄对象的眼睛，表示对焦点的琥珀色边框将出现在任一眼睛的周围。若照相机侦测到多张脸部或多只眼睛，对焦点的两侧将出现三角形;若要将对焦点置于其他脸部或眼睛上，请向左或向右按下多重选择器或副选择器。','Vue 中文本内容超出规定行数后展1',1,1,NULL,1,1558165214),(14,'FPl1Annr143V/jvY/eAReg==','Vue 中文本内容超出规定行数后展3','/store/image/1558331686655.png','若照相机侦测到人物拍摄对象的脸部，该脸部将以表示对焦点的琥珀色边框标识。若侦测到拍摄对象的眼睛，表示对焦点的琥珀色边框将出现在任一眼睛的周围。若照相机侦测到多张脸部或多只眼睛，对焦点的两侧将出现三角形;若要将对焦点置于其他脸部或眼睛上，请向左或向右按下多重选择器或副选择器。若照相机侦测到人物拍摄对象的脸部，该脸部将以表示对焦点的琥珀色边框标识。若侦测到拍摄对象的眼睛，表示对焦点的琥珀色边框将出现在任一眼睛的周围。若照相机侦测到多张脸部或多只眼睛，对焦点的两侧将出现三角形;若要将对焦点置于其他脸部或眼睛上，请向左或向右按下多重选择器或副选择器。','Vue 中文本内容超出规定行数后展3',0,0,NULL,1,1558166798),(1,'ic_1','这是标题1','http://localhost:8080/image/1557971729925.png','这是内容这是内容这是内容这是内容这是内容这是内容容这是内容这是内容这是内容这是内容这是内容','成功案例1',0,0,NULL,0,123),(2,'ic_2','这是标题2','http://localhost:8080/image/1557971741294.png','这是内容这是内容这是内容这是内容这是内容这是内容这容这是内容这是内容','成功案例2',0,0,NULL,0,123),(3,'ic_3','这是标题3','http://localhost:8080/image/1557971750520.png','这是内容这是内容这是内容这是内容这是内容这是内容这容这是内容这是内容','成功案例3',0,1,NULL,0,123),(4,'ic_4','这是标题4','http://localhost:8080/image/1557971756581.png','这是内容这是内容这是内容这是内容这是内容这是内容这容这是内容这是内容','成功案例4',1,1,NULL,0,123),(12,'KYDSFidBE5aCzID3kIQmGQ==','Vue 中文本内容超出规定行数后展','/store/image/1558331692349.png','若照相机侦测到人物拍摄对象的脸部，该脸部将以表示对焦点的琥珀色边框标识。若侦测到拍摄对象的眼睛，表示对焦点的琥珀色边框将出现在任一眼睛的周围。若照相机侦测到多张脸部或多只眼睛，对焦点的两侧将出现三角形;若要将对焦点置于其他脸部或眼睛上，请向左或向右按下多重选择器或副选择器。','Vue 中文本内容超出规定行数后展',1,1,'w90v1231pRDHQd128MJ+Tw==',1,1558165472),(10,'mi6NSLpW4Nb1256/fjgdJw==','测试案例','http://localhost:8080/image/bg7.png','这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试这是测试','测试成功',1,0,NULL,0,1556709891),(13,'Ry+ARV2ukVXmle6AyQ1PNA==','Vue 中文本内容超出规定行数后展2','/store/image/1558331699311.png','若照相机侦测到人物拍摄对象的脸部，该脸部将以表示对焦点的琥珀色边框标识。若侦测到拍摄对象的眼睛，表示对焦点的琥珀色边框将出现在任一眼睛的周围。若照相机侦测到多张脸部或多只眼睛，对焦点的两侧将出现三角形;若要将对焦点置于其他脸部或眼睛上，请向左或向右按下多重选择器或副选择器。','Vue 中文本内容超出规定行数后展2',0,0,NULL,1,1558166771);

/*Table structure for table `item_experience` */

DROP TABLE IF EXISTS `item_experience`;

CREATE TABLE `item_experience` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `expert_id` varchar(64) NOT NULL,
  `expert_img` varchar(255) NOT NULL,
  `expert_title` varchar(255) NOT NULL,
  `expert_desc` text NOT NULL,
  `article_id` varchar(64) DEFAULT NULL,
  `expert_status` int(11) NOT NULL DEFAULT '0',
  `expert_create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`expert_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `item_experience` */

insert  into `item_experience`(`id`,`expert_id`,`expert_img`,`expert_title`,`expert_desc`,`article_id`,`expert_status`,`expert_create_time`) values (16,'+6pX1aIV+etP6QPaT613MA==','E:\\img\\1556698756268.png','123123','123123',NULL,0,1556100820),(20,'0/kXTZX/EJCpIdXqnjvFig==','E:\\img\\1556103357868.png','','',NULL,0,1556103357),(21,'49tbvshIJB2fvkAPl6jCKA==','E:\\img\\1556103566959.png','','',NULL,0,1556103566),(10,'4YaiKR4qVeBe4tkOaEE+ww==','E:\\img\\1556100179528.png','123','123',NULL,0,1556100179),(24,'71048snRL3aMtGBpkCho+Q==','E:\\img\\1556103876251.png','','',NULL,0,1556103876),(26,'7KTXAFbn25X/YJwU8YlhRQ==','E:\\img\\1556104052270.png','123','123',NULL,0,1556104052),(22,'bqxgSGrVHtee3tpvDBFMog==','E:\\img\\1556103577429.png','123','123',NULL,0,1556103577),(1,'ex_1','/store/image/1558333312248.png','满意的客户','Everage敏捷框架提供了一个健壮的概要，以提供高水平的俯视无人机驾驶员','w90v1231pRDHQd128MJ+Tw==',1,123),(2,'ex_2','/store/image/1558326262206.png','项目做完','通过工作场所多元化的目的，有机地发展颠覆性创新的整体世界观',NULL,1,123),(3,'ex_3','/store/image/1558326271251.png','飞行时间','采用双赢的生存策略，确保视频的主动控制',NULL,1,123),(4,'ex_4','/store/image/1558326288760.png','4 k的决议','利用低垂的水果，确定一个球场价值增加的活动，以驾驶考试',NULL,1,123),(8,'ex_5','132','123','123',NULL,0,123),(12,'jJzSiXbDmuVIKWYPCQ0Chg==','E:\\img\\1556100233674.png','123','123',NULL,0,1556100233),(23,'KGQp6z3F/lxwNUZ+ouh95A==','E:\\img\\1556103609438.png','123','123',NULL,0,1556103609),(14,'l8BduhmVOmr6cptX++WHzA==','E:\\img\\1556100499038.png','12','213',NULL,0,1556100499),(15,'TIzJwcoYtDuntbf6b4DFqw==','E:\\img\\1556100570547.png','123','123',NULL,0,1556100570),(17,'tXyjlpC8IUnck6MS6nPzEw==','E:\\img\\1556102286928.png','','',NULL,0,1556102286),(11,'vvV/9ltp2uhm6qBGEQEUyQ==','E:\\img\\1556100206944.png','123','123',NULL,0,1556100206),(19,'XvdmuVe/gsMecgt+5CADUA==','E:\\img\\1556103334988.png','','',NULL,0,1556103334),(18,'yEfs501zD26kS+OZibc+fQ==','E:\\img\\1556102505376.png','','',NULL,0,1556102505),(13,'yEuZupTBF2NYLC7AcCuFEA==','E:\\img\\1556100482267.png','123','123',NULL,0,1556100482),(27,'YFlAJBHZjwfoq0bra6I5CA==','E:\\img\\1556115313609.png','123','123',NULL,0,1556115313),(25,'yp9SQ3Kvi5hj5RyrV82Qtg==','E:\\img\\1556103889836.png','','',NULL,0,1556103889),(9,'YzfsAxNiZaNJ1ipNI8vI7w==','E:\\img\\1556100158167.png','12','123',NULL,0,1556100158);

/*Table structure for table `item_file` */

DROP TABLE IF EXISTS `item_file`;

CREATE TABLE `item_file` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(64) NOT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `file_download` varchar(255) DEFAULT NULL,
  `file_context_title` varchar(255) NOT NULL,
  `file_context_desc` text,
  `article_id` varchar(64) DEFAULT NULL,
  `file_status` int(11) NOT NULL DEFAULT '1',
  `file_create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`file_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `item_file` */

insert  into `item_file`(`id`,`file_id`,`file_url`,`file_download`,`file_context_title`,`file_context_desc`,`article_id`,`file_status`,`file_create_time`) values (18,'9VmClN3M1LUBPGZq1pA/rg==','/store/file/1558332959192.pdf','D:\\tomcat8.5\\webapps\\store\\file\\1558332959192.pdf','测','饿','w90v1231pRDHQd128MJ+Tw==',1,1558332959),(17,'xFZWLPzj5DI0ZtoFHYoC/Q==','http://localhost:8080/file/1556721152602.pdf','D:\\tomcat8.5\\webapps\\file\\1556721152602.pdf','fastdfs单节点安装手册','FastDFS是一个开源的轻量级分布式文件系统，它对文件进行管理，功能包括：文件存储、文件同步、文件访问（文件上传、文件下载）等，解决了大容量存储和负载均衡的问题。',NULL,1,1556721152);

/*Table structure for table `item_service` */

DROP TABLE IF EXISTS `item_service`;

CREATE TABLE `item_service` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `service_id` varchar(64) NOT NULL,
  `service_img` varchar(255) DEFAULT NULL,
  `service_title` varchar(255) DEFAULT NULL,
  `service_desc` text,
  `article_id` varchar(64) DEFAULT NULL,
  `service_status` int(11) NOT NULL DEFAULT '0',
  `service_create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`service_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `item_service` */

insert  into `item_service`(`id`,`service_id`,`service_img`,`service_title`,`service_desc`,`article_id`,`service_status`,`service_create_time`) values (6,'0dXvXrLC/83qFqLA9aIDqQ==','http://localhost:8080/image/1556709077094.png','测试','偶然承诺书222',NULL,0,1556097661),(21,'2kbI42Yc5jBTNj1p06rbJg==','http://localhost:8080/image/1558259983631.png','测','测',NULL,0,1558259983),(19,'fWonLk6xufnps05P4zw44A==','/store/image/1558326676690.png','无人机产品52','这是无人机内容这是无人机内容这是等等',NULL,1,1557974628),(15,'hJkM7ead/tlxFUffibHR7A==','/store/image/1558326663558.png','无人机产品4','这是无人机内容这是无人机内容这是',NULL,1,1557974456),(13,'JJYDoveq1QdaktGLFBkmNA==','/store/image/1558332668172.png','无人机产品2','这是无人机内容这是无人机内容这是',NULL,1,1557974336),(20,'jYTWoAMeYFeDADr6mgrfnw==','http://localhost:8080/image/1558164500666.png','ce ','ce ',NULL,0,1558164500),(11,'lufDsmVM/Q0oXxB63Gr7vA==','http://localhost:8080/image/1557974096910.png','无人机产品1','这是无人机内容这是无人机内容这是无人机内容这是无人机内容',NULL,0,1557974096),(18,'ouxL35wNXh5n0Qgxu3k9Ng==','/store/image/1558326655183.png','无人机产品51','这是无人机内容这是无人机内容这是等等','w90v1231pRDHQd128MJ+Tw==',1,1557974621),(14,'p/YzLt04IkXVqvLaOlcO5g==','http://localhost:8080/image/1557974449049.png','无人机产品3','这是无人机内容这是无人机内容这是',NULL,0,1557974449),(16,'pGZelQc/RMGCtDlN2B0+sQ==','http://localhost:8080/image/1557974609523.png','无人机产品54','这是无人机内容这是无人机内容这是等等',NULL,0,1557974609),(12,'qnH86kFmEjV5TXoX5nL8Ew==','http://localhost:8080/image/1557974269913.png','无人机产品1','这是无人机内容这是无人机内容这是无人机内容这是无人机内容',NULL,0,1557974269),(17,'SEEgG6Sxlq2cJZH71WaGmQ==','/store/image/1558326669909.png','无人机产品53','这是无人机内容这是无人机内容这是等等',NULL,1,1557974617),(1,'se_1','http://localhost:8080/image/1557972037328.png','网络服务器维护','让你的网站永不掉线1',NULL,0,123123),(2,'se_2','http://localhost:8080/image/1556709077094.png','网络服务器维护2','让你的网站永不掉线2',NULL,0,123123),(3,'se_3','http://localhost:8080/image/1556709077094.png','网络服务器维护3','让你的网站永不掉线3',NULL,0,1523689294);

/*Table structure for table `item_team` */

DROP TABLE IF EXISTS `item_team`;

CREATE TABLE `item_team` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `team_id` varchar(64) NOT NULL,
  `team_img` varchar(255) NOT NULL,
  `team_title` varchar(255) NOT NULL,
  `team_desc` text NOT NULL,
  `article_id` varchar(64) DEFAULT NULL,
  `team_status` int(11) NOT NULL DEFAULT '0',
  `team_create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`team_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `item_team` */

insert  into `item_team`(`id`,`team_id`,`team_img`,`team_title`,`team_desc`,`article_id`,`team_status`,`team_create_time`) values (8,'/SoZ1E+MD73333RnUFjF0Q==','/store/image/1558333408353.jpg','asdasd','asdasdasd',NULL,0,123),(3,'/SoZ1E+MD7T/22RnUFjF0Q==','/store/image/1558333408353.jpg','杰克·科瓦尔斯基','3十年的飞行经验。廉价无人机服务，用于空中无人机检查。',NULL,1,123),(4,'/SoZ1E+MD7T/911nUFjF0Q==','/store/image/1558333408353.jpg','杰克·科瓦尔斯基','4十年的飞行经验。廉价无人机服务，用于空中无人机检查。','w90v1231pRDHQd128MJ+Tw==',1,123),(2,'/SoZ1E+MD7T/98Rn23jF0Q==','/store/image/1558333408353.jpg','杰克·科瓦尔斯基','2十年的飞行经验。廉价无人机服务，用于空中无人机检查。',NULL,1,123),(10,'/SoZ1E+MD7T/98RnUFjF0Q==','/store/image/1558333408353.jpg','asdasdasd','casdasdas',NULL,0,1556766336),(1,'/SoZ1E1237T/98RnUFjF0Q==','/store/image/1558333408353.jpg','杰克·科瓦尔斯基','1十年的飞行经验。廉价无人机服务，用于空中无人机检查。',NULL,1,123),(9,'K18STrMbNExefUvUlZ83jQ==','/store/image/1558333408353.jpg','123饿22','123饿',NULL,0,1556115233);

/*Table structure for table `item_video` */

DROP TABLE IF EXISTS `item_video`;

CREATE TABLE `item_video` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `video_id` varchar(64) NOT NULL,
  `video_img` varchar(255) DEFAULT NULL,
  `video_url` varchar(255) NOT NULL,
  `video_context_img` varchar(255) NOT NULL,
  `video_context_title` varchar(255) NOT NULL,
  `video_context_desc` text NOT NULL,
  `video_status` int(11) NOT NULL DEFAULT '1',
  `video_create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`video_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `item_video` */

insert  into `item_video`(`id`,`video_id`,`video_img`,`video_url`,`video_context_img`,`video_context_title`,`video_context_desc`,`video_status`,`video_create_time`) values (13,'CmpkIZ162cBQ26WX2oz8kw==','123','123','213','123','123',0,1556762451),(12,'dmDkXhQQoHMMwrL4RlK+yw==','https://image.pearvideo.com/main/20190419/11965843-143128-1.png','https://video.pearvideo.com/mp4/adshort/20190419/cont-1544890-11965843-162530_adpkg-ad_hd.mp4','https://image.pearvideo.com/main/20190419/11965843-143128-1.png','中国大疆无人机助力巴黎圣母院灭火','在巴黎圣母院的灭火行动中，法国派出了两架中国大疆无人机，法国消防员表示它们在其中发挥了很大的作用。消防队员依靠无人机实现了对火灾动态的追踪，来帮助他们进行现场安排以及火场勘测。',1,1556710980),(11,'fK9pWsoj5mlEpTIckRn5sw==','https://image1.pearvideo.com/cont/20190424/cont-1546915-11936344.jpg','https://video.pearvideo.com/mp4/adshort/20190424/cont-1546915-13842153_adpkg-ad_hd.mp4','https://image1.pearvideo.com/cont/20190424/cont-1546915-11936344.jpg','谷歌获美国首个无人机快递许可','美国联邦航空局宣布，向谷歌母公司“字母表”旗下的无人机配送公司“翼航空”发放首个无人机配送许可。翼航空此前在澳大利亚已经进行了数千次安全飞行，美国联邦航空局称其已达到取得许可证的安全要求。',1,1556710905),(14,'mWeBov2a3bugRETz6SyxDA==','https://image1.pearvideo.com/cont/20190517/cont-1555763-11965063.jpg','https://video.pearvideo.com/mp4/adshort/20190517/cont-1555763-13919429_adpkg-ad_hd.mp4','https://image1.pearvideo.com/cont/20190517/cont-1555763-11965063.jpg','完全就业时代！日毕业生就业率97%','5月17日，日本文部省公布数据：2019年春季毕业的应届大学生的就业率达到97.6%，就业市场持续为卖方市场。因为没有拿到第一志愿的公司内定（获得offer），便决定不工作再等一年的毕业生也有不少。',1,1558150365);

/*Table structure for table `mail` */

DROP TABLE IF EXISTS `mail`;

CREATE TABLE `mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mail_id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `mail_url` varchar(255) NOT NULL,
  `mail_pwd` varchar(64) NOT NULL,
  `mail_status` int(11) NOT NULL DEFAULT '1',
  `mail_create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`mail_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `mail` */

insert  into `mail`(`id`,`mail_id`,`user_id`,`mail_url`,`mail_pwd`,`mail_status`,`mail_create_time`) values (1,'mail_2','+8friRqbU7WK20F1tMKvhA==','871289229@qq.com','vgqmhtqqkouobaid',1,123);

/*Table structure for table `name` */

DROP TABLE IF EXISTS `name`;

CREATE TABLE `name` (
  `id` int(11) NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `name` */

insert  into `name`(`id`,`name`) values (1,'王五');

/*Table structure for table `oauth_access_token` */

DROP TABLE IF EXISTS `oauth_access_token`;

CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_access_token` */

insert  into `oauth_access_token`(`token_id`,`token`,`authentication_id`,`user_name`,`client_id`,`authentication`,`refresh_token`) values ('ac7615c7e1218f9ba08375453b662ca6','��\0sr\09com.kkSpringBoot2.oauth2Common.common.MyOAuth2AccessToken��6$��\0L\0tokenEntityt\0ALcom/kkSpringBoot2/oauth2Common/common/MyOauth2AccessTokenEntity;xpsr\0?com.kkSpringBoot2.oauth2Common.common.MyOauth2AccessTokenEntity���c��9\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0my_valuet\0Ljava/lang/String;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypeq\0~\0L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z���\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0k�s��xt\0只是我自定义的数据111sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/�Gc��ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens�\ncT�^\0L\0valueq\0~\0xpt\0$cebea810-8927-4374-a023-6bf9635b6fadsq\0~\0w\0\0l;�z�xsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0allxt\0bearert\0$8f3433a0-6e22-4d12-8461-4335a70c058c','6e802dfca1ed8d760f0f6f05236d557c','user','admin','��\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1��\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_adminxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0adminsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0userxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0allxsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0-sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP���[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0user','9f77c9d3a5273ab700dbca3685cbb784');

/*Table structure for table `oauth_refresh_token` */

DROP TABLE IF EXISTS `oauth_refresh_token`;

CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_refresh_token` */

insert  into `oauth_refresh_token`(`token_id`,`token`,`authentication`) values ('9f77c9d3a5273ab700dbca3685cbb784','��\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/�Gc��ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens�\ncT�^\0L\0valuet\0Ljava/lang/String;xpt\0$cebea810-8927-4374-a023-6bf9635b6fadsr\0java.util.Datehj�KYt\0\0xpw\0\0l;�z�x','��\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1��\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_adminxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0adminsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0userxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0allxsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0-sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP���[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0user');

/*Table structure for table `page_about` */

DROP TABLE IF EXISTS `page_about`;

CREATE TABLE `page_about` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `page_id` varchar(64) NOT NULL,
  `mexpert_title` varchar(255) NOT NULL,
  `mexpert_subhead` varchar(255) NOT NULL,
  `mteam_title` varchar(255) NOT NULL,
  `mteam_subhead` varchar(255) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`page_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `page_about` */

insert  into `page_about`(`id`,`page_id`,`mexpert_title`,`mexpert_subhead`,`mteam_title`,`mteam_subhead`,`status`,`create_time`) values (1,'default_p5','经验和措施','最优化的经验和措施','专业团队','最优秀的专业团队',1,123);

/*Table structure for table `page_case` */

DROP TABLE IF EXISTS `page_case`;

CREATE TABLE `page_case` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `page_id` varchar(255) NOT NULL,
  PRIMARY KEY (`page_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `page_case` */

insert  into `page_case`(`id`,`page_id`) values (1,'default_p3');

/*Table structure for table `page_course` */

DROP TABLE IF EXISTS `page_course`;

CREATE TABLE `page_course` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `page_id` varchar(64) NOT NULL,
  PRIMARY KEY (`page_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `page_course` */

insert  into `page_course`(`id`,`page_id`) values (1,'default_p4');

/*Table structure for table `page_index` */

DROP TABLE IF EXISTS `page_index`;

CREATE TABLE `page_index` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `page_id` varchar(64) NOT NULL,
  `intro_img` varchar(255) NOT NULL COMMENT '介绍图片',
  `intro_title` varchar(255) NOT NULL COMMENT '介绍标题',
  `intro_subhead` varchar(255) NOT NULL COMMENT '介绍副标题',
  `intro_desc` text NOT NULL COMMENT '介绍描述',
  `advantage_title` varchar(255) NOT NULL COMMENT '优势标题',
  `advantage_subhead` varchar(255) NOT NULL COMMENT '优势副标题',
  `case_title` varchar(255) NOT NULL COMMENT '案例标题',
  `case_subhead` varchar(255) NOT NULL COMMENT '案例副标题',
  `status` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`page_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `page_index` */

insert  into `page_index`(`id`,`page_id`,`intro_img`,`intro_title`,`intro_subhead`,`intro_desc`,`advantage_title`,`advantage_subhead`,`case_title`,`case_subhead`,`status`,`create_time`) values (1,'default_p1','/store/image/1558326222221.jpg','企业介绍11','做最好的企业服务商工商注册11','企业服务网是一站式商业服务平台。诞生于2000年，总部位于成都，经历了17年发展历程。目前集团人数2000人。经营了100多项企业服务项目，其中包括工商注册、资质注册、会计代理、办公装修、企业上市辅导、争做最好的企业服务商工商注册。','公司优势','客户至上，服务周到，质量第一，科技领先','公司业务','客户至上，服务周到，质量第一，科技领先',1,1523689294);

/*Table structure for table `page_product` */

DROP TABLE IF EXISTS `page_product`;

CREATE TABLE `page_product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `page_id` varchar(64) NOT NULL,
  `service_t` varchar(255) NOT NULL COMMENT '服务标题',
  `service_subhead` varchar(255) NOT NULL COMMENT '服务副标题',
  `case_title` varchar(255) NOT NULL COMMENT '案例标题',
  `case_subhead` varchar(255) NOT NULL COMMENT '案例副标题',
  `status` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`page_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `page_product` */

insert  into `page_product`(`id`,`page_id`,`service_t`,`service_subhead`,`case_title`,`case_subhead`,`status`,`create_time`) values (1,'default_p2','我们的产品','齐全快速 三步创造属于你的网站','我们的案例','齐全快速 三步创造属于你的网站',1,123123);

/*Table structure for table `relate_bigimg_pages` */

DROP TABLE IF EXISTS `relate_bigimg_pages`;

CREATE TABLE `relate_bigimg_pages` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `page_id` varchar(64) NOT NULL,
  `relation_id` varchar(64) NOT NULL,
  PRIMARY KEY (`page_id`,`relation_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `relate_bigimg_pages` */

/*Table structure for table `upload_file` */

DROP TABLE IF EXISTS `upload_file`;

CREATE TABLE `upload_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uf_id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL DEFAULT 'admin',
  `uf_url` varchar(255) NOT NULL,
  `uf_type` int(11) NOT NULL DEFAULT '1' COMMENT '1-图片，2-视频，3-文件',
  `uf_status` int(11) NOT NULL DEFAULT '1',
  `uf_create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`uf_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `upload_file` */

insert  into `upload_file`(`id`,`uf_id`,`user_id`,`uf_url`,`uf_type`,`uf_status`,`uf_create_time`) values (1,'el2S4RL/EaWF1RfwC1wz3A==','admin','/store/video/1558274965143056859.mp4',2,1,1558274965),(2,'L5JPfXcBlfvvjArlQpn/Lw==','admin','/store/image/1558275015240007070.png',1,1,1558275015),(3,'5TOcUEUs9xr0E+pucmGSZA==','admin','/store/file/1558275070544037113.rar',3,1,1558275070),(4,'c3NkSKEJJ4+rMD29fmYgvQ==','admin','/store/image/1558275407754043692.png',1,1,1558275407),(5,'XjBqZueM4JCHgFEI+KAsQA==','admin','/store/file/1558309337834020826.pdf',3,1,1558309337),(6,'jvcg+RCeBbQly/t/S8Hryg==','admin','/store/file/1558309400892009786.doc',3,1,1558309400);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `type` varchar(64) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_pwd` varchar(255) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `user_update_time` bigint(20) NOT NULL,
  `user_create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`user_id`,`type`,`user_name`,`user_pwd`,`status`,`user_update_time`,`user_create_time`) values (2,'123','admin','user','$2a$10$jnKfMxaptpfXksrj.foELeEOaWY8SzF6v0qm4EZgAoUZ9OWiyrEdG',1,12,123);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
