package com.lshop.web.userCenter.page;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gv.core.datastructure.page.Pagination;



/**
 * @author dh
 *
 */

public class PageHandle {	

	public PageHandle(){
	}

	public static Pagination getPage(Map<Integer,Object> map,int pageNum,int numPerPage){
		Pagination pag = new Pagination(pageNum,numPerPage,map.size());

	    //获取当前页码
		if(pag.getPageNum() < 1){
			pag.setPageNum(1);
		}else if(pag.getPageNum() > pag.getTotalPage())
			{
				pag.setPageNum(pag.getTotalPage());
			}
		
		int start = 0,end = 0;
		List<Object> list = new ArrayList<Object>();
		
		//如果输入的页数大于或等于总页数
		if(pag.getPageNum() == pag.getTotalPage()){
			start = pag.getPageNum() * pag.getNumPerPage() - (pag.getNumPerPage()-1);
			end = map.size();

			for(int i=start;i<=end;i++){
				list.add(map.get(i));
			}
			
		}else{
			start = pag.getPageNum() * pag.getNumPerPage() - (pag.getNumPerPage()-1);
			end = pag.getPageNum() * pag.getNumPerPage();
			
			if(end > map.size()){
				end = map.size();
			}
			
			for(int i=start;i<=end;i++){
				list.add(map.get(i));
			}
		}
		pag.setList(list);
		return pag;
	}
	
	public static void main(String[] args) throws IOException {
		
		
		
	}

}
