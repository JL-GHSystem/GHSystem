package com.server.iDao;

import java.util.ArrayList;

import com.common.enums.Pagination;
import com.server.model.LineModel;

public interface ILineDao {

	public ArrayList<LineModel> select(Pagination page);

}
