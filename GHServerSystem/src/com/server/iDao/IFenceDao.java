package com.server.iDao;

import java.util.ArrayList;

import com.common.enums.Pagination;
import com.server.model.FenceModel;
import com.server.model.FenceNodeModel;

public interface IFenceDao {

	public ArrayList<FenceModel> select(Pagination page, String search);
	public ArrayList<FenceModel> selectByLine(Pagination page);
	public ArrayList<FenceModel> selectByLine(Pagination page, String o_LineId);
	public ArrayList<FenceModel> selectExceptLine(Pagination page, String o_LineId, String o_Fencename);

	public FenceModel selectByNode(FenceModel fenceModel);
	public FenceModel selectByLineDetail(FenceModel fenceModel);
	public FenceModel select(FenceModel fenceModel);

	public boolean insert(FenceModel fenceModel);

	public boolean insert(String o_FENCEID, ArrayList<FenceNodeModel> f_FENCENODES);
	
	public boolean insertByLine(FenceModel fenceModel);

	public boolean deleteByNode(String o_FENCEID);

	public boolean delete(String o_FENCEID);
	
	public boolean deleteByLine(String ids, String fid);

	public boolean update(FenceModel fenceModel);
	public boolean updateByLine(FenceModel fenceModel);
	


}
