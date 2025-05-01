package in.fireye.bbd.server.service;

import in.fireye.bbd.commons.dto.Deleted;
import in.fireye.bbd.commons.dto.ResponseCode;
import in.fireye.bbd.commons.exception.BbdBusinessException;
import in.fireye.bbd.server.dto.BbdFeeTypeDto;
import in.fireye.bbd.server.entity.BbdFeeType;
import in.fireye.bbd.server.entity.BbdFeeTypeTree;
import in.fireye.bbd.server.repository.BbdFeeTypeRepository;
import in.fireye.bbd.server.repository.BbdFeeTypeTreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeeTypeService implements IFeeTypeService {

  private final BbdFeeTypeRepository feeTypeRepository;
  private final BbdFeeTypeTreeRepository feeTypeTreeRepository;


  @Override
  @Transactional(rollbackFor = Exception.class)
  public BbdFeeTypeDto createFeeType(BbdFeeTypeDto feeTypeDto) {

    BbdFeeType feeType = new BbdFeeType();
    feeType.setTypeName(feeTypeDto.getTypeName());
    // todo:sby 待登录完成后此处修改为获取当前登录用户id
    feeType.setCreateUser(0);
    feeType.setModifyUser(0);
    feeType.setDeleted(Deleted.NO.getCode());
    feeType.setParentId(feeTypeDto.getParentId());
    feeTypeRepository.saveAndFlush(feeType);

    List<BbdFeeTypeTree> parentTrees = feeTypeTreeRepository.findByDescendantId(feeType.getParentId());

    List<BbdFeeTypeTree> childrenTrees = parentTrees.stream().map(parentTree -> {
      BbdFeeTypeTree feeTypeTree = new BbdFeeTypeTree();
      feeTypeTree.setAncestorId(parentTree.getAncestorId());
      feeTypeTree.setDescendantId(feeType.getFeeTypeId());
      feeTypeTree.setDistance(parentTree.getDistance() + 1);
      return feeTypeTree;
    }).collect(Collectors.toList());

    childrenTrees.add(new BbdFeeTypeTree(feeType.getFeeTypeId(), feeType.getFeeTypeId(), 0));
    feeTypeTreeRepository.saveAll(childrenTrees);

    feeTypeDto.setFeeTypeId(feeType.getFeeTypeId());
    return feeTypeDto;
  }

  @Override
  public void updateFeeType(BbdFeeTypeDto feeTypeDto) {
    Optional<BbdFeeType> feeType = feeTypeRepository.findById(feeTypeDto.getFeeTypeId());
    BbdFeeType type = feeType.orElseThrow(() -> new BbdBusinessException(ResponseCode.PARAM_ERROR));
    type.setTypeName(feeTypeDto.getTypeName());
    type.setModifyTime(LocalDateTime.now());
    // todo:sby 待登录完成后此处修改为获取当前登录用户id
    type.setModifyUser(0);
    feeTypeRepository.save(type);
  }


  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteFeeType(Integer feeTypeId) {
    // todo:sby 待登录完成后此处修改为获取当前登录用户id
    feeTypeRepository.deleteFeeType(feeTypeId, LocalDateTime.now(), 0);
    feeTypeTreeRepository.deleteByDescendantId(feeTypeId);
  }

  @Override
  public BbdFeeTypeDto getFeeType(Integer feeTypeId) {
    Optional<BbdFeeType> feeType = feeTypeRepository.findById(feeTypeId);
    BbdFeeType type = feeType.orElseThrow(() -> new BbdBusinessException(ResponseCode.PARAM_ERROR));
    return new BbdFeeTypeDto(type);
  }

  @Override
  public List<BbdFeeTypeDto> getAllFeeType() {

    List<BbdFeeType> feeTypes = feeTypeRepository.findAllByDeleted(Deleted.NO.getCode());
    return feeTypes.stream().map(BbdFeeTypeDto::new).toList();
  }

  @Override
  public List<BbdFeeTypeDto> getFeeTypeByParentId(Integer parentId) {

    List<BbdFeeType> feeTypes = feeTypeRepository.getFeeTypeByParentId(parentId);
    return feeTypes.stream().map(BbdFeeTypeDto::new).toList();
  }

  @Override
  public List<BbdFeeTypeDto> getFeeTypeTreeByParentId(Integer parentId) {

    List<BbdFeeType> feeTypes = feeTypeRepository.getFeeTypeTreeByParentId(parentId);
    return feeTypes.stream().map(BbdFeeTypeDto::new).toList();
  }

  @Override
  public List<BbdFeeTypeDto> getFeeTypeTreeById(Integer feeTypeId) {
    List<BbdFeeType> feeTypes = feeTypeRepository.getFeeTypeTreeById(feeTypeId);
    return feeTypes.stream().map(BbdFeeTypeDto::new).toList();
  }
}
