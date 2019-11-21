package cn.xq.boot.generator.service.impl;

import cn.xq.boot.generator.domain.GenTableColumn;
import cn.xq.boot.generator.service.IGenTableColumnService;

import java.util.List;

/**
 * 功能描述
 * 业务字段 服务层实现
 * @author xieqiong
 * @date 2019/11/20
 */
public class GenTableColumnServiceImpl implements IGenTableColumnService
{
    /**
     * 查询业务字段列表
     *
     * @param genTableColumn 业务字段信息
     * @return 业务字段集合
     */
    @Override
    public List<GenTableColumn> selectGenTableColumnListByTableId(GenTableColumn genTableColumn) {
        return null;
    }

    /**
     * 新增业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @Override
    public int insertGenTableColumn(GenTableColumn genTableColumn) {
        return 0;
    }

    /**
     * 修改业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @Override
    public int updateGenTableColumn(GenTableColumn genTableColumn) {
        return 0;
    }

    /**
     * 删除业务字段信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGenTableColumnByIds(String ids) {
        return 0;
    }
}
