import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.involves.commons.parser.CSVParser;

public class CSVParserTest {

	@Test
	public void testParserPojo() throws Exception {
		CSVParser csvp = new CSVParser();
		PojoTest1 pt = new PojoTest1("a", "a", 111);
		OutputStream ret = csvp.parsePojo(pt);
		String string = "name,email,zip\n" + "a,a,111".trim();
		Assert.assertEquals(string, ret.toString().trim());
	}

	@Test
	public void testParsersPojo() throws Exception {
		CSVParser csvp = new CSVParser();

		List<PojoTest1> objs = new ArrayList<PojoTest1>();
		objs.add(new PojoTest1("a", "a", 111));
		objs.add(new PojoTest1("b", "b", 222));
		OutputStream ret = csvp.parsePojos(objs);
		String string = "name,email,zip\n" + "a,a,111\nb,b,222".trim();
		Assert.assertEquals(string, ret.toString().trim());
	}

	@Test
	public void testParserPojoDataTypeParserException() {
		CSVParser csvp = new CSVParser();
	
		PojoTest2 pt = new PojoTest2("a", "a", 111);

		try {
			OutputStream ret = csvp.parsePojo(pt);
			Assert.fail("expected DataTypeParserException");
		} catch (Exception e) {
			// ignore, this exception is expected.
		}
	}

}
