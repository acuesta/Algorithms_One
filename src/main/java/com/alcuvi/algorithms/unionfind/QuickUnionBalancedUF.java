package com.alcuvi.algorithms.unionfind;

public class QuickUnionBalancedUF implements UnionFind {

	private int[] id;
	private int[] sz;

	public QuickUnionBalancedUF(int N) {
		id = new int[N];
		sz = new int[N];

		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}

	private int root(int i) {
		while (i != id[i]) {
			id[i] = id[id[i]]; // flatten tree -> Every node in path points to
								// its grandparent
			i = id[i];
		}
		return i;
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if (i == j)
			return;

		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];

		} else {
			id[j] = i;
			sz[i] += sz[j];
		}
	}
	
	public void print(){
		System.out.print("ARRAY: ");
		for (int i = 0; i < id.length; i++) {
			System.out.print(id[i] + " ");
			id[i] = i;
		}		
		System.out.println();
		System.out.print("SIZE: ");
		for (int i = 0; i < sz.length; i++) {
			System.out.print(sz[i] + " ");
			sz[i] = i;
		}
	}
}