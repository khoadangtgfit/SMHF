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

package jp.co.screen.smarthf.model.property;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jp.co.screen.equios.ui.common.bean.button.JButtonExAction;
import jp.co.screen.smarthf.common.Constants;
import jp.co.screen.smarthf.common.resource.IconKey;
import jp.co.screen.smarthf.controller.property.SmartHFPropertyAddAction;
import jp.co.screen.smarthf.controller.property.SmartHFPropertyDeleteAction;

/**
 * Class Description
 * 
 * @author kdang1
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class PropertyMacroRowPnl extends JPanel {
  enum eBtnAction {
    mAddAction, mDeleteAction
  }

  private PropertyMacroRowsPnl mParentContainer;

  private List<String> mMacroList;
  private String mMacroValSelected;
  private String mSeparator;
  private JComboBox<String> mMacroCbx;
  private JTextField mSepTf;
  private JButtonExAction mMacroAddBtn;
  private JButtonExAction mMacroDelBtn;
  private eBtnAction mCurAction;
  private Box mVerBottomGap;

  /**
   * Constructor of SmartHFPropertyMacroRowPnl.java
   *
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  public PropertyMacroRowPnl(PropertyMacroRowsPnl inParentContainer, List<String> inMacroList, String inMacroVal,
      String inSepVal) {
    mParentContainer = inParentContainer;
    mMacroList = inMacroList;
    mMacroValSelected = inMacroVal;
    mSeparator = inSepVal;

    init();
  }

  private void init() {
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setAlignmentX(LEFT_ALIGNMENT);

    // Create Macro ComboBox
    String[] macroArr = new String[mMacroList.size()];
    for (int i = 0; i < mMacroList.size(); i++) {
      macroArr[i] = mMacroList.get(i);
    }

    mMacroCbx = new JComboBox<String>(macroArr);
    mMacroCbx.setPreferredSize(new Dimension(150, 25));
    mMacroCbx.setMaximumSize(new Dimension(150, 25));

    if (!mMacroValSelected.isEmpty()) {
      mMacroCbx.setSelectedItem(mMacroValSelected);
    } else {
      mMacroCbx.setSelectedItem(mMacroCbx.getItemAt(mMacroList.size() - 1));
    }

    // Create Separator TextField
    mSepTf = new JTextField();
    mSepTf.setPreferredSize(new Dimension(150, 25));
    mSepTf.setMaximumSize(new Dimension(150, 25));
    mSepTf.setText(mSeparator);

    // Create Macro Add button
    mMacroAddBtn = new JButtonExAction();
    mMacroAddBtn.setBorderPainted(false);
    mMacroAddBtn.setContentAreaFilled(false);
    mMacroAddBtn.setIcon(new ImageIcon(IconKey.ADDMACRO_ICON));

    // Create Macro Delete button
    mMacroDelBtn = new JButtonExAction();
    mMacroDelBtn.setBorderPainted(false);
    mMacroDelBtn.setContentAreaFilled(false);
    mMacroDelBtn.setIcon(new ImageIcon(IconKey.DELETEMACRO_ICON));

    add(Box.createHorizontalStrut(20));
    add(mMacroCbx);
    add(Box.createHorizontalStrut(10));
    add(mSepTf);
    add(Box.createHorizontalStrut(10));
    add(mMacroDelBtn);

    mCurAction = eBtnAction.mDeleteAction;

    mVerBottomGap = Box.createVerticalBox();
    mVerBottomGap.add(Box.createVerticalStrut(20));

    installListener();
  }

  private void installListener() {
    // Macro ComboBox
    mMacroCbx.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        mParentContainer.getParentContainer().getParentContainer().getPropertyBotomPnl()
            .updateFileNameRule(getTopSepTf(), getMacroRowsPnl());
      }
    });

    mMacroAddBtn.setAction(new SmartHFPropertyAddAction(this));
    mMacroDelBtn.setAction(new SmartHFPropertyDeleteAction(this));

    mSepTf.getDocument().addDocumentListener(new DocumentListener() {
      public void removeUpdate(DocumentEvent paramDocumentEvent) {
        mParentContainer.getParentContainer().getParentContainer().getPropertyBotomPnl()
            .updateFileNameRule(getTopSepTf(), mParentContainer);
      }

      public void insertUpdate(DocumentEvent paramDocumentEvent) {
        mParentContainer.getParentContainer().getParentContainer().getPropertyBotomPnl()
            .updateFileNameRule(getTopSepTf(), mParentContainer);
      }

      public void changedUpdate(DocumentEvent paramDocumentEvent) {
        mParentContainer.getParentContainer().getParentContainer().getPropertyBotomPnl()
            .updateFileNameRule(getTopSepTf(), mParentContainer);
      }
    });
  }

  private JTextField getTopSepTf() {
    return mParentContainer.getParentContainer().getParentContainer().getPropertyCenterPnl().getmTopTf();
  }

  private PropertyMacroRowsPnl getMacroRowsPnl() {
    // TODO Auto-generated method stub
    return mParentContainer.getParentContainer().getParentContainer().getPropertyCenterPnl().getMacroRowsPnl();
  }

  public void changeMacroBtn(boolean inIsDelBtn) {
    if (inIsDelBtn) {
      if (mCurAction == eBtnAction.mAddAction) {
        remove(mMacroAddBtn);
        add(mMacroDelBtn);
        if (this.mSeparator == "end") {
          mSepTf.setText(Constants.EMPTY_STRING);
        }
      }
      revalidate();
      repaint();
      mCurAction = eBtnAction.mDeleteAction;
    } else {
      if (mCurAction == eBtnAction.mDeleteAction) {
        remove(mMacroDelBtn);
        add(mMacroAddBtn);
      }
      revalidate();
      repaint();
      mCurAction = eBtnAction.mAddAction;
    }
  }

  public String getRowString() {
    return mMacroCbx.getSelectedItem().toString() + mSepTf.getText();
  }

  public PropertyMacroRowsPnl getParentContainer() {
    return mParentContainer;
  }

  public Box getVerBottomGap() {
    return mVerBottomGap;
  }

  public String getMacro() {
    return mMacroCbx.getSelectedItem().toString();
  }

  public String getSeparator() {
    return mSepTf.getText();
  }

}
