package com.server.iDao;

import java.util.ArrayList;

import com.common.enums.Pagination;
import com.server.model.LineModel;

public interface ILineDao {

	public ArrayList<LineModel> select(Pagination page, LineModel lineModel);

	public ArrayList<LineModel> selectAllInTable();

	public boolean insert(LineModel lineModel);

	public ArrayList<LineModel> selectByScheme(Pagination page);

	public boolean delete(String[] ids);

	public boolean update(LineModel lineModel);

}
