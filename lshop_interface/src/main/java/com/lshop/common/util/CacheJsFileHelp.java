package com.lshop.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import net.sf.json.JSONArray;

public class CacheJsFileHelp {

	/**
	 * 缓存js文件
	 * @param jspath 存放路径
	 * @param filename 文件名
	 * @param tvitem 缓存数据 list->map->string
	 * @return
	 */
	public static boolean writeFile(String jspath,String filename, List tvitem){
		try {
			File file = new File(jspath);
			if (!file.exists()) {
				file.mkdirs();
			}
			OutputStream os = new FileOutputStream(new File(file, filename + ".js"));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
			String tvitemString = JSONArray.fromObject(tvitem).toString();
			bw.write(tvitemString);
			bw.flush();
			bw.close();
			os.close();
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}
}
