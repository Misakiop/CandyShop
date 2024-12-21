package cn.lmu.candy.mapper;

import cn.lmu.candy.domain.Candys;

public class SelectProvider {
    public String  findCandysWhere (Candys candys) {
        StringBuilder sql = new StringBuilder("select * from candys where 1=1");

        if (candys.getName() != null && !candys.getName().isEmpty()) {
            sql.append(" and name like '%" + candys.getName() + "%' ");
        }
        if (candys.getCategory() != null && candys.getCategory().getId() != null) {
            sql.append(" and category = '" + candys.getCategory().getId() + "' ");
        }

        sql.append(" order by id desc");
        return sql.toString();
    }

    public String findCandysWhereUser(Candys candys){
        StringBuilder sql = new StringBuilder("select * from candys where 1=1 and state > 0");
        if (candys.getId() != null){
            sql.append("and id='"+candys.getId()+"' ");
        }
        if (candys.getName() != null && !candys.getName().equals("")){
            sql.append(" and name like '%"+candys.getName()+"%'");
        }
        if (candys.getCategory()!= null && candys.getCategory().getId()!=null){
            sql.append(" and category='"+candys.getCategory().getId()+"' ");
        }
        sql.append(" order by id desc");
        return sql.toString();
    }
}
