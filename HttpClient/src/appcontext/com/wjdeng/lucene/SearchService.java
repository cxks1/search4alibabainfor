/******************************************************************************** 
 * Create Author   : Administrator
 * Create Date     : Sep 17, 2010
 * File Name       : SearchService.java
 *
 ********************************************************************************/
package com.wjdeng.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.util.Version;

import com.wjdeng.lucene.config.LuceneConfig;

public class SearchService {
	
	
	private IndexManager index= IndexManager.Instance();
	
	public static SearchService Instance(){
		return new SearchService();
	}
	
	
	/**
	 * 
	 * 判断该条记录是否存在
	 * @param map
	 * @return
	 */
	public boolean isExist(Map<String,String> map){
		try {
			return this.search(map, new ConverDoc(){

				@Override
				public List<Map<String, String>> convertDoc(
						IndexSearcher indexSearcher, ScoreDoc[] scoreDocs,
						int totalHits) throws CorruptIndexException, IOException,
						InvalidTokenOffsetsException {
					List<Map<String, String>> list = new ArrayList<Map<String, String>>();
					if(totalHits>0){
						Map<String ,String> map = new HashMap<String, String>();
						list.add(map);
					}
					return list;
				}}).size()>0?true:false;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidTokenOffsetsException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * 根据条件获取全部记录
	 * @param map
	 * @return
	 */
	public List<Map<String,String>> searchAll(Map<String,String> map){
		try {
			return this.search(map, new ConverDoc(){

				@Override
				public List<Map<String, String>> convertDoc(
						IndexSearcher indexSearcher, ScoreDoc[] scoreDocs,
						int totalHits) throws CorruptIndexException, IOException,
						InvalidTokenOffsetsException {
					List<Map<String, String>> list = new ArrayList<Map<String, String>>();
					Set<String> fieldNames = LuceneConfig.getAllFieldNames();
					for(int i=0 ;i<totalHits;i++){
						Map<String ,String> map = new HashMap<String, String>();
						Document doc = indexSearcher.doc(scoreDocs[i].doc);
						for(String key : fieldNames){
							map.put(key, doc.get(key));
						}
						list.add(map);
					}
					return list;
				}});
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidTokenOffsetsException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 基础查询方法
	 * @param map
	 * @param conver
	 * @return
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws InvalidTokenOffsetsException 
	 */
	private List<Map<String,String>> search(Map<String,String> map , ConverDoc conver) throws IllegalArgumentException,NullPointerException,ParseException, IOException, InvalidTokenOffsetsException{
			if(map==null)throw new NullPointerException("数据不能为空");
			Query query = this.createQueryByValMap(map);
			IndexSearcher indexSearcher =index.getIndexSearcher();
			int length = indexSearcher.maxDoc() > 0 ? index.getIndexSearcher().maxDoc() : 1;
			TopDocs topDoc=indexSearcher.search(query, length);
			return conver.convertDoc(indexSearcher, topDoc.scoreDocs, topDoc.totalHits);
	}
	
	/**
	 * 
	 * 查询条件
	 * @param map
	 * @return
	 * @throws ParseException
	 */
	private Query createQueryByValMap(Map<String,String> map) throws ParseException,IllegalArgumentException,NullPointerException{
		if(map==null)throw new NullPointerException("数据不能为空");
		Set<Field> fsets = LuceneConfig.getFields(map);
		if(fsets.size()==0) throw new IllegalArgumentException("map中数据不正确!");
		String[] querystrs = new String[map.size()];
		String[] fields = new String[map.size()];
		int i=0;
		for(String key : map.keySet()){
			fields[i]=key;
			querystrs[i]=map.get(key);
			i++;
		}
		BooleanClause.Occur[] occur = this.getShuldOccur(fsets.size());
		Query query = MultiFieldQueryParser.parse(Version.LUCENE_29, querystrs, fields,occur, AnalyzerService.getIKAnalyzerInstance());
		return query;
	}
	
	/**
	 * 
	 * 条件运算符
	 * @param length
	 * @return
	 */
	private BooleanClause.Occur[] getShuldOccur(int length) {
		BooleanClause.Occur[] innerLogicCalculus= new BooleanClause.Occur[length];
		for(int i=0; i<length; i++){
			innerLogicCalculus[i] = BooleanClause.Occur.SHOULD;
		}
		return innerLogicCalculus;
	}
	
	/**
	 * 
	 * 转换结果接口
	 *
	 * @author Administrator
	 * @version 1.0
	 */
	interface ConverDoc{
		public List<Map<String,String>> convertDoc(IndexSearcher indexSearcher, ScoreDoc[] scoreDocs,int totalHits)
		throws CorruptIndexException, IOException, InvalidTokenOffsetsException;
	}
	
}

