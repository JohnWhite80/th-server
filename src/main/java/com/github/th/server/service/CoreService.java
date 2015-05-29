package com.github.th.server.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("coreService")
public class CoreService {
	
	private @Value("${app.image.store.path}")
	String imgPath;
	private @Value("${app.client.store.path}")
	String clientPath;
	
	public File getCurrentClientFile() {
		return new File(clientPath + File.separator + "peer.apk");
	}

	public Object login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
