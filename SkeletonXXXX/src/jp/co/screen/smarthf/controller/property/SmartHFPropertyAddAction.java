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

package jp.co.screen.smarthf.controller.property;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;

import jp.co.screen.smarthf.common.Constants;
import jp.co.screen.smarthf.model.property.PropertyMacroRowPnl;
import jp.co.screen.smarthf.utils.CommonUtils;

/**
 * Class Description
 * 
 * @author kdang1
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFPropertyAddAction extends AbstractAction {

  private PropertyMacroRowPnl mParentContainer;

  /**
   * {@inheritDoc}
   */

  /**
   * Constructor of SmartHFPropertyAddAction.java
   *
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  public SmartHFPropertyAddAction(PropertyMacroRowPnl inPropertyMarcoRowPnl) {
    // TODO Auto-generated constructor stub
    this.mParentContainer = inPropertyMarcoRowPnl;
  }

  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    List<String> macroList = CommonUtils.getRuleEngineMacroList();
    this.mParentContainer.getParentContainer().addMacroRow(new PropertyMacroRowPnl(
        this.mParentContainer.getParentContainer(), macroList, Constants.EMPTY_STRING, Constants.END));
  }

}
