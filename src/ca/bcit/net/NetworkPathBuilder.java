package ca.bcit.net;

import ca.bcit.graph.PathBuilder;

import java.util.ArrayList;

public class NetworkPathBuilder extends PathBuilder<NetworkNode, NetworkPath, Network> {
	private ArrayList<NetworkNode> path;
	private int length;
	
	@Override
	public void init() {
		path = new ArrayList<>();
	}

	@Override
	public boolean contains(NetworkNode node) {
		return path.contains(node);
	}

	@Override
	public void addNode(NetworkNode node) {
		path.add(node);
		if (path.size() > 1)
			length += getGraph().getLink(node, path.get(path.size() - 2)).getLength();
	}

	@Override
	public void removeTail() {
		NetworkNode node = path.remove(path.size() - 1);
		if (path.size() > 0)
			length -= getGraph().getLink(node, path.get(path.size() - 1)).getLength();
	}

	@Override
	public NetworkPath getPath() {
		return new NetworkPath(path.toArray(new NetworkNode[path.size()]), length);
	}
}
