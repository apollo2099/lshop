
package com.lshop.common.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.gv.core.datastructure.Dto;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvStore;

/**
 * 搜索引擎lucene
 * @author zheng xue
 * @version 1.0 2013-01-31
 *
 */
public class SearchUtil {

	private static Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
//	private static Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
	private static String productSource = null;
	private static String storeSource = null;
	
	/**
	 * 
	 * 配置的是相对路径
	 */
	static{
		productSource = (String) PropertiesHelper.getConfiguration().getProperty("search.product.path");  
		storeSource = (String) PropertiesHelper.getConfiguration().getProperty("search.store.path");  
	}
	
	/**
	 * 搜索
	 */	
	public static String search(Dto dto) throws IOException, ParseException{
		String ids = null;
		String key = dto.getAsString("keyword");
		key = filterKeyword(key);
		
		String mark = dto.getAsString("mark");
		String source = productSource;
		if(mark.equals("store")){
			source = storeSource;
		}
		File file = new File(source);
		
//		if(!file.exists() || ObjectUtils.isEmpty(key)){//如果索引不存在或者关键字为空，则直接返回null
//			return null;
//		}
		
		Directory directory = new SimpleFSDirectory(file);
		BooleanClause.Occur[] clause = {BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD};
		IndexReader ireader = IndexReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		Query query = null;
		TopDocs topDocs = null;
		StringBuilder sbd = new StringBuilder(",");
		
		//先精确查找
		Term t = new Term("name",key);
		query = new WildcardQuery(t);
		topDocs = isearcher.search(query, 1000000000);
		for (ScoreDoc sdoc : topDocs.scoreDocs) {			
			Document doc = isearcher.doc(sdoc.doc);
			sbd.append(doc.get("id")).append(",");
		}
		
		//再模糊查找
		t = new Term("name","*"+key+"*");
		query = new WildcardQuery(t);
		topDocs = isearcher.search(query, 1000000000);
		for (ScoreDoc sdoc : topDocs.scoreDocs) {
			Document doc = isearcher.doc(sdoc.doc);
			String id = doc.get("id");
			if(!sbd.toString().contains(","+id+",")){
				sbd.append(id).append(",");
			}
		}
		
		//分词查找
		List<String> list = queryWords(key);
		for(String s : list){
			t = new Term("name","*"+s+"*");
			query = new WildcardQuery(t);
			topDocs = isearcher.search(query, 1000);
			for (ScoreDoc sdoc : topDocs.scoreDocs) {
				Document doc = isearcher.doc(sdoc.doc);
				//对关键字高亮设置
				try {
					//getHighLight( doc, analyzer,query,"name");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String id = doc.get("id");
				if(!sbd.toString().contains(id+",")){
					sbd.append(id).append(",");
				}
			}
		}

		ireader.close();
		isearcher.close();

		if(sbd != null && sbd.toString().length() > 1){
			ids = sbd.toString();
			return ids.substring(1, ids.length()-1);
		}
		
		return null;
		
	}
	
	/**
	 * 搜索商品名称索引
	 * @param keyword
	 * @return
	 */
	public static String searchProd(String keyword) {
		IndexReader ireader = null;
		IndexSearcher isearcher = null;
		StringBuilder sbd = new StringBuilder(",");
		try {
			Directory directory = new SimpleFSDirectory(new File(productSource));
			ireader = IndexReader.open(directory);
			isearcher = new IndexSearcher(ireader);
			TopDocs topDocs = null;
			QueryParser qp=new MultiFieldQueryParser(Version.LUCENE_36, new String[]{"name"}, analyzer); 
			Query query=qp.parse(keyword);
			topDocs = isearcher.search(query, 1000000000);
			for (ScoreDoc sdoc : topDocs.scoreDocs) {
				Document doc = isearcher.doc(sdoc.doc);
				String id = doc.get("id");
				if(!sbd.toString().contains(","+id+",")){
					sbd.append(id).append(",");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			try {
				ireader.close();
				isearcher.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if(sbd != null && sbd.toString().length() > 1){
			return sbd.toString().substring(1, sbd.length()-1);
		}
		return null;
	}
	/**
	 * 初始化索引（搜商品）
	 */
	@SuppressWarnings("unchecked")
	public static boolean initProductInfo(HibernateBaseDAO dao) throws Exception {		
		//创建IndexWriterConfig
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_36, analyzer);
		indexWriterConfig.setOpenMode(OpenMode.CREATE);
		//创建系统文件
		Directory directory = new SimpleFSDirectory(new File(productSource));
		//创建索引
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		String hql = "from LvProduct where status=1 and isSupport=1 order by orderId desc";
		List<LvProduct> productList = (List<LvProduct>)dao.find(hql, null);
		Document doc;
		if(null != productList && productList.size() > 0){
			for(LvProduct product:productList){
				doc = new Document();
				//获取id
				doc.add(new Field("id",product.getId().toString(),Store.YES,Index.NOT_ANALYZED_NO_NORMS));
				//获取产品名称,过滤掉标签
				doc.add(new Field("name",HtmlRegexpUtil.filterHtml(product.getProductName()),Store.YES,Index.ANALYZED));
				//添加文本内容至索引
				indexWriter.addDocument(doc);
			}
		}
		if(null != indexWriter){
			indexWriter.close();
		}	
		return false;
	}
	
	/**
	 * 初始化索引（搜店铺）
	 */
	@SuppressWarnings("unchecked")
	public static boolean initStoreInfo(HibernateBaseDAO dao) throws Exception {		
		//创建IndexWriterConfig
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_36, analyzer);
		indexWriterConfig.setOpenMode(OpenMode.CREATE);
		//创建系统文件
		Directory directory = new SimpleFSDirectory(new File(storeSource));
		//创建索引
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		String hql = "from LvStore where isdel=0 and status=1 and parentCode!='0' order by orderValue desc,createTime desc";
		List<LvStore> storeList = (List<LvStore>)dao.find(hql, null);
		Document doc;
		if(null != storeList && storeList.size() > 0){
			for(LvStore store:storeList){
				doc = new Document();
				//获取id
				doc.add(new Field("id",store.getId().toString(),Store.YES,Index.NOT_ANALYZED_NO_NORMS));
				//获取产品名称,过滤掉标签
				doc.add(new Field("name",HtmlRegexpUtil.filterHtml(store.getName()),Store.YES,Index.ANALYZED));
				//添加文本内容至索引
				indexWriter.addDocument(doc);
			}
		}
		if(null != indexWriter){
			indexWriter.close();
		}	
		return false;
	}
	
	private static String filterKeyword(String keyword) {
		String _keyword = keyword.replaceAll("\\*", "").replaceAll("\\?", "");
		return _keyword.toLowerCase();
	}
	
    public static List<String> queryWords(String str) throws IOException {   	
      	 List<String> list = new ArrayList<String>();
           StringReader input = new StringReader(str.trim());
           IKSegmenter ikSeg = new IKSegmenter(input, true);// true　用智能分词　，false细粒度
           for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
               list.add(lexeme.getLexemeText());
           }
           int len = list.size();
           if(len > 1){
        	   for(int i=1;i<len;i++){
            	   String s = (String)list.get(0);
            	   String s1 = (String)list.get(i);
            	   if(s1.length() > s.length()){
            		   list.remove(i);
            		   list.remove(0);
            		   list.add(0, s1);
            		   list.add(i, s);
            	   }
               }
           }
          return list;
      }
    
	/**
	 * 高亮设置
	 */
	public static String getHighLight(Document doc, Analyzer analyzer, Query query,String field) throws Exception {
		// 设置高亮显示格式
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
		// 语法高亮显示设置 
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
		highlighter.setTextFragmenter(new SimpleFragmenter(100));
		String str = doc.get(field);
		String highLightText = "";
		if (str != null) {    
            TokenStream tokenStream = analyzer.tokenStream(field,new StringReader(str));    
            highLightText = highlighter.getBestFragment(tokenStream, str); 
            System.out.println(highLightText);    
        }
		return highLightText;
	}
}
