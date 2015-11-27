package org.zucc.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zucc.dao.UserMapper;
import org.zucc.entity.User;
import org.zucc.entity.UserExample;


public class test {
	
	SqlSession sqlSession = null;
	UserMapper userMap = null;
	
	@Before
	public void setup() throws IOException{
		// mybatis配置文件
		String resource = "config.xml";
		// 得到配置文件流
		InputStream is = Resources.getResourceAsStream(resource);
		// 创建会话工厂，传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(is);
		// 通过工厂得到SqlSession
		sqlSession = sqlSessionFactory.openSession();
		// 通过SqlSession操作数据库
		// 第一个参数：映射文件中statement的id,等于"namespace.statment的id"
		// 第二个参数：指定和映射文件中所匹配的parameterType类型的参数
		// SqlSession.selectOne结果是与映射文件中所匹配的resultType类型的对 象	
		userMap = sqlSession.getMapper(UserMapper.class);
	}
	
	@After
	public void gameover(){
		sqlSession.close();
		
//***********接口映射ID查询*************	
	}
	@Test
	public void find() throws IOException {		
		User user = sqlSession.selectOne("org.zucc.dao.UserMapper.selectByPrimaryKey", 1);
		System.out.println(user);	
	}
//***********接口映射添加*************

	@Test
	public void adduser() throws IOException {
	
		User user = new User();	
		user.setUserName("dada@qq.com");
		user.setUserRealname("赵四");
		user.setUserPassword("wewq3");
		user.setUserTelphone("13123213");
		userMap.insert(user);
		sqlSession.commit();
	}
//***********接口映射构造方法添加*************
	@Test
	public void addUser() throws IOException {
		User user = new User("1","2","3","4");	

		userMap.insert(user);
		sqlSession.commit();
	}
//***********接口映射删除*************
	@Test
	public void userDelete() throws IOException{
	
		userMap.deleteByPrimaryKey(8);
		sqlSession.commit();		
	}
	
//	@Test
//	public void updateUser1(){
//		User user = new User();	
//		user.setName("haxi");
//		user.setId(4);
//		user = sqlSession.selectOne("cn.zucc.dao.UserMap.updateUser",user);
//		sqlSession.commit();
//	}
//***********接口映射更新*************
	@Test
	public void updateUser() throws IOException{
		User user = new User();
		user.setUserId(5);
		user.setUserName("happy");
		user.setUserPassword("zheshimima");
//		user.setUserRealname("greed");
//		user.setUserTelphone("good2");
		userMap.updateByPrimaryKeySelective(user);
		sqlSession.commit();
		}
		
//***********接口映射按多个id查询*************
	@Test
		public void findUserByIds() throws IOException{

			UserExample userExample = new UserExample();
			UserExample.Criteria criteria =userExample.createCriteria();
			List<Integer>ids = new ArrayList<Integer>();
			ids.add(1);
			ids.add(3);
			ids.add(5);
			criteria.andUserIdIn(ids);
			List<User>users = userMap.selectByExample(userExample);
			for(User user:users){
				System.out.println(user);
			}		
			//******一级缓存*****
			List<User>users1 = userMap.selectByExample(userExample);
			for(User user:users1){
				System.out.println(user);
			}	
	}
}


	
	
