package tsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Main() {
        // TODO Auto-generated constructor stub
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Object nodes = null;
		Object mutations = null;
		try {
			nodes = new JSONTokener(request.getParameter("nodes")).nextValue();
			mutations = new JSONTokener(request.getParameter("mutations")).nextValue();
		} catch (JSONException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		if(nodes!=null && mutations!= null){
			Genetic.evolutionary(Integer.parseInt(nodes.toString()), Integer.parseInt(mutations.toString()));
		}

		JSONArray json = new JSONArray();

		if( NodeList.size()>0){
		for(int i=0;i<NodeList.size();i++){

				try {
					json.put(NodeList.findNode(i).getX());
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					json.put(NodeList.findNode(i).getY());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


		}}

		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}

}
