package com.userDAO.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bugdao.controller.Bug;
import com.bugdao.controller.BugAlreadyExitsException;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int user_id=Integer.parseInt(request.getParameter("user_id"));
		String user_email=request.getParameter("user_email");
		String user_name=request.getParameter("user_name");
		String user_type=request.getParameter("user_type");
		String user_password=request.getParameter("user_password");
		try {
			DAOUserFactory.getBugDAOImpl().addBug(new Bug(bugTitle, bugDescription, projectId, createdBy, openDate, assignedBy, markedForClosing, closedBy, closedDate, status, severity));
		} catch (BugAlreadyExitsException e) {
			response.setStatus(403);
			response.setContentType("text/html");
			response.getWriter().print(e.toString());
			e.printStackTrace();
		}
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
