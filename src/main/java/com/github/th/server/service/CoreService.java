package com.github.th.server.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.th.server.APIException;
import com.github.th.server.entity.User;
import com.github.th.server.repository.UserRepository;

@Service("coreService")
public class CoreService {
	
	private @Value("${app.image.store.path}") String imgPath;
	
	private @Value("${app.client.store.path}") String clientPath;
	
	@Autowired
    private UserRepository userRepository;
	
	public File getCurrentClientFile() {
		return new File(clientPath + File.separator + "peer.apk");
	}

	@Transactional
	public User login(String email, String password) {
		List<User> users = userRepository.findByEmail(email);
		if (users != null && users.size() > 0) {
			User user = users.get(0);
			if("���".equals(user.getStatus())){
				throw new APIException("fail", "���ѱ�ͬ�й���Ա��ţ�������������ϵ����");
			}
			user.setLastLoginAt(new Date());
			userRepository.save(user);
			return user;
		}
		throw new APIException("fail", "�û��������벻��ȷ");
	}

}
