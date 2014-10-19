package com.github.kb2.mains;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.kb2.dtos.ZipCodeDto;
import com.github.kb2.utils.DBUtils;

public class H2DBTestMain01 {

	public static void main(String args[]){
		{
			// 郵便番号絞り込み検索
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("zipCode", "1000001");
			List<ZipCodeDto> zipCodeDtoList = DBUtils.getResultList(ZipCodeDto.class, "./sqls/selectZipCodes.sql", paramMap);
			for(ZipCodeDto zipCodeDto : zipCodeDtoList){
				System.out.println(zipCodeDto.zipCode);
				System.out.println(zipCodeDto.address1);
			}
		}
		{
			// アドレス絞り込み検索
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("address2", "台東区");
			List<ZipCodeDto> zipCodeDtoList = DBUtils.getResultList(ZipCodeDto.class, "./sqls/selectZipCodes.sql", paramMap);
			for(ZipCodeDto zipCodeDto : zipCodeDtoList){
				System.out.println("-----------");
				System.out.println(zipCodeDto.zipCode);
				System.out.println(zipCodeDto.address1);
				System.out.println(zipCodeDto.address2);
				System.out.println(zipCodeDto.address3);
			}
		}
		{
			// 正規表現検索
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("address3Regex", "（.*階）");
			List<ZipCodeDto> zipCodeDtoList = DBUtils.getResultList(ZipCodeDto.class, "./sqls/selectZipCodes.sql", paramMap);
			for(ZipCodeDto zipCodeDto : zipCodeDtoList){
				System.out.println("-----------");
				System.out.println(zipCodeDto.zipCode);
				System.out.println(zipCodeDto.address1);
				System.out.println(zipCodeDto.address2);
				System.out.println(zipCodeDto.address3);
			}
		}
	}
}
