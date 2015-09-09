package com.involves.commons.parser;

import java.io.OutputStream;
import java.util.List;

import com.involves.commons.exception.DataFieldAccessException;
import com.involves.commons.exception.DataTypeParserException;

public abstract class  InvolvesParser {

	public abstract OutputStream parsePojo(Object obj) throws Exception;

	public abstract OutputStream parsePojos(List list) throws Exception;
	
	protected abstract String printHeaders(Class<?> clazz) throws DataTypeParserException;
	
	protected abstract String printRow(Object obj, Class<?> clazz) throws DataFieldAccessException;


}
