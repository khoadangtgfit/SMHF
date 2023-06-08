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

import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jp.co.screen.smarthf.common.resource.LangKey;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.model.property.PropertyMacroRowsPnl;
import jp.co.screen.smarthf.utils.CommonUtils;

/**
 * Class Description
 * 
 * @author kdang1
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFPropertyCenterPnl extends JPanel {
  private SmartHFPropertyPnl mParentContainer = null;

  private JTextField mTopTf;

  private PropertyMacroRowsPnl mMacroRowsPnl;

  /**
   * Constructor of SmartHFPropertyCenter.java
   *
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  public SmartHFPropertyCenterPnl(SmartHFPropertyPnl inParentContainer) {
    // TODO Auto-generated constructor stub
    this.mParentContainer = inParentContainer;
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
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    SmartHFDataModel dataModel =
        mParentContainer.getParentContainer().getParentContainer().getSmartHFMainContentPane().getSelectedRow();
    List<String> sepList = null;
    if (!mParentContainer.getParentContainer().isAddHotFolder() && dataModel != null) {
      sepList = CommonUtils.getSeparatorList(dataModel.getSmartHFRulePropertyFileModel().getRule());
    }

    // Initialize for Top separator
    mTopTf = new JTextField();
    mTopTf.setPreferredSize(new Dimension(100, 25));
    mTopTf.setMaximumSize(new Dimension(100, 25));

    if (sepList != null && sepList.size() > 0) {
      mTopTf.setText(sepList.get(0));
    } else
      mTopTf.setText("oggi-");

    mTopTf.getDocument().addDocumentListener(new DocumentListener() {
      public void removeUpdate(DocumentEvent paramDocumentEvent) {
        mParentContainer.getPropertyBotomPnl().updateFileNameRule(mTopTf, mMacroRowsPnl);
      }

      public void insertUpdate(DocumentEvent paramDocumentEvent) {
        mParentContainer.getPropertyBotomPnl().updateFileNameRule(mTopTf, mMacroRowsPnl);
      }

      public void changedUpdate(DocumentEvent paramDocumentEvent) {
        mParentContainer.getPropertyBotomPnl().updateFileNameRule(mTopTf, mMacroRowsPnl);
      }
    });

    Box box = Box.createHorizontalBox();
    box.add(Box.createHorizontalStrut(10));
    box.add(mTopTf);
    box.add(Box.createHorizontalGlue());

    JScrollPane scrollPane = new JScrollPane(getMacroRowsPnl());
    if (mMacroRowsPnl.getMacroRowPnlList().size() > 0) {
      Dimension scrollSize = new Dimension();
      scrollSize.width = mMacroRowsPnl.getMacroRowPnlList().get(0).getPreferredSize().width;
      scrollSize.height = mMacroRowsPnl.getMacroRowPnlList().get(0).getPreferredSize().height * 3 + 90;
      scrollPane.getViewport().setPreferredSize(scrollSize);
      scrollPane.getViewport().setMaximumSize(scrollSize);
      scrollPane.setBorder(BorderFactory.createEmptyBorder());
    }
    Box box1 = Box.createHorizontalBox();
    box1.add(scrollPane);
    box1.add(Box.createHorizontalGlue());
    Box box2 = Box.createVerticalBox();
    box2.add(Box.createVerticalStrut(10));
    box2.add(box);
    box2.add(Box.createVerticalStrut(10));
    box2.add(box1);
    box2.add(Box.createVerticalStrut(10));
    box2.add(Box.createVerticalGlue());

    add(box2);

    setBorder(BorderFactory.createTitledBorder(SmartHFResource.getResource(LangKey.WORD_FILENAMERULESETTINGTITLE)));

    mParentContainer.getPropertyBotomPnl().updateFileNameRule(mTopTf, mMacroRowsPnl);

  }

  public SmartHFPropertyPnl getParentContainer() {
    return mParentContainer;
  }

  public JTextField getmTopTf() {
    return mTopTf;
  }

  public PropertyMacroRowsPnl getMacroRowsPnl() {
    if (mMacroRowsPnl == null) {
      mMacroRowsPnl = new PropertyMacroRowsPnl(this);
    }

    return mMacroRowsPnl;
  }

}
