package in.fireye.bbd.server.service;

import in.fireye.bbd.server.dto.BbdFeeTypeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IFeeTypeService {

  /**
   * 创建费用类型
   *
   * @param feeTypeDto 费用类型
   */
  BbdFeeTypeDto createFeeType(BbdFeeTypeDto feeTypeDto);

  /**
   * 更新费用类型
   *
   * @param feeTypeDto 费用类型
   */
  void updateFeeType(BbdFeeTypeDto feeTypeDto);

  /**
   * 删除费用类型
   *
   * @param feeTypeId 费用类型ID
   */
  void deleteFeeType(Integer feeTypeId);

  /**
   * 获取费用类型
   *
   * @param feeTypeId 费用类型ID
   * @return 费用类型
   */
  BbdFeeTypeDto getFeeType(Integer feeTypeId);

  /**
   * 获取所有费用类型
   *
   * @return 所有费用类型
   */
  List<BbdFeeTypeDto> getAllFeeType();

  /**
   * 根据父级ID获取费用类型
   *
   * @param parentId 父级ID
   * @return 获取子级费用类型仅下级
   */
  List<BbdFeeTypeDto> getFeeTypeByParentId(Integer parentId);

  /**
   * 根据父级ID获取费用类型
   *
   * @param parentId 父级ID
   * @return 获取子级费用类型直至叶子节点
   */
  List<BbdFeeTypeDto> getFeeTypeTreeByParentId(Integer parentId);

  /**
   * 根据ID获取费用类型树
   *
   * @param feeTypeId ID
   * @return 获取费用类型树
   */
  List<BbdFeeTypeDto> getFeeTypeTreeById(Integer feeTypeId);

  /**
   * 根据ID获取费用类型树名称
   *
   * @param feeTypeId ID
   * @return 获取费用类型树名称
   */
  String getFeeTypeTreeNameById(Integer feeTypeId);


}
