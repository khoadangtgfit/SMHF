package jp.co.screen.smarthf.properrty;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jp.co.screen.equios.ui.common.bean.button.JButtonExAction;
import jp.co.screen.equios.ui.common.util.ComponentUtilities;
import jp.co.screen.equios.ui.resource.lang.common.LangKeyCommon;
import jp.co.screen.smarthf.common.Constants;
import jp.co.screen.smarthf.common.resource.LangKey;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.controller.property.SmartHFPropertyCancelAction;
import jp.co.screen.smarthf.controller.property.SmartHFPropertyOkAction;
import jp.co.screen.smarthf.model.property.PropertyMacroRowsPnl;

/**
 * Class Description
 * 
 * @author kdang1
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFPropetyBotomPnl extends JPanel {
  /**
   * Constructor of SmartHFPropetyBotomPnl.java
   *
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  private SmartHFPropertyPnl mParentContainer;

  private JLabel mFileNameRuleLb;
  private String mFileNameRuleTitle;
  private String mFileNameRule;
  private JButtonExAction mOkBtn;
  private JButtonExAction mCancelBtn;
  protected ActionMap mActionMap;
  
  public SmartHFPropetyBotomPnl(SmartHFPropertyPnl inParentContainer) {
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
    setLayout(new BorderLayout());
    add(Box.createHorizontalGlue(), BorderLayout.WEST);
    add(Box.createHorizontalGlue(), BorderLayout.EAST);
    Box boxNORTH = Box.createVerticalBox();
    boxNORTH.add(Box.createVerticalStrut(10));
    boxNORTH.add(PropertyFileNameRuleLbPnl());
    boxNORTH.add(Box.createHorizontalStrut(10));
    boxNORTH.add(mFileNameRuleLb);
    this.add(boxNORTH, BorderLayout.NORTH);

    setBorder(ComponentUtilities.getButtonPanelBorder());
    Box boxCENTER = Box.createHorizontalBox();
    boxCENTER.add(Box.createHorizontalGlue());
    boxCENTER.add(getOkButton());
    boxCENTER.add(Box.createHorizontalStrut(20));
    boxCENTER.add(getCancelButton());

    PropertyGroupBtnPnl();
    this.add(boxCENTER, BorderLayout.CENTER);

    this.add(Box.createVerticalStrut(15), BorderLayout.SOUTH);

  }

  public void updateFileNameRule(String inFileNameRule) {
    mFileNameRule = inFileNameRule;
    String fileNameuRule = mFileNameRuleTitle + Constants.SPACE_STRING + inFileNameRule;
    mFileNameRuleLb.setToolTipText(inFileNameRule);
    this.mFileNameRuleLb.setText(fileNameuRule);
  }

  public void updateFileNameRule(JTextField mTopSepTf, PropertyMacroRowsPnl mMacroRowsPnl) {
    // TODO Auto-generated method stub
    String txt= mTopSepTf.getText();
    String rows = mMacroRowsPnl.getRowsString();
    String fileNameRule = mTopSepTf.getText() + mMacroRowsPnl.getRowsString();
    updateFileNameRule(fileNameRule);
  }

  private JLabel PropertyFileNameRuleLbPnl() {
    mFileNameRuleTitle = SmartHFResource.getResource(LangKey.WORD_FILENAMERULETITLE);
    mFileNameRuleLb = new JLabel(mFileNameRuleTitle);
    mFileNameRuleLb.setMaximumSize(new Dimension(400, 25));
    mFileNameRuleLb.setPreferredSize(new Dimension(400, 25));
    return mFileNameRuleLb;
  }
  
  

  public JComponent getOkButton() {
    if (this.mOkBtn == null) {
      this.mOkBtn = new JButtonExAction(SmartHFResource.getResource(LangKeyCommon.WORD_OK));
    }
    return this.mOkBtn;
  }

  public JComponent getCancelButton() {
    if (this.mCancelBtn == null) {
      this.mCancelBtn = new JButtonExAction(SmartHFResource.getResource(LangKeyCommon.WORD_CANCEL));
    }
    return this.mCancelBtn;
  }
  
  public void PropertyGroupBtnPnl() {
    installListener();
  }
  
  public String getRule() {
    return this.mFileNameRule;
  }
  
  public void installListener() {
    mOkBtn.setAction(new SmartHFPropertyOkAction(this));
    mCancelBtn.setAction(new SmartHFPropertyCancelAction(this));
  }

  public SmartHFPropertyPnl getParentContainer() {
    return mParentContainer;
  }
  
  
}
