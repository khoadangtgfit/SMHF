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

package jp.co.screen.smarthf.properrty;

import org.apache.log4j.Logger;

import jp.co.screen.equios.ui.common.util.NameUtilities;
import jp.co.screen.smarthf.common.resource.LangKey;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.utils.CommonUtils;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class Description
 * 
 * @author kdang1
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFPropertyTopPnl extends JPanel {
  private static Logger LOGGER = Logger.getLogger(SmartHFPropertyFrm.class);

  private SmartHFPropertyPnl mParentContainer = null;

  JTextField mHFNameTf = null;

  /**
   * Constructor of SmartHFPropetyTopPnl.java
   *
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  public SmartHFPropertyTopPnl(SmartHFPropertyPnl mParentContainer) {
    // TODO Auto-generated constructor stub
    this.mParentContainer=mParentContainer;
    init();
  }

  /**
   * Method description
   * 
   * @author kdang1
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */

  private void init() {
    // TODO Auto-generated method stub
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    JLabel hfNameLb = new JLabel(SmartHFResource.getResource(LangKey.WORD_HFNAME));
    mHFNameTf = new JTextField();
    if(mParentContainer.getParentContainer().isAddHotFolder()) {
      mHFNameTf.setText(NameUtilities.createNewName(SmartHFResource.getResource(LangKey.WORD_DEFAULT_HFNAME), CommonUtils.getHotfolderNameList()));
    }else {
      SmartHFDataModel dataModel = mParentContainer.getParentContainer().getParentContainer().getSmartHFMainContentPane().getSelectedRow();
      mHFNameTf.setText(dataModel.getSmartHFPropertyFileModel().getDisplayName());
    }
    
    mHFNameTf.setPreferredSize(new Dimension(150, 25));
    mHFNameTf.setMaximumSize(new Dimension(150, 25));
    add(Box.createHorizontalStrut(20));
    add(hfNameLb);
    add(Box.createHorizontalStrut(20));
    add(mHFNameTf);
    add(Box.createHorizontalGlue());
  }
  
  public String getHFname() {
    return mHFNameTf.getText();
  }
}
