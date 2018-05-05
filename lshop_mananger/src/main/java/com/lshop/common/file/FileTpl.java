package com.lshop.common.file;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class FileTpl implements Tpl {
	private File file;
	// 应用的根目录
	private String root;

	public FileTpl(File file, String root) {
		this.file = file;
		this.root = root;
	}

	public String getName() {
         
		return file.getName();
	}

	public String getPath() {
		
		return "/"+getName();
	}

	public String getFilename() {
		return file.getName();
	}

	public String getSource() {
		if (file.isDirectory()) {
			return null;
		}
		try {
			return FileUtils.readFileToString(this.file, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public String getLastModifiedDate() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(this.getLastModified()));
	}

	public long getLength() {
		return file.length();
	}

	public int getSize() {
		return (int) (getLength() / 1024) + 1;
	}

	public boolean isDirectory() {
		return file.isDirectory();
	}
	@Override
	public long getLastModified() {
		// TODO Auto-generated method stub
		return file.lastModified();
	}
}
