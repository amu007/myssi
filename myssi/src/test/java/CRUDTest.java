

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.amu.demo.myssi.common.crud.SearchFilter;
import org.amu.demo.myssi.entity.User;
import org.amu.demo.myssi.service.AccountManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


/**
 * CRUD功能。简单的单元测试类
 * @author amu 
 */
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class CRUDTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private AccountManager accountManager;

	@Test
	public void allClassMapping() throws Exception {
		
		User user = new User();
		user.setLoginName("amu_login");
		user.setName("amu");
		accountManager.save(user);
		
		List<SearchFilter> searchFilterList = new ArrayList<SearchFilter>();
		SearchFilter sf1 = new SearchFilter("name", SearchFilter.Operator.LIKE, "amu");
		SearchFilter sf2 = new SearchFilter("loginName", SearchFilter.Operator.EQ, "amu_login");
		searchFilterList.add(sf1);
		searchFilterList.add(sf2);
		List<User> userList = accountManager.findBy(searchFilterList);
		Assert.assertEquals(1, userList.size());
		
		User user2 = userList.get(0);
		user2.setName("amu2");
		accountManager.update(user2);
		
		User user3 = accountManager.get(user2.getId());
		Assert.assertEquals("amu2", user3.getName());
		
		accountManager.delete(user2.getId());
		

		User user4 = accountManager.get(user2.getId());
		Assert.assertNull("user supposed to be deleted", user4);
		
	}
}


