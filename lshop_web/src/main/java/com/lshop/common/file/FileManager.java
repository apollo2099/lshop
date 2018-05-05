package com.lshop.common.file;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public interface FileManager {
	/**
	 * 获得文件列表。根据前缀，用于选择文件。
	 * 
	 * @param prefix
	 *            前缀
	 * @return
	 */
	public List<? extends Tpl> getListByPrefix(String prefix);

	public List<String> getNameListByPrefix(String prefix);

	/**
	 * 获得下级文件列表。根据路径，用于展现下级目录和文件。
	 * 
	 * @param path
	 *            路径
	 * @return
	 */
	public List<? extends Tpl> getChild(String path);


	/**
	 * 保存文件
	 * 
	 * @param path
	 * @param source
	 */
	public void save(String path, String source);

	/**
	 * 获得文件
	 * 
	 * @param 文件路径
	 * @return
	 */
	public Tpl get(String path);

	/**
	 * 更新文件
	 * 
	 * @param name
	 *            文件文件路径
	 * @param source
	 *            文件内容
	 */
	public void update(String path, String source);

	/**
	 * 修改文件名称或路径
	 * 
	 * @param orig源
	 * @param dist目标
	 */
	public void rename(String orig, String dist);

	/**
	 * 删除文件
	 * 
	 * @param names
	 *            文件名称数组
	 * @return 被删除的文件数量
	 */
	public int delete(String[] names);
	/**
	 * 查询文件路径是否符合归则
	 * 返回true 或false
	 * true
	 *
	 */
	public boolean isCheckPath(String path);
}
