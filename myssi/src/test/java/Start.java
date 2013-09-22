

import org.eclipse.jetty.server.Server;

/**
 * 使用Jetty运行调试Web应用, 在Console输入回车停止服务.
 * 
 * @author calvin
 */
public class Start { 

	public static final int PORT = 8080;
	public static final int TEST_PORT = 8083;
	public static final String CONTEXT = "/myssi";
	public static final String BASE_URL = "http://localhost:8080/myssi";
	public static final String TEST_BASE_URL = "http://localhost:8083/myssi";
	
	
	public static void main(String[] args) throws Exception {
		Server server = JettyFactory.buildNormalServer(TEST_PORT, CONTEXT);
		server.start();
		System.out.println("Server running at " + TEST_BASE_URL);
		System.out.println("demo url at " + TEST_BASE_URL + "/my/demo");
		System.out.println("Hit Enter in console to stop server");
		
		if (System.in.read() != 0) {
			server.stop();
			System.out.println("Server stopped");
		}
	}
}
