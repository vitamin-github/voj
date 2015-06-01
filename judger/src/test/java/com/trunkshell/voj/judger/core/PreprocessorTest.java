package com.trunkshell.voj.judger.core;

import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.trunkshell.voj.judger.mapper.SubmissionMapper;
import com.trunkshell.voj.judger.model.Submission;

/**
 * 预处理器的测试类.
 * 
 * @author Xie Haozhe
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({"classpath:test-spring-context.xml"})
public class PreprocessorTest {
	/**
	 *  测试用例: 测试createTestCode(Submission, String, String)方法
	 *  测试数据: 使用存在的提交记录(Submission#1000)
	 *  预期结果: 在指定位置创建源代码文件
	 */
	@Test
	public void testCreateTestCodeCpp() {
		Submission submission = submissionMapper.getSubmission(1000);
		String workDirectory = "/tmp/voj-1000/";
		String baseFileName = "random-name";
		
		try {
			preprocessor.createTestCode(submission, workDirectory, baseFileName);
			FileInputStream inputStream = new FileInputStream("/tmp/voj-1000/random-name.cpp");
	        String code = IOUtils.toString(inputStream);
	        inputStream.close();
	        Assert.assertEquals(submission.getCode(), code);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 *  测试用例: 测试createTestCode(Submission, String, String)方法
	 *  测试数据: 使用存在的提交记录(Submission#1001)
	 *  预期结果: 在指定位置创建源代码文件
	 */
	@Test
	public void testCreateTestCodeJava() {
		Submission submission = submissionMapper.getSubmission(1001);
		String workDirectory = "/tmp/voj-1001/";
		String baseFileName = "RandomName";
		
		try {
			preprocessor.createTestCode(submission, workDirectory, baseFileName);
			FileInputStream inputStream = new FileInputStream("/tmp/voj-1001/RandomName.java");
	        String code = IOUtils.toString(inputStream);
	        inputStream.close();
	        Assert.assertEquals(submission.getCode(), code);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 待测试的Preprocessor对象.
	 */
	@Autowired
	private Preprocessor preprocessor;
	
	/**
	 * 自动注入的SubmissionMapper对象.
	 * 用于构造测试用例.
	 */
	@Autowired
	private SubmissionMapper submissionMapper;
}