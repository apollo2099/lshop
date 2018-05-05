/* 服务网点 */
USE `gv_tvpad_web`;

DROP TABLE IF EXISTS `lv_service_network`;
CREATE TABLE `lv_service_network` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contry_id` int(11) DEFAULT NULL COMMENT '国家id',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `channel_name` varchar(100) DEFAULT NULL COMMENT '渠道名称',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `responsible_person` varchar(50) DEFAULT NULL COMMENT '负责人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `order_value` int(11) DEFAULT '0' COMMENT '排序值',
  `web_language` enum('cn','tw','en','kn') DEFAULT NULL COMMENT '语言(cn-中文,tw-繁体,en-英文,kn-韩文)',
  `store_id` varchar(32) DEFAULT NULL COMMENT '店铺编号',
  PRIMARY KEY (`id`),
  KEY `store_id` (`store_id`)
) ENGINE=MyISAM AUTO_INCREMENT=145 DEFAULT CHARSET=utf8;


#
# Dumping data for table lv_service_network
#

INSERT INTO `lv_service_network` VALUES (1,100007,'Australia','Sydney','K-MAX Computer Services','Suite 76, 47 Neridah Street, Chatswood NSW 2067 Australia','Kenneth X','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (2,100007,'Australia','Sydney','Hobicom Australia','Suit 3, 16 Ninth Ave. Campsie 2194 NSW Australia','Danny Kim','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (3,100007,'Australia','Sydney','Evernet','104 Adderton Rd. Carlingford NSW Australia 2118','Danny Cha','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (4,100007,'Australia','Sydney','Korea-IPTV','1F, 9-13 Redmyre Rd Strathfield NSW 2135','Danny Kim','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (5,100007,'Australia','Sydney','Intact Media Pty Ltd','22/52 Holker St Silverwater NSW 2128','Jay Wu','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (6,100007,'Australia','Melbourne','Ying xuan pty ltd.','28 Cambridge st, Boxhill 3128 (匯華電腦服務中心)','Gordon Wu','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (7,100007,'Australia','Melbourne','BONDAT PTY LTD','52-54 O\'Sullivan Road Glen Waverley Vic Melbourne Australia 3150','Anna Huang','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (8,100007,'Australia','Perth','KUTTE UNION (AUSTRALIA) PTY LTD','11 Janter Close Willetton Wa 6155 Australia','Charry Xu','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (9,100226,'USA','Chicago','HM enterprise LLC','5821 SE 82AVE STE 103 Portland OR 97267 U.S.A','Jeffrey Chan','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (10,100226,'USA','Atlanta','','11270 Donnington Dr.Johns Creek, GA 30097(USA)','Jun Wang','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (11,100226,'USA','Washington','Y.S. International, Inc.','8659 Baltimore National Pike, Suite FEllicott City, Maryland21043  U.S.A.','Mr. Byun','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (12,100226,'USA','Guam','SY Supplies','PO Box 6154  Tamauning, Guam 96931','James Yang','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (13,100226,'USA','Las Vegas','Korean News','222 S. Rainbow Blvd #219 Las Vegas NV 89145','Max Moon','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (14,100226,'USA','Duluth','Korean Installation Service','3740 Club Dr, APT 3110 Duluth GA 30096-1826','Micheal Kim','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (15,100226,'USA','Maryland','SMART TVPAD INC','9612 FT.MEADE RD, LAUREL, MD 20707 .USA','Sean Hahn','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (16,100226,'USA','Hawaii','','76 N PAUAHI ST HONOLULU HAWAII.','KEN/Ms. Li ','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (17,100226,'USA','Castro Valley','USA Club TVpad','4101 Dublin BlvdSte F PMB 18 Dublin, CA 94568-4603','Ben','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (18,100226,'USA','New York','ELECTRONIC LAND','163-15 Northern Blvd. suite 1Flushing NY 11358','Peter Yoo','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (19,100226,'USA','Connecticut','Asian Trade Ventures Inc','294 Central Avenue Norwich, Connecticut','Raymond Ng','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (20,100226,'USA','Dallas','Private business','5323 Harry Hines Boulevard, Dallas, Texas, 75390','Raymond Ng','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (21,100226,'USA','Houston','Formark Resource Asset Management LLC - SERIES B','11152 Westheimer Rd #108, Houston, TX 77042','Edward Wang','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (22,100226,'USA','Dallas','Jong Lee','4204 Briarbend Rd Dallas TX 75287 USA','','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (23,100226,'USA','Los Angeles','TVpad USA','6 Tidewater cove. Buena Park, DA 90621 U.S.A','','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (24,100038,'France','Paris','SARL KW WORLD COM','117 AV D IVRY 75013 PARIS FRANCE','Miss Xu','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (25,100112,'UK','London','phoneunlock.com','6 Macaret Close, London, N20 9RA','Philip Kuch','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (26,100112,'UK','London','K-EYETEL/TVpad Europe','4 Presburg Road New Malden Surrey, UK KT3 5AH','','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (27,100023,'China','Shenzhen','HUAYANGINTERNATIONAL TECHNOLOGY LIMITED','http://www.itvpad.com','Justin','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (28,100023,'China','Xi’an','陝西韓流商務交流有限公司','173 Dongyi Road, Yanta District, Xi’an, Shaanxi, China','Mr. Shin','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (29,100023,'China','Shenyang','瀋陽世雅裝飾裝修工程有限公司','5-19 65 Alley, Beiyi Road East, Tiexi Area, Shenyang, China','Mr. Kim','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (30,100023,'China','Shanghai','田胡商貿有限公司','6-402, 27-50th Lane, Wubao Road, Minhang District, Shanghai, China','Mr. Hu','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (31,100023,'China','Yanji','天洲經濟貿易有限公司','Gaosong Food Market, Niushi Street, Yanji, Jilin Province, China','Mr. Zheng','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (32,100023,'China','Guangzhou','廣州市海高電腦有限公司','122 Kangyi Street, Yuanjing Road, Baiyun District, Guangzhou, China','Mr. Cui','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (33,100023,'China','Zhangjiagang','特科（TICO）通讯有限公司','27-3 Donghuyuan, Zhangjiagang City, Suzhou, China','Miss He','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (34,100179,'Malaysia','Kuala Lumpur','OK MARINE SERVICES SDN BHD','B-05-13A, Gateway Kiaramas Corporate Suites, No 1, Jalan DesaKiara, Mont Kiara, 50480 Kuala Lumpur, ','Kim Seung Soo','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (35,100179,'Malaysia','Kuala Lumpur','GLOBAL UNIVERSAL TECHNOLOGY','D-13-03, CONNAUGHT AVENUE, JALAN9, TAMAN BUKIT CHERAS, CHERAS KUALA LUMPUR','Thong Yuhshy','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (36,100179,'Malaysia','Kuala Lumpur','Ace AV Solution','Lot 2.53, 2nd Floor, Low Yat Plaza, No. 7, Jalan Bintang, Off Jalan Bukit Bintang, Bukit Bintang Cen','Ang Guan Kang','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (37,100179,'Malaysia','Kuala Lumpur','TVPad KL- Store','FF-231, 5/F, the Gardens Mal Mid Valley City, Kuala Lumpur, 59200 Malaysia','Partrick','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (38,100179,'Malaysia','Ipoh','CLFT TRADING','72/4B Hala Pengkalan Timur 1, Taman Pengkalan Puteri, 31500 Ipoh Perak','Kenny Tan','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (39,100054,'Japan','Tokyo','有限會社PC-TAKU','日本国東京都台東区上野3-9-1朝日ビル12号館4Ｆ','Fang Tuo','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (40,100054,'Japan','Tokyo','THREE ACE Co., Ltd','SHIMOMURA B/D 102 4-13-7 KOTOBASHI SUMIDAKU Tokyo JAPAN 130-0022','Lee Shi Lak','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (41,100054,'Japan','Tokyo','有限會社PC-TAKU（急速電腦）','PC―TAKU LTD.4F ASAHI BUILDING NO.12, UENO3-9-1, TAITOU-KU, TOKYO, JAPAN 〒110-0005','Mr. Fang','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (42,100083,'Panama','Panama City','INVERSIONES WEN, S.A.','Edif.Tecnic.Panama City Apartado:835-521 zona 10','Willy Wen','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (43,100084,'Paraguay','Ciudad del Este','AGROGANADERA CAMPO FLOR S.R.L','CALLE CAMILO RECALDE 101 AP. 204','Ricky Lam','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (44,100084,'Paraguay','Asuncion','SPA EL TIGRE','Paraguay Asuncion Europa 2186 Casi Proceres De Mayo','','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (45,100015,'Brazil','São Paulo','LI SI INDUSTRIA COMERCIO IMPORTACAO E EXPORTACAO LTDA.','Av. Sebastiao de Brito, 890 – Dona Clara- Belo Horizonte – MG -Brasil','Guang Sheng Jian','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (46,100015,'Brazil','São Paulo','Augusto Nam','R.Silva Pinto 381, Sao Paulo, S.P, Brazil','Augusto Nam','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (47,100015,'Brazil','Rio','H&L COMERCIAL IMPORTADORA E EXPORTADORA LTDA','AV EMBAIXADOR ABELARDO BUENO N°3500 SALA 1213 BARRA DA TIJUCARIO DE JANEIRO R-J BRASAL','Huang Lirong','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (48,100015,'Brazil','Curitiba','CASA YIFA PRESENTES LDTA','RUA EMILIANO PERNETA 195 APT93B CENTRO CEP:80010-050CURITIBA -PRBRAZIL','Zheng Rongyu','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (49,100165,'Indonesia','Djakarta','INDO SUNG-IL JAYA','Jl. Gatot Subroto Km. 8 No. 122 Telesonik Ujung Desa Pasir JayaKec. Jati Uwung Tangerang 15135, INDO','Jihoon Shin','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (50,100165,'Indonesia','Djakarta','PT.MEDIART JAYA','BLOK B21 PINANGSIA LIPPO KARAWACI TANGERANG INDONESIA','Shin Jihoon','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (51,100218,'Thailand','Samut Prakan Province','KTV on','577 / 1198, Srinakarin Road, Samrongnua Muang, Samutparakan10270, THAILAND','Kim Seung Soo','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (52,100218,'Thailand','Bangkok','大视界 (Large Vision)','326/4 Soi Sukhumvit 63, Sukhumvit Road, Klongtonnur, Vadhana, Bangkok 10110','Mike Chen','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (53,100215,'Sweden','Malmo','Store 4 Fun (TVpad.se)','Blekingsborgsgatan 3D 21463 Malmoe Skane, Sweden','Niklas Man','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (54,100076,'Netherlands','Amsterdam','周福 (威信) 貿易公司（Andrew Trading）','Clara Bartonstraat 16, 1025KT Amsterdam, The Netherlands','Winson Chau','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (55,100076,'Netherlands','Rotterdam','Dimension Automatisering','Goudsesingel 93, 3031 EE Rotterdam, Holland','Andrew','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (56,100076,'Netherlands','Apeldoorn','Kenji Trading','Rousseaustraat 287323 GP ApeldoornThe Netherlands','Andy Wong','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (57,100076,'Netherlands','Den Haag','Note2.nl','Wagenstraat 149 (Den Haag Center), 2512 AV Den Haag, The Netherlands','Dr. Isamu (Yong)','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (58,100085,'Philippines','Manila','SITTIXIAN','227 Aguirre Ave BF Homes Paranaque City. Manila. Philippines','Kim dug chun','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (59,100171,'South Korea','Daejeon','Creble Inc.','Korea, Daejeon, Yuseong-gu, Jeonmin-dong, 463-1, 2013#','Jonghoon Eom','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (60,100115,'Vietnam','Ho Chi Minh City','MODEL LINE CO., LTD','47 NGUYEN VAN MAI P.8 Q.3 HO CHI MINH CITY','Mr Kim','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (61,100115,'Vietnam','Ho Chi Minh City','KORVIET Co.','Room 2.2, K-Apartment, KDC Lang Dai Hoc,101/21/3B, Le Van Luong Xa Phuoc Kien, Huyen Nha Be HCMC HCM','','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (62,100009,'Bangladesh','Dacca','JANG WON CORP, LTD','HoUSE NO -99, rOAD NO -4, BLOCK-1, BANANI, DHAKA-1213, BANGLADESH','Mr JONG HAK ,RYOO','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (63,100169,'Kazakhstan','Almaty','TETRO MDD','Room 804 Entrance 2 B, Business Center \"Nurly Tau\"','Jung Tae Hyeng','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (64,100190,'New Zealand','Auckland','Roxapac International Trading Co., Ltd','11A/266 Onewa Road, North Shore Auckland 0626, New Zealand','Pat Hou','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (65,100212,'Spain','Valencia','Han Chinese Language School Marbella','Av de Gustavo Adolfo Bécquer, 34, 29660 Nueva Andalucía, Málaga','Yingying Xu','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (66,100102,'Switzerland','Lausanne','Moderne Pont','MODERNE PONT, Avenue de Vinet 24, CH - 1004 Lausanne, Switzerland','Tony','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (67,100049,'Hungary','Budapest','Realm-Global Kereskedelmi Kft','1194 Budapest Fadrusz J. u. 2., Hungary','Chen Jun','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (68,100138,'Chile','San Diego','TVpad Chile','Cerro Colorado 4922 C-111, Las Condes, Santiago, CHILE','','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (69,100123,'Argentina','Buenos Aires','TVpad Argentina','gral alvares condarco 522 piso6 deto a 1406 ciudad autonoma buenos aire','','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (70,100123,'Argentina','Buenos Aires','OCEANTEXIL','CONCORDIA 373/407 cap.FED BS.AS ARGENTINA','','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (71,100050,'India','Chennai','FJ NETWORK PRIVATE LIMITED','PLAT NO 404 MERIDIAN HEIGHTS, NEW NO 154, PETERS ROAD, ROYAPETTAH, CHENNAI, TN-600014 TAMILNADU, IND','','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (72,100041,'Germany','Essen','Qed-Consulting Gmbh','c/o Jo-Seob Kim Kawehlstr. 2 45130 Essen','Jo-Seob Kim','2014-08-19 11:45:10',NULL,0,'en','en');
INSERT INTO `lv_service_network` VALUES (73,100007,'澳大利亞\t','悉尼','K-MAX COMPUTER SERVICES','SUITE 76, 47 NERIDAH STREET, CHATSWOOD NSW 2067 AUSTRALIA','Kenneth X','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (74,100007,'澳大利亞\t','悉尼','HOBICOM AUSTRALIA','Suit 3, 16 Ninth Ave. Campsie 2194 NSW Australia','Danny Kim','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (75,100007,'澳大利亞\t','悉尼','Evernet','104 Adderton Rd. Carlingford NSW Australia 2118','Danny Cha','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (76,100007,'澳大利亞\t','悉尼','korea-iptv','1F,9-13 Redmyre Rd Strathfield NSW 2135','DANNY KIM','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (77,100007,'澳大利亞\t','悉尼','Intact Media Pty Ltd','22/52 Holker St Silverwater NSW 2128','Jay Wu','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (78,100007,'澳大利亞\t','墨爾本','Ying xuan pty ltd.','28 Cambridge st,boxhill 3128(匯華電腦服務中心)','Gordon Wu','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (79,100007,'澳大利亞\t','墨爾本','BONDAT PTY LTD','52-54 O\'Sullivan Road Glen Waverley Vic Melbourne Australia 3150','Anna Huang','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (80,100007,'澳大利亞\t','佩斯','KUTTE UNION (AUSTRALIA) PTY LTD','11 Janter Close Willetton Wa 6155 Australia','Charry Xu','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (81,100226,'美国\t','芝加哥','HM enterprise LLC','5821 SE 82AVE STE 103 Portland OR 97267 U.S.A','Jeffrey Chan','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (82,100226,'美国\t','亞特蘭大','','11270 Donnington Dr.Johns Creek, GA 30097(USA)','Jun Wang','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (83,100226,'美国\t','華盛頓','Y.S. International, Inc.','8659 Baltimore National Pike, Suite FEllicott City, Maryland21043  U.S.A.','Mr Byun','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (84,100226,'美国\t','關島','SY Supplies','PO Box 6154  Tamauning, Guam 96931','James Yang','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (85,100226,'美国\t','Las Vegas','Koreana News','222 S. Rainbow Blvd #219 Las Vegas NV 89145','Max Moon','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (86,100226,'美国\t','杜魯士','Korean Installation Sevice','3740 Club Dr, APT 3110Duluth  GA  30096-1826','Micheal Kim','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (87,100226,'美國\t','Maryland','SMART TVPAD INC','9612 FT.MEADE RD,LAUREL,MD 20707 .USA','SEAN HAHN','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (88,100226,'美國\t','夏威夷州','','76 N PAUAHI ST HONOLULU HAWAII.','KEN / Ms Li(黎小姐)','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (89,100226,'美國\t','卡斯特羅穀','USA Club TVpad','4101 Dublin BlvdSte F PMB 18 Dublin, CA 94568-4603','Ben','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (90,100226,'美國\t','紐約','ELECTRONIC LAND','163-15 Northern Blvd. suite 1Flushing NY 11358','Peter Yoo','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (91,100226,'美國\t','康州','Asian Trade Ventures Inc','294 Central Avenue Norwich, Connecticut','Raymond Ng','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (92,100226,'美國\t','達拉斯','個人經營','5323 Harry Hines Boulevard，Dallas, Texas, 75390','Raymond Ng','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (93,100226,'美國\t','休斯頓','Formark Resource Asset Management LLC - SERIES B','11152 Westheimer Rd #108, Houston, TX 77042','Edward Wang','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (94,100226,'美國\t','達拉斯','Jong Lee','4204 Briarbend Rd Dallas TX 75287 USA','','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (95,100226,'美國\t','洛杉磯','Tvpad USA','6 Tidewater cove. Buena Park,DA 90621 U.S.A','','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (96,100038,'法國\t','巴黎','SARL KW WORLD COM','117 AV D IVRY 75013 PARIS FRANCE','Miss Xu','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (97,100112,'英國\t','伦敦','phoneunlock.com','6 Macaret Close, London, N20 9RA','Philip Kuch','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (98,100112,'英國\t','倫敦','K-EYETEL/TVpad Europe','4 Presburg Road New Malden Surrey, UK KT3 5AH','','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (99,100023,'中國\t','深圳','HUAYANGINTERNATIONAL TECHNOLOGY LIMITED','http://www.itvpad.com','Justin','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (100,100023,'中國\t','西安市','陝西韓流商務交流有限公司','陝西省西安市雁塔區東儀路173號 （東風儀錶廠對面）','Mr Shin','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (101,100023,'中國\t','瀋陽市','瀋陽世雅裝飾裝修工程有限公司','瀋陽市鐵西區北一東路65甲5號19門','金先生','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (102,100023,'中國\t','上海','田胡商貿有限公司','上海市 閔行區 吳寶路27弄50支弄 6號402室','胡先生','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (103,100023,'中國\t','延吉市','天洲經濟貿易有限公司','吉林省延吉市牛市街高嵩食品超市','鄭先生','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (104,100023,'中國\t','廣州市','廣州市海高電腦有限公司','廣州市白雲區遠景路康意街122號','崔先生','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (105,100023,'中國\t','张家港','特科（TICO）通讯有限公司','苏州张家港市东湖苑27-3','河小姐','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (106,100179,'馬來西亞\t','吉隆坡','OK MARINE SERVICES SDN BHD','B-05-13A, Gateway Kiaramas Corporate Suites,No 1, Jalan DesaKiara, Mont Kiara,50480 Kuala Lumpur,Mal','Kim Seung Soo','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (107,100179,'馬來西亞\t','吉隆坡','GLOBAL UNIVERSAL TECHNOLOGY','D-13-03, CONNAUGHT AVENUE, JALAN9, TAMAN BUKIT CHERAS, CHERAS KUALA LUMPUR','THONG YUH SHY','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (108,100179,'馬來西亞\t','吉隆坡','Ace AV Solution','Lot 2.53, 2nd Floor, Low Yat Plaza,No. 7, Jalan Bintang,Off Jalan Bukit Bintang, Bukit Bintang Centr','Ang Guan Kang','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (109,100179,'馬來西亞\t','吉隆坡','TVPad KL- Store','FF-231, 5/F, the Gardens Mal Mid Valley City, Kuala Lumpur,59200 Malaysia','Partrick','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (110,100179,'馬來西亞\t','怡保','CLFT TRADING','72/4B Hala Pengkalan Timur 1, Taman Pengkalan Puteri, 31500 Ipoh Perak','Kenny Tan','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (111,100054,'日本\t','東京','有限會社PC-TAKU','日本国東京都台東区上野3-9-1朝日ビル12号館4Ｆ','方 拓','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (112,100054,'日本\t','東京','THREE ACE company.ltd','SHIMOMURA B/D 102 4-13-7 KOTOBASHI SUMIDAKU Tokyo JAPAN 130-0022','LEE SHI LAK','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (113,100054,'日本\t','東京','有限會社PC-TAKU（急速電腦）','PC―TAKU LTD.4F ASAHI BUILDING NO.12,UENO3-9-1,TAITOU-KU,TOKYO,JAPAN 〒110-0005','方先生','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (114,100083,'巴拿馬\t','巴拿馬城','INVERSIONES WEN,S.A.','Edif.Tecnic.Panama City Apartado:835-521 zona 10','Willy Wen','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (115,100084,'巴拉圭\t','東方市','AGROGANADERA CAMPO FLOR S.R.L','CALLE CAMILO RECALDE 101 AP. 204','Ricky Lam','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (116,100084,'巴拉圭\t','亞松森','SPA EL TIGRE','Paraguay asuncion Europa 2186 casi proceres de mayo','','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (117,100015,'巴西\t','聖保羅','LI SI INDUSTRIA COMERCIO IMPORTACAO E EXPORTACAO LTDA.','Av. Sebastiao de Brito,890 – Dona Clara- Belo Horizonte – MG -Brasil','GUANG SHENG JIAN','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (118,100015,'巴西\t','聖保羅','Augusto Nam','R.Silva Pinto 381,Sao Paulo,S.P.,Brazil','Augusto Nam','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (119,100015,'巴西\t','裏約','H&L COMERCIAL IMPORTADORA E EXPORTADORA LTDA','AV EMBAIXADOR ABELARDO BUENO N°3500 SALA 1213 BARRA DA TIJUCARIO DE JANEIRO R-J BRASAL','HUANG LIRONG','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (120,100015,'巴西\t','庫裡提巴','CASA YIFA PRESENTES LDTA','RUA EMILIANO PERNETA 195 APT93B CENTRO CEP:80010-050CURITIBA -PRBRAZIL','ZHENG RONGYU','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (121,100165,'印尼\t','雅加達','INDO SUNG-IL JAYA','Jl. Gatot Subroto Km. 8 No. 122 Telesonik Ujung Desa Pasir JayaKec. Jati Uwung Tangerang 15135, INDO','Jihoon Shin','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (122,100165,'印尼\t','雅加達','PT.MEDIART JAYA','BLOK B21 PINANGSIA LIPPO KARAWACI TANGERANG INDONESIA','Shin Jihoon','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (123,100218,'泰國\t','北欖府','KTV on','577 / 1198, Srinakarin Road, Samrongnua Muang, Samutparakan10270, THAILAND','Kim Seung Soo','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (124,100218,'泰國\t','曼谷','大视界','326/4 Soi Sukhumvit 63, Sukhumvit Road, Klongtonnur, Vadhana, Bangkok 10110','Mike Chen','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (125,100215,'瑞典\t','馬爾默','Store 4 Fun(TVpad.se)','Blekingsborgsgatan 3D 21463 Malmoe Skane, Sweden','Niklas Man (文律健)','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (126,100076,'荷蘭\t','阿姆斯特丹','周福 (威信) 貿易公司（Andrew Trading）','Clara Bartonstraat 16,1025KT Amsterdam,The Netherlands','Winson Chau','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (127,100076,'荷蘭\t','鹿特丹','Dimension Automatisering','Goudsesingel 93 ,3031 EE Rotterdam, Holland','Andrew','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (128,100076,'荷蘭\t','阿帕爾多倫','Kenji Trading','Rousseaustraat 287323 GP ApeldoornThe Netherlands','Andy Wong','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (129,100076,'荷蘭\t','海牙','Note2.nl','Wagenstraat 149 (Den Haag Center),2512 AV Den Haag,The Netherlands','Dr. Isamu (Yong)','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (130,100085,'菲律賓\t','馬尼拉','SITTIXIAN','227 Aguirre ave BF homes Paranaque City. Manila. Phillippines','Kim dug chun','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (131,100171,'韓國\t','大田','Creble Inc.','Korea, Daejeon, Yuseong-gu, Jeonmin-dong, 463-1, 2013#','Jonghoon Eom','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (132,100115,'越南\t','胡志明','MODEL LINE CO.,LTD','47 NGUYEN VAN MAI P.8 Q.3 HO CHI MINH CITY','Mr Kim','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (133,100115,'越南\t','胡志明','KORVIET Co.','Room 2.2, K-Apartment, KDC Lang Dai Hoc,101/21/3B, Le Van Luong Xa Phuoc Kien, huyen Nha Be HCMC HCM','','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (134,100009,'孟加拉\t','達卡','JANG WON CORP, LTD','HoUSE NO -99 , rOAD NO -4, BLOCK-1 , BANANI, DHAKA-1213 ,BANGLADESH','Mr JONG HAK ,RYOO','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (135,100169,'哈薩克斯坦\t','ALMATY','TETRO MDD','Room 804 entrance 2 B, Business Center \"Nurly Tau\"','JUNG TAE HYENG','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (136,100190,'新西兰\t','奥克兰','Roxapac International Trading Co.,Limited','11A/266 Onewa Road,North Shore Auckland 0626,New Zealand','Pat Hou','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (137,100212,'西班牙\t','瓦倫西亞','Han Chinese Language School Marbella','Av de Gustavo Adolfo Bécquer, 34, 29660 Nueva Andalucía, Málaga','YINGYING XU','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (138,100102,'瑞士\t','洛桑','Moderne Pont','MODERNE PONT,Avenue de Vinet 24,CH - 1004 Lausanne,Switzerland','Tony','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (139,100049,'匈牙利\t','布達佩斯','Realm-Global Kereskedelmi Kft','1194 Budapest Fadrusz J. u. 2.,Hungary','Chen Jun','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (140,100138,'智利\t','聖地牙哥','TV Pad Chile','Cerro Colorado 4922 C-111, Las Condes, Santiago, CHILE','','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (141,100123,'阿根廷\t','布宜諾斯艾利斯','TVPAD argentina','gral alvares condarco 522 piso6 deto a 1406 ciudad autonoma buenos aire','','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (142,100123,'阿根廷\t','布宜諾斯艾利斯','OCEANTEXIL','CONCORDIA 373/407 cap.FED BS.AS ARGENTINA','','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (143,100050,'印度\t','加奈','FJ NETWORK PRIVATE LIMITED','PLAT NO 404 MERIDIAN HEIGHTS, NEW NO 154, PETERS ROAD, ROYAPETTAH, CHENNAI,TN-600014 TAMILNADU,INDIA','','2014-08-19 11:45:10',NULL,0,'tw','www');
INSERT INTO `lv_service_network` VALUES (144,100041,'德國\t','埃森','Qed-Consulting Gmbh','c/o Jo-Seob Kim Kawehlstr. 2 45130 Essen','Jo-Seob Kim','2014-08-19 11:45:10',NULL,0,'tw','www');


/* 经销商申请 */

#
# Source for table lv_dealer_application
#

DROP TABLE IF EXISTS `lv_dealer_application`;
CREATE TABLE `lv_dealer_application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apply_cmp` varchar(100) NOT NULL COMMENT '申请人/公司',
  `apply_name` varchar(50) NOT NULL COMMENT '联系人',
  `apply_tel` varchar(20) NOT NULL COMMENT '联系电话',
  `apply_email` varchar(100) NOT NULL COMMENT 'email',
  `apply_addr` varchar(100) NOT NULL COMMENT '通讯地址',
  `apply_area` varchar(100) NOT NULL COMMENT '申请代理的区域',
  `apply_reason` varchar(512) NOT NULL COMMENT '申请理由',
  `apply_intro` varchar(512) NOT NULL COMMENT '申请人/公司介绍',
  `apply_plan` varchar(512) DEFAULT NULL COMMENT '营销计划',
  `appy_suggest` varchar(512) DEFAULT NULL COMMENT '对BananaTv公司建议',
  `type` varchar(50) NOT NULL COMMENT '获取招商信息方式',
  `other_text` varchar(50) DEFAULT NULL,
  `store_id` varchar(32) NOT NULL COMMENT '店铺编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `store_id` (`store_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
