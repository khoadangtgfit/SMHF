/*
 * Copyright (C) 2008-2012 Dainippon Screen Mfg. Co., Ltd.
 * CONFIDENTIAL Proprietary to Dainippon Screen Mfg. Co., Ltd.
 * 
 * �{�v���O�����̒��쌠�͑���{�X�N���[������������ЂɋA��������̂ł���A
 * ���Ђ͂�����c�Ɣ閧�Ƃ��ĊǗ�������̂ł��B�]���A�{�v���O�����̑S�āA
 * �ꕔ�ɂ�����炸�A���̕����A�Еz���s�����Ƃ́A���Ђ̎��O�̏��ʂɂ��
 * �������Ȃ�����ł��ւ�������̂ł��B
 * 
 * The copyright of this program shall belong to
 * Dainippon Screen Mfg. Co., Ltd.("SCREEN") as a "work made for hire."
 * Also, SCREEN will treat this program as its trade secret. Accordingly,
 * no one is allowed to copy and/or distribute this program, as a whole or
 * in part, without obtaining SCREEN' prior permission to do so in writing.
 */

package jp.co.screen.smarthf.model;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import jp.co.screen.equios.ui.common.bean.table.AbstractTableModelEx;
import jp.co.screen.smarthf.common.resource.LangKey;
import jp.co.screen.smarthf.utils.CommonUtils;
import jp.co.screen.smarthf.utils.FileUtils;

/**
 * table model
 * 
 * @author syptn
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFTableModel extends AbstractTableModelEx {

  List<SmartHFDataModel> mResourceList = new ArrayList<SmartHFDataModel>();

  /**
   * Method description
   * 
   * @param inLstObj
   * @author syptn
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  public SmartHFTableModel() {
    super();
    init();
  }

  private void init() {
    loadSmartHFData();
  }

  public void refresh() {
    fireTableDataChanged();
  }

  public void setResourceList(List<SmartHFDataModel> inLstObj) {
    this.mResourceList = inLstObj;
  }

  /**
   * {@inheritDoc}
   */
  public int getRowCount() {
    // TODO Auto-generated method stub
    if (mResourceList == null)
      return 0;
    return mResourceList.size();
  }

  /**
   * {@inheritDoc}
   */
  public Object getValueAt(int rowIndex, int columnIndex) {

    if (!checkValuePosition(rowIndex, columnIndex)) {
      return null;
    }

    SmartHFDataModel obj = mResourceList.get(rowIndex);

    if (obj == null)
      return null;

    String colName = getColumnName(columnIndex);

    return convertToColumnValue(obj, colName);
  }

  private Object convertToColumnValue(SmartHFDataModel obj, String colName) {

    // compare with the resource of each column and return corresponding value
    if (colName.equals(LangKey.WORD_HFNAME)) {
      return obj.getSmartHFPropertyFileModel().getDisplayName();
    }
    if (colName.equals(LangKey.WORD_RULE)) {
      return obj.getSmartHFRulePropertyFileModel().getRule();
    }

    return null;
  }

  private void loadSmartHFData() {
    List<String> hfList = CommonUtils.getHotfolderNameList();
    for (int idx = 0; idx < hfList.size(); idx++) {
      SmartHFPropertyFileModel propertyFileModel =
          CommonUtils.readPropertyFile(Paths.get(FileUtils.getSmartHotfolderRootPath(), hfList.get(idx)).toString());
      SmartHFRulePropertyFileModel rulePropertyFileModel = CommonUtils
          .readRulePropertyFile(Paths.get(FileUtils.getSmartHotfolderRootPath(), hfList.get(idx)).toString());

      if (propertyFileModel != null && rulePropertyFileModel != null) {
        SmartHFDataModel dataModel = new SmartHFDataModel();
        dataModel.setSmartHFPropertyFileModel(propertyFileModel);
        dataModel.setSmartHFRulePropertyFileModel(rulePropertyFileModel);

        mResourceList.add(dataModel);
      }
    }
  }

  public void addSmartHFData(SmartHFDataModel inSmartHFData) {
    mResourceList.add(inSmartHFData);
    refresh();
  }

  public void removeSmartHFData(SmartHFDataModel inSmartHFData) {
    mResourceList.remove(inSmartHFData);
    refresh();
  }

  public SmartHFDataModel getSmartHFData(int inRowIdx) {
    SmartHFDataModel dataModel = null;
    if (inRowIdx < mResourceList.size()) {
      dataModel = mResourceList.get(inRowIdx);
    }

    return dataModel;
  }
  
  public SmartHFDataModel getRowValue(int index) {
    if (index < 0 || mResourceList == null) {
      return null;
    } else {
      if (mResourceList.size() <= index) {
        return null;
      } else {
        return mResourceList.get(index);
      }
    }
  }

}
