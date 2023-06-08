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

package jp.co.screen.smarthf.view.copydgl;

import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import jp.co.screen.equios.ui.resource.lang.common.LangKeyCommon;
import jp.co.screen.smarthf.common.resource.LangKey;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.view.main.SmartHFManagerPnl;

public class SmartHFCopyDlg extends JDialog {

  private SmartHFManagerPnl mParentContainer;

  /**
   * Constructor of SmartHFCopyDlg.java
   *
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  public SmartHFCopyDlg(SmartHFManagerPnl inParentContainer) {
    super(inParentContainer.getParentContainer(), Dialog.ModalityType.APPLICATION_MODAL);
    mParentContainer = inParentContainer;
    init();
  }

  public SmartHFManagerPnl getParentContainer() {
    return mParentContainer;
  }

  private void init() {

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        dispose();
      }
    });

    SmartHFCopyContentPnl contentPnl = new SmartHFCopyContentPnl(this);
    add(contentPnl);
    setLocationRelativeTo(null);
    pack();
    setVisible(true);
  }
  
  public void confirmExit() {
    int confirmed =
        JOptionPane.showConfirmDialog(this, SmartHFResource.getResource(LangKey.MSG_INF_EXITDLGSMARTHFPROPERTY),
            SmartHFResource.getResource(LangKeyCommon.WORD_CONFIRMATION), JOptionPane.YES_NO_OPTION);
    if (confirmed == JOptionPane.YES_OPTION) {
      dispose();
    }
  }

}
