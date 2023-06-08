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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import jp.co.screen.equios.ui.resource.lang.common.LangKeyCommon;
import jp.co.screen.smarthf.common.ConfigManager;
import jp.co.screen.smarthf.common.resource.LangKey;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.view.main.SmartHFManagerPnl;

/**
 * Class Description
 * 
 * @author kdang1
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFPropertyFrm extends JFrame {

  /**
   * Constructor of SmartHFPropertyFrm.java
   *
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  private static Logger LOGGER = Logger.getLogger(SmartHFPropertyFrm.class);

  private SmartHFManagerPnl mParentContainer = null;

  private SmartHFPropertyPnl mPropertyPnl = null;

  private boolean mIsAdd;

  public SmartHFPropertyFrm(SmartHFManagerPnl mParentContainer, boolean isadd) {
    // TODO Auto-generated constructor stub
    super();
    this.mParentContainer = mParentContainer;
    this.mIsAdd = isadd;
    mPropertyPnl = new SmartHFPropertyPnl(this);
    this.setLocationRelativeTo(null);
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
    LOGGER.info("Start.");
    add(getmPropertyPnl());
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        confirmExit();
      }
    });

    if (getSize() == null || (getSize().width <= 0 && getSize().height <= 0)) {
      setPreferredSize(ConfigManager.getWindowSize());

      pack();
    }

    setTitle(SmartHFResource.getResource(LangKey.WORD_SMARTHF_PROPERTY_TITLE));
    setVisible(true);
    LOGGER.info("end");
  }

  public void confirmExit() {
    int confirmed =
        JOptionPane.showConfirmDialog(this, SmartHFResource.getResource(LangKey.MSG_INF_EXITDLGSMARTHFPROPERTY),
            SmartHFResource.getResource(LangKeyCommon.WORD_CONFIRMATION), JOptionPane.YES_NO_OPTION);
    if (confirmed == JOptionPane.YES_OPTION) {
      dispose();
    }
  }

  public boolean isAddHotFolder() {
    // TODO Auto-generated method stub
    return mIsAdd;
  }

  public SmartHFPropertyPnl getmPropertyPnl() {
    return mPropertyPnl;
  }

  public void setmPropertyPnl(SmartHFPropertyPnl mPropertyPnl) {
    this.mPropertyPnl = mPropertyPnl;
  }

  public SmartHFManagerPnl getParentContainer() {
    // TODO Auto-generated method stub
    return mParentContainer;
  }

}
