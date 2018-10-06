import java.util.ArrayList;

public class Node
{

	private Node parent;
	private ThreeDigitNumber number;
	private ArrayList<Node> children = new ArrayList<Node>();
	private int id;
	public Node(ThreeDigitNumber start)
	{
		this.number = start;
	}
	public Node getParent()
	{
		return parent;
	}
	public void setParent(Node parent)
	{
		this.parent = parent;
	}
	public ThreeDigitNumber getNumber()
	{
		return number;
	}
	public void setNumber(ThreeDigitNumber number)
	{
		this.number = number;
	}
	public ArrayList<Node> getChildren()
	{
		return children;
	}
	public void setChildren(ArrayList<Node> children)
	{
		this.children = children;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
