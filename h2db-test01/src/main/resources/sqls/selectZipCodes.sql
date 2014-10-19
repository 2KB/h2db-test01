select
		*
	from 
		csvread(
			-- CSVファイルパス
			'./src/main/resources/KEN_ALL.CSV',
			-- カラム名一覧。カンマ区切り。CSVの一行目にカラム名が記載されている場合は、nullにする。なぜか大文字しかダメな模様。
			'LOCAL_GOVERNMENT_CODE,OLD_ZIP_CODE,ZIP_CODE,KANA1,KANA2,KANA3,ADDRESS1,ADDRESS2,ADDRESS3,PREF_CODE',
			-- 文字コード
			'Shift-JIS'
		)
	where 1=1
		/*IF zipCode != null */
		and ZIP_CODE = /*zipCode*/'9071801'
		/*END*/
		/*IF address2 != null */
		and ADDRESS2 = /*address2*/'目白'
		/*END*/
		/*IF address3Regex != null */
		and ADDRESS3 regexp /*address3Regex*/'町$'
		/*END*/