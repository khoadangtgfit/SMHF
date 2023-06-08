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

package jp.co.screen.smarthf.controller.copy;

import java.awt.event.ActionEvent;
import java.nio.file.Paths;

import javax.swing.AbstractAction;

import jp.co.screen.smarthf.common.resource.LangKey;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.utils.CommonUtils;
import jp.co.screen.smarthf.utils.FileUtils;
import jp.co.screen.smarthf.utils.JOptionPaneUtils;
import jp.co.screen.smarthf.view.copydgl.SmartHFCopyGroupBtnPnl;
import jp.co.screen.swc.util.NameUtilities;

/**
 * Class Description
 * 
 * @author kdang1
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFCopyOkAction extends AbstractAction {

  private SmartHFCopyGroupBtnPnl mParentContainer;

  private SmartHFDataModel mDataModel;

  /**
   * Constructor of SmartHFCopyOkAction.java
   *
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  public SmartHFCopyOkAction(SmartHFCopyGroupBtnPnl inParentContainer, SmartHFDataModel inDataModel) {
    // TODO Auto-generated constructor stub
    this.mDataModel = inDataModel;
    this.mParentContainer = inParentContainer;
  }

  /**
   * {@inheritDoc}
   */
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    String hfName = mDataModel.getSmartHFPropertyFileModel().getDisplayName();

    String newName = NameUtilities.createCopyName(hfName, CommonUtils.getHotfolderNameList());

    SmartHFDataModel newDataModel = mDataModel.clone();
    newDataModel.getSmartHFPropertyFileModel().setDisplayName(newName);
    CommonUtils.copyHotfolder(hfName, newName);

    if (CommonUtils.writePropertyFile(Paths.get(FileUtils.getSmartHotfolderRootPath(), newName).toString(),
        newDataModel.getSmartHFPropertyFileModel())) {
      mParentContainer.getParentContainer().getParentContainer().getParentContainer().getSmartHFMainContentPane()
          .getTblModel().addSmartHFData(newDataModel);
      mParentContainer.getParentContainer().getParentContainer().getParentContainer().updateStateButton(0);
      mParentContainer.getParentContainer().getParentContainer().dispose();
    } else {
      JOptionPaneUtils.showErrorDialog(mParentContainer.getParentContainer().getParentContainer(),
          SmartHFResource.getResource(LangKey.MSG_ERR_FAILTOUPDATEHF));
    }
  }

}
