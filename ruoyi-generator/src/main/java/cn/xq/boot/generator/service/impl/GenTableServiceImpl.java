package cn.xq.boot.generator.service.impl;

import cn.xq.boot.common.constant.GenConstants;
import cn.xq.boot.common.utils.StringUtils;
import cn.xq.boot.generator.domain.GenTable;
import cn.xq.boot.generator.mapper.GenTableColumnMapper;
import cn.xq.boot.generator.mapper.GenTableMapper;
import cn.xq.boot.generator.service.IGenTableService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 功能描述
 * 业务 服务层实现
 * @author xieqiong
 * @date 2019/11/20
 */
@Service
public class GenTableServiceImpl implements IGenTableService
{
    private static final Logger log = LoggerFactory.getLogger(GenTableServiceImpl.class);

    @Autowired
    private GenTableMapper genTableMapper;

    @Autowired
    private GenTableColumnMapper genTableColumnMapper;

    /**
     * 查询业务列表
     * @param genTable 业务信息
     * @return 业务集合
     */
    @Override
    public List<GenTable> selectGenTableList(GenTable genTable) {
        return genTableMapper.selectGenTableList(genTable);
    }

    /**
     * 查询据库列表
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    @Override
    public List<GenTable> selectDbTableList(GenTable genTable) {
        return null;
    }

    /**
     * 查询据库列表
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    @Override
    public List<GenTable> selectDbTableListByNames(String[] tableNames) {
        return null;
    }

    /**
     * 查询业务信息
     * @param id 业务ID
     * @return 业务信息
     */
    @Override
    public GenTable selectGenTableById(Long id) {
        GenTable genTable = genTableMapper.selectGenTableById(id);
        setTableFromOptions(genTable);
        return genTable;
    }

    /**
     * 修改业务
     * @param genTable 业务信息
     * @return 结果
     */
    @Override
    public void updateGenTable(GenTable genTable) {

    }

    /**
     * 删除业务信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public void deleteGenTableByIds(String ids) {

    }

    /**
     * 导入表结构
     * @param tableList 导入表列表
     * @param operName  操作人员
     */
    @Override
    public void importGenTable(List<GenTable> tableList, String operName) {

    }

    /**
     * 预览代码
     * @param tableId 表编号
     * @return 预览数据列表
     */
    @Override
    public Map<String, String> previewCode(Long tableId) {
        return null;
    }

    /**
     * 生成代码
     * @param tableName 表名称
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String tableName) {
        return new byte[0];
    }

    /**
     * 批量生成代码
     * @param tableNames 表数组
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String[] tableNames) {
        return new byte[0];
    }

    /**
     * 修改保存参数校验
     * @param genTable 业务信息
     */
    @Override
    public void validateEdit(GenTable genTable) {

    }
    /**
     * 设置代码生成其他选项值
     *
     * @param genTable 设置后的生成对象
     */
    public void setTableFromOptions(GenTable genTable){
        JSONObject paramsObj = JSONObject.parseObject(genTable.getOptions());
        if (StringUtils.isNotNull(paramsObj))
        {
            String treeCode = paramsObj.getString(GenConstants.TREE_CODE);
            String treeParentCode = paramsObj.getString(GenConstants.TREE_PARENT_CODE);
            String treeName = paramsObj.getString(GenConstants.TREE_NAME);
            genTable.setTreeCode(treeCode);
            genTable.setTreeParentCode(treeParentCode);
            genTable.setTreeName(treeName);
        }
    }
}
