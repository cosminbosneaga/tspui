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
@WebServlet("/Game")
public class GameCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GameCreator() {
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
		Evolutionary evo = null;
		JSONObject jsonResponse = new JSONObject();
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
			evo = new Evolutionary();
			evo.evolveInstance(Integer.parseInt(nodes.toString()), Integer.parseInt(mutations.toString()));
		}

		JSONArray positionsJson = new JSONArray();
		JSONArray heuristicJson = new JSONArray();
		try {
			positionsJson = generatePositionsJson(evo.getInstance());
			heuristicJson = generateHeuristicJson(evo.getHeuristicTour());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			jsonResponse.put("instance", positionsJson);
			jsonResponse.put("heuristic", heuristicJson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.setContentType("application/json");
		response.getWriter().write(jsonResponse.toString() );
	}
	
	private JSONArray generateHeuristicJson(Tour heuristicTour){
		JSONArray json = new JSONArray();
		if( heuristicTour.size()>0){
			for(int i=0;i<heuristicTour.size();i++){
				json.put(heuristicTour.getNode(i));
			}
		} else {
			return null;
		}
		return json;
	}
	
	private JSONArray generatePositionsJson(Instance instance) throws JSONException{
		JSONArray json = new JSONArray();
		
		if( instance.size()>0){
			
			for(int i=0;i<instance.size();i++){

				json.put(instance.findNode(i).getX());
				json.put(instance.findNode(i).getY());
			}
		} else {
			return null;
		}
		
		return json;
		
	}

}
