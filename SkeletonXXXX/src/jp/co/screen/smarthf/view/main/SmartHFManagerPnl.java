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

package jp.co.screen.smarthf.view.main;

import java.awt.BorderLayout;

import javax.swing.ActionMap;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import jp.co.screen.smarthf.controller.SmartHFActionKey;
import jp.co.screen.smarthf.controller.SmartHFAddAction;
import jp.co.screen.smarthf.controller.SmartHFCloseAction;
import jp.co.screen.smarthf.controller.SmartHFCopyAction;
import jp.co.screen.smarthf.controller.SmartHFDeleteAction;
import jp.co.screen.smarthf.controller.SmartHFEditAction;

/**
 * main panel of frame
 * 
 * @author syptn
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFManagerPnl extends JPanel {

  /**
   * logger
   */
  private static Logger LOGGER = Logger.getLogger(SmartHFManagerPnl.class);

  /**
   * read only mode
   */
  protected final boolean isReadOnly;

  /**
   * action map
   */
  protected ActionMap mActionMap;

  /**
   * panel top
   */
  private JPanel mPnlTop;

  /**
   * group panel
   */
  private SmartHFGroupBtnPnl mButtonPanel;

  /**
   * main content pane
   */
  private SmartHFMainContentPane mainContentPane;
  
  private SmartHFManagerFrm mParentContainer;

  /**
   * Constructor of LBRSManagerPnl.java
   *
   * @param inIsReadMode
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  public SmartHFManagerPnl(boolean inIsReadMode) {
    super();
    this.isReadOnly = inIsReadMode;
    init();
  }

  /**
   * init component
   * 
   * @author syptn
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  private void init() {
    this.setLayout(new BorderLayout());

    this.add(getTopPnl(), BorderLayout.NORTH);
    this.add(getButtonPanel(), BorderLayout.EAST);

    this.add(getMainContentPane(), BorderLayout.CENTER);
    installListener();
    updateStateButton(0);

  }

  /**
   * main content pane
   * 
   * @return
   * @author syptn
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  private SmartHFMainContentPane getMainContentPane() {
    if (this.mainContentPane == null) {
      this.mainContentPane = new SmartHFMainContentPane(this);
    }

    return this.mainContentPane;
  }

  /**
   * create group button
   * 
   * @return
   * @author syptn
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  private SmartHFGroupBtnPnl getButtonPanel() {
    if (mButtonPanel == null) {
      mButtonPanel = new SmartHFGroupBtnPnl();
    }
    return mButtonPanel;
  }

  /**
   * top panel for add label
   * 
   * @return
   * @author syptn
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  private JPanel getTopPnl() {
    if (this.mPnlTop == null) {
      this.mPnlTop = new JPanel();
    }
    return this.mPnlTop;
  }

  public SmartHFManagerFrm getParentContainer() {
    return mParentContainer;
  }

  /**
   * install listener for component
   * 
   * @author syptn
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  public void updateStateButton(int selectedRows) {
    getButtonPanel().getAddButton().setEnabled(true);
    getButtonPanel().getDeleteButton().setEnabled(false);
    getButtonPanel().getCopyButton().setEnabled(false);
    getButtonPanel().getPropertyButton().setEnabled(false);
    if(selectedRows!=0) {
      if(selectedRows==1) {
        getButtonPanel().getDeleteButton().setEnabled(true);
        getButtonPanel().getCopyButton().setEnabled(true);
        getButtonPanel().getPropertyButton().setEnabled(true);
      }else {
        getButtonPanel().getDeleteButton().setEnabled(true);
      }
    }
  }
  
  private void installListener(){
      LOGGER.info("Start installListener ");
          getButtonPanel().getAddButton().setAction( getListenerActionMap().get( SmartHFActionKey.KEY_ADD_ACTION  ) );
          getButtonPanel().getPropertyButton().setAction( getListenerActionMap().get( SmartHFActionKey.KEY_EDIT_ACTION ) );
          getButtonPanel().getCopyButton().setAction( getListenerActionMap().get( SmartHFActionKey.KEY_COPY_ACTION) );
          getButtonPanel().getDeleteButton().setAction( getListenerActionMap().get( SmartHFActionKey.KEY_DELETE_ACTION ) );
          getButtonPanel().getBtnClose().setAction( getListenerActionMap().get( SmartHFActionKey.KEY_CLOSE_ACTION ) );
          LOGGER.info("End installListener");
  }
  
  /**
   * get listener action map
   *  
   * @return
   * @author syptn
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
   private ActionMap getListenerActionMap() {
        if (mActionMap == null) {
            LOGGER.info("Set action map");
            mActionMap = new ActionMap();
            mActionMap.put( SmartHFActionKey.KEY_CLOSE_ACTION , new SmartHFCloseAction( this ));
            mActionMap.put( SmartHFActionKey.KEY_ADD_ACTION , new SmartHFAddAction( this ));
            mActionMap.put( SmartHFActionKey.KEY_EDIT_ACTION , new SmartHFEditAction( this));
            mActionMap.put( SmartHFActionKey.KEY_DELETE_ACTION, new SmartHFDeleteAction( this));
            mActionMap.put( SmartHFActionKey.KEY_COPY_ACTION , new SmartHFCopyAction( this ));
            LOGGER.info("End set action map.");
        }
        return mActionMap;
    }
  
   public SmartHFMainContentPane getSmartHFMainContentPane() {
     if(this.mainContentPane==null){
       this.mainContentPane= new SmartHFMainContentPane(this);
     }
     return this.mainContentPane;
   }
}
