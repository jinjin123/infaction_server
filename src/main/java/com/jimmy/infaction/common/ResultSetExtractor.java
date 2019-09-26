package com.jimmy.infaction.common;

import java.sql.ResultSet;

/**
 * @author jimmy on 2019/9/26 16:25
 */
public interface ResultSetExtractor<T> {
	public abstract T extractData(ResultSet rs);

}
