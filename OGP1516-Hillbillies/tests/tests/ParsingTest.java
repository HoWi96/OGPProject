package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import hillbillies.expression.Expression;
import hillbillies.model.Task;
import hillbillies.model.TaskFactory;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.TaskParser;
import hillbillies.statement.Statement;

public class ParsingTest {



	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ITaskFactory<Expression, Statement, Task> factory = new TaskFactory();
		parser = TaskParser.create(factory);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	private static TaskParser<?, ?, Task> parser;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test @Ignore // working alone
	public void testParsingDig() throws IOException{
		assertTrue(this.parsingSuccesfull("resources/tasks/dig.txt"));
	}

	@Test
	public void testParsingDigTunnel() throws IOException{
		assertTrue(this.parsingSuccesfull("resources/tasks/digtunnel.txt"));
	}

	@Test
	public void testParsingDigTunnelIf() throws IOException{
		assertTrue(this.parsingSuccesfull("resources/tasks/digtunnel_if.txt"));
	}

	@Test
	public void testParsingGoto() throws IOException{
		assertTrue(this.parsingSuccesfull("resources/tasks/goto_10_10_10.txt"));
	}

	@Test
	public void testParsingWorkshop() throws IOException{
		assertTrue(this.parsingSuccesfull("resources/tasks/operate_workshop.txt"));
	}
	
	private boolean parsingSuccesfull(String file){
		Optional<List<Task>> task;
		try{
			task = parser.parseFile(file, Collections.emptyList());
			if (task.isPresent()) {
				task.get().toString();
				return true;
			} else {
				System.out.println("Parsing failed");
				System.out.println(parser.getErrors());
				return false;
			}
		} catch(Throwable e) {
			System.out.println("caught " +e.toString());
			return false;
			}
	}
}
