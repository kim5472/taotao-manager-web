package com.taotao.fastdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.taotao.utils.FastDFSClient;

public class TestFastDFS {
	@Test
	public void uploadFile()throws Exception{
		// 1向工程中添加jar包
		// 2创建一个配置文件。配置tracker服务器地址
		// 3加载配置文件
		ClientGlobal.init("E:\\eclipse\\workspace\\taotao-manager-web\\src\\main\\resources\\resource\\client.conf");
		// 4创建一个TrackerClient对象
		TrackerClient trackerClient = new TrackerClient();
		// 5使用TrackerClient对象获得trackerserver对象
		TrackerServer trackerServer = trackerClient.getConnection();
		// 6创建一个StorageServer的引用null就可以
		StorageServer storageServer = null;
		// 7创建一个storageClient对象 trackerserver、StorageServer两个参数
		StorageClient storageClient = new StorageClient(trackerServer,storageServer);
		// 8使用storageClient对象上传文件
		String[] strings = storageClient.upload_file("D:/timg.jpg", "jpg", null);
		for (String string : strings) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testFastUtils()throws Exception{
		FastDFSClient fastDFSClient = new FastDFSClient("E:\\eclipse\\workspace\\taotao-manager-web\\src\\main\\resources\\resource\\client.conf");
		String uploadFile = fastDFSClient.uploadFile("D:/timg.jpg");
		System.out.println(uploadFile);
	}
}
