package com.involves.commons.parser;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Logger;

import com.involves.commons.exception.DataFieldAccessException;
import com.involves.commons.exception.DataTypeParserException;

public class CSVParser extends InvolvesParser {

	/**
	 * 
	 */
	private static String CSV_SEPARATOR = ",";

	private static Logger logger = Logger.getLogger(CSVParser.class.getName());

	/**
	 * Parse a single POJO to an OutpuStream object
	 * 
	 * @param obj
	 *            pojo to parse.
	 * @param separator
	 *            change the separator character, comma(",") is default
	 * @return return an OutputStream object with a single POJO parsed
	 * @throws Exception
	 */
	public OutputStream parsePojo(Object obj, String separator) throws Exception {
		CSV_SEPARATOR = separator;
		return parsePojo(obj);
	}

	/**
	 * Parse a single POJO to a
	 * 
	 * @param obj
	 *            pojo to parse. Use comma(",") is default separator character
	 * @return return an OutputStream with a single POJO parsed
	 * @throws Exception
	 */
	public OutputStream parsePojo(Object obj) throws Exception {
		Class<?> clazz = obj.getClass();
		logger.info("clazzName: " + clazz.getName());

		OutputStream out = new ByteArrayOutputStream();
		out.write(printHeaders(clazz).getBytes());
		out.write(printRow(obj, clazz).getBytes());
		out.close();
		return out;
	}

	/**
	 * Parse a POJO's list to an OutpuStream object
	 * 
	 * @param list
	 *            Pojo's list to parse.
	 * @param separator
	 *            change the separator character, comma(",") is default
	 * @return return an OutputStream with a single POJO parsed
	 * @throws Exception
	 */
	public OutputStream parsePojos(List list, String separator) throws Exception {
		CSV_SEPARATOR = separator;
		return parsePojos(list);
	}

	/**
	 * Parse a POJO's list to an OutpuStream object
	 * 
	 * @param list
	 *            Pojo's list. Use comma(",") is default separator character
	 * @return return an OutputStream with a single POJO parsed
	 * @throws Exception
	 */
	public OutputStream parsePojos(List list) throws Exception {
		Object obj = list.get(0);
		Class<?> clazz = obj.getClass();
		logger.info("clazzName: " + clazz.getName());

		OutputStream out = new ByteArrayOutputStream();
		out.write(printHeaders(clazz).getBytes());
		for (Object obj2 : list) {
			out.write(printRow(obj2, clazz).getBytes());
		}
		out.close();
		return out;
	}

	/**
	 * Print a header with Pojo's field names
	 * 
	 * @param clazz
	 *            a Pojo's class
	 * @return String with csv's header.
	 * @throws DataTypeParserException
	 */
	protected String printHeaders(Class<?> clazz) throws DataTypeParserException {
		StringBuilder header = new StringBuilder();
		Field[] fields = clazz.getDeclaredFields();

		for (Field tempField : fields) {
			logger.info("Field " + tempField.getName() + " datatype " + tempField.getType());
			if (!tempField.getType().isPrimitive() && !tempField.getType().isAssignableFrom(String.class))
				throw new DataTypeParserException("Field " + tempField.getName() + " has a datatype not supported!");
			header.append(CSV_SEPARATOR + tempField.getName());
		}
		header.append("\n");
		return header.toString().substring(1);
	}

	/**
	 * Print a row of Pojo's field values
	 * 
	 * @param obj
	 *            a Pojo
	 * @param clazz
	 *            a Pojo's Class
	 * @return String a with csv's row
	 * @throws DataFieldAccessException
	 */
	protected  String printRow(Object obj, Class<?> clazz) throws DataFieldAccessException {
		StringBuilder row = new StringBuilder();
		Field[] fields = clazz.getDeclaredFields();

		for (Field tempField : fields) {
			try {
				tempField.setAccessible(true);
				logger.info("Field " + tempField.getName() + " value " + tempField.get(obj));
				row.append(CSV_SEPARATOR + tempField.get(obj));
			} catch (IllegalAccessException e) {
				throw new DataFieldAccessException("Field " + tempField.getName() + " is not accessible!");

			}
		}
		row.append("\n");
		return row.toString().substring(1);
	}

}
