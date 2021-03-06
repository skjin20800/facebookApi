package filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import anno.GetMapping;
import anno.PostMapping;
import web.GetController;
import web.PostController;
import web.dto.CMRespDto;

public class Dispatcher implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// UTF변환
		request.setCharacterEncoding("utf-8");
		// 응답 데이터타입
		response.setContentType("application/json; charset=UTF-8");


		String endPoint = request.getRequestURI().replaceAll(request.getContextPath(), "");


		if (request.getMethod().equals("GET")) {
			GetController getController = new GetController();
			Method[] methods = getController.getClass().getDeclaredMethods();

			get(methods, endPoint, getController, request, response);

		} else if (request.getMethod().equals("POST")) {
			PostController postController = new PostController();
			Method[] methods = postController.getClass().getDeclaredMethods();

			post(methods, endPoint, postController, request, response);
		}

	}


	
	private <T> void setData(T dtoInstance, HttpServletRequest request) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String input = "";
		String json = "";
		while ((input = br.readLine()) != null) {
			json += input;
		}

		// 파라미터 키 추출
		Iterator<String> keys = jsonToKeys(json).iterator();
		
		// 파라미터  값 추출, 
		Map<String, String> values = jsonToValues(json);
		
		while (keys.hasNext()) { // keys의 데이터 수 만큼 반복 //다음번 주소가 있는지 체크.
			String key = (String) keys.next(); // keys의 값을 key에담고 다음 keys값을 앞으로 땡겨놓는다
			String methodKey = keyToMethodKey(key); // setUsername

			Method[] methods = dtoInstance.getClass().getDeclaredMethods();

			for (Method method : methods) {
				if (method.getName().equals(methodKey)) {
					try {
						method.invoke(dtoInstance, values.get(key)); // String
					} catch (Exception e) {
						try {
							int value = Integer.parseInt(values.get(key));
							method.invoke(dtoInstance, value);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}
			}
		}
	}

	// 키, 값중 키 리스트 추출
	private List<String> jsonToKeys(String json) {
		String[] all = json.split(",");

		List<String> keys = new ArrayList<>();
		for (int i = 0; i < all.length; i++) {
			String[] all2 = all[i].split("\"");
			keys.add(all2[1]);
		}

		return keys;
	}

	// 키, 값중 값 리스트 추출
	private Map<String, String> jsonToValues(String json) {
		String[] all = json.split(",");

		Map<String, String> values = new HashMap<>();
		for (int i = 0; i < all.length; i++) {
			String[] all2 = all[i].split("\"");
			values.put(all2[1], all2[3]);
		}

		return values;
	}

	// 키 값 setter변경
	private String keyToMethodKey(String key) {
		String firstKey = "set";
		String upperKey = key.substring(0, 1).toUpperCase();
		String remainKey = key.substring(1);

		return firstKey + upperKey + remainKey;
	}

	// 응답
	private void get(Method[] methods, String endPoint, GetController getController, HttpServletRequest request,
			HttpServletResponse response) {
		for (Method method : methods) {
			Annotation annotation = method.getDeclaredAnnotation(GetMapping.class);
			GetMapping getMapping = (GetMapping) annotation;
			if (getMapping.value().equals(endPoint)) {
				CMRespDto<?> cmRespDto = null;
				try {
					Parameter[] params = method.getParameters();
					if (params.length != 0) {
						cmRespDto = (CMRespDto<?>) method.invoke(getController, request);
					} else {
						cmRespDto = (CMRespDto<?>) method.invoke(getController);
					}
					PrintWriter out = response.getWriter();
					out.print("{ \"statuscode\": " + cmRespDto.getStatusCode() + ", ");
					out.print("\n\"Message\": \"" + cmRespDto.getMsg() + "\", ");
					out.print("\n\"Data\": \"" + cmRespDto.getData() + "\"} ");
					out.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	
	// 응답
	private void post(Method[] methods, String endPoint, PostController postController, HttpServletRequest request,
			HttpServletResponse response) {
		for (Method method : methods) {
			Annotation annotation = method.getDeclaredAnnotation(PostMapping.class);
			PostMapping postMapping = (PostMapping) annotation;
			
			//POST요청
			if (postMapping.value().equals(endPoint)) {
				CMRespDto<?> cmRespDto = null;
				try {
					Parameter[] params = method.getParameters();
					if (params.length != 0) {
						// 해당 dtoInstance를 리플렉션해서 set함수 호출(username, password)
						Object dtoInstance = params[0].getType().newInstance();
						setData(dtoInstance, request); // request에서 적용할 setter값을 전부 dtoInstance에 넣어 DTO로 전달한다.
						cmRespDto = (CMRespDto<?>) method.invoke(postController, dtoInstance);
					} else {
						cmRespDto = (CMRespDto<?>) method.invoke(postController);
					}
					
					if(postMapping.value().equals("/login")) {//로그인
						HttpSession session = request.getSession();
						session.setAttribute("session", cmRespDto.getData()); 
					}else if(postMapping.value().equals("/logout")) { //로그아웃
						HttpSession session = request.getSession();
						session.invalidate();		
					}
					
					PrintWriter out = response.getWriter();
					out.print("{ \"statuscode\": " + cmRespDto.getStatusCode() + ", ");
					out.print("\n\"Message\": \"" + cmRespDto.getMsg() + "\", ");
					out.print("\n\"Data\": \"" + cmRespDto.getData() + "\"} ");

					out.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
