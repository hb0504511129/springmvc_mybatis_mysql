CREATE TABLE `stats` (
`id`  varchar(36) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,
`countryCode`  varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '国家编码' ,
`countryName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '国家名称' ,
`provinceCode`  int(10) NULL DEFAULT NULL COMMENT '省编码' ,
`provinceName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '省名称' ,
`cityCode`  int(10) NULL DEFAULT NULL COMMENT '城市编码' ,
`cityName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '城市名称' ,
`countyCode`  int(10) NULL DEFAULT NULL COMMENT '县/区编码' ,
`countyName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '县/区名称' ,
`streetCode`  int(10) NULL DEFAULT NULL COMMENT '街道编码' ,
`streetName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '街道名称' ,
PRIMARY KEY (`id`)
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_unicode_ci
CHECKSUM=0
ROW_FORMAT=DYNAMIC
DELAY_KEY_WRITE=0
;

