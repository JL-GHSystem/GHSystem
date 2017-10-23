package com.server.iDao;

import java.util.ArrayList;

import com.common.enums.Pagination;
import com.server.model.FenceModel;
import com.server.model.FenceNodeModel;

public interface IFenceDao {

	public ArrayList<FenceModel> select(Pagination page);

	public FenceModel selectByNode(FenceModel fenceModel);
	public FenceModel select(FenceModel fenceModel);

	public boolean insert(FenceModel fenceModel);

	public boolean insert(String o_FENCEID, ArrayList<FenceNodeModel> f_FENCENODES);

	public boolean deleteByNode(String o_FENCEID);

	public boolean delete(String o_FENCEID);

	public boolean update(FenceModel fenceModel);

}
