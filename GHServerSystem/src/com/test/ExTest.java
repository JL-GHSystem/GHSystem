package com.test;

import com.server.map.Map;
import com.server.support.DaoEx;

public class ExTest {
	public static void main(String[] args) {
		DaoEx ex = new DaoEx();
		System.out.println(2/3);
		System.out.println(
				ex.select(Map.FENCENODE_MAP + ".O_FENCEID", "O_FENCETYPE", "O_LONGITUDE", "O_LATITUDE", "O_RADIUS")
				.from(Map.FENCENODE_MAP)
				.leftJoin(Map.FENCE_MAP).on(Map.FENCENODE_MAP, "O_FENCEID", Map.FENCE_MAP)
				.where(Map.FENCE_MAP+".O_FENCEID = ?")
				.orderBy("O_POINTNO").end());
	}
}
