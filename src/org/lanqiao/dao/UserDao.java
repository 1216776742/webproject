package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.User;

public interface UserDao {
	public void insert(User user);
	public User getUserByLoginId(String loginid);
	public void upate(User user);
	public List<User> getUserList();
	public boolean remouser(String userid);
	public boolean hasemail(String email);
}
