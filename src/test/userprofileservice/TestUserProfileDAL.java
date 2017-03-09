package test.userprofileservice;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.swap.dao.user.UserDao;
import com.swap.entity.common.UserEntity;

@RunWith(MockitoJUnitRunner.class)
public class TestUserProfileDAL {

	@Mock
	private static UserDao mockedUserDao;
	private static UserEntity userDetails1;
	private static UserEntity userDetails2;

	@BeforeClass
	public static void setUp() {

		// Create few instances of userentity class.
		userDetails1.setUserId(1);
		userDetails1.setUserName("username1");
		userDetails1.setFirstName("firstName1");
		userDetails1.setLastName("lastName1");
		userDetails1.setEmail("firstName1@gmail.com");
		userDetails1.setFeedbackScore(100);
		userDetails1.setPhoneNumber("4081111111");
		userDetails1.setActive("true");
		userDetails1.setCreatedDate("2/3/2017");

		userDetails2.setUserId(2);
		userDetails2.setUserName("username2");
		userDetails2.setFirstName("firstName2");
		userDetails2.setLastName("lastName2");
		userDetails2.setEmail("firstName2@gmail.com");
		userDetails2.setFeedbackScore(101);
		userDetails2.setPhoneNumber("4081111111");
		userDetails2.setActive("true");
		userDetails2.setCreatedDate("2/2/2017");

		// Stubbing the methods of mocked userdetails DAL with mocked data.
		Mockito.when(mockedUserDao.getUserByUserName(userDetails1)).thenReturn(userDetails1);
		Mockito.when(mockedUserDao.getUserById(userDetails1)).thenReturn(userDetails1);
		Mockito.when(mockedUserDao.createUser(userDetails2)).thenReturn(userDetails2);

	}

	@Test
	public void testGetUseByUserName() throws Exception {
		UserEntity userEntity = mockedUserDao.getUserByUserName(userDetails1);
		assertEquals(1, userEntity.getUserId());
		assertEquals("username1", userEntity.getUserName());
		assertEquals("firstName1", userEntity.getFirstName());
		assertEquals("lastName1", userEntity.getLastName());
		assertEquals("firstName1@gmail.com", userEntity.getEmail());
		assertEquals(100, userEntity.getFeedbackScore());
		assertEquals("4081111111", userEntity.getPhoneNumber());

	}

}
