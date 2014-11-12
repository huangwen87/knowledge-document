package com.gw.ncps.respository.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;
import com.gw.ncps.respository.IBaseDao;

public class BaseDaoImpl extends SqlSessionDaoSupport implements IBaseDao {

	@Override
	public Page queryPage(String searchSqlPath, String rowCountSqlPath, SearchDTO searchDTO) {
		Page page = getNullResultPage(rowCountSqlPath,searchDTO);

		searchDTO.setStartRowNum(page.getStartRowNum());
		searchDTO.setEndRowNum(page.getEndRowNum());
		List<?> list = getSqlSession().selectList(searchSqlPath, searchDTO,new RowBounds(searchDTO.getStartRowNum(),searchDTO.getEndRowNum()));
		page.setDataRows(list);
		return page;
	}

	/**
	 * 获取无结果集的分页对象
	 * @param rowCountSqlPath
	 * @param searchDTO
	 * @return
	 */
	private Page getNullResultPage(String rowCountSqlPath,SearchDTO searchDTO) {
		int rowCount = getRowCount(rowCountSqlPath,searchDTO);
		int pageCount = getPageCount(rowCount, searchDTO.getPageSize());
		int startRow = getStartRow(searchDTO.getPageNo(), searchDTO.getPageSize());
		int endRow = getEndRow(searchDTO.getPageNo(), searchDTO.getPageSize());

		Page page = new Page(searchDTO.getPageNo(), searchDTO.getPageSize(), pageCount, rowCount, null);
		page.setStartRowNum(startRow);
		page.setEndRowNum(endRow);

		return page;
	}

	/**
	 * 获取总行数
	 */
	private int getRowCount(String rowCountSqlPath, SearchDTO searchDTO) {
		Integer count = (Integer) getSqlSession().selectOne(rowCountSqlPath, searchDTO);
		return count;
	}

	/**
	 * 获取总页数
	 * 
	 * @param rowCount
	 * @param pageSize
	 * @return
	 */
	private int getPageCount(long rowCount, int pageSize) {
		return (int) ((rowCount + pageSize - 1) / pageSize);
	}

	/**
	 * 获取开始行
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	private int getStartRow(int currentPage, int pageSize) {
		return (currentPage - 1) * pageSize;
	}

	/**
	 * 获取结束行
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	private int getEndRow(int currentPage, int pageSize) {
		return (currentPage - 1) * pageSize + pageSize;
	}

}
