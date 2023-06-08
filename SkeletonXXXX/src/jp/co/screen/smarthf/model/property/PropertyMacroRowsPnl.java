/*
 * Copyright (C) 2008-2012 Dainippon Screen Mfg. Co., Ltd.
 * CONFIDENTIAL Proprietary to Dainippon Screen Mfg. Co., Ltd.
 * 
 * 本プログラムの著作権は大日本スクリーン製造株式会社に帰属するものであり、
 * 同社はこれを営業秘密として管理するものです。従い、本プログラムの全て、
 * 一部にかかわらず、その複製、頒布を行うことは、同社の事前の書面による
 * 承諾がない限り固く禁じられるものです。
 * 
 * The copyright of this program shall belong to
 * Dainippon Screen Mfg. Co., Ltd.("SCREEN") as a "work made for hire."
 * Also, SCREEN will treat this program as its trade secret. Accordingly,
 * no one is allowed to copy and/or distribute this program, as a whole or
 * in part, without obtaining SCREEN' prior permission to do so in writing.
 */

package jp.co.screen.smarthf.model.property;

import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jp.co.screen.smarthf.common.Constants;
import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.properrty.SmartHFPropertyCenterPnl;
import jp.co.screen.smarthf.utils.CommonUtils;

/**
 * Class Description
 * 
 * @author kdang1
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class PropertyMacroRowsPnl extends JPanel {
  private SmartHFPropertyCenterPnl mParentContainer;
  private List<PropertyMacroRowPnl> mMacroRowPnlList;
  private List<String> mRuleEngineMacroList;

  /**
   * Constructor of PropertyMacroRowsPnl.java
   *
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  public PropertyMacroRowsPnl(SmartHFPropertyCenterPnl inParentContainer) {
    // TODO Auto-generated constructor stub
    this.mParentContainer = inParentContainer;
    mRuleEngineMacroList = null;
    mMacroRowPnlList = null;

    init();
  }

  public SmartHFPropertyCenterPnl getParentContainer() {
    return mParentContainer;
  }

  /**
   * Method description
   * 
   * @author kdang1
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */

  private void init() {
    // TODO Auto-generated method stub
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setAlignmentY(TOP_ALIGNMENT);

    mRuleEngineMacroList = CommonUtils.getRuleEngineMacroList();
    getMacroRowPnlList();

    for (int idx = 0; idx < mMacroRowPnlList.size(); idx++) {
      add(mMacroRowPnlList.get(idx));

      if (idx != mMacroRowPnlList.size() - 1) {
        add(mMacroRowPnlList.get(idx).getVerBottomGap());
      }
    }
  }

  public List<PropertyMacroRowPnl> getMacroRowPnlList() {
    if (mMacroRowPnlList == null) {
      mMacroRowPnlList = new LinkedList<PropertyMacroRowPnl>();

      if (!mParentContainer.getParentContainer().getParentContainer().isAddHotFolder()) {
        SmartHFDataModel dataModel = mParentContainer.getParentContainer().getParentContainer().getParentContainer()
            .getSmartHFMainContentPane().getSelectedRow();
        List<String> macroList = CommonUtils.getMacroList(dataModel.getSmartHFRulePropertyFileModel().getRule());
        List<String> sepList = CommonUtils.getSeparatorList(dataModel.getSmartHFRulePropertyFileModel().getRule());
        for (int idx = 0; idx < macroList.size(); idx++) {
          String separator = null;
          separator = sepList.get(idx + 1);
          mMacroRowPnlList.add(new PropertyMacroRowPnl(this, mRuleEngineMacroList, macroList.get(idx), separator));
        }
      } else {
        List<String> requiredMacroList = CommonUtils.getRuleEngineRequiredMacroList();
        if (requiredMacroList.size() > 0) {
          for (int idx = 0; idx < requiredMacroList.size(); idx++) {
            mMacroRowPnlList.add(new PropertyMacroRowPnl(this, mRuleEngineMacroList, requiredMacroList.get(idx),
                Constants.EMPTY_STRING));
          }
        } else {
          for (int idx = 0; idx < Constants.MAX_NEW_ROWS; idx++) {
            mMacroRowPnlList.add(
                new PropertyMacroRowPnl(this, mRuleEngineMacroList, Constants.EMPTY_STRING, Constants.EMPTY_STRING));
          }
        }
      }
    }

    if (mMacroRowPnlList != null && mMacroRowPnlList.size() > 0) {
      mMacroRowPnlList.get(mMacroRowPnlList.size() - 1).changeMacroBtn(false);
    }

    return mMacroRowPnlList;
  }

  public void addMacroRow(PropertyMacroRowPnl inNewMacroRow) {
    inNewMacroRow.changeMacroBtn(false);

    if (mMacroRowPnlList != null && mMacroRowPnlList.size() > 0) {
      mMacroRowPnlList.get(mMacroRowPnlList.size() - 1).changeMacroBtn(true);
      add(mMacroRowPnlList.get(mMacroRowPnlList.size() - 1).getVerBottomGap());
    }

    mMacroRowPnlList.add(inNewMacroRow);
    add(inNewMacroRow);

    revalidate();
    repaint();

    mParentContainer.getParentContainer().getPropertyBotomPnl().updateFileNameRule(getTopSepTf(), getMacroRowsPnl());
  }

  public String getRowsString() {
    String rowsString = new String();
    for (PropertyMacroRowPnl macroRow : mMacroRowPnlList) {
      rowsString += macroRow.getRowString();
    }

    return rowsString;
  }

  private PropertyMacroRowsPnl getMacroRowsPnl() {
    // TODO Auto-generated method stub
    return mParentContainer.getMacroRowsPnl();
  }

  private JTextField getTopSepTf() {
    // TODO Auto-generated method stub
    return mParentContainer.getmTopTf();
  }
  
  public void removeMacroRow(PropertyMacroRowPnl inMacroRow) {
    if (mMacroRowPnlList != null && mMacroRowPnlList.size() >  0) {
      mMacroRowPnlList.remove(inMacroRow);
    }
    
    remove(inMacroRow.getVerBottomGap());
    remove(inMacroRow);
    
    revalidate();
    repaint();
    
    mParentContainer.getParentContainer().getPropertyBotomPnl().updateFileNameRule(
        getTopSepTf(),getMacroRowsPnl());
  }
    
}
