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

package jp.co.screen.smarthf.properrty;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JPanel;

/**
 * Class Description
 * 
 * @author kdang1
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFPropertyPnl extends JPanel {
  /**
   * Constructor of SmartHFPropetyPnl.java
   *
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */

  private SmartHFPropertyFrm mParentContainer = null;

  private SmartHFPropetyBotomPnl mPropertyBotomPnl;

  private SmartHFPropertyTopPnl mHfPropertyTopPnl;

  private SmartHFPropertyCenterPnl mHfPropertyCenterPnl;

  public SmartHFPropertyPnl(SmartHFPropertyFrm smartHFPropertyFrm) {
    // TODO Auto-generated constructor stub
    super();
    this.mParentContainer = smartHFPropertyFrm;
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
    setLayout(new BorderLayout());
    Box boxTop = Box.createVerticalBox();
    boxTop.add(Box.createVerticalStrut(10));
    boxTop.add(getmHfPropertyTopPnl());
    add(boxTop, BorderLayout.NORTH);

    Box boxCenter = Box.createVerticalBox();
    boxCenter.add(Box.createVerticalStrut(10));
    boxCenter.add(getPropertyCenterPnl());
    add(boxCenter, BorderLayout.CENTER);

    Box boxBotom = Box.createVerticalBox();
    boxBotom.add(Box.createVerticalStrut(10));
    boxBotom.add(getPropertyBotomPnl());
    add(boxBotom, BorderLayout.SOUTH);

  }

  public SmartHFPropertyFrm getParentContainer() {
    return mParentContainer;
  }

  public SmartHFPropertyTopPnl getmHfPropertyTopPnl() {
    if (this.mHfPropertyTopPnl == null) {
      this.mHfPropertyTopPnl = new SmartHFPropertyTopPnl(this);
    }
    return this.mHfPropertyTopPnl;
  }

  public SmartHFPropetyBotomPnl getPropertyBotomPnl() {
    if (this.mPropertyBotomPnl == null) {
      this.mPropertyBotomPnl = new SmartHFPropetyBotomPnl(this);
    }

    return this.mPropertyBotomPnl;
  }

  public SmartHFPropertyCenterPnl getPropertyCenterPnl() {
    if (this.mHfPropertyCenterPnl == null) {
      this.mHfPropertyCenterPnl = new SmartHFPropertyCenterPnl(this);
    }

    return this.mHfPropertyCenterPnl;
  }
}
