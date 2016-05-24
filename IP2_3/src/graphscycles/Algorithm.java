package graphscycles;

import java.util.ArrayList;
import java.util.List;

import buildingParts.BuildingPart;
import buildingParts.Coordinates;
import buildingParts.Door;
import buildingParts.Stairs;
import buildingParts.Wall;
import buildingParts.Window;
import graphModel.Room;

public class Algorithm {

	private static List<Float> areas = new ArrayList<Float>();
	static BuildingPart bp;
	static int index;
	public static List<Room> finalRooms = new ArrayList<Room>();

	List<Node> nodesList = new ArrayList<Node>();
	List<BuildingPart> roomObject;
	List<Node[]> cycle = new ArrayList<Node[]>();

	int nodeLabel = 1;
	List<Edge> mEdges = new ArrayList<Edge>();

	List<Window> windows = new ArrayList<Window>();
	List<Door> doors = new ArrayList<Door>();
	List<Wall> walls = new ArrayList<Wall>();
	List<Stairs> stairs = new ArrayList<Stairs>();

	List<Node[]> finalGraphs = new ArrayList<Node[]>();

	List<AlgorithmBuildingPart> mAlgBuildingParts = new ArrayList<AlgorithmBuildingPart>();

	List<Coordinates> mCoordinates = new ArrayList<Coordinates>();

	Node[] nodes;

	List<Integer> roomsCount = new ArrayList<Integer>();
	List<Room> roomList = new ArrayList<Room>();

	public Algorithm(){

		initialize();
		setBuildingParts();
		setAlgorithmParts();
		algorithm();
		setRooms();
	}

	public Algorithm(List<Wall> walls,List<Window> windows,List<Door> doors,List<Stairs> stairs){

		this.windows = windows;
		this.doors = doors;
		this.stairs = stairs;
		this.walls = walls;

		setBuildingParts();
		setAlgorithmParts();
		algorithm();
		setRooms();

	}

	private void initialize(){

		windows.add(new Window(new Coordinates(2, 0),new Coordinates(3,0)));
		windows.add(new Window(new Coordinates(7,0),new Coordinates(8,0)));

		doors.add(new Door(new Coordinates(2,3),new Coordinates(3,3)));
		doors.add(new Door(new Coordinates(7,3),new Coordinates(8,3)));

		walls.add(new Wall(new Coordinates(0,0),new Coordinates(2,0)));
		walls.add(new Wall(new Coordinates(0,0),new Coordinates(0,3)));
		walls.add(new Wall(new Coordinates(3,0),new Coordinates(5,0)));
		walls.add(new Wall(new Coordinates(5,0),new Coordinates(7,0)));
		walls.add(new Wall(new Coordinates(8,0),new Coordinates(10,0)));
		walls.add(new Wall(new Coordinates(10,0),new Coordinates(10,3)));
		walls.add(new Wall(new Coordinates(10,3),new Coordinates(8,3)));
		walls.add(new Wall(new Coordinates(7,3),new Coordinates(5,3)));
		walls.add(new Wall(new Coordinates(5,3),new Coordinates(3,3)));
		walls.add(new Wall(new Coordinates(2,3),new Coordinates(0,3)));
		walls.add(new Wall(new Coordinates(5,3),new Coordinates(5,0)));

		walls.add(new Wall(new Coordinates(5,3),new Coordinates(5,5)));
		walls.add(new Wall(new Coordinates(5,5),new Coordinates(10,5)));
		walls.add(new Wall(new Coordinates(10,5),new Coordinates(10,3)));

		stairs.add(new Stairs(new Coordinates(12,3),new Coordinates(14,0)));
		stairs.add(new Stairs(new Coordinates(11,3),new Coordinates(12,0)));
		/*walls.add(new Wall(new Coordinates(5,5),new Coordinates(5,7)));
		walls.add(new Wall(new Coordinates(5,7),new Coordinates(10,7)));
		walls.add(new Wall(new Coordinates(10,7),new Coordinates(10,5)));

		walls.add(new Wall(new Coordinates(10,0),new Coordinates(14,0)));
		walls.add(new Wall(new Coordinates(14,0),new Coordinates(14,3)));
		walls.add(new Wall(new Coordinates(14,3),new Coordinates(10,3)));
		*/




		/*
		windows.add(new Window(new Coordinates(0,8),new Coordinates(0,10)));
		windows.add(new Window(new Coordinates(3,0),new Coordinates(5,0)));

		doors.add(new Door(new Coordinates(10,6),new Coordinates(12,6)));
		doors.add(new Door(new Coordinates(9,8),new Coordinates(9,10)));
		doors.add(new Door(new Coordinates(7,13),new Coordinates(5,13)));

		walls.add(new Wall(new Coordinates(0,0),new Coordinates(3,0)));
		walls.add(new Wall(new Coordinates(5,0),new Coordinates(12,0)));
		walls.add(new Wall(new Coordinates(12,0),new Coordinates(12,6)));
		walls.add(new Wall(new Coordinates(10,6),new Coordinates(0,6)));
		walls.add(new Wall(new Coordinates(0,6),new Coordinates(0,0)));
		walls.add(new Wall(new Coordinates(9,6),new Coordinates(9,8)));
		walls.add(new Wall(new Coordinates(9,10),new Coordinates(9,13)));
		walls.add(new Wall(new Coordinates(9,13),new Coordinates(7,13)));
		walls.add(new Wall(new Coordinates(0,13),new Coordinates(5,13)));
		walls.add(new Wall(new Coordinates(0,13),new Coordinates(0,10)));
		walls.add(new Wall(new Coordinates(0,8),new Coordinates(0,6)));
		*/
		/*windows.add(new Window(new Coordinates(3,0),new Coordinates(5,0)));

		doors.add(new Door(new Coordinates(10,6),new Coordinates(12,6)));

		walls.add(new Wall(new Coordinates(0,0),new Coordinates(3,0)));
		walls.add(new Wall(new Coordinates(5,0),new Coordinates(12,0)));
		walls.add(new Wall(new Coordinates(12,0),new Coordinates(12,6)));
		walls.add(new Wall(new Coordinates(12,0),new Coordinates(12,6)));
		walls.add(new Wall(new Coordinates(12,6),new Coordinates(0,6)));
		walls.add(new Wall(new Coordinates(0,6),new Coordinates(0,0)));*/











	}

	private void setBuildingParts(){
		for(int i=0;i<windows.size();i++){
			mAlgBuildingParts.add(new AlgorithmBuildingPart(windows.get(i).getStart().getX(),windows.get(i).getStart().getY(),
					windows.get(i).getEnd().getX(),windows.get(i).getEnd().getY(),"window",nodeLabel++,nodeLabel++));

		}

		for(int i=0;i<walls.size();i++){
			mAlgBuildingParts.add(new AlgorithmBuildingPart(walls.get(i).getStart().getX(),walls.get(i).getStart().getY(),
					walls.get(i).getEnd().getX(),walls.get(i).getEnd().getY(),"wall",nodeLabel++,nodeLabel++));
		}

		for(int i=0;i<doors.size();i++){
			mAlgBuildingParts.add(new AlgorithmBuildingPart(doors.get(i).getStart().getX(),doors.get(i).getStart().getY(),
					doors.get(i).getEnd().getX(),doors.get(i).getEnd().getY(),"door",nodeLabel++,nodeLabel++));
		}
	}

	private void setAlgorithmParts(){

		for(int i=0;i<mAlgBuildingParts.size();i++){

			if(!mCoordinates.contains(new Coordinates(mAlgBuildingParts.get(i).startX,mAlgBuildingParts.get(i).startY))){
				mCoordinates.add(new Coordinates(mAlgBuildingParts.get(i).startX,mAlgBuildingParts.get(i).startY));
			}
			if(!mCoordinates.contains(new Coordinates(mAlgBuildingParts.get(i).endX,mAlgBuildingParts.get(i).endY))){
				mCoordinates.add(new Coordinates(mAlgBuildingParts.get(i).endX,mAlgBuildingParts.get(i).endY));
			}

		}

		for(int i=0;i<=mCoordinates.size();i++){

			//System.out.println("Coordinates " + i + ":" + mCoordinates.get(i).getX() + " " + mCoordinates.get(i).getY());
			nodesList.add(new Node(String.valueOf(i)));

		}

		for(int i=0;i<mAlgBuildingParts.size();i++){
			for(int j=0;j<mCoordinates.size();j++){
				if(mAlgBuildingParts.get(i).getStart().equals(mCoordinates.get(j))){
					for(int k=0;k<mCoordinates.size();k++){
						if(j!=k){
							if(mAlgBuildingParts.get(i).getEnd().equals(mCoordinates.get(k))){
								mEdges.add(new Edge(j+1,k+1));

							}
						}
					}
				}
			}
		}

		for(int i=0;i<mEdges.size();i++){

			//System.out.println("Edge " + i + ":" + mEdges.get(i).getX() + " " + mEdges.get(i).getY());

		}

		/*
		edges.add(new Edge(0,1));
		edges.add(new Edge(1,2));
		edges.add(new Edge(2,3));
		edges.add(new Edge(3,4));
		edges.add(new Edge(4,5));
		edges.add(new Edge(5,0));
		edges.add(new Edge(6,0));
		edges.add(new Edge(6,7));
		edges.add(new Edge(7,3));
		//edges.add(new Edge(8,9));
		//edges.add(new Edge(9,0));

		//System.out.println(edges.size()*2);
		/*
		Node[] nodes = new Node[edges.size()*2];
		int counter = 0;
		for(int i=0;i<edges.size();i++){
			nodes[counter++] = nodesList.get(edges.get(i).getX());
			//System.out.print(nodes[counter-1].label + " ");
			nodes[counter++] = nodesList.get(edges.get(i).getY());
			/////System.out.print(nodes[counter-1].label + " ");
		}

		/*System.out.println(nodes.length);

		for(int i=0;i<nodes.length;i++){
			System.out.print(nodes[i].label + " ");
		}*/

		nodes = new Node[mEdges.size()*2];
		//System.out.println(mEdges.size()*2);
		int counter = 0;
		for(int i=0;i<mEdges.size();i++){
			//System.out.println(mEdges.get(i).getX());
			nodes[counter++] = nodesList.get(mEdges.get(i).getX());
			//System.out.print(nodes[counter-1].label + " ");
			nodes[counter++] = nodesList.get(mEdges.get(i).getY());
			/////System.out.print(nodes[counter-1].label + " ");
		}
	}

	private void algorithm(){

		List<Graph> cycles = GraphCycleFinder.GetCycles(new Graph(nodes));


		if (cycles.isEmpty())
		{
			System.out.println("Nu a fost gasit nici un ciclu");
		}
		else
		{
			for (int i = 0; i < cycles.size(); i++)
			{
				cycle.add(cycles.get(i).PrintGraph());
			}
		}

		for(int i=0;i<cycle.size();i++){

			List<Graph> auxGraph =  GraphCycleFinder.GetCycles(new Graph(cycle.get(i)));
			if(auxGraph.isEmpty()){
				finalGraphs.add(cycle.get(i));
			}else{
				for(int j=0;j<cycle.get(i).length;j++){
					if(cycle.get(i)[j]!=null);
						//System.out.print(cycle.get(i)[j].label + ",");
				}
				//System.out.println("");
			}
		}

		List<Node[]> rooms = new ArrayList<Node[]>();
		for(int i=0;i<finalGraphs.size();i++){
			for(int j=0;j<finalGraphs.get(i).length;j++){
				//System.out.print(finalGraphs.get(i)[j].label + ",");

			}
			//System.out.println("");
			rooms.add(finalGraphs.get(i));
		}

		for(int i=1;i<rooms.size();i++){
			for(int j=0;j<rooms.get(i).length;j++){
				//System.out.print(rooms.get(i)[j].label + ",");
			}
			//System.out.println("");
		}

		int roomName = 1;


		List<Coordinates> corners;
		for(int i=0;i<rooms.size();i++){
			corners = new ArrayList<Coordinates>();
			roomObject = new ArrayList<BuildingPart>();
			for(int j=0;j<rooms.get(i).length-1;j++){

				//System.out.print("Node:" + rooms.get(i)[j].label + " ->>" + rooms.get(i)[j+1].label + " ");
				//System.out.print(mCoordinates.get(Integer.parseInt(rooms.get(i)[j].label)-1).getX() + " " + mCoordinates.get(Integer.parseInt(rooms.get(i)[j].label)-1).getY() + "->");
				corners.add(mCoordinates.get(Integer.parseInt(rooms.get(i)[j].label)-1));
				for(int k=0;k<mAlgBuildingParts.size();k++){
						if((mAlgBuildingParts.get(k).getStart().equals(mCoordinates.get(Integer.parseInt(rooms.get(i)[j].label)-1)) && mAlgBuildingParts.get(k).getEnd().equals(mCoordinates.get(Integer.parseInt(rooms.get(i)[j+1].label)-1))) || (mAlgBuildingParts.get(k).getStart().equals(mCoordinates.get(Integer.parseInt(rooms.get(i)[j+1].label)-1)) && mAlgBuildingParts.get(k).getEnd().equals(mCoordinates.get(Integer.parseInt(rooms.get(i)[j].label)-1)))){
							//System.out.print("Coord:" + mCoordinates.get(Integer.parseInt(rooms.get(i)[j].label)-1).getX()+"|"+ mCoordinates.get(Integer.parseInt(rooms.get(i)[j].label)-1).getY() + "->" + mCoordinates.get(Integer.parseInt(rooms.get(i)[j+1].label)-1).getX() + "|"+ mCoordinates.get(Integer.parseInt(rooms.get(i)[j+1].label)-1).getY() + " " + mAlgBuildingParts.get(k).getLabel() + " ") ;
								String bName = mAlgBuildingParts.get(k).label;
							 	bp = new BuildingPart(mCoordinates.get(Integer.parseInt(rooms.get(i)[j].label)-1),mCoordinates.get(Integer.parseInt(rooms.get(i)[j+1].label)-1)){

								@Override
								public boolean equals(Object o) {
									// TODO Auto-generated method stud
								    BuildingPart b = (BuildingPart)o;
								    if(bp.getStart().getX()!=b.getStart().getX())
								    	return false;
								    if(bp.getStart().getY()!=b.getStart().getY())
								    	return false;
								    if(bp.getEnd().getX()!=b.getEnd().getX())
								    	return false;
								    if(bp.getEnd().getY()!=b.getEnd().getY())
								    	return false;
									return true;
								}

								@Override
								public String toString() {
									// TODO Auto-generated method stub
									return bName;
								}
							};

							roomObject.add(bp);
						}
				}

			}



			//System.out.print("Node:" + rooms.get(i)[rooms.get(i).length-1].label + " ->>" + rooms.get(i)[0].label + " ");
			corners.add(mCoordinates.get(Integer.parseInt(rooms.get(i)[rooms.get(i).length-1].label)-1));
			for(int k=0;k<mAlgBuildingParts.size();k++){
				if((mAlgBuildingParts.get(k).getStart().equals(mCoordinates.get(Integer.parseInt(rooms.get(i)[rooms.get(i).length-1].label)-1)) && mAlgBuildingParts.get(k).getEnd().equals(mCoordinates.get(Integer.parseInt(rooms.get(i)[0].label)-1))) || (mAlgBuildingParts.get(k).getStart().equals(mCoordinates.get(Integer.parseInt(rooms.get(i)[0].label)-1)) && mAlgBuildingParts.get(k).getEnd().equals(mCoordinates.get(Integer.parseInt(rooms.get(i)[rooms.get(i).length-1].label)-1)))){
					//System.out.print("Coord:" + mCoordinates.get(Integer.parseInt(rooms.get(i)[rooms.get(i).length-1].label)-1).getX()+"|"+ mCoordinates.get(Integer.parseInt(rooms.get(i)[rooms.get(i).length-1].label)-1).getY() + "->" + mCoordinates.get(Integer.parseInt(rooms.get(i)[0].label)-1).getX() + "|"+ mCoordinates.get(Integer.parseInt(rooms.get(i)[0].label)-1).getY() + " " + mAlgBuildingParts.get(k).getLabel() + " ");
					String bName = mAlgBuildingParts.get(k).label;

					bp = new BuildingPart(mCoordinates.get(Integer.parseInt(rooms.get(i)[rooms.get(i).length-1].label)-1),mCoordinates.get(Integer.parseInt(rooms.get(i)[0].label)-1)){

						@Override
						public boolean equals(Object o) {
							// TODO Auto-generated method stub
							 BuildingPart b = (BuildingPart)o;
							    if(bp.getStart().getX()!=b.getStart().getX())
							    	return false;
							    if(bp.getStart().getY()!=b.getStart().getY())
							    	return false;
							    if(bp.getEnd().getX()!=b.getEnd().getX())
							    	return false;
							    if(bp.getEnd().getY()!=b.getEnd().getY())
							    	return false;
								return true;
						}

						@Override
						public String toString() {
							// TODO Auto-generated method stub
							return bName;
						}


					};

					roomObject.add(bp);
				}
			}
			Room r = new Room(String.valueOf(roomName),"etaj 1");;
			r.setParts(roomObject);
			r.setCorners(corners);
			areas.add((float) r.area());
			roomName++;
			roomList.add(r);
			//System.out.println("");
			//System.out.println("Corners:" + r.getCorners().size());
			//corners.clear();

			//System.out.println("");
		}
		/*
		for(int i=0;i<roomList.size();i++){
			System.gc();
			System.out.println("Room:" + roomList.get(i).getName() + "=>" + roomList.get(i).getCorners().size() + " " + roomList.get(i).area());
			System.out.println("Parts:" + roomList.get(i).getParts().size());
			areas.add((float) roomList.get(i).area());
			for(int j=0;j<roomList.get(i).getCorners().size();j++){
				System.out.print(roomList.get(i).getCorners().get(j).getX() + "-" + roomList.get(i).getCorners().get(j).getY() + " ==>>" );
			}
			System.out.println("");
		}
		*/

		float temp;
		Node[] temp_graph;
		Room temp_room;
		for(int i=0;i<areas.size()-1;i++)
			for(int j=i+1; j<areas.size();j++)
				if(areas.get(i)>areas.get(j))
				{
					temp=areas.get(i);
					areas.set(i, areas.get(j));
					areas.set(j, temp);
					temp_graph=finalGraphs.get(i);
					finalGraphs.set(i, finalGraphs.get(j));
					finalGraphs.set(j, temp_graph);
					temp_room = roomList.get(i);
					roomList.set(i, roomList.get(j));
					roomList.set(j, temp_room);
				}
		//System.out.println();
		//for(int i=0;i<areas.size();i++)
			//System.out.print(areas.get(i) + " ");
		//}
		//System.out.println("\n\nCamere finale:");


		for(int i=0;i<areas.size()-1;i++){
			boolean ok=true;
			for(int j=i+1; j<areas.size();j++)
				for(int k=j+1;k<areas.size();k++)
					if(areas.get(k)==(areas.get(i)+areas.get(j)))
					{
						//System.out.println("DA");
						int matchNo=0;
						for(int x=0;x<finalGraphs.get(i).length;x++){
							//System.out.println(finalGraphs.get(i)[x].label);
								for(int y=0;y<finalGraphs.get(k).length;y++)
									if(finalGraphs.get(i)[x].label.equals(finalGraphs.get(k)[y].label)){
										matchNo++;
										//System.out.print(" " + finalGraphs.get(i)[x].label);
										break;
									}
						}
						//System.out.println(matchNo);
						if(matchNo>=4){
							matchNo=0;
							for(int x=0;x<finalGraphs.get(j).length;x++)
									for(int y=0;y<finalGraphs.get(k).length;y++)
										if(finalGraphs.get(j)[x].label.equals(finalGraphs.get(k)[y].label)){
											matchNo++;
											break;
										}
							if(matchNo<4){
								//roomsF.add(finalGraphs.get(k));
								roomsCount.add(k);
								ok=false;
							}
							else{

								//roomsF.add(finalGraphs.get(i));
								roomsCount.add(i);
								roomsCount.add(k*(-1));
								ok=false;
							}
						}
					}
					else
						roomsCount.add(k);

				if(ok)
					//roomsF.add(finalGraphs.get(i));
					roomsCount.add(i);
				}
	}

	private void setRooms(){
		for(int i=0;i<finalGraphs.size();i++)
			if(roomsCount.contains(i) && !roomsCount.contains(i*-1) || i==0 ){
				//System.out.println("");
				roomList.get(i).setName("c" + String.valueOf(i+1));
				finalRooms.add(roomList.get(i));
			}

			//System.out.println(finalRooms.size());
	}

	public List<Room> getRoomList(){
		return finalRooms;
	}

	public void getStairsMatch(){
		/*
		Scara primaScara = new Scara(new Punct(5,10), new Punct(1,5));
        Scara aDouaScara = new Scara(new Punct(3,6), new Punct(5,11.5f));
        primaScara.getOrigine().displayPunct();
        aDouaScara.getOrigine().displayPunct();
        System.out.println(primaScara.sePotriveste(aDouaScara));
        */
        for(int i=0;i<stairs.size();i++){
        	for(int j=0;j<stairs.size();j++){
        		if(i!=j){
        			Scara s1 = new Scara(stairs.get(i).getStart(),stairs.get(i).getEnd());
        			Scara s2 = new Scara(stairs.get(j).getStart(),stairs.get(j).getEnd());
        			if(s1.sePotriveste(s2)){
        				System.out.println("Scara " + (i+1) + " si Scara " + (j+1) + " sunt conectate");
        			}
        		}
        	}
        }
	}


}
