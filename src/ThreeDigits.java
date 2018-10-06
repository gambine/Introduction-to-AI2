import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ThreeDigits
{
	 static ThreeDigitNumber start;
	 static ThreeDigitNumber goal;
	 static final int SEARCHLIMIT =1000;
	 
	 Queue<Node> fringe=new LinkedList<Node>();
	 static Queue<Node> expanded=new LinkedList<Node>();
	 static Queue<Node> tmpexpanded=new LinkedList<Node>();
	 static Stack<Node> path=new Stack<Node>();
	 static ArrayList<String> forbidden = new ArrayList<String>(); 
	 ArrayList<String> children = new ArrayList<String>(); 
	 Queue<Node> greedyfringe;
	 
	 public void BFS(Node node)
	 {
		 int stratergy=1;
		 Node currentNode=null;
		
			 fringe.add(node);
		
		
		 
		 while(expanded.size()<SEARCHLIMIT)
		 {
			 int flag=0;
			
			if (fringe.size()==0)
				{
					return;
				}
				   
				currentNode = fringe.remove();
				if(!expanded.isEmpty())
				{
					for (Node n:expanded)
					{
						if(currentNode.getNumber().getNumber().equals(n.getNumber().getNumber())&&
								Arrays.equals(currentNode.getNumber().arr,n.getNumber().arr)
								||forbidden.contains(currentNode.getNumber().getNumber()))
						{
							//System.out.println(currentNodeentNode.getNumber().getNumber());
							//System.out.println(currentNodeentNode.getParent().getNumber().getNumber());
							//System.out.println(currentNodeentNode.getParent().getNumber().arr.toString());
							//System.out.println(n.getNumber().arr.toString());
							flag=-1;
							break;
						}
						
					}
				}
					if(flag==-1)
					{
						
					}
					else
					{
						expanded.add(currentNode);
					}
			
			 if(currentNode.getNumber().getNumber().equals(goal.getNumber()))
				{
					//System.out.println("Reached Goal");
					while(currentNode!=null)
					{
						path.push(currentNode);
						currentNode=currentNode.getParent();
					}
					//System.out.println("Path ");
					while (!path.isEmpty())
					{
						System.out.print(path.pop().getNumber().getNumber());
						if (path.size()!=0)
							System.out.print(",");
					}
					System.out.println();
				
					while(!expanded.isEmpty())
					{
							System.out.print(expanded.remove().getNumber().getNumber());
							if (!expanded.isEmpty())
								System.out.print(",");
							
					
						
					}	
					return;
					
				}
				if (currentNode.getNumber().arr[1]==false)	
				{
					
					int digit=1;
					
					if (currentNode.getNumber().firstDigit()>0)
					{
						
							digitGreater0(currentNode,digit,stratergy);	
					}
					
					if (currentNode.getNumber().firstDigit()<9)
					{
						digitLess9(currentNode,digit,stratergy);
						
					}
				}
				
				if (currentNode.getNumber().arr[2]==false)	
				{
					
					int digit=2;
					
					if (currentNode.getNumber().SecondDigit()>0)
					{
						
							digitGreater0(currentNode,digit,stratergy);	
					}
					
					if (currentNode.getNumber().SecondDigit()<9)
					{
						digitLess9(currentNode,digit,stratergy);
						
					}
				}
				if (currentNode.getNumber().arr[3]==false)	
				{
					
					int digit=3;
					
					if (currentNode.getNumber().thirdDigit()>0)
					{
						
							digitGreater0(currentNode,digit,stratergy);	
					}
					
					if (currentNode.getNumber().thirdDigit()<9)
					{
						digitLess9(currentNode,digit,stratergy);
						
					}
				}
	 }
 }
	
	public boolean DFS(Node node)
	 {
		if(expanded.size()==SEARCHLIMIT)
		{
			//System.out.println("Depth Limit Reached");
			return false;
		}
		if(!expanded.isEmpty())
		{
			for (Node n:expanded)
			{
				if(node.getNumber().getNumber().equals(n.getNumber().getNumber())&&
						Arrays.equals(node.getNumber().arr,n.getNumber().arr)
								||forbidden.contains(node.getNumber().getNumber()))
				{
					
					return false;
				}
				
						
			}
			
		}
		expanded.add(node);
		
		if(node.getNumber().getNumber().equals(goal.getNumber()))
		{
			//System.out.println("Goaal reached");
			path.push(node);
			return true;
		}
		else
		{
			//System.out.println("Entered");
			if(calc(node))
			{
				return true;
			}
			else
			{
				
				return false;
			}
			
				
			
		}
	 }
	public boolean IDS(Node node)
	{
		boolean result = false;
		for (int limit=0;limit<10000;limit++)
		{
			result = DLS(node,limit);
			
			tmpexpanded.clear();
			if (result)
				return result;
			
			if (expanded.size()==SEARCHLIMIT)
				return result;
		}
		return result;
	}
	public boolean DLS(Node node,int depth)
	 {
		if(expanded.size()==SEARCHLIMIT)
		{
			//System.out.println("Depth Limit Reached");
			return false;
		}
		if(depth>=0)
		{
			
			if(!expanded.isEmpty())
			{
				for (Node n:tmpexpanded)
				{
					
					if(node.getNumber().getNumber().equals(n.getNumber().getNumber())&&
							Arrays.equals(node.getNumber().arr,n.getNumber().arr)
									||node.getNumber().getNumber().equals(start.getNumber()))
					{
						
						return false;
					}
					
				}
				
			}
		
				tmpexpanded.add(node);
				expanded.add(node);
			
			
			
			if(node.getNumber().getNumber().equals(goal.getNumber()))
			{
				//System.out.println("Goaal reached");
				path.push(node);
				return true;
			}
			if(node.getNumber().arr[1]==false)
			{
				if(node.getNumber().firstDigit>0)
				{
					ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
					tmpNum.firstDigitIncDec(false);
					if(!forbidden.contains(tmpNum.getNumber()))
					{
						Node tmpNode=new Node(tmpNum);
						tmpNode.setParent(node);
						node.getChildren().add(tmpNode);
						if(DLS(tmpNode,depth-1))
						{
							path.push(node);
							return true;
						}
					}
				}
				if(node.getNumber().firstDigit<9)
				{
					ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
					tmpNum.firstDigitIncDec(true);
					
					if(!forbidden.contains(tmpNum.getNumber()))
					{
						Node tmpNode=new Node(tmpNum);
						tmpNode.setParent(node);
						node.getChildren().add(tmpNode);
						if(DLS(tmpNode,depth-1))
						{
							path.push(node);
							return true;
						}
					}
				}
			}
			if(node.getNumber().arr[2]==false)
			{
				if(node.getNumber().secondDigit>0)
				{
					ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
					tmpNum.secondDigitIncDec(false);
					if(!forbidden.contains(tmpNum.getNumber()))
					{
						Node tmpNode=new Node(tmpNum);
						tmpNode.setParent(node);
						node.getChildren().add(tmpNode);
						if(DLS(tmpNode,depth-1))
						{
							path.push(node);
							return true;
						}
					}
				}
				if(node.getNumber().secondDigit<9)
				{
					ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
					tmpNum.secondDigitIncDec(true);
					if(!forbidden.contains(tmpNum.getNumber()))
					{
						Node tmpNode=new Node(tmpNum);
						tmpNode.setParent(node);
						node.getChildren().add(tmpNode);
						if(DLS(tmpNode,depth-1))
						{
							path.push(node);
							return true;
						}
					}
				}
			}
			if(node.getNumber().arr[3]==false)
			{
				if(node.getNumber().thirdDigit>0)
				{
					ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
					tmpNum.thirdDigitIncDec(false);
					if(!forbidden.contains(tmpNum.getNumber()))
					{
						Node tmpNode=new Node(tmpNum);
						tmpNode.setParent(node);
						node.getChildren().add(tmpNode);
						if(DLS(tmpNode,depth-1))
						{
							path.push(node);
							return true;
						}
					}
				}
				if(node.getNumber().thirdDigit<9)
				{
					ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
					tmpNum.thirdDigitIncDec(true);
					if(!forbidden.contains(tmpNum.getNumber()))
					{
						Node tmpNode=new Node(tmpNum);
						tmpNode.setParent(node);
						node.getChildren().add(tmpNode);
						if(DLS(tmpNode,depth-1))
						{
							path.push(node);
							return true;
						}
					}
				}
			}
			
		}
		return false;
	 }
	public boolean calc(Node node)
	{
		if(node.getNumber().arr[1]==false)
		{
			if(node.getNumber().firstDigit>0)
			{
				ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
				tmpNum.firstDigitIncDec(false);
				Node tmpNode=new Node(tmpNum);
				if(DFS(tmpNode))
				{
					path.push(node);
					return true;
				}
				
				
				
			}
			if(node.getNumber().firstDigit<9)
			{
				ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
				tmpNum.firstDigitIncDec(true);
				Node tmpNode=new Node(tmpNum);
				if(DFS(tmpNode))
				{
					path.push(node);
					return true;
				}
				
				
				
			}
		}
		if(node.getNumber().arr[2]==false)
		{
			if(node.getNumber().secondDigit>0)
			{
				ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
				tmpNum.secondDigitIncDec(false);
				Node tmpNode=new Node(tmpNum);
				if(DFS(tmpNode))
				{
					path.push(node);
					return true;
				}
				
				
				
			}
			if(node.getNumber().secondDigit<9)
			{
				ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
				tmpNum.secondDigitIncDec(true);
				Node tmpNode=new Node(tmpNum);
				if(DFS(tmpNode))
				{
					path.push(node);
					return true;
				}
				
				
				
			}
		}
		if(node.getNumber().arr[3]==false)
		{
			if(node.getNumber().thirdDigit>0)
			{
				ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
				tmpNum.thirdDigitIncDec(false);
				Node tmpNode=new Node(tmpNum);
				if(DFS(tmpNode))
				{
					path.push(node);
					return true;
				}
				
				
			}
			if(node.getNumber().thirdDigit<9)
			{
				ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
				tmpNum.thirdDigitIncDec(true);
				Node tmpNode=new Node(tmpNum);
				if(DFS(tmpNode))
				{
					path.push(node);
					return true;
				}
				
				
				
			}
		}
		return false;
	}
	public boolean calcDLS(Node node,int depth)
	{
		if(node.getNumber().arr[1]==false)
		{
			if(node.getNumber().firstDigit>0)
			{
				ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
				tmpNum.firstDigitIncDec(false);
				if(!forbidden.contains(tmpNum.getNumber()))
				{
					Node tmpNode=new Node(tmpNum);
					tmpNode.setParent(node);
					node.getChildren().add(tmpNode);
					if(DLS(tmpNode,depth-1))
					{
						path.push(node);
						return true;
					}
				}
				
				
				
				
			}
			if(node.getNumber().firstDigit<9)
			{
				ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
				tmpNum.firstDigitIncDec(true);
				
				if(!forbidden.contains(tmpNum.getNumber()))
				{
					Node tmpNode=new Node(tmpNum);
					tmpNode.setParent(node);
					node.getChildren().add(tmpNode);
					if(DLS(tmpNode,depth-1))
					{
						path.push(node);
						return true;
					}
				}
			}
		}
		if(node.getNumber().arr[2]==false)
		{
			if(node.getNumber().secondDigit>0)
			{
				ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
				tmpNum.secondDigitIncDec(false);
				if(!forbidden.contains(tmpNum.getNumber()))
				{
					Node tmpNode=new Node(tmpNum);
					tmpNode.setParent(node);
					node.getChildren().add(tmpNode);
					if(DLS(tmpNode,depth-1))
					{
						path.push(node);
						return true;
					}
				}
			}
			if(node.getNumber().secondDigit<9)
			{
				ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
				tmpNum.secondDigitIncDec(true);
				if(!forbidden.contains(tmpNum.getNumber()))
				{
					Node tmpNode=new Node(tmpNum);
					tmpNode.setParent(node);
					node.getChildren().add(tmpNode);
					if(DLS(tmpNode,depth-1))
					{
						path.push(node);
						return true;
					}
				}
			}
		}
		if(node.getNumber().arr[3]==false)
		{
			if(node.getNumber().thirdDigit>0)
			{
				ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
				tmpNum.thirdDigitIncDec(false);
				if(!forbidden.contains(tmpNum.getNumber()))
				{
					Node tmpNode=new Node(tmpNum);
					tmpNode.setParent(node);
					node.getChildren().add(tmpNode);
					if(DLS(tmpNode,depth-1))
					{
						path.push(node);
						return true;
					}
				}
			}
			if(node.getNumber().thirdDigit<9)
			{
				ThreeDigitNumber tmpNum = new ThreeDigitNumber(node);
				tmpNum.thirdDigitIncDec(true);
				if(!forbidden.contains(tmpNum.getNumber()))
				{
					Node tmpNode=new Node(tmpNum);
					tmpNode.setParent(node);
					node.getChildren().add(tmpNode);
					if(DLS(tmpNode,depth-1))
					{
						path.push(node);
						return true;
					}
				}
			}
		}
		return false;
	}
	 
	public void digitGreater0(Node currentNodeentNode,int digit,int stratergy)
	{
		ThreeDigitNumber Number=new ThreeDigitNumber(currentNodeentNode);
		Node newNum=new Node(Number);
		if(stratergy==1)
		{
			if(digit==1)
			{
				if (!forbidden.contains(newNum.getNumber().getNumber()))
				{
					Number.firstDigitIncDec(false);
					newNum.setParent(currentNodeentNode);
					currentNodeentNode.getChildren().add(newNum);
					fringe.add(newNum);
				}
			}
			if(digit==2)
			{
				if (!forbidden.contains(newNum.getNumber().getNumber()))
				{
					Number.secondDigitIncDec(false);
					newNum.setParent(currentNodeentNode);
					currentNodeentNode.getChildren().add(newNum);
					fringe.add(newNum);
				}
			}
			if(digit==3)
			{
				if (!forbidden.contains(newNum.getNumber().getNumber()))
				{
					Number.thirdDigitIncDec(false);
					newNum.setParent(currentNodeentNode);
					currentNodeentNode.getChildren().add(newNum);
					fringe.add(newNum);
				}
			}
		}
	}
	
	public void digitLess9(Node currentNodeentNode,int digit,int stratergy)
	{
		ThreeDigitNumber Number=new ThreeDigitNumber(currentNodeentNode);
		Node newNum=new Node(Number);
		if(stratergy==1)
		{
			if(digit==1)
			{
				if (!forbidden.contains(newNum.getNumber().getNumber()))
				{
					Number.firstDigitIncDec(true);
					newNum.setParent(currentNodeentNode);
					currentNodeentNode.getChildren().add(newNum);
					fringe.add(newNum);
				}
			}
			if(digit==2)
			{
				if (!forbidden.contains(newNum.getNumber().getNumber()))
				{
					Number.secondDigitIncDec(true);
					newNum.setParent(currentNodeentNode);
					currentNodeentNode.getChildren().add(newNum);
					fringe.add(newNum);
				}
			}
			if(digit==3)
			{
				if (!forbidden.contains(newNum.getNumber().getNumber()))
				{
					Number.thirdDigitIncDec(true);
					newNum.setParent(currentNodeentNode);
					currentNodeentNode.getChildren().add(newNum);
					fringe.add(newNum);
				}
			}
		}
		
		
		
	}
	
	/*public boolean digitLess9Dfs(Node currentNodeentNode,int digit,int stratergy)
	{
		ThreeDigitNumber Number=new ThreeDigitNumber(currentNodeentNode);
		Node newNum=new Node(Number);
		if(stratergy==2)
		{
			if(digit==1)
			{
				if (!forbidden.contains(newNum.getNumber().getNumber()))
				{	
					Number.firstDigitIncDec(true);
					newNum.setParent(currentNodeentNode);
					currentNodeentNode.getChildren().add(newNum);
					if (DFS(newNum))
					{
						path.push(currentNodeentNode);
						return true;
					}	
						
				}
			}
			if(digit==2)
			{
				if (!forbidden.contains(newNum.getNumber().getNumber()))
				{	
					Number.secondDigitIncDec(true);
					newNum.setParent(currentNodeentNode);
					currentNodeentNode.getChildren().add(newNum);
					if (DFS(newNum))
					{
						path.push(currentNodeentNode);
						return true;
					}	
					
				}
			}
			if(digit==3)
			{
				if (!forbidden.contains(newNum.getNumber().getNumber()))
				{	
					Number.thirdDigitIncDec(true);
					newNum.setParent(currentNodeentNode);
					currentNodeentNode.getChildren().add(newNum);
					if (DFS(newNum))
					{
						path.push(currentNodeentNode);
						return true;
					}	
						
				}
			}
			
			
		}
		return false;
	}*/
	private boolean GREEDY(Node node) 
	{
	
		int nodeId = 1;
		
		 fringe=new PriorityQueue<Node>(11,new Comparator<Node>(){
			@Override
			public int compare(Node node1,Node node2)
			{
				if (heuristic(node1)>heuristic(node2))
					return 1;
				else if (heuristic(node1)<heuristic(node2))
					return -1;
				else
				{
					if(node1.getId()>node2.getId())
					{
						return-1;
					}
					if(node1.getId()<node2.getId())
					{
						return 1;
					}
					return 0;
				}
			}
		});
		
		fringe.add(node);
		while(expanded.size()<SEARCHLIMIT)
		{	
			
				if (fringe.size()==0)
					return true;
				node = fringe.remove();
				
				if(!expanded.isEmpty())
				{
					for (Node n:expanded)
					{
						
						if(node.getNumber().getNumber().equals(n.getNumber().getNumber())&&
								Arrays.equals(node.getNumber().arr,n.getNumber().arr)||node.getNumber().getNumber().equals(start.getNumber()))
						{
							
							return false;
						}
						
					}
					
				}
			expanded.add(node);
			
			if (node.getNumber().getNumber().equals(goal.getNumber()))
			{
				while(node!=null)
				{
					path.push(node);
					node=node.getParent();
				}		
				return true;
			}
					
			if (node.getNumber().arr[1]==false)
			{
				
				if (node.getNumber().firstDigit()>0)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.firstDigitIncDec(false);
					if (!forbidden.contains(Number.getNumber()))
					{
						
						newNum.setId(nodeId++);	
						newNum.setParent(node);
						node.getChildren().add(newNum);
						fringe.add(newNum);
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
				if (node.getNumber().firstDigit()<9)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.firstDigitIncDec(true);
					if (!forbidden.contains(Number.getNumber()))
					{
						
						newNum.setId(nodeId++);	
						newNum.setParent(node);
						node.getChildren().add(newNum);
						fringe.add(newNum);
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
			}
			if (node.getNumber().arr[2]==false)
			{
				if (node.getNumber().SecondDigit()>0)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.secondDigitIncDec(false);
					if (!forbidden.contains(Number.getNumber()))
					{
						
						newNum.setId(nodeId++);	
						newNum.setParent(node);
						node.getChildren().add(newNum);
						fringe.add(newNum);
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
				if (node.getNumber().SecondDigit()<9)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.secondDigitIncDec(true);
					if (!forbidden.contains(Number.getNumber()))
					{
						
						newNum.setId(nodeId++);	
						newNum.setParent(node);
						node.getChildren().add(newNum);
						fringe.add(newNum);
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
			}
			if (node.getNumber().arr[3]==false)
			{
				
				if (node.getNumber().thirdDigit()>0)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.thirdDigitIncDec(false);
					if (!forbidden.contains(Number.getNumber()))
					{
						
						newNum.setId(nodeId++);	
						newNum.setParent(node);
						node.getChildren().add(newNum);
						fringe.add(newNum);
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
				if (node.getNumber().thirdDigit()<9)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.thirdDigitIncDec(true);
					if (!forbidden.contains(Number.getNumber()))
					{
						
						newNum.setId(nodeId++);
						newNum.setParent(node);
						node.getChildren().add(newNum);
						fringe.add(newNum);
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
			}
		}
		return false;
	}
	
	private boolean ASTAR(Node node) 
	{
	
		int nodeId = 1;
		
		 fringe=new PriorityQueue<Node>(11,new Comparator<Node>(){
			@Override
			public int compare(Node node1,Node node2)
			{
				if (fheuristic(node1)>fheuristic(node2))
					return 1;
				else if (fheuristic(node1)<fheuristic(node2))
					return -1;
				else
				{
					if(node1.getId()>node2.getId())
					{
						return-1;
					}
					if(node1.getId()<node2.getId())
					{
						return 1;
					}
					return 0;
				}
			}
		});
		
		fringe.add(node);
		while(expanded.size()<SEARCHLIMIT)
		{	
			
				if (fringe.size()==0)
					return true;
				node = fringe.remove();
				
				if(!expanded.isEmpty())
				{
					for (Node n:expanded)
					{
						
						if(node.getNumber().getNumber().equals(n.getNumber().getNumber())&&
								Arrays.equals(node.getNumber().arr,n.getNumber().arr)||node.getNumber().getNumber().equals(start.getNumber()))
						{
							
							return false;
						}
						
					}
					
				}
			expanded.add(node);
			
			if (node.getNumber().getNumber().equals(goal.getNumber()))
			{
				while(node!=null)
				{
					path.push(node);
					node=node.getParent();
				}		
				return true;
			}
					
			if (node.getNumber().arr[1]==false)
			{
				
				if (node.getNumber().firstDigit()>0)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.firstDigitIncDec(false);
					if (!forbidden.contains(Number.getNumber()))
					{
						
						newNum.setId(nodeId++);	
						newNum.setParent(node);
						node.getChildren().add(newNum);
						fringe.add(newNum);
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
				if (node.getNumber().firstDigit()<9)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.firstDigitIncDec(true);
					if (!forbidden.contains(Number.getNumber()))
					{
						
						newNum.setId(nodeId++);	
						newNum.setParent(node);
						node.getChildren().add(newNum);
						fringe.add(newNum);
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
			}
			if (node.getNumber().arr[2]==false)
			{
				if (node.getNumber().SecondDigit()>0)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.secondDigitIncDec(false);
					if (!forbidden.contains(Number.getNumber()))
					{
						
						newNum.setId(nodeId++);	
						newNum.setParent(node);
						node.getChildren().add(newNum);
						fringe.add(newNum);
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
				if (node.getNumber().SecondDigit()<9)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.secondDigitIncDec(true);
					if (!forbidden.contains(Number.getNumber()))
					{
						
						newNum.setId(nodeId++);	
						newNum.setParent(node);
						node.getChildren().add(newNum);
						fringe.add(newNum);
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
			}
			if (node.getNumber().arr[3]==false)
			{
				
				if (node.getNumber().thirdDigit()>0)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.thirdDigitIncDec(false);
					if (!forbidden.contains(Number.getNumber()))
					{
						
						newNum.setId(nodeId++);	
						newNum.setParent(node);
						node.getChildren().add(newNum);
						fringe.add(newNum);
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
				if (node.getNumber().thirdDigit()<9)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.thirdDigitIncDec(true);
					if (!forbidden.contains(Number.getNumber()))
					{
						
						newNum.setId(nodeId++);
						newNum.setParent(node);
						node.getChildren().add(newNum);
						fringe.add(newNum);
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
			}
		}
		return false;
	}
	private boolean HillClimbing(Node node) 
	{
	
		Node min = null;
		
		while(expanded.size()<SEARCHLIMIT)
		{	
				if(!expanded.isEmpty())
				{
					for (Node n:expanded)
					{
						
						if(node.getNumber().getNumber().equals(n.getNumber().getNumber())&&
								Arrays.equals(node.getNumber().arr,n.getNumber().arr)||node.getNumber().getNumber().equals(start.getNumber()))
						{
							
							return false;
						}
						
					}
					
				}
			expanded.add(node);
			
			if (node.getNumber().getNumber().equals(goal.getNumber()))
			{
				while(node!=null)
				{
					path.push(node);
					node=node.getParent();
				}		
				return true;
			}
					
			if (node.getNumber().arr[1]==false)
			{
				
				if (node.getNumber().firstDigit()>0)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.firstDigitIncDec(false);
					if (!forbidden.contains(Number.getNumber()))
					{
						newNum.setParent(node);
						node.getChildren().add(node);
						if(min!=null)
						{
							if (heuristic(newNum)<=heuristic(min))
								{
									min = newNum;
								}
						}
						else
						{
							min=newNum;
						}
						
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
				if (node.getNumber().firstDigit()<9)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.firstDigitIncDec(true);
					if (!forbidden.contains(Number.getNumber()))
					{
						newNum.setParent(node);
						node.getChildren().add(node);
						if(min!=null)
						{
							if (heuristic(newNum)<=heuristic(min))
								{
									min = newNum;
								}
						}
						else
						{
							min=newNum;
						}
						
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
			}
			if (node.getNumber().arr[2]==false)
			{
				if (node.getNumber().SecondDigit()>0)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.secondDigitIncDec(false);
					if (!forbidden.contains(Number.getNumber()))
					{
						newNum.setParent(node);
						node.getChildren().add(node);
						if(min!=null)
						{
							if (heuristic(newNum)<=heuristic(min))
								{
									min = newNum;
								}
						}
						else
						{
							min=newNum;
						}
						
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
				if (node.getNumber().SecondDigit()<9)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.secondDigitIncDec(true);
					if (!forbidden.contains(Number.getNumber()))
					{
						newNum.setParent(node);
						node.getChildren().add(node);
						if(min!=null)
						{
							if (heuristic(newNum)<=heuristic(min))
								{
									min = newNum;
								}
						}
						else
						{
							min=newNum;
						}
						
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
			}
			if (node.getNumber().arr[3]==false)
			{
				
				if (node.getNumber().thirdDigit()>0)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.thirdDigitIncDec(false);
					if (!forbidden.contains(Number.getNumber()))
					{
						newNum.setParent(node);
						node.getChildren().add(node);
						if(min!=null)
						{
							if (heuristic(newNum)<=heuristic(min))
								{
									min = newNum;
								}
						}
						else
						{
							min=newNum;
						}
						
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
				if (node.getNumber().thirdDigit()<9)
				{
					ThreeDigitNumber Number=new ThreeDigitNumber(node);
					Node newNum=new Node(Number);
					Number.thirdDigitIncDec(true);
					if (!forbidden.contains(Number.getNumber()))
					{
						newNum.setParent(node);
						node.getChildren().add(node);
						if(min!=null)
						{
							if (heuristic(newNum)<=heuristic(min))
								{
									min = newNum;
								}
						}
						else
						{
							min=newNum;
						}
						
						//System.out.println("Added TO fringe"+newNum.getNumber().getNumber());
					}
				}
			}
			if (min!=null && !(min.getNumber().getNumber().equals(node.getNumber().getNumber())))
			{
				if (heuristic(min)<=heuristic(node))
					node = min;
				else
					return false;
			}
			else
			{
				return false;
			}
		}
		return false;
	}
	public int heuristic(Node node)
	{
		int firstDigitDiff=Math.abs(node.getNumber().firstDigit()-goal.firstDigit());
		int	secondDigitDiff=Math.abs(node.getNumber().SecondDigit()-goal.SecondDigit());
		int	thirdDigitDiff=Math.abs(node.getNumber().thirdDigit()-goal.thirdDigit());
				
		return firstDigitDiff + secondDigitDiff + thirdDigitDiff;
	}
	public int depth(Node root)
	{
		int fdepth = 0;
		while(root!=null)
		{
			root=root.getParent();
			fdepth++;
			
		}
		return fdepth;
	}
	public int fheuristic(Node root)
	{
		int fval;
		fval=heuristic(root)+depth(root);
		//System.out.println(fval);
		return fval;
		
	}
	public void digitGreater0Greedy(Node node,int digit,int nodeId)
	{
		//System.out.println("greedy inc");
		
			if(digit==1)
			{
				
			}
			if(digit==2)
			{
				
			}
			if(digit==3)
			{
				
			}
	}
	
	public void digitLess9Greedy(Node node,int digit,int nodenum)
	{
		//System.out.println("greedy dec");
		ThreeDigitNumber Number=new ThreeDigitNumber(node);
		Node newNum=new Node(Number);
			if(digit==1)
			{
				
			}
			if(digit==2)
			{
				
			}
			if(digit==3)
			{
				
			}
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		/*start=new ThreeDigitNumber("345");
		goal=new ThreeDigitNumber("555");
		forbidden.add("455");
		forbidden.add("545");
		forbidden.add("554");
		System.out.println(forbidden.get(0));
		System.out.println(forbidden.get(1));
		System.out.println(forbidden.get(2));
		*/
		String everything = null;
		try(BufferedReader br = new BufferedReader(new FileReader(args[1]))) 
		{
			  StringBuilder sb = new StringBuilder();
			    String line = br.readLine();

			    while (line != null) {
			        sb.append(line);
			        line = br.readLine();
			    }
			     everything = sb.toString();
		}
		catch(IOException e)
		{
			
		}
		start=new ThreeDigitNumber(everything.substring(0,3));
		goal=new ThreeDigitNumber(everything.substring(3,6));
		//System.out.println(start.getNumber());
		//System.out.println(goal.getNumber());
		String e = null;
		if(everything.length()>6)
		{
			e=everything.substring(6,everything.length());
			String[] splited = e.split("\\,+");
			for (int i=0;i<splited.length;i++)
			{
				forbidden.add(splited[i]);
			}
		}
		
		
		
		
			//System.out.print("forbidden values are"+forbidden);
		
		ThreeDigits threeDigits = new ThreeDigits();
		String n=args[0];
		if(n.equals("B"))
		{
			
		TreeTraversal tree=new TreeTraversal(new Node(start));
		
		threeDigits.BFS(tree.root());
		}
		else if(n.equals("D"))
		{
			TreeTraversal tree=new TreeTraversal(new Node(start));
			if(threeDigits.DFS(tree.root())==true)
			{
				//System.out.println("THE SIZE IS"+expanded.size());
				//System.out.println("path");
				while (!path.isEmpty())
				{
					System.out.print(path.pop().getNumber().getNumber());
					if (path.size()!=0)
						System.out.print(",");
				}
				System.out.println();
				while(!expanded.isEmpty())
				{
					System.out.print(expanded.remove().getNumber().getNumber());
					if (!expanded.isEmpty())
						System.out.print(",");
				}
			}
			else
			{
				System.out.println("No Solution Found.");
				while(!expanded.isEmpty())
				{
					System.out.print(expanded.remove().getNumber().getNumber());
					if (!expanded.isEmpty())
						System.out.print(",");
				}
			}
			
		}
		else if(n.equals("I"))
		{
			TreeTraversal tree=new TreeTraversal(new Node(start));
			if(threeDigits.IDS(tree.root()))
			{
				while (!path.isEmpty())
				{
					System.out.print(path.pop().getNumber().getNumber());
					if (path.size()!=0)
						System.out.print(",");
				}
				System.out.println();
				while(!expanded.isEmpty())
				{
					System.out.print(expanded.remove().getNumber().getNumber());
					if (!expanded.isEmpty())
						System.out.print(",");
				}
			}
			else
			{
				System.out.println("No Solution Found.");
				while(!expanded.isEmpty())
				{
					System.out.print(expanded.remove().getNumber().getNumber());
					if (!expanded.isEmpty())
						System.out.print(",");
				}
			}
			
				
		}
		else if(n.equals("G"))
		{
			TreeTraversal tree=new TreeTraversal(new Node(start));
			if(threeDigits.GREEDY(tree.root()))
			{
				//System.out.print("yes");
				while (!path.isEmpty())
				{
					System.out.print(path.pop().getNumber().getNumber());
					if (path.size()!=0)
						System.out.print(",");
				}
				System.out.println();
				while(!expanded.isEmpty())
				{
					System.out.print(expanded.remove().getNumber().getNumber());
					if (!expanded.isEmpty())
						System.out.print(",");
				}
			}
			else
			{
				System.out.println("No Solution Found.");
				while(!expanded.isEmpty())
				{
					System.out.print(expanded.remove().getNumber().getNumber());
					if (!expanded.isEmpty())
						System.out.print(",");
				}
			}
		}
		else if(n.equals("A"))
		{
			TreeTraversal tree=new TreeTraversal(new Node(start));
			if(threeDigits.ASTAR(tree.root()))
			{
				//System.out.print("yes");
				while (!path.isEmpty())
				{
					System.out.print(path.pop().getNumber().getNumber());
					if (path.size()!=0)
						System.out.print(",");
				}
				System.out.println();
				while(!expanded.isEmpty())
				{
					System.out.print(expanded.remove().getNumber().getNumber());
					if (!expanded.isEmpty())
						System.out.print(",");
				}
			}
			else
			{
				System.out.println("No Solution Found.");
				while(!expanded.isEmpty())
				{
					System.out.print(expanded.remove().getNumber().getNumber());
					if (!expanded.isEmpty())
						System.out.print(",");
				}
			}
		}
		else if(n.equals("H"))
		{
			TreeTraversal tree=new TreeTraversal(new Node(start));
			if(threeDigits.HillClimbing(tree.root()))
			{
				//System.out.print("yes");
				while (!path.isEmpty())
				{
					System.out.print(path.pop().getNumber().getNumber());
					if (path.size()!=0)
						System.out.print(",");
				}
				System.out.println();
				while(!expanded.isEmpty())
				{
					System.out.print(expanded.remove().getNumber().getNumber());
					if (!expanded.isEmpty())
						System.out.print(",");
				}
			}
			else
			{
				System.out.println("No Solution Found.");
				while(!expanded.isEmpty())
				{
					System.out.print(expanded.remove().getNumber().getNumber());
					if (!expanded.isEmpty())
						System.out.print(",");
				}
			}
		}
		
		//System.out.println("hey");
	
		
		//ThreeDigits tree = new ThreeDigits();
			
		//TreeTraversal tree1=new TreeTraversal(new Node(start));
		//tree.BFS(tree1.root());
	}

	

}
