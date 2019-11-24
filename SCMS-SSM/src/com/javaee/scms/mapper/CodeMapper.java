package com.javaee.scms.mapper;

import java.sql.SQLException;
import java.util.List;

import com.javaee.scms.pojo.Code;

public interface CodeMapper {

	public void add(Code code) throws SQLException ;
	public List<Code> findAllCode() throws SQLException;
	public Code findById(Integer id) throws SQLException;
	public void update(Code code) throws SQLException;
	public void delete(Integer id) throws SQLException ;
	
}
