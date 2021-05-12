package filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import anno.GetMapping;
import anno.PostMapping;
import web.FrontController;
import web.dto.CMRespDto;

public class Dispatcher implements Filter {


	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		//UTF변환
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String endPoint = request.getRequestURI().replaceAll(request.getContextPath(), "");
		
		
		System.out.println("엔드포인트 : " + endPoint); // /user/login
		System.out.println("겟 메서드"+request.getMethod());

		FrontController frontController = new FrontController();

		Method[] methods = frontController.getClass().getDeclaredMethods();

		for (Method method : methods) {
				Annotation annotation = method.getDeclaredAnnotation(GetMapping.class);
				GetMapping requestMapping = (GetMapping) annotation;	
			
			if (requestMapping.value().equals(endPoint)) {
				CMRespDto<?> cmRespDto = null;
				try {
					Parameter[] params = method.getParameters(); // LoginDto
					String path = null;
					if (params.length != 0) {
						// 해당 dtoInstance를 리플렉션해서 set함수 호출(username, password)
						Object dtoInstance = params[0].getType().newInstance();
						setData(dtoInstance, request); // request에서 적용할 setter값을 전부 dtoInstance에 넣어 DTO로 전달한다.
						cmRespDto = (CMRespDto<?>) method.invoke(frontController, dtoInstance);
					} else {
						cmRespDto = (CMRespDto<?>) method.invoke(frontController);
					}
					
					PrintWriter out = response.getWriter();
					if(cmRespDto.getStatusCode() == 1) {
						out.print(cmRespDto.getMsg());
					}else {
						out.print("fail");
					}
					out.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	private <T> void setData(T dtoInstance, HttpServletRequest request) {
		Enumeration<String> keys = request.getParameterNames(); // 크기 : 2 (username, password)
		while (keys.hasMoreElements()) { // 2번 돈다 //다음번 주소가 있는지 체크.
			String key = (String) keys.nextElement(); //keys의 값을 key에담고 다음 keys값을 앞으로 땡겨놓는다
			String methodKey = keyToMethodKey(key); // setUsername

			Method[] methods = dtoInstance.getClass().getDeclaredMethods(); 

			for (Method method : methods) {
				if (method.getName().equals(methodKey)) {
					try {
						method.invoke(dtoInstance, request.getParameter(key)); // String
					} catch (Exception e) {
						try {
							int value = Integer.parseInt(request.getParameter(key));
							method.invoke(dtoInstance, value);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}
			}
		}
	}

	private String keyToMethodKey(String key) {
		String firstKey = "set";
		String upperKey = key.substring(0, 1).toUpperCase();
		String remainKey = key.substring(1);

		return firstKey + upperKey + remainKey;
	}

}