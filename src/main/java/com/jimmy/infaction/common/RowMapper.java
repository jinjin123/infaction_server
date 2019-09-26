package com.jimmy.infaction.common;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author jimmy on 2019/9/26 16:26
 */
public interface RowMapper<T> {
	public abstract T mapRow(ResultSet rs, int index) throws SQLException;
}
