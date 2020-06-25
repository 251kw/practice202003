package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBResearch3;
import dao.DEUpdetaManager;
import dto.UserDTO;

/**
 * Servlet implementation class Updeta
 */
@WebServlet("/Updeta")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String profile=request.getParameter("profile");
		String icon=request.getParameter("icon");
		String userName=request.getParameter("userName");
		String del=request.getParameter("del");
		HttpSession session = request.getSession();
		DBResearch3 dbs=new DBResearch3();
		UserDTO users=dbs.getLoginUser7(userName);
		RequestDispatcher dispatcher = null;
		int i=9;
		if(loginId.equals("") || password.equals(""))  {
			String message = "ログインIDとパスワードは必須入力です";
			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			request.setAttribute(loginId, loginId);
			request.setAttribute(userName, userName);
			request.setAttribute(icon, icon);
			request.setAttribute(password, password);
			request.setAttribute(profile, profile);
			request.setAttribute("alert", message);
			request.setAttribute(del, del);
			dispatcher = request.getRequestDispatcher("NewFile2.jsp");
			dispatcher.forward(request, response);
		}else if(users!=null){
			String message = "ユーザー名は既に使われています";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			request.setAttribute(loginId, loginId);
			request.setAttribute(userName, userName);
			request.setAttribute(icon, icon);
			request.setAttribute(password, password);
			request.setAttribute(profile, profile);
			request.setAttribute(del, del);
			dispatcher = request.getRequestDispatcher("NewFile2.jsp");
			dispatcher.forward(request, response);
		}else if(loginId.length() > i){
			String message = "ログインIDは8文字以下で入力してください";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			request.setAttribute("alert", message);
			request.setAttribute(loginId, loginId);
			request.setAttribute(userName, userName);
			request.setAttribute(icon, icon);
			request.setAttribute(password, password);
			request.setAttribute(profile, profile);
			request.setAttribute(del, del);
			dispatcher = request.getRequestDispatcher("NewFile2.jsp");
			dispatcher.forward(request, response);
		}else if(!loginId.matches("^[-@+*;:#$%&\\w]+$")) {
String message = "半角英数記号で入力してください";

			request.setAttribute("alert", message);
			request.setAttribute(loginId, loginId);
			request.setAttribute(userName, userName);
			request.setAttribute(icon, icon);
			request.setAttribute(password, password);
			request.setAttribute(profile, profile);
			request.setAttribute(del, del);
			dispatcher = request.getRequestDispatcher("NewFile2.jsp");
			dispatcher.forward(request, response);
		}else {
			DEUpdetaManager dbm = new DEUpdetaManager();
			dbm.getLoginUser(loginId, password,profile,icon,userName);
			request.setAttribute(loginId, loginId);
			request.setAttribute(userName, userName);
			request.setAttribute(password, password);
			request.setAttribute(profile, profile);
			request.setAttribute(del, del);
			dispatcher = request.getRequestDispatcher("NewFile3.jsp");
			dispatcher.forward(request, response);
		}

	}


}
